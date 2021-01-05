package advent2015

import scalaadventutils.Problem

object Day9 {

    def main(args: Array[String]) {
        val lines = Problem.parseInputToList("day9")
        println(part1(lines))
        println(part2(lines))
    }

    def part1(lines: List[String]): Int = {
        return getAllDistances(lines).min
    }

    def part2(lines: List[String]): Int = {
        return getAllDistances(lines).max
    }

    private def getAllDistances(lines: List[String]): List[Int] = {
        val parser = """(\w+) to (\w+) = (\d+)""".r

        val distances = lines.map(l => {
            l match {
                case parser(from, to, dist) => {
                    Map((from, to) -> dist.toInt, (to, from) -> dist.toInt)
                }
            }
        }).flatten.toMap

        val perms = distances.keys.map(k => k._1).toSet.toList.permutations

        return perms.map(p => {
            p.sliding(2, 1).map(s => distances(s(0), s(1))).sum
        }).toList
    }

}
