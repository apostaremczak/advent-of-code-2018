package com.postaremczak.advent_of_code.day_12


object SurvivalRule {
  private val rulePattern = """([.#]+) => ([.#])""".r

  def parse(rawRules: Seq[String]): Unit = {
    rawRules
      .map {
        rule: String =>
          rule match {
            case rulePattern(surroundings, effect) =>
              (surroundings.map(Pot(_)), Pot(effect.head))
          }
      }
  }
}

