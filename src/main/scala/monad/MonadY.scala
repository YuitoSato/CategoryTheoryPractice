package monad

import applicative.ApplicativeY

trait MonadY[F[_]] extends ApplicativeY[F] {

  def flatMap[A, B](fa: F[A])(f: A => F[B]): F[B]

  def flatten[A](ffa: F[F[A]]): F[A] = flatMap(ffa)(fa => fa)

  override def ap[A, B](ff: F[A => B])(fa: F[A]): F[B] = flatMap(ff)(f => map(fa)(f))

  override def map[A, B](fa: F[A])(f: A => B): F[B] = flatMap(fa)(a => pure(f(a)))

}
