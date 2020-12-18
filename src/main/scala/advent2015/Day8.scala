package advent2015

object Day8 {

    def main(args: Array[String]) {
        var lines = Problem.parseInputToList("day8")

        println(part1(lines))
        println(part2(lines))
    }

    def part1(lines: List[String]): Int = {
        val literalCount = lines.map(_.length).sum

        val charCount = lines.map(part1fsa).sum

        return literalCount - charCount
    }

    def part2(lines: List[String]): Int = {
        val literalCount = lines.map(_.length).sum

        val encodedCount = lines.map(part2fsa).sum

        return encodedCount - literalCount
    }

    def part1fsa(input: String): Int = {
        return input.foldLeft((0, 0))((s, c) => (c, s._1) match {
            case ('"',  0) => (1, s._2)
            case ('\\', 1) => (2, s._2)
            case ('"',  1) => (5, s._2)
            case (_,    1) => (1, s._2 + 1)
            case ('\\', 2) => (1, s._2 + 1)
            case ('"',  2) => (1, s._2 + 1)
            case ('x',  2) => (3, s._2)
            case (_,    3) => (4, s._2)
            case (_,    4) => (1, s._2 + 1)
            case (_,    _) => (0, s._2)
        })._2
    }

    def part2fsa(input: String): Int = {
        return input.foldLeft((0, ""))((s, c) => (c, s._1) match {
            case ('"',  0) => (1, s._2 + "\"\\\"")
            case ('\\', 1) => (2, s._2 + "\\\\")
            case ('"',  1) => (5, s._2 + "\"\\\"")
            case (a,    1) => (1, s._2 + a)
            case ('\\', 2) => (1, s._2 + "\\\\")
            case ('"',  2) => (1, s._2 + "\\\"")
            case ('x',  2) => (3, s._2 + "x")
            case (a,    3) => (4, s._2 + a)
            case (a,    4) => (1, s._2 + a)
            case (_,    _) => (0, s._2)
        })._2.length
    }

}
