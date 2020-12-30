package advent2015

import org.scalatest.funsuite.AnyFunSuite
import scala.collection.mutable.ArrayBuffer

class CellulaAutomataSpec extends AnyFunSuite {
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
        val grid = List[String](
            "#.#",
            ".#.",
            "#.#"
        )
        val ca = CAUtils.from2DCharArray(grid, '#')

        assert( ca.get(0, 0))
        assert(!ca.get(0, 1))
        assert(!ca.get(1, 0))

        assert(grid.mkString("\n") == ca.toString())
    }
}
