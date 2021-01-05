package advent2015

import scalaadventutils.Problem

import scala.collection.mutable.HashMap

object Day3 {
    def main(args: Array[String]) = {
        val dirs = Problem.parseInputLineToList("day3", "")

        println(part1(dirs))
        println(part2(dirs))
    }

    def part1(dirs: List[String]): Int = {
        var positions = HashMap((0, 0) -> 1)
        var current = (0, 0)

        for (d <- dirs) {
            current = processCommand(current, d)
            positions = addToMap(positions, current)
        }

        return positions.size
    }

    def part2(dirs: List[String]): Int = {
        var positions = HashMap((0, 0) -> 1)

        var realCurrent = (0, 0)
        var roboCurrent = (0, 0)

        for ((d, i) <- dirs.view.zipWithIndex) {
            if (i % 2 == 0) {
                realCurrent = processCommand(realCurrent, d)
                positions = addToMap(positions, realCurrent)
            } else {
                roboCurrent = processCommand(roboCurrent, d)
                positions = addToMap(positions, roboCurrent)
            }
        }

        return positions.size
    }

    private def addToMap
        (map: HashMap[Tuple2[Int, Int], Int]
        , tuple: Tuple2[Int, Int]
        ): HashMap[Tuple2[Int, Int], Int] = {

        if (!map.contains(tuple)) {
            map += (tuple -> 1)
        }

        return map
    }

    private def processCommand
        ( start: Tuple2[Int, Int]
        , command: String
        ): Tuple2[Int, Int] = {
        command match {
            case ">" => return (start._1 + 1, start._2)
            case "<" => return (start._1 - 1, start._2)
            case "^" => return (start._1, start._2 + 1)
            case "v" => return (start._1, start._2 - 1)
        }
    }
}
