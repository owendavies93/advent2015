package advent2015

import org.scalatest.funsuite.AnyFunSuite

class Day19Spec extends AnyFunSuite {

    val testInput = List[String](
        "H => HO",
        "H => OH",
        "O => HH"
    )

    val machine = Day19.parseInput(testInput)

    test("Day 19 parsing") {

        assertResult("HO") {
            machine("H")(0)
        }

        assertResult(2) {
            machine("H").size
        }

        assertResult("HH") {
            machine("O")(0)
        }
    }

    test("Day 19 part 1") {
        assertResult(4) {
            Day19.part1(machine, "HOH")
        }
    }
}
