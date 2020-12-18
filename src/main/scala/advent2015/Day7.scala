package advent2015

import scala.collection.mutable.Map

object Day7 {
    private var state = Map[String, String]()

    def main(args: Array[String]) {
        val lines = Problem.parseInputToList("day7")
                            .map(_.split(" -> ").toArray)

        println(part1(lines))
    }

    def part1(lines: List[Array[String]]): Int = {
        lines.foreach(l => state(l(1)) = l(0))
        return processCommand("a", state)
    }

    def processCommand(ident: String, state: Map[String, String]): Int = {
        val numeric = """(\d+)""".r
        val assign  = """(\w+)""".r
        val unary   = """NOT (\w+)""".r
        val binary  = """(\w+) (\w+) (\w+)""".r

        if (ident matches """(\d+)""") {
            return ident.toInt
        }

        val comm = state(ident)
        var result = 0

        comm match {
            case numeric(i)       => return i.toInt
            case assign(v)        => return processCommand(v, state)
            case unary(i)         => {
                result = ~processCommand(i, state) & 0xFFFF;
            }
            case binary(x, op, y) => {
                op match {
                    case "AND"    =>
                        result = processCommand(x, state) &
                                 processCommand(y, state)
                    case "OR"     =>
                        result = processCommand(x, state) |
                                 processCommand(y, state)
                    case "LSHIFT" =>
                        result = processCommand(x, state) << y.toInt
                    case "RSHIFT" =>
                        result = processCommand(x, state) >> y.toInt
                }
            }
        }

        state(ident) = result.toString
        return result
    }
}
