package semigroup

object SemigroupYInstances {

  implicit val intAdditionSemigroupY: SemigroupY[Int] = new SemigroupY[Int] {
    override def combine(x: Int, y: Int): Int = x + y
  }

}
