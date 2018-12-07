package com.postaremczak.advent_of_code.day_07

import com.postaremczak.advent_of_code.Solution

object TheSumOfItsParts extends Solution(adventDay = 7) {

  private val stepPattern = """Step (\w) must be finished before step (\w) can begin\.""".r
  lazy val steps: Map[Char, Step] = parseInstructions(puzzleInput.read)

  def parseInstructions(instructions: Seq[String]): Map[Char, Step] = {
    instructions
      .map {
        instruction: String =>
          instruction match {
            case stepPattern(stepBefore, step) => (stepBefore.head, step.head)
          }
      }
      .sortWith((s1: (Char, Char), s2: (Char, Char)) => s1._1 < s2._1)
      .groupBy(_._2)
      .mapValues(_.map(_._1))
      .map {
        case (stepName: Char, stepsBefore: Seq[Char]) =>
          (stepName, Step(stepName, stepsBefore))
      }
  }

  def findStepOrder: Char = {
    // Find the step which has no steps to be executed before
    val firstStep = steps
      .flatMap(_._2.stepsBefore)
      .toSeq
      .filter((s: Char) => !steps.keys.toSeq.contains(s))
      .head

    ???
  }

  def main(args: Array[String]): Unit = {

  }

}
