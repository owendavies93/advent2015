package advent2015

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.collection.mutable.Set

class WeightedUndirectedGraph[N](graph: Map[N, Map[N, Int]]) {

    def get(n: N) = graph.getOrElse(n, Map.empty)

    def keys = graph.keys

    def neighbours(n: N) = get(n).keys

    def getAllPaths(start: N): List[List[N]] = {
        var paths = new ListBuffer[List[N]]()

        def pathFinder
            (node: N
            , paths: ListBuffer[List[N]]
            , currentPath: ListBuffer[N]
            , visited: Set[N]) {

            val ns = neighbours(node)
            if (ns.isEmpty) {
                paths += currentPath.toList
                return
            }

            visited += node

            for (n <- ns) {
                if (!visited.contains(n)) {
                    currentPath += n
                    pathFinder(n, paths, currentPath, visited)
                    currentPath -= n
                }
            }

            visited -= node
        }

        var p = ListBuffer[N](start)
        var v = Set[N]()
        pathFinder(start, paths, p, v)
        return paths.toList
    }
}
