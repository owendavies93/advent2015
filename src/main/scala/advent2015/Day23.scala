package advent2015

object Day23 {

    def main(args: Array[String]) {
        val lines = Problem.parseInputToList("day23")
        println(part1(lines.map(parseLine))("b"))
        println(part2(lines.map(parseLine))("b"))
    }

    def part1(lines: List[List[String]]): Map[String, Int] = {
        val regs = Map("a" -> 0, "b" -> 0)
        return run(lines, regs)
    }

    def part2(lines: List[List[String]]): Map[String, Int] = {
        val regs = Map("a" -> 1, "b" -> 0)
        return run(lines, regs)
    }

    private def run
        ( lines: List[List[String]]
        , r: Map[String, Int]
        ): Map[String, Int] = {

        var ptr = 0
        var regs = r
        while (ptr >= 0 && ptr < lines.size) {
            step(regs, ptr, lines(ptr)) match {
                case (x, y) => regs = x; ptr = y
                case _      =>
            }
        }

        return regs
    }

    def parseLine(line: String): List[String] =
        line.split(" ").flatMap(_.split(",")).toList

    def jumpToNumber(start: Int, arg: String): Int = {
        val regex = """(\+|-)(\d+)""".r
        val regex(op, num) = arg
        return if (op == "+") start + num.toInt else start - num.toInt
    }

    def step
        ( regs: Map[String, Int]
        , ptr: Int
        , comm: List[String]
        ): (Map[String, Int], Int) = {

        val cmd :: args = comm
        val reg = args(0)

        cmd match {
            case "hlf" => (regs.updated(reg, regs(reg) / 2), ptr + 1)
            case "tpl" => (regs.updated(reg, regs(reg) * 3), ptr + 1)
            case "inc" => (regs.updated(reg, regs(reg) + 1), ptr + 1)
            case "jmp" => (regs, jumpToNumber(ptr, args(0)))
            case "jie" => {
                val newPtr =
                    if (regs(reg) % 2 == 0) jumpToNumber(ptr, args(1))
                    else ptr + 1
                (regs, newPtr)
            }
            case "jio" => {
                val newPtr =
                    if (regs(reg) == 1) jumpToNumber(ptr, args(1))
                    else ptr + 1
                (regs, newPtr)
            }
        }
    }
}

