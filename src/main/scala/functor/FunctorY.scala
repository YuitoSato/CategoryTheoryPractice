package functor

trait FunctorY[F[_]] {

  def map[A, B](fa: F[A])(f: A => B): F[B]

}
