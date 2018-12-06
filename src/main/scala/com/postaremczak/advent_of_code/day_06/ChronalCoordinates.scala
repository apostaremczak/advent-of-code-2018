package com.postaremczak.advent_of_code.day_06

import com.postaremczak.advent_of_code.Solution

object ChronalCoordinates extends Solution(adventDay = 6) {

  case class GridPoint(
                        self: Point,
                        closest: Point
                      )

  val pointsOfInterest: Seq[Point] = puzzleInput.read.map(Point(_))
  // Find the smallest rectangle enclosing the points
  val grid = new Grid(pointsOfInterest)

  def findLargestArea: Int = {
    // Map the closest point of interest to each point on the grid
    val closestPoints = grid
      .generateEnclosure
      .flatMap {
        gridPoint: Point =>
          for {
            closest <- gridPoint.findClosest(pointsOfInterest)
          } yield GridPoint(gridPoint, closest)
      }

    // Drop the points which areas touch the grid edge - they lead to infinite results
    val onEdges = closestPoints
      .filter {
        gridPoint: GridPoint => gridPoint.self.isOnGridEdge(grid)
      }
      .map(_.closest)
      .toSet

    pointsOfInterest
      .filter(!onEdges.contains(_))
      .map { p: Point => closestPoints.count(_.closest == p) }
      .max
  }

  def main(args: Array[String]): Unit = {
    // Part one
    println(s"The largest finite area: $findLargestArea")
  }
}
