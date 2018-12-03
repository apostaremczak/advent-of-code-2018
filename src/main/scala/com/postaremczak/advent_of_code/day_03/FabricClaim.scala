package com.postaremczak.advent_of_code.day_03

import scala.util.matching.Regex

case class FabricClaim(
                        id: Int,
                        fromLeft: Int,
                        fromTop: Int,
                        width: Int,
                        height: Int
                      ) {

  lazy val squares: Seq[(Int, Int)] = getSquares

  def getSquares: Seq[(Int, Int)] = {
    def generateSquare(x: Int)(y: Int): (Int, Int) = {
      (x, y)
    }

    Range(fromTop, fromTop + height).flatMap {
      x: Int =>
        Range(fromLeft, fromLeft + width).map(generateSquare(x))
    }
  }
}

object FabricClaim {
  private val pattern: Regex = """#(\d{1,4}) @ (\d{1,3}),(\d{1,3}): (\d{1,3})x(\d{1,3})""".r

  def apply(claim: String): Option[FabricClaim] = {
    // Exemplary claim: #123 @ 3,2: 5x4
    claim match {
      case pattern(id, fromLeft, fromTop, width, height) =>
        Some(FabricClaim(id.toInt, fromLeft.toInt, fromTop.toInt, width.toInt, height.toInt))
      case _ => None
    }
  }
}
