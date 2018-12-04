package com.postaremczak.advent_of_code.day_04

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

case class StalkerRecord(
                          time: DateTime,
                          message: String,
                          guardOnDutyId: Option[String]
                        )

object StalkerRecord {
  private val recordPattern = """\[(\d{4}-\d{2}-\d{2} \d{2}:\d{2})\] (.*)""".r
  private val guardPattern = """(\w+) #(\d+) ((?:\w|\s)+)""".r
  private val dateTimeFormat = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm")

  /**
    * @param rawMessage e.g. '[1518-11-01 00:05] falls asleep'
    */
  def parse(rawMessage: String): Option[StalkerRecord] = {
    rawMessage match {
      case recordPattern(timeString, message) =>
        // Check if there's a guard information
        val guard: Option[String] = message match {
          case guardPattern(_, guardId, _) => Some(guardId)
          case _ => None
        }

        Some(StalkerRecord(DateTime.parse(timeString, dateTimeFormat), message, guard))

      case _ => None
    }
  }
}
