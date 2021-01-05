package advent2015

import org.scalatest.funsuite.AnyFunSuite

class Day25Spec extends AnyFunSuite {
    test("Day 25: getCode") {
        assertResult(31916031) {
            Day25.getCode(2)
        }

        assertResult(24592653) {
            Day25.getCode(7)
        }
    }

    test("Day 25: getIndex") {
        assertResult(1) {
            Day25.getIndex(1, 1)
        }

        assertResult(10) {
            Day25.getIndex(1, 4)
        }

        assertResult(12) {
            Day25.getIndex(4, 2)
        }

        assertResult(19) {
            Day25.getIndex(3, 4)
        }
    }
}

