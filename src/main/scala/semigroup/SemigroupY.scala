package semigroup

trait SemigroupY[A] {

  def combine(x: A, y: A): A

}
