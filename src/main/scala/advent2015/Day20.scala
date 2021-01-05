package advent2015

import scalaadventutils.Problem
import scalaadventutils.Factorise

import scala.collection.mutable.Map

object Day20 {

    def main(args: Array[String]) = {
        println(part1(33100000))
        println(part2(33100000))
    }

    def part1(target: Int):Int = {
        // Only bother checking multiples of 6, since they have far more
        // factors than multiples of other low numbers
        return Stream.from(6, 6).dropWhile(i => {
            Factorise.factors(i).sum * 10 < target
        })(0)
    }

    def part2(target: Int):Int = {
        var deliveredCounts = Map[Int, Int]()
        // The factors of 6 trick doesn't work here since we need to
        // count the occurrences of each factor
        return Stream.from(1).dropWhile(i => {
            val factors = Factorise.factors(i)
            val filtered = factors.filter(f => {
                deliveredCounts.getOrElse(f, 0) <= 50
            })
            factors.foreach(f => {
                val count = deliveredCounts.getOrElse(f, 0)
                deliveredCounts(f) = count + 1
            })
            filtered.sum * 11 < target
        })(0)
    }
}
