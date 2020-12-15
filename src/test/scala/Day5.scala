package advent2015

import org.scalatest.funsuite.AnyFunSuite

class Day5Spec extends AnyFunSuite {

    test("Day 5 Part 1") {
        assert(Day5.part1IsNice("ugknbfddgicrmopn"))
        assert(Day5.part1IsNice("aaa"))
        assert(!Day5.part1IsNice("jchzalrnumimnmhp"))
        assert(!Day5.part1IsNice("haegwjzuvuyypxyu"))
        assert(!Day5.part1IsNice("dvszwmarrgswjxmb"))
    }

    test("Day 5 Part 2") {
        assert(Day5.part2IsNice("qjhvhtzxzqqjkmpb"))
        assert(Day5.part2IsNice("xxyxx"))
        assert(!Day5.part2IsNice("uurcxstgmygtbstg"))
        assert(!Day5.part2IsNice("ieodomkazucvgmuy"))
        assert(!Day5.part2IsNice("aaa"))
    }
}
