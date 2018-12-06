package com.postaremczak.advent_of_code.day_06

import scala.util.matching.Regex

case class Point(
                  x: Int,
                  y: Int
                ) {

  def distance(another: Point): Int = {
    (x - another.x).abs + (y - another.y).abs
  }

  def findClosest(points: Seq[Point]): Option[Point] = {
    if (points.nonEmpty) {
      val distances = points
        .map {
          point => (point, distance(point))
        }
        .sortWith((p1: (Point, Int), p2: (Point, Int)) => p1._2 < p2._2)

      val closest = Some(distances.head._1)
      if (distances.length >= 2) {
        // Ensure that the minimum distances are not the same
        if (distances.head._2 == distances(1)._2) {
          None
        } else {
          closest
        }
      } else {
        closest
      }
    } else {
      None
    }
  }
}


object Point {
  private val coordPattern: Regex = """(\d+), (\d+)""".r

  def apply(coordinates: String): Point = {
    coordinates match {
      case coordPattern(x, y) => Point(x.toInt, y.toInt)
    }
  }
}
