package advent2015

import org.scalatest.funsuite.AnyFunSuite

class Day15Spec extends AnyFunSuite {

    val input = Problem.parseInputToList("day15-test")
    val map = Day15.parseInput(input)

    test("Day 15 Parsing") {
        assertResult(-1) {
            map("Butterscotch")(0)
        }
    }

    test("Day 15 Part 1") {
        assertResult(62842880) {
            Day15.part1(map)
        }
    }

    test("Day 15 Part 2") {
        assertResult(57600000) {
            Day15.part2(map, 500)
        }
    }
}
