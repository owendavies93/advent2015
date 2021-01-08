package advent2015

import org.scalatest.funsuite.AnyFunSuite

import java.security.MessageDigest

class Day4Spec extends AnyFunSuite {

    test("Day 4 Part 1") {
        val testInput = "abcdef"

        assertResult(609043) {
            Day4.part1(testInput)
        }
    }

    test("Day 4: checkZeros") {
        val string = "abc3231929"
        val digest = MessageDigest.getInstance("MD5")
        val md5 = digest.digest(string.getBytes)

        assert(Day4.checkZeros(md5, 5))
        assert(Day4.checkZeros(md5, 4))
        assert(!Day4.checkZeros(md5, 6))
    }
}
