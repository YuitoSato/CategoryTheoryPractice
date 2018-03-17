package option

import applicative.ApplicativeY
import functor.FunctorY
import monad.MonadY

sealed trait OptionY[+A] { self =>

  def map[B](f: A => B)(implicit functorY: FunctorY[OptionY]): OptionY[B] = functorY.map(self, f)

  def flatMap[B](f: A => OptionY[B])(implicit monadY: MonadY[OptionY]): OptionY[B] = monadY.flatMap(self, f)

}

final case class SomeY[A](a: A) extends OptionY[A]

case object NoneY extends OptionY[Nothing]

object OptionY {

  implicit val optionYFunctorYInstance: FunctorY[OptionY] = new FunctorY[OptionY] {
    override def map[A, B](fa: OptionY[A], f: A => B): OptionY[B] = fa match {
      case SomeY(a) => SomeY(f(a))
      case NoneY => NoneY
    }
  }

  implicit val optionYApplicativeYInstance: ApplicativeY[OptionY] = new ApplicativeY[OptionY] {
    override def product[A, B](fa: OptionY[A], fb: OptionY[B]): OptionY[(A, B)] = (fa, fb) match {
      case (SomeY(a), SomeY(b)) => SomeY((a, b))
      case _ => NoneY
    }

    override def pure[A](a: A): OptionY[A] = SomeY(a)

    override def ap[A, B](ff: OptionY[A => B])(fa: OptionY[A]): OptionY[B] = (fa, ff) match {
      case (SomeY(a), SomeY(f)) => SomeY(f(a))
      case _ => NoneY
    }
  }

  implicit val optionYMonadYInstance: MonadY[OptionY] = new MonadY[OptionY] {
    override def flatten[A](ffa: OptionY[OptionY[A]]): OptionY[A] = ffa match {
      case SomeY(fa) => fa
      case NoneY => NoneY
    }

    override def flatMap[A, B](fa: OptionY[A], f: A => OptionY[B]): OptionY[B] = fa match {
      case SomeY(a) => f(a)
      case NoneY => NoneY
    }

    override def product[A, B](fa: OptionY[A], fb: OptionY[B]): OptionY[(A, B)] = optionYApplicativeYInstance.product(fa, fb)

    override def pure[A](a: A): OptionY[A] = optionYApplicativeYInstance.pure(a)

    override def ap[A, B](ff: OptionY[A => B])(fa: OptionY[A]): OptionY[B] = optionYApplicativeYInstance.ap(ff)(fa)
  }

}
