import utils.Logging._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
object Main extends App {

  def duplicateCount(str: String): Int = {
    str.groupBy(_.toLower).filterNot(_._2.length <= 1).size
  }

  println(duplicateCount("abcdea"))


}
