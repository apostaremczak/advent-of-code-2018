package com.postaremczak.advent_of_code.day_11

import com.postaremczak.advent_of_code.Solution

object ChronalCharge extends Solution(adventDay = 11) {
  val serialNumber: Int = puzzleInput.read.head.toInt

  def findMostPowerfulRegionTopCell(grid: Grid): (Int, Int) = {
    grid
      .cells
      .keys
      .map {
        coords: (Int, Int) =>
          (grid.calculateRegionalPower(coords), coords)
      }
      .toMap
      .max
      ._2
  }

  def main(args: Array[String]): Unit = {
    val grid = Grid(serialNumber)

    // Part one
    println(s"The most powerful region's top left cell: ${findMostPowerfulRegionTopCell(grid)}")
  }
}
