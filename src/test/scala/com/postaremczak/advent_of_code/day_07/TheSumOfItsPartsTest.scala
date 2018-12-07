package com.postaremczak.advent_of_code.day_07

import org.scalatest.FunSuite

class TheSumOfItsPartsTest extends FunSuite {
  test("correctFirst") {
    assert(TheSumOfItsParts.findStepOrder == 'C')
  }

}
