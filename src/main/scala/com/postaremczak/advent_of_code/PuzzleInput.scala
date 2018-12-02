package com.postaremczak.advent_of_code

import java.io.InputStream

import scala.io.Source

class PuzzleInput(adventDay: Int) {
  val fileName = s"day_${if (adventDay / 10 > 1) adventDay else "0" + adventDay}_input.txt"
  val stream: InputStream = getClass.getResourceAsStream(s"/$fileName")
}

object PuzzleInput {
  def read(adventDay: Int): Seq[String] = {
    val puzzle = new PuzzleInput(adventDay)
    val bufferedFile = Source.fromInputStream(puzzle.stream)
    val fileContent = bufferedFile.getLines().toSeq
    bufferedFile.close()

    fileContent
  }
}
