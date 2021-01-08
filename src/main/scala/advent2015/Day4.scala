package advent2015

import scalaadventutils.Problem

import java.security.MessageDigest

object Day4 {
    val digest = MessageDigest.getInstance("MD5")

    def main(args: Array[String]) = {
        val input = Problem.parseInputToString("day4")

        println(part1(input))
        println(part2(input))
    }

    def part1(input: String): Int = {
        return findWithZeros(input, 1, 5)
    }

    def part2(input: String): Int = {
        return findWithZeros(input, 1, 6)
    }

    private def findWithZeros(input: String, candidate: Int, zeros: Int): Int =
        if (checkZeros(
             digest.digest((input + candidate.toString).getBytes),
             zeros)
           ) return candidate
        else findWithZeros(input, candidate + 1, zeros)

    def checkZeros(input: Array[Byte], zeros: Int): Boolean = {
        if (zeros % 2 == 0) (0 until zeros / 2).forall(input(_) == 0)
        else (0 until (zeros - 1) / 2).forall(input(_) == 0) &&
             ((input((zeros - 1) / 2) & 0xF0) == 0)
    }

}
