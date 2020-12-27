package advent2015

import scala.collection.mutable.Map

object Day14 {

    def main(args: Array[String]) = {
        val input = Problem.parseInputToList("day14")
        val data = parseInput(input)
        println(part1(data))
        println(part2(data))
    }

    def part1(data: Map[String, Map[String, Int]]): Int = {
        return race(data, 2503)._1.values.max
    }

    def part2(data: Map[String, Map[String, Int]]): Int = {
        return race(data, 2503)._2.values.max
    }

    def race
        ( data: Map[String, Map[String, Int]]
        , seconds: Int)
        : Tuple2[Map[String, Int], Map[String, Int]] = {

        var results = collection.mutable.Map[String, Int]()
        var leaders = collection.mutable.Map[String, Int]()

        def step(t: Int) {
            if (t < seconds) {
                data.keys.foreach(reindeer => {
                    val r = data(reindeer)
                    if (t == 0) {
                        results += (reindeer -> r("speed"))
                        leaders += (reindeer -> 0)
                    } else {
                        val total = r("time") + r("rest")
                        if (t % total < r("time")) {
                            results(reindeer) += r("speed")
                        }
                    }
                })

                val max = results.maxBy(_._2)._2
                val maxRs = results.filter(_._2 == max).keys

                maxRs.foreach(leaders(_) += 1)

                step(t + 1)
            }
        }

        step(0)
        return (results, leaders)
    }

    def parseInput(input: List[String]): Map[String, Map[String, Int]] = {
        val parser = """^(\w+)[^\d]+(\d+)[^\d]+(\d+)[^\d]+(\d+).*$""".r

        var map = collection.mutable.Map[String, Map[String, Int]]()
        input.foreach(line => {
            val parser(name, speed, time, rest) = line
            val inner = Map(
                "speed" -> speed.toInt,
                "time"  -> time.toInt,
                "rest"  -> rest.toInt
            )
            map += (name -> inner)
        })

        return map
    }
}
