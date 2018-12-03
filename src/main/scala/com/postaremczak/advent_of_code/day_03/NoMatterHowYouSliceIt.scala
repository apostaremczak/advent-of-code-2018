package com.postaremczak.advent_of_code.day_03

import com.postaremczak.advent_of_code.PuzzleInput


object NoMatterHowYouSliceIt {
  val puzzleInput: PuzzleInput = PuzzleInput(3)
  val fabricClaims: Seq[FabricClaim] = puzzleInput.read.flatMap(FabricClaim(_))

  def getOverlappingSquares: Seq[(Int, Int)] = {
    fabricClaims
      .flatMap(_.squares)
      .groupBy(identity)
      .mapValues(_.size)
      .filter(_._2 > 1)
      .keys
      .toSeq
  }

  def countOverlappingSquares(overlappingSquares: Seq[(Int, Int)]): Int = {
    overlappingSquares.size
  }

  def findNotOverlappingClaimId(overlappingSquares: Set[(Int, Int)]): Int = {
    fabricClaims
      .map { fabricClaim => (fabricClaim.id, fabricClaim.squares) }
      .filter(_._2.toSet.intersect(overlappingSquares).isEmpty)
      .head
      ._1
  }

  def main(args: Array[String]): Unit = {
    val overlappingSquares: Seq[(Int, Int)] = getOverlappingSquares
    // Part one
    println(s"Number of overlapping squares: ${countOverlappingSquares(overlappingSquares)}")

    // Part two
    println(s"ID of the only not overlapping claim: ${findNotOverlappingClaimId(overlappingSquares.toSet)}")
  }
}
