package monoid

import semigroup.SemigroupY

trait MonoidY[A] extends SemigroupY[A] {

  def empty: A

}
