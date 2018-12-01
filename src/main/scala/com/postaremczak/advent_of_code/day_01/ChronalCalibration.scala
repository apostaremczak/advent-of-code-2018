package com.postaremczak.advent_of_code.day_01

import java.io.InputStream

import scala.io.{BufferedSource, Source}

object ChronalCalibration {
  val inputFile: InputStream = getClass.getResourceAsStream("/day_01_input.txt")

  def findFinalFrequency(frequencyInput: Seq[Int]): Int = {
    frequencyInput.sum
  }

  def findRepeatedFrequency(frequencyInput: Seq[Int], frequenciesSeen: Set[Int] = Set(0), currentFrequency: Int = 0): Int = {
    val nextFrequency = currentFrequency + frequencyInput.head
    if (frequenciesSeen.contains(nextFrequency)) {
      nextFrequency
    } else {
      findRepeatedFrequency(
        frequencyInput.drop(1).union(Seq(frequencyInput.head)),
        frequenciesSeen.union(Set(nextFrequency)),
        nextFrequency
      )
    }
  }

  def main(args: Array[String]): Unit = {
    val bufferedFile: BufferedSource = Source.fromInputStream(inputFile)
    val frequencyInput: Seq[Int] = bufferedFile.getLines().toSeq.map(_.toInt)

    // Part one
    println(s"Last frequency: ${findFinalFrequency(frequencyInput)}")
    // Part two
    println(s"First repeated frequency: ${findRepeatedFrequency(frequencyInput)}")

    bufferedFile.close()
  }
}
