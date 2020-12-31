package advent2015

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set

object Day19 {

    def main(args: Array[String]) = {
        val input = Problem.parseInputToList("day19")
        val start = input.last
        val machine = parseInput(input.takeWhile(!_.isEmpty))
        println(part1(machine, start))
        println(part2(start))
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

    /*
        We have a decent shortcut here.
        Note that Rn, Ar and Y only ever appear on the RHS of
        the rules, and a Y causes the terms on the right of it to
        be removed. Ar and Rn always match so their counts are the
        same. Remove one extra because we need to start with "e",
        not an empty string.
        The total number of symbols in the string is just the number
        of upper case symbols in the string.
    */
    def part2(start: String): Int = {
        val chars = start.filter(_.isUpper).size
        val rn = """Rn""".r
        val ys = """Y""".r

        return chars - ((2 * rn.findAllMatchIn(start).size) +
                        (2 * ys.findAllMatchIn(start).size) + 1)
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
