package advent2015

import org.scalatest.funsuite.AnyFunSuite

class Day24Spec extends AnyFunSuite {
    val input = List(1, 2, 3, 4, 5, 7, 8, 9, 10, 11)

    test("Day 24 Part 1") {
        assertResult(99) {
            Day24.part1(input)
        }
    }

    test("Day 24 Part 2") {
        assertResult(44) {
            Day24.part2(input)
        }
    }
}
