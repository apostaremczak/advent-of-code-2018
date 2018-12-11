package com.postaremczak.advent_of_code.day_11


case class Grid(serialNumber: Int) {

  lazy val cells: Map[(Int, Int), Cell] = {
    Range(1, 301)
      .flatMap(
        x =>
          Range(1, 301)
            .map(
              y => ((x, y), Cell(x, y)(serialNumber))
            )
      )
      .toMap
  }

  def calculateRegionalPower(topLeftCoords: (Int, Int)): Int = {
    // Region size: 3x3
    val (minX, minY) = topLeftCoords

    Range(minX, minX + 3)
      .flatMap(
        x =>
          Range(minY, minY + 3)
            .map(
              y =>
                cells.get((x, y)) match {
                  case Some(cell) => cell.power
                  case None => 0
                }
            )
      )
      .sum
  }
}
