package advent2015

import scalaadventutils.Problem

import scala.collection.mutable.StringBuilder

object Day10 {

    def main(args: Array[String]) {
        val input = "1113222113"
        println(part1(input))
        println(part2(input))
    }

    def part1(input: String): Int = {
        return run(input, 40)
    }

    def part2(input: String): Int = {
        return run(input, 50)
    }

    def run(input: String, times: Int): Int = {
        var i = 1
        var sb = input
        while (i <= times) {
            sb = step(sb)
            i += 1
        }

        return sb.length
    }

    def step(input: String): String = {
        val chars = input.toCharArray

        var currentChar = chars(0)
        var charCount = 1
        var output = new StringBuilder()

        def countChars(i: Int) {
            if (i > chars.length - 1) {
                output.append(charCount.toString)
                output.append(currentChar)
                return
            }

            if (chars(i) == currentChar) {
                charCount += 1;
            } else {
                output.append(charCount.toString)
                output.append(currentChar)
                charCount = 1
                currentChar = chars(i)
            }

            return countChars(i + 1)
        }

        countChars(1)

        return output.toString
    }
}
