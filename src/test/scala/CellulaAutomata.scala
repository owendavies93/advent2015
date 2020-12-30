package advent2015

import org.scalatest.funsuite.AnyFunSuite
import scala.collection.mutable.ArrayBuffer

class CellulaAutomataSpec extends AnyFunSuite {
    val grid = List[String](
        "#.#",
        ".#.",
        "#.#"
    )

    test("CellulaAutomata: checkBounds") {
        val width = 2
        val height = 2
        val arr = ArrayBuffer(true, false, false, true)

        assert(arr.size == width * height)

        val ca = new CellulaAutomata(arr, width, height)

        assert( ca.checkBounds(0 , 0))
        assert(!ca.checkBounds(-1, 0))
        assert( ca.checkBounds(1 , 1))
        assert(!ca.checkBounds(4 , 1))

        assert(!ca.checkBounds(width, height))
    }

    test("CellulaAutomata: get") {
        val ca = CAUtils.from2DCharArray(grid, '#')

        assert( ca.get(0, 0))
        assert(!ca.get(0, 1))
        assert(!ca.get(1, 0))

        assert(grid.mkString("\n") == ca.toString())
    }

    test("CellulaAutomata: neighbours") {
        val ca = CAUtils.from2DCharArray(grid, '#')

        assertResult(3) {
            ca.neighbours(0, 0).size
        }

        assertResult(8) {
            ca.neighbours(1, 1).size
        }

        assertResult(5) {
            ca.neighbours(2, 1).size
        }
    }

    test("CellulaAutomata: step") {
        var ca1 = CAUtils.from2DCharArray(grid, '#')

        def stepFn(x: Int, y: Int): Boolean = {
            val neighbourCount = ca1.neighbours(x, y)
                                    .filter(n => ca1.get(n._1, n._2))
                                    .size

            if (ca1.get(x, y)) {
                return neighbourCount == 2
            } else {
                return neighbourCount > 1
            }
        }

        ca1 = ca1.step(stepFn)

        val grid2 = List[String](
            ".#.",
            "#.#",
            ".#."
        )

        assert(grid2.mkString("\n") == ca1.toString())
    }
}

