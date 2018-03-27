package cont

import org.scalacheck.Prop.forAll
import org.scalacheck.Properties


class ContYProperties[_] extends Properties("ContY") { self =>

  def plusOne(i: Int): FutureContY[Int] = FutureContY {
    iToFuture => iToFuture(i + 1)
  }

  property("for") = forAll { randInt: Int =>
    for {
      i <- plusOne(randInt)
      ii <- plusOne(i)
    } yield ii


  }

}
