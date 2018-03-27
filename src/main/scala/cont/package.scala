import scala.concurrent.Future

package object cont {

  type FutureContY[A] = ContY[Future[A], A]

}
