package advent2015

import org.scalatest.funsuite.AnyFunSuite

class Day8Spec extends AnyFunSuite {

    test("Day 8 Part 1") {
        assertResult(7) { Day8.part1fsa("\"aaa\\\"aaa\"") }
        assertResult(1) { Day8.part1fsa("\"\\x27\"") }
    }

    test("Day 8 Part 2") {
        assertResult(9)  { Day8.part2fsa("\"abc\"") }
        assertResult(16) { Day8.part2fsa("\"aaa\\\"aaa\"") }
    }
}
