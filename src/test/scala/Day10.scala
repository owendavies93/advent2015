package advent2015

import org.scalatest.funsuite.AnyFunSuite

class Day10Spec extends AnyFunSuite {

    test("Day 10") {
        assertResult("21") {
            Day10.step("11")
        }

        assertResult("111221") {
            Day10.step("1211")
        }

        assertResult("312211") {
            Day10.step("111221")
        }
    }

}
