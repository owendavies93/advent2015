package advent2015

import scala.collection.mutable.Map

object WeightedDirectedGraph {
    type Graph[N] = Map[N, Map[N, Int]]
}
