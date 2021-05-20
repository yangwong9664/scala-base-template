

import config.IntegrationSpecBase
import org.scalatest.time.{Millis, Seconds, Span}

import scala.util.Random

class TestRouteISpec extends IntegrationSpecBase {

  implicit val defaultPatience: PatienceConfig = PatienceConfig(timeout = Span(5, Seconds), interval = Span(100, Millis))
  override val port: Int = 2000 + Random.nextInt(999)

  "The route" should {
    "return a 200" in {
      val res = buildGetClient(s"http://localhost:$port/hello")
      whenReady(res) { result =>
        result.discardEntityBytes()
        result.status.intValue() shouldBe 200
      }
    }
  }

}
