package config

import akka.http.scaladsl.model.HttpResponse
import com.google.inject.{Guice, Injector}
import com.typesafe.config.{Config, ConfigFactory}
import org.scalatest._
import org.scalatest.concurrent.{Eventually, IntegrationPatience, ScalaFutures}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

trait IntegrationSpecBase extends AnyWordSpec
  with GivenWhenThen with TestSuite with ScalaFutures with IntegrationPatience with Matchers
  with WiremockHelper with BeforeAndAfterEach with BeforeAndAfterAll with Eventually {

  val port: Int

  lazy val testInjector: Injector = Guice.createInjector(new GuiceModule {
    override def configure(): Unit = {
      bind(classOf[AppConfig]) to classOf[WiremockAppConfig]
    }
  })

  implicit lazy val app = new MainApp {
    override lazy val injector: Injector = testInjector
    override lazy val httpPort: Int = port
  }

  def statusOf(res: Future[HttpResponse])(implicit timeout: Duration): Int = Await.result(res, timeout).status.intValue()

  override def beforeEach(): Unit = {
    resetWiremock()
  }

  override def beforeAll(): Unit = {
    super.beforeAll()
    app.main(Array.empty)
    startWiremock()
  }

  override def afterAll(): Unit = {
    stopWiremock()
    super.afterAll()
  }

}

class WiremockAppConfig extends ApplicationConfig with WireMockConfig {
  override lazy val config: Config = ConfigFactory.parseString(
    s"""
       |app.name = "scala-api-template"
       |
       |akka {
       |  loggers = ["akka.event.slf4j.Slf4jLogger"]
       |  loglevel = "INFO"
       |  logging-filter = "akka.event.slf4j.Slf4jLoggingFilter"
       |  http {
       |      client {
       |        idle-timeout = 300 s
       |        connecting-timeout = 300 s
       |      }
       |      server {
       |        server-header = TEST
       |        idle-timeout = 300 s
       |        request-timeout= 300 s
       |        bind-timeout = 300 s
       |      }
       |      host-connection-pool {
       |        max-connections = 16
       |        max-open-requests = 128
       |        idle-timeout = 300 s
       |        client {
       |            idle-timeout = 300 s
       |        }
       |      }
       |  }
       |}
       |
       |http {
       |  host = "0.0.0.0"
       |  port = 8000
       |}
       |
       |""".stripMargin)

  override lazy val httpPort: Int = 2222

}
