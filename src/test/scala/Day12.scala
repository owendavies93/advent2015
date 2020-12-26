package advent2015

import org.scalatest.funsuite.AnyFunSuite

class Day12Spec extends AnyFunSuite {

    test("Day 12 part 1") {
        var json = "{\"a\":2,\"b\":4}"

        assertResult(6) {
            val map = Day12.parse(json)
            Day12.part1(map)
        }

        json = "{\"a\":{\"b\":4},\"c\":-1}"

        assertResult(3) {
            val map = Day12.parse(json)
            Day12.part1(map)
        }

        json = "[]"

        assertResult(0) {
            val map = Day12.parse(json)
            Day12.part1(map)
        }

        json = "[-1,{\"a\":1}]"

        assertResult(0) {
            val map = Day12.parse(json)
            Day12.part1(map)
        }
    }

    test("Day 12 part 2") {
        var json = "[1,{\"c\":\"red\",\"b\":2},3]"

        assertResult(4) {
            val map = Day12.parse(json)
            Day12.part2(map)
        }
    }
}
