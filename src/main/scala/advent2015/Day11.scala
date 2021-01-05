package advent2015

import scalaadventutils.Problem

object Day11 {

    def main(args: Array[String]) = {
        val input = "vzbxkghb"
        val newPass = part1(input)
        println(newPass)
        println(part2(newPass))
    }

    def part1(input: String): String = {
        val pass = new Password(input)
        pass.incUntilValid()
        return pass.get()
    }

    def part2(input: String): String = {
        val pass = new Password(input)
        pass.inc()
        pass.incUntilValid()
        return pass.get()
    }
}

class Password(var str: String) {

    def get(): String = {
        return str
    }

    def inc() = {
        val chars = str.toCharArray.reverse

        def incChars(i: Int): Unit = {
            if (i < chars.length) {
                if (chars(i) == 'z') {
                    chars(i) = 'a'
                    incChars(i + 1)
                } else {
                    chars(i) = (chars(i).toInt + 1).toChar
                }
            }
        }

        incChars(0)
        str = chars.reverse.mkString
    }

    def incUntilValid() = {
        while (!validate()) {
            inc()
        }
    }

    def validate(): Boolean = {
        if (str.contains('i') ||
            str.contains('o') ||
            str.contains('l')) {
            return false
        }

        val repeat = """(.)\1.*(.)\2""".r
        if (repeat.findFirstIn(str).isEmpty) {
            return false
        }

        val chars = str.toCharArray
        def checkThreeSeq(i: Int): Boolean = {
            if (i > chars.length - 3) {
                return false
            }

            val next = (chars(i).toInt + 1).toChar
            val nextnext = (chars(i).toInt + 2).toChar

            if (chars(i + 1) == next && chars(i + 2) == nextnext) {
                return true
            } else {
                return checkThreeSeq(i + 1)
            }
        }
        return checkThreeSeq(0)
    }

}
