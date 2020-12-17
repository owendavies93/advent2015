package advent2015

import org.scalatest.funsuite.AnyFunSuite

class Day6Spec extends AnyFunSuite {

    test("Day 6 Part 1") {
        
        assertResult(1000000) {
            Day6.countLights(Day6.change(
                "turn on", getAllOffInput(), (0, 0), (999, 999)
            ))
        }
        
        assertResult(1000) {
            Day6.countLights(Day6.change(
                "toggle", getAllOffInput(), (0, 0), (999, 0)
            ))
        }

        assertResult(999996) {
            Day6.countLights(Day6.change(
                "turn off", getAllOnInput(), (499, 499), (500, 500)
            ))
        }
    }

    def getAllOffInput() = Array.fill[Int](1000000)(0)
    def getAllOnInput() = Array.fill[Int](1000000)(1)
}
