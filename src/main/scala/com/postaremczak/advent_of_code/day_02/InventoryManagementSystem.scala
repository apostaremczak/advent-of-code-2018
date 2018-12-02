package com.postaremczak.advent_of_code.day_02

import com.postaremczak.advent_of_code.PuzzleInput

object InventoryManagementSystem {
  val puzzleInput: PuzzleInput = PuzzleInput(2)
  val boxIds: Seq[String] = puzzleInput.read

  def countDoublesAndTriples(word: String): Map[Int, Char] = {
    // Drops duplicates - if more than one letter occurred 2 or 3 times,
    // only one of them will be saved
    word.toSet.toSeq
      .map { letter: Char => (word.count(_ == letter), letter) }
      .filter { letterCount => Seq(2, 3).contains(letterCount._1) }
      .toMap
  }

  def calculateChecksum(words: Seq[String]): Int = {
    val wordScores = words
      .map(countDoublesAndTriples)

    wordScores.count(_.get(2).isDefined) * wordScores.count(_.get(3).isDefined)
  }

  def main(args: Array[String]): Unit = {
    println(s"Checksum: ${calculateChecksum(boxIds)}")

    puzzleInput.stream.close()
  }
}
