package com.postaremczak.advent_of_code.day_06

class Grid(pointsInside: Seq[Point]) {
  private val xs: Seq[Int] = pointsInside.map(_.x)
  private val ys: Seq[Int] = pointsInside.map(_.y)

  val minX: Int = xs.min
  val maxX: Int = xs.max
  val minY: Int = ys.min
  val maxY: Int = ys.max

  def generateEnclosure: Seq[Point] = {
    Range(minX, maxX)
      .flatMap(
        x => Range(minY, maxY)
          .map(
            y =>
              Point(x, y)
          )
      )
  }
}
