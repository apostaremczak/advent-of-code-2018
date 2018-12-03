package com.postaremczak.advent_of_code.day_03

import com.postaremczak.advent_of_code.PuzzleInput
import FabricClaim._


object NoMatterHowYouSliceIt {
  val puzzleInput: PuzzleInput = PuzzleInput(3)
  val fabricClaims: Seq[Either[TransformError, FabricClaim]] = puzzleInput.read.map(FabricClaim(_))

  def countOverlappingSquares: Int = ???

  def main(args: Array[String]): Unit = {
    // Part one
    println(s"Number of overlapping squares: $countOverlappingSquares")
  }
}
