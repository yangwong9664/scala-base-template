package services

import scala.concurrent.Future

class TestService {

  def test(): Future[Boolean] = Future.successful(true)

}
