package advent2015

import org.scalatest.funsuite.AnyFunSuite

class Day23Spec extends AnyFunSuite {

    test("Day 23: parsing") {
        assertResult(List("inc", "a")) {
            Day23.parseLine("inc a")
        }

        assertResult(List("jio", "a", "+2")) {
            Day23.parseLine("jio a, +2")
        }
    }

    test("Day 23: step") {
        var regs = Map("a" -> 0)

        assertResult(1) {
            regs = Day23.step(regs, 0, List("inc", "a"))._1
            regs("a")
        }

        assertResult(3) {
            Day23.step(regs, 0, List("tpl", "a"))._1("a")
        }
    }

    test("Day 23: part1") {
        val lines = List[String](
            "inc a",
            "jio a, +2",
            "tpl a",
            "inc a"
        )
        val cmds = lines.map(Day23.parseLine)
        val regs = Day23.part1(cmds)

        assertResult(2) {
            regs("a")
        }
    }
}
