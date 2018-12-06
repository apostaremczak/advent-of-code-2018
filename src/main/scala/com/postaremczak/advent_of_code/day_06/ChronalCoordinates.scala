package com.postaremczak.advent_of_code.day_06

import com.postaremczak.advent_of_code.Solution

object ChronalCoordinates extends Solution(adventDay = 6) {
  val pointsOfInterest: Seq[Point] = puzzleInput.read.map(Point(_))

  def findLargestArea: Int = {
    // Find the smallest rectangle enclosing the points
    val grid = new Grid(pointsOfInterest)
    val closestPoints = grid
      .generateEnclosure
      .flatMap {
        gridPoint: Point => gridPoint.findClosest(pointsOfInterest)
      }


    val filtered = closestPoints
      .toSet
      .filter {
        // Drop the points that touch the grid edge - they lead to infinite areas
        point: Point => !(point.x == grid.minX || point.x == grid.maxX || point.y == grid.minY || point.y == grid.maxY)
      }

    println(filtered)

    filtered
      .map { p: Point => closestPoints.count(_ == p) }
      .max
  }

  def main(args: Array[String]): Unit = {
    // Part one
    println(s"The largest finite area: $findLargestArea")
  }
}
