package advent2015

import scalaadventutils.Hashing
import scalaadventutils.Problem

object Day4 {

    def main(args: Array[String]) = {
        val input = Problem.parseInputToString("day4")

        println(part1(input))
        println(part2(input))
    }

    def part1(input: String): Int =
        Hashing.findHashWithLeadingChars(input, 1, 5, 0)

    def part2(input: String): Int =
        Hashing.findHashWithLeadingChars(input, 1, 6, 0)

}
