package com.postaremczak.advent_of_code.day_01

import java.io.InputStream

import scala.io.{BufferedSource, Source}

object ChronalCalibration {
  val inputFile: InputStream = getClass.getResourceAsStream("/day_01_input.txt")

  def findFinalFrequency(frequencyInput: Seq[Int]): Int = {
    frequencyInput.sum
  }

  def findRepeatedFrequency(frequenciesSeen: Seq[Int], frequencyInput: Seq[Int]): Int = {
    val nextFrequency = frequenciesSeen.last + frequencyInput.head
    if (frequenciesSeen.contains(nextFrequency)) {
      nextFrequency
    } else {
      findRepeatedFrequency(
        frequenciesSeen.union(Seq(nextFrequency)),
        frequencyInput.drop(1).union(Seq(frequencyInput.head))
      )
    }
  }

  def main(args: Array[String]): Unit = {
    val bufferedFile: BufferedSource = Source.fromInputStream(inputFile)
    val frequencyInput: Seq[Int] = bufferedFile.getLines().toSeq.map(_.toInt)

    // Part one
    println(s"Last frequency: ${findFinalFrequency(frequencyInput)}")
    // Part two
    println(s"First repeated frequency: ${findRepeatedFrequency(Seq(0), frequencyInput)}")

    bufferedFile.close()
  }
}
