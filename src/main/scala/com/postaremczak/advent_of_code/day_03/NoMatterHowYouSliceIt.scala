package com.postaremczak.advent_of_code.day_03

import com.postaremczak.advent_of_code.PuzzleInput


object NoMatterHowYouSliceIt {
  val puzzleInput: PuzzleInput = PuzzleInput(3)
  val fabricClaims: Seq[Option[FabricClaim]] = puzzleInput.read.map(FabricClaim(_))

  def countOverlappingSquares: Int = {
    fabricClaims
      .flatten
      .flatMap(_.getSquares)
      .groupBy(identity)
      .mapValues(_.size)
      .count(_._2 > 1)
  }

  def main(args: Array[String]): Unit = {
    // Part one
    println(s"Number of overlapping squares: $countOverlappingSquares")
  }
}
