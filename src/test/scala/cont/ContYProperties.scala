package cont

import org.scalacheck.Prop.forAll
import org.scalacheck.Properties

import scala.util.{ Success, Try }

class ContYProperties[_] extends Properties("ContY") {
  self =>

  def plusOne(i: Int): TryContY[Int] = TryContY {
    iToTry => {
      // 遅延評価される
      iToTry(i + 1)
    }
  }

  property("for") = forAll { randInt: Int =>
    val cont = for {
      i <- plusOne(randInt)
      ii <- plusOne(i)
    } yield ii

    val tryCont = cont.run(i => Try {
      i + 1
    })

    tryCont match {
      case Success(s) => s == randInt + 3
      case _ => false
    }
  }

}
