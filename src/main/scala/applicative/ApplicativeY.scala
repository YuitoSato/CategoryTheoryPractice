package applicative

import functor.FunctorY

trait ApplicativeY[F[_]] extends FunctorY[F] {

  def ap[A, B](ff: F[A => B])(fa: F[A]): F[B]

  def pure[A](a: A): F[A]

  def product[A, B](fa: F[A], fb: F[B]): F[(A, B)]

  override def map[A, B](fa: F[A])(f: A => B): F[B] = {
    ap(pure(f))(fa)
  }

}
