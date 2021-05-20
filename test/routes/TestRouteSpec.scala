package routes

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalamock.scalatest.MockFactory
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import services.TestService

import scala.concurrent.Future

class TestRouteSpec extends AnyWordSpec with MockFactory with ScalatestRouteTest with Matchers {

  val mockTestService: TestService = mock[TestService]
  val testClass: Route = new TestRoute(mockTestService).routes

  "TestClass" should {

    "return test" in {

      (mockTestService.test _)
        .expects()
        .returning(Future.successful(true))


      Get("/hello") ~> testClass ~> check {
        responseAs[String] shouldEqual "test"
      }
    }
  }

}
