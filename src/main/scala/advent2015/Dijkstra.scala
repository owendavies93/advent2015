package advent2015

import scala.collection.mutable.Map
import scala.collection.mutable.Set

object Dijkstra {

    def dijkstra[N]
        ( graph:  WeightedUndirectedGraph[N]
        , source: N )
        : (Map[N, Int], Map[N, N]) = {

        val active = Set(source)
        var result = Map(source -> 0)
        var pred   = Map[N, N]()

        while (active.nonEmpty) {
            val minNode = active.minBy(result)
            active -= minNode

            val minCost = result(minNode)

            for ((node, cost) <- graph.get(minNode)){
                val cost_ = cost + minCost

                if (cost_ < result.getOrElse(node, Int.MaxValue)) {
                    active += node
                    result += (node -> cost_)
                    pred   += (node -> minNode)
                }
            }
        }

        return (result, pred)
    }

    def shortestPath[N]
        ( graph: WeightedUndirectedGraph[N]
        , source: N
        , target: N)
        : List[N] = {

        val pred = dijkstra(graph, source)._2

        def right[N](x: N, pred: Map[N, N]): List[N] = {
            def go(x: N, acc: List[N]): List[N] = {
                pred.get(x) match {
                    case Some(y) => go(y, x :: acc)
                    case None    => x :: acc
                }
            }

            return go(x, List.empty)
        }

        return right(target, pred)
    }

    def shortestPathTotalWeight[N]
        ( g: WeightedUndirectedGraph[N]
        , path: List[N])
        : Int = {

        var total = 0

        def get(i: Int) {
            if (i < path.size - 1) {
                total += g.get(path(i))(path(i + 1))
                get(i + 1)
            }
        }
        get(0)

        return total
    }
}
