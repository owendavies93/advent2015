package advent2015

import org.scalatest.funsuite.AnyFunSuite
import scala.collection.mutable.Map

class WeightedUndirectedGraphSpec extends AnyFunSuite {

    test("Basic string graph") {
        val g = Map("a" -> Map("b" -> 464, "c" -> 518),
                    "b" -> Map("d" -> 10, "c" -> 12),
                    "c" -> Map("e" -> 2),
                    "e" -> Map("f" -> 1))

        val graph = new WeightedUndirectedGraph(g)
        println(graph.getAllPaths("a"))

        val g2 = Map("l" -> Map("d" -> 464, "b" -> 518),
                     "d" -> Map("b" -> 141))

        val graph2 = new WeightedUndirectedGraph(g2)
        println(graph2.getAllPaths("l"))
    }
}
