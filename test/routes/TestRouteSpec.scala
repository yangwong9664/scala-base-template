package routes

import org.scalamock.scalatest.MockFactory
import org.scalatest.concurrent.ScalaFutures._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import services.TestService
class TestRouteSpec extends AnyWordSpec with MockFactory with Matchers {

  val service = new TestService()

  "TestClass" should {

    "return test" in {

      whenReady(service.test()) { res =>
        res shouldBe true
      }
    }
  }

}
