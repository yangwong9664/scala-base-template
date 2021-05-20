package config

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives.concat
import com.google.inject.Guice
import routes.TestRoute
import services.TestService
import utils.Logging

import java.net.InetAddress
import scala.concurrent.ExecutionContext

trait MainApp extends Logging {

  implicit val system: ActorSystem = ActorSystem("my-system")
  implicit val executionContext: ExecutionContext = system.dispatcher

  lazy val injector = Guice.createInjector()
  lazy val testService = injector.getInstance(classOf[TestService])
  lazy val appConfig: AppConfig = injector.getInstance(classOf[AppConfig])
  lazy val testRoute = injector.getInstance(classOf[TestRoute]).routes
  lazy val allRoutes = concat(testRoute)

  lazy val httpPort: Int = appConfig.httpPort

  def main(args: Array[String]): Unit = {
    Http().bindAndHandle(allRoutes, appConfig.httpHost, httpPort)
    logInfo(s"Application Running running at ${appConfig.httpHost}:$httpPort on ${InetAddress.getLocalHost}")
  }
}

object Main extends MainApp
