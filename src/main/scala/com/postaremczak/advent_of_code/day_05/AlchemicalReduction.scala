package com.postaremczak.advent_of_code.day_05

import com.postaremczak.advent_of_code.Solution

import scala.annotation.tailrec

object AlchemicalReduction extends Solution(adventDay = 5) {
  val polymer: String = puzzleInput.read.head

  def willUnitsReact(unit1: Char, unit2: Char): Boolean = {
    if (unit1.toLower == unit2.toLower) {
      val units = Seq(unit1, unit2)
      units.exists(_.isLower) && units.exists(_.isUpper)
    } else {
      false
    }
  }

  @tailrec
  def scanPolymer(toBeScanned: String, prev: Option[Char] = None, alreadyScanned: String = ""): String = {
    if (toBeScanned.isEmpty) {
      // Base case:
      // Nothing's left to be scanned, no more reactions will occur
      alreadyScanned + prev.getOrElse("")
    } else {
      // Look for any possible reactions
      val next = toBeScanned.head

      if (prev.isDefined) {
        // Remove those two characters and start from the beginning
        if (willUnitsReact(prev.get, next)) {
          if (alreadyScanned.isEmpty) {
            scanPolymer(
              toBeScanned.drop(1),
              None,
              alreadyScanned
            )
          } else {
            scanPolymer(
              toBeScanned.drop(1),
              Some(alreadyScanned.last),
              alreadyScanned.dropRight(1)
            )
          }
        } else {
          // Move to the next comparison and save previous unit
          scanPolymer(
            toBeScanned.drop(1),
            Some(next),
            alreadyScanned + prev.get
          )
        }
      } else {
        // Starting from the bottom now we're here
        scanPolymer(
          toBeScanned.drop(1),
          Some(next),
          alreadyScanned
        )
      }
    }
  }

  def findReducedPolymerLength(polymer: String): Int = {
    scanPolymer(toBeScanned = polymer).length
  }

  def findShortestPolymer(polymer: String): Int = {
    val availableUnits = polymer.toLowerCase.toSet

    availableUnits
      .map {
        unit: Char =>
          val polymerWithRemovedUnit = polymer.filter(_.toLower != unit)
          findReducedPolymerLength(polymerWithRemovedUnit)
      }
      .min
  }

  def main(args: Array[String]): Unit = {
    // Part one
    println(s"Boom! The whole polymer has reacted and is now reduced to ${findReducedPolymerLength(polymer)} units")

    // Part two
    println(s"The shortest possible polymer has ${findShortestPolymer(polymer)} units")
  }
}
