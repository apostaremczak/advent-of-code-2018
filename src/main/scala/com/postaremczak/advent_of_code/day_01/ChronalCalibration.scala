package com.postaremczak.advent_of_code.day_01

import java.io.InputStream

import scala.io.{BufferedSource, Source}
import scala.collection.mutable

object ChronalCalibration {
  val inputFile: InputStream = getClass.getResourceAsStream("/day_01_input.txt")

  def findFinalFrequency(frequencyInput: Seq[Int]): Int = {
    frequencyInput.sum
  }

  def findRepeatedFrequency(frequenciesSeen: mutable.Buffer[Int], frequencyInput: Seq[Int]): Int = {
    val nextFrequency = frequenciesSeen.last + frequencyInput.head
    if (frequenciesSeen.contains(nextFrequency)) {
      nextFrequency
    } else {
      frequenciesSeen.append(nextFrequency)
      findRepeatedFrequency(frequenciesSeen, frequencyInput.drop(1).union(Seq(frequencyInput.head)))
    }
  }

  def main(args: Array[String]): Unit = {
    val bufferedFile: BufferedSource = Source.fromInputStream(inputFile)
    val frequencyInput: Seq[Int] = bufferedFile.getLines().toSeq.map(_.toInt)

    // Part one
    println(s"Last frequency: ${findFinalFrequency(frequencyInput)}")
    // Part two
    println(s"First repeated frequency: ${findRepeatedFrequency(mutable.Buffer(0), frequencyInput)}")

    bufferedFile.close()
  }
}
