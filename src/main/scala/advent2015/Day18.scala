package advent2015

import scalaadventutils.Problem
import scalaadventutils.CAUtils

object Day18 {

    def main(args: Array[String]) = {
        val input = Problem.parseInputToList("day18")
        println(part1(input))
        println(part2(input))
    }

    def part1(input: List[String]): Int = {
        var ca = CAUtils.from2DCharArray(input, '#')

        def stepFn(x: Int, y: Int): Boolean = {
            val neighbourCount = ca.neighbours(x, y)
                                   .filter(n => ca.get(n._1, n._2))
                                   .size

            if (ca.get(x, y)) {
                return neighbourCount == 2 || neighbourCount == 3
            } else {
                return neighbourCount == 3
            }
        }

        for (i <- 0 until 100) {
            ca = ca.step(stepFn)
        }

        return ca.countOn
    }

    def part2(input: List[String]): Int = {
        var ca = CAUtils.from2DCharArray(input, '#')
        val length = input.size - 1

        def stepFn(x: Int, y: Int): Boolean = {
            val neighbourCount = ca.neighbours(x, y)
                                   .filter(n => ca.get(n._1, n._2))
                                   .size

            if ((x == 0 || x == length) &&
                (y == 0 || y == length)) {
                return true
            }

            if (ca.get(x, y)) {
                return neighbourCount == 2 || neighbourCount == 3
            } else {
                return neighbourCount == 3
            }
        }

        for (i <- 0 until 100) {
            ca = ca.step(stepFn)
        }

        return ca.countOn
    }
}
