package com.postaremczak.advent_of_code.day_04

import com.postaremczak.advent_of_code.PuzzleInput

object ReposeRecord {
  val puzzleInput: PuzzleInput = PuzzleInput(4)
  val records: Seq[StalkerRecord] = puzzleInput.read
    .flatMap(StalkerRecord.parse)
    .sortWith((r1, r2) => r1.time.isBefore(r2.time))
  val guardShifts: Seq[Shift] = Shift.extract(records)


  def findLaziestGuard(): Int = {
    val winner = guardShifts
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
              favouriteNappingMinute = favouriteMinute._2,
              totalNapTime = napTime
            ))
          } else {
            None
          }
      }
      .toSeq
      .sortWith((g1, g2) => g1.totalNapTime < g2.totalNapTime)
      .last

    winner.id * winner.favouriteNappingMinute
  }

  def main(args: Array[String]): Unit = {
    // Part one
    println(s"ID of the laziest guard multiplied by the most popular sleeping minute: $findLaziestGuard")

    puzzleInput.stream.close()
  }
}
