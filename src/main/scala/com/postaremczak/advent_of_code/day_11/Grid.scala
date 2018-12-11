package com.postaremczak.advent_of_code.day_11


case class Grid(serialNumber: Int) {
  lazy val cells: Seq[Cell] = {
    Range(1, 301)
      .flatMap(
        x => Range(1, 301)
          .map(
            y => Cell(x, y)
          )
      )
  }

  def calculatePower(fuelCell: Cell): Int = {
    def getHundreds(n: Int): Int = {
      (n / 100) % 10
    }

    val rackId = fuelCell.x + 10
    getHundreds((rackId * fuelCell.y + serialNumber) * rackId) - 5
  }

}
