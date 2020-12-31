package advent2015

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set

object Day19 {

    def main(args: Array[String]) = {
        val input = Problem.parseInputToList("day19")
        val start = input.last
        val machine = parseInput(input.takeWhile(!_.isEmpty))
        println(part1(machine, start))
    }

    def part1(machine: Map[String, List[String]], start: String): Int = {
        val r = """([A-Ze]{1}[algih]{0,1})""".r
        val matches = r.findAllMatchIn(start).map(_.toString).toList
        val res =
            for (
                (m, i) <- matches.zipWithIndex; if machine.contains(m)
            )
            yield {
                val bef = matches.slice(0, i).mkString
                val aft = matches.slice(i + 1, start.length).mkString

                machine(m).map(out => bef + out + aft)
            }

        return res.flatten.toSet.size
    }

    def parseInput(input: List[String]): Map[String, List[String]] = {
        val parser = """(\w+) => (\w+)""".r

        val map = collection.mutable.Map[String, ListBuffer[String]]()
        input.foreach(line => {
            val parser(in, out) = line
            if (map.contains(in)) {
                map(in) += out
            } else {
                map(in) = ListBuffer(out)
            }
        })

        return map.keys.map(k => {
            k -> map(k).toList
        }).toMap
    }
}
