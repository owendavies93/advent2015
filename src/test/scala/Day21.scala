package advent2015

import org.scalatest.funsuite.AnyFunSuite

class Day21Spec extends AnyFunSuite {

    test("Day 21: fight") {
        val boss = new Player(7, 2, 12)
        val you = new Player(5, 5, 8)

        assertResult(you) {
            Day21.fight(you, boss)
        }
    }
}
