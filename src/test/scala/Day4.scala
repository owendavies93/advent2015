package advent2015

import org.scalatest.funsuite.AnyFunSuite

class Day4Spec extends AnyFunSuite {

    test("Day 4 Part 1") {
        val testInput = "abcdef"

        assertResult(609043) {
            Day4.part1(testInput)
        }
    }

}
