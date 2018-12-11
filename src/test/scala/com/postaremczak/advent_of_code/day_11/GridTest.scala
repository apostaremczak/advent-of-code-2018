package com.postaremczak.advent_of_code.day_11

import org.scalatest.FunSuite

class GridTest extends FunSuite {

  test("Grid.calculatePower") {
    assert(Grid(8).calculatePower(Cell(3, 5)) == 4)
    assert(Grid(57).calculatePower(Cell(122, 79)) == -5)
    assert(Grid(39).calculatePower(Cell(217, 196)) == 0)
    assert(Grid(71).calculatePower(Cell(101, 153)) == 4)
  }
}
