package advent2015

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map

object Day13 {

    def main(args: Array[String]) = {
        val lines = Problem.parseInputToList("day13")
        val map = parseInput(lines)
        println(part1(map))
        println(part1(addMeToInput(map)))
    }

    def part1(mappings: Map[(String, String), Int]): Int = {
        val perms = mappings.keys.map(k => k._1).toSet.toList.permutations

        val sums = perms.map(p => {
            var vs = p.sliding(2, 1).flatMap(s => {
                List(mappings(s(0), s(1)), mappings(s(1), s(0)))
            }).toList

            var l = ListBuffer[Int]()
            l ++= vs
            l += mappings((p(0), p.last))
            l += mappings((p.last, p(0)))
            l.toList.sum
        })

        return sums.max
    }

    def parseInput(lines: List[String]): Map[(String, String), Int] = {
        val parser = """(\w+).*(lose|gain)\s(\d+).*to\s(\w+)\.$""".r

        var map = collection.mutable.Map[(String, String), Int]()
        lines.foreach(l => {
            val parser(to, sign, points, from) = l

            var pointVal = points.toInt
            if (sign == "lose") {
                pointVal *= -1
            }

            map += ((to, from) -> pointVal)
        })

        return map
    }

    private def addMeToInput
        ( mappings: Map[(String, String), Int] )
        : Map[(String, String), Int] = {

        val others = mappings.keys.map(k => k._1).toSet
        others.foreach(o => {
            mappings += ((o, "Me") -> 0)
            mappings += (("Me", o) -> 0)
        })

        return mappings
    }

}
