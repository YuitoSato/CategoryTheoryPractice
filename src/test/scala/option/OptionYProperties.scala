package option

import org.scalacheck.Properties
import org.scalacheck.Prop.forAll
import option.OptionY._

class OptionYProperties[_] extends Properties("OptionY") { self =>

  property("map") = forAll { randInt: Int =>
    SomeY(randInt).map(_ + 1) match {
      case SomeY(i) => i == randInt + 1
      case _ => false
    }
  }

  property("flatMap") = forAll { randInt: Int =>
    SomeY(randInt).flatMap(i => SomeY(i + 1)) match {
      case SomeY(i) => i == randInt + 1
      case _ => false
    }
  }

  property("for") = forAll { randInt: Int =>
    (for {
      i <- SomeY(randInt+ 1)
      ii <- SomeY(i + 1)
    } yield ii) match {
      case SomeY(i) => i == randInt + 2
      case NoneY => false
    }
  }

}
