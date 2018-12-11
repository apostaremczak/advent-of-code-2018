package com.postaremczak.advent_of_code.day_11


case class Grid(serialNumber: Int) {
  lazy val cells: Seq[Cell] = {
    Range(1, 301)
      .flatMap(
        x => Range(1, 301)
          .map(
            y => Cell(x, y)(serialNumber)
          )
      )
  }

}
