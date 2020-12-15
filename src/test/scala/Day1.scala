package advent2015

import org.scalatest.funsuite.AnyFunSuite

class Day1Spec extends AnyFunSuite {
    test("Day 1 Part 1") {
        val testInput = ")())())".split("").toList

        assertResult(-3) {
            Day1.part1(testInput)
        }
    }

    test("Day 1 Part 2") {
        val testInput = "()())".split("").toList

        assertResult(5) {
            Day1.part2(testInput)
        }        
    }
}
