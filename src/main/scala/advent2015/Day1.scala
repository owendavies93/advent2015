package advent2015

object Day1 {
    def main(args: Array[String]) = {
        val parens = Problem.parseInputLineToList("day1", "")
        
        println(part1(parens))
        println(part2(parens))
    }

    def part1(parens: List[String]): Int = {
        var res = 0

        for (p <- parens) {
            if (p == "(") res += 1 else res -= 1
        }

        return res
    }

    def part2(parens: List[String]): Int = {
        var res = 0

        for ((p, i) <- parens.view.zipWithIndex) {
            if (p == "(") res += 1 else res -= 1

            if (res == -1) return i + 1
        }

        return -1
    }
}
