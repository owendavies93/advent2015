package advent2015

import org.scalatest.funsuite.AnyFunSuite

class Day9Spec extends AnyFunSuite {

    test("Day 9") {
        val lines = Problem.parseInputToList("day9-test")
        assertResult(605) {
            Day9.part1(lines)
        }
    }

}
