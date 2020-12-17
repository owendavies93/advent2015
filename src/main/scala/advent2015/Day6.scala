package advent2015

import scala.collection.mutable.ListBuffer

object Day6 {
    private val length = 1000;

    def main(args: Array[String]) {
        val lines = Problem.parseInputToList("day6")

        println(part1(lines))
        println(part2(lines))
    }

    def part1(lines: List[String]): Int = {
        val data = populateData(lines)
        var state = Array.fill[Int](1000000)(0)
        data.foreach(i => state = change(i._1, state, i._2, i._3))
        return countLights(state)
    }

    def part2(lines: List[String]): Int = {
        val data = populateData(lines)
        var state = Array.fill[Int](1000000)(0)
        data.foreach(i => state = changeBrightness(i._1, state, i._2, i._3))
        return state.sum
    }

    def change
        (command: String
        , state: Array[Int]
        , start: Tuple2[Int, Int]
        , end: Tuple2[Int, Int])
        : Array[Int] = {

        val indices = getLights(start, end)

        command match {
            case "turn on"  => indices.foreach( i => state(i) = 1 )
            case "turn off" => indices.foreach( i => state(i) = 0 )
            case "toggle"   => indices.foreach( i => state(i) = 1 - state(i) )
        }

        return state
    }

    def changeBrightness
        (command: String
        , state: Array[Int]
        , start: Tuple2[Int, Int]
        , end: Tuple2[Int, Int])
        : Array[Int] = {

        val indices = getLights(start, end)

        command match {
            case "turn on"  =>
                indices.foreach( i => state(i) += 1 )
            case "turn off" =>
                indices.foreach( i => state(i) = 0.max(state(i) - 1) )
            case "toggle"   =>
                indices.foreach( i => state(i) += 2 )
        }

        return state
    }

    def countLights(state: Array[Int]) = state.filter(_ > 0).size

    private def populateData
                (lines: List[String])
                : List[Tuple3[String, Tuple2[Int, Int], Tuple2[Int, Int]]] = {
        val parser =
            """(turn on|turn off|toggle) (\d+),(\d+) through (\d+),(\d+)""".r
        var data = new ListBuffer[
            Tuple3[String, Tuple2[Int, Int], Tuple2[Int, Int]]
        ]()

        lines.foreach(l => {
            val parser(comm, sx, ex, sy, ey) = l
            data += Tuple3(
                comm.toString,
                Tuple2(sx.toInt, ex.toInt),
                Tuple2(sy.toInt, ey.toInt)
            )
        })

        return data.toList
    }

    private def getLights
        (start: Tuple2[Int, Int]
        , end: Tuple2[Int, Int])
        : List[Int] = {
        val startx = start._1
        val starty = start._2
        val endx   = end._1
        val endy   = end._2

        var indices = ListBuffer[Int]()

        for (x <- startx to endx) {
            for (y <- starty to endy) {
                indices += (x * length + y)
            }
        }

        return indices.toList
    }

}

