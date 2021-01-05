package advent2015

import scalaadventutils.Problem

import org.scalatest.funsuite.AnyFunSuite

class Day13Spec extends AnyFunSuite {

    test("Day 13 parsing") {
        val lines = Problem.parseInputToList("day13-test")
        val mappings = Day13.parseInput(lines)

        assertResult(41) {
            mappings(("David", "Carol"))
        }

        assertResult(-7) {
            mappings(("Bob", "Carol"))
        }
    }

    test("Day 13 part 1") {
        val lines = Problem.parseInputToList("day13-test")
        val mappings = Day13.parseInput(lines)

        assertResult(330) {
            Day13.part1(mappings)
        }
    }
}
