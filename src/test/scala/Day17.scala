package advent2015

import org.scalatest.funsuite.AnyFunSuite

class Day17Spec extends AnyFunSuite {

    val testList = List(20, 15, 10, 5, 5)

    test("Day 17 Part 1") {
        assertResult(4) {
            Day17.part1(testList, 25)
        }
    }

    test("Day 17 Part 2") {
        assertResult(3) {
            Day17.part2(testList, 25)
        }
    }
}
