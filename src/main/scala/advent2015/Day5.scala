package advent2015

import scalaadventutils.Problem

object Day5 {
    def main(args: Array[String]) {
        val strings = Problem.parseInputToList("day5")

        println(strings.map(part1IsNice).count( _ == true ))
        println(strings.map(part2IsNice).count( _ == true ))
    }

    def part1IsNice(input: String): Boolean = {
        val badList = "ab|cd|pq|xy".r.unanchored

        badList.findFirstIn(input).foreach { e => return false }

        if (input.replaceAll("[^aeiou]","").length() < 3) {
            return false
        }

        val repeat =  """(.)\1""".r
        return repeat.findFirstIn(input).isDefined
    }

    def part2IsNice(input: String): Boolean = {
        val doublePairs =  """(..).*\1""".r
        val repeat =  """(.).\1""".r

        return doublePairs.findFirstIn(input).nonEmpty &&
               repeat.findFirstIn(input).nonEmpty
    }
}
