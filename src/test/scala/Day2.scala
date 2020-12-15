package advent2015

import org.scalatest.funsuite.AnyFunSuite

class Day2Spec extends AnyFunSuite {
    val testInput = List("2x3x4".split("x").toArray.map { _.toInt })

    test("Day 2 Part 1") {
        assertResult(58) {
            Day2.part1(testInput)
        }
    }

    test("Day 2 Part 2") {
        assertResult(34) {
            Day2.part2(testInput)
        }
    }
}
