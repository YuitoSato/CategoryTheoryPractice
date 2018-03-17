package monad

import applicative.ApplicativeY

trait MonadY[F[_]] extends ApplicativeY[F] {

  def flatMap[A, B](fa: F[A], f: A => F[B]): F[B]

  def flatten[A](fa: F[F[A]]): F[A]

}
