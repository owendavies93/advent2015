package advent2015

import org.scalatest.funsuite.AnyFunSuite
import scala.collection.mutable.Map

class DijkstraSpec extends AnyFunSuite {

    test("Basic string graph") {
        val g = Map("a" -> Map("b" -> 464, "c" -> 518),
                    "b" -> Map("c" -> 141, "d" -> 10),
                    "c" -> Map("a" -> 2))

        assertResult(474) {
            val path = Dijkstra.shortestPath(g, "a", "d")
            Dijkstra.shortestPathTotalWeight(g, path)
        }
    }

}
