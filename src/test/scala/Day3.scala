package advent2015

import org.scalatest.funsuite.AnyFunSuite

class Day3Spec extends AnyFunSuite {
    val testInput = parseInput("^v^v^v^v^v")

    test("Day 3 Part 1") {
        assertResult(2) {
            Day3.part1(testInput)
        }
    }

    test("Day 3 Part 2") {
        assertResult(11) {
            Day3.part2(testInput)
        }
    }

    private def parseInput(input: String) = input.split("").toList
}
