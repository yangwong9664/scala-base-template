import org.scalatest._
import wordspec._


class TestClassSpec extends AnyWordSpec {

  val testClass = new TestClass

  "TestClass" should {
    "return true" in {

      assert(1 == 1)

    }
  }

}
