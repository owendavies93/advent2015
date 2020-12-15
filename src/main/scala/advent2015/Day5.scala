package advent2015

object Day5 {
    def main(args: Array[String]) {
        val strings = Problem.parseInputToList("day5")

        println(strings.map(part1IsNice).count( _ == true ))
        //println(strings.map(part2IsNice).count( _ == true ))
        part2IsNice("aaa")
    }

    def part1IsNice(input: String): Boolean = {
        val badList = "ab|cd|pq|xy".r.unanchored
        
        badList.findFirstIn(input).foreach { e => return false }
        
        if (input.replaceAll("[^aeiou]","").length() < 3) { 
            return false
        }
        
        val chars = input.toCharArray

        def findDupe(i: Int): Boolean = {
            if (i >= chars.size - 1) {
                return false;
            } else if (chars(i) == chars(i + 1)) {
                return true
            } else {
                return findDupe(i + 1)        
            }
        }

        return findDupe(0)
    }

    def part2IsNice(input: String): Boolean = {

        val chars = input.toCharArray

        val pairs = chars zip chars.tail
        val diff  = pairs.diff(pairs.distinct)

        diff.foreach(println)

        if (diff.size == 0) {
            return false
        }

        def findDupeWithInBetween(i: Int): Boolean = {
            if (i >= chars.size - 2) {
                return false;
            } else if (chars(i) == chars(i + 2) && chars(i) != chars(i + 1)) {
                return true
            } else {
                return findDupeWithInBetween(i + 1)        
            }
        }
        
        return findDupeWithInBetween(0)
    }
}
