import scala.util.Try

package object cont {

  type TryContY[A] = ContY[Try[A], A]

}
