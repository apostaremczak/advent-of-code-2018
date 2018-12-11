package com.postaremczak.advent_of_code.day_11

import org.scalatest.FunSuite

class ChronalChargeTest extends FunSuite {
 test("ChronalChargeTest.findMostPowerfulRegionTopCell") {
   val grid1 = Grid(18)
   assert(ChronalCharge.findMostPowerfulRegionTopCell(grid1) == (33, 45))

   val grid2 = Grid(42)
   assert(ChronalCharge.findMostPowerfulRegionTopCell(grid2) == (21, 61))
 }
}
