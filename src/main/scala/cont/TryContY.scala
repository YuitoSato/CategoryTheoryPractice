package cont

import scala.util.Try

object TryContY {

  def apply[A](f: (A => Try[A]) => Try[A]) = ContY(f)

}
