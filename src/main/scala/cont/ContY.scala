package cont

case class ContY[R, A](run: (A => R) => R) {

  def map[B](aToB: A => B): ContY[R, B] = ContY(
    // 返り値のContY[R, B]は (B => R) => R をもつ
    bToR => run(a => bToR(aToB(a)))
  )

  def flatMap[B](aToContRB: A => ContY[R, B]): ContY[R, B] = ContY(
    bToR => run(a => aToContRB(a).run(bToR))
  )

}

