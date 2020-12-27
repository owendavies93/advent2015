package advent2015

import org.scalatest.funsuite.AnyFunSuite

class Day14Spec extends AnyFunSuite {

    val test = "Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds."
    val test2 = "Dancer can fly 16 km/s for 11 seconds, but then must rest for 162 seconds."
    val data = Day14.parseInput(List(test, test2))

    test("Day 14 parsing") {
        assertResult(14) {
            data("Comet")("speed")
        }

        assertResult(10) {
            data("Comet")("time")
        }

        assertResult(127) {
            data("Comet")("rest")
        }
    }

    test("Race") {
        val results = Day14.race(data, 1000)

        assertResult(1120) {
            results._1("Comet")
        }

        assertResult(1056) {
            results._1("Dancer")
        }

        assertResult(312) {
            results._2("Comet")
        }

        assertResult(689) {
            results._2("Dancer")
        }
    }
}
