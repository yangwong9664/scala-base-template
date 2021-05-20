package routes

import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.google.inject.Inject
import StatusCodes._
import services.TestService

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}

class TestRoute @Inject()(testService: TestService) {

  def routes(implicit ec: ExecutionContext): Route =
    path("hello") {
      get {
        onComplete(testService.test()) {
          case Success(_) => complete(OK, "test")
          case Failure(_) => complete(InternalServerError)
        }
      }
    }
}
