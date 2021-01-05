package advent2015

import scalaadventutils.Problem

object Day25 {

    def main(args: Array[String]) {
        println(part1(2947, 3029))
    }

    def part1(row: Int, col: Int) = getCode(getIndex(row, col))

    def getCode(index: Int): BigInt =
        20151125 * BigInt(252533).modPow(index - 1, 33554393) % 33554393

    def getIndex(row: Int, col: Int) =
        (1 to col).sum + (0 until row - 1).map(i => col + i).sum
}
