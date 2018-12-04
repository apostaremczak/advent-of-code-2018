package com.postaremczak.advent_of_code.day_04

import com.postaremczak.advent_of_code.PuzzleInput

object ReposeRecord {
  val puzzleInput: PuzzleInput = PuzzleInput(4)
  val records: Seq[StalkerRecord] = puzzleInput.read
    .flatMap(StalkerRecord.parse)
    .sortWith((r1, r2) => r1.time.isBefore(r2.time))

  def getGuards: Seq[Guard] = {
    Shift
      .extract(records)
      .groupBy(_.guardId)
      .mapValues {
        shifts: Seq[Shift] =>
          (shifts.flatMap(_.sleepingMinutes).sorted, shifts.map(_.sleepingTime).sum)
      }
      .flatMap {
        case (guardId: Int, (sleepingMinutes: Seq[Int], napTime: Int)) =>
          if (sleepingMinutes.nonEmpty) {
            val favouriteMinute = sleepingMinutes
              .distinct
              .map { minute: Int => (sleepingMinutes.count(_ == minute), minute) }
              .toMap
              .max

            Some(Guard(
              id = guardId,
              totalNapTime = napTime,
              favouriteNappingMinute = favouriteMinute._2,
              favouriteNappingCount = favouriteMinute._1
            ))
          } else {
            None
          }
      }
      .toSeq
  }

  def findLaziestGuard(guards: Seq[Guard]): Int = {
    val winner = guards
      .sortWith((g1, g2) => g1.totalNapTime < g2.totalNapTime)
      .last

    winner.id * winner.favouriteNappingMinute
  }

  def findFavMinuteGuard(guards: Seq[Guard]): Int = {
    val winner = guards
      .sortWith((g1, g2) => g1.favouriteNappingCount < g2.favouriteNappingCount)
      .last

    winner.id * winner.favouriteNappingMinute
  }


  def main(args: Array[String]): Unit = {
    val guards = getGuards

    // Part one
    println(s"ID of the laziest guard multiplied by the most popular sleeping minute: ${findLaziestGuard(guards)}")

    // Part two
    println(s"ID of the guard which is most frequently asleep on the same minute multiplied by that minute: ${findFavMinuteGuard(guards)}")

    puzzleInput.stream.close()
  }
}
