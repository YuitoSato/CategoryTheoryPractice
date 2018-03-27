package cont

import scala.concurrent.{ ExecutionContext, Future }

object FutureContY {

  def apply[A](f: (A => Future[A]) => Future[A])(implicit ec: ExecutionContext) = ContY(f)

}
