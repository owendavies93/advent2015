package advent2015

import scalaadventutils.Problem

import java.security.MessageDigest

object Day4 {
    def main(args: Array[String]) = {
        val input = Problem.parseInputToString("day4")

        println(part1(input))
        println(part2(input))
    }

    def part1(input: String): Int = {
        return findWithZeros(input, 5)
    }

    def part2(input: String): Int = {
        return findWithZeros(input, 6)
    }

    private def findWithZeros(input: String, zeros: Int): Int = {
        val digest = MessageDigest.getInstance("MD5")
        var candidate = 0
        var res = ""
        val comp = List.fill(zeros)("0").mkString

        while(res.slice(0, zeros) != comp) {
            candidate += 1;
            val testString = input + candidate.toString

            res = digest.digest(testString.getBytes).map("%02x".format(_)).mkString
        }

        return candidate
    }
}
