package advent2015

import org.scalatest.funsuite.AnyFunSuite
import scala.collection.mutable.Map

class Day7Spec extends AnyFunSuite {

    test("Day 7 Part 1") {
        val lines = Problem.parseInputToList("day7-test")
                           .map(_.split(" -> ").toArray)

        var state = Map[String, String]()
        lines.foreach(l => state(l(1)) = l(0))

        assertResult(507) {
            Day7.processCommand("e", state)
        }
    }
}
