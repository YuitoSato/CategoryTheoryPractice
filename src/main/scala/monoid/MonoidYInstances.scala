package monoid

object MonoidYInstances {

  implicit val intAdditionMonoid: MonoidY[Int] = new MonoidY[Int] {
    override def identity: Int = 0
    override def combine(x: Int, y: Int): Int = x + y
  }

}
