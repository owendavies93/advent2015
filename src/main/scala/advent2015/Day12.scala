package advent2015

import play.api.libs.json._

object Day12 {

    def main(args: Array[String]) {
        val input = Problem.parseInputToString("day12")
        val json = parse(input)
        println(part1(json))
        println(part2(json))
    }

    def parse(input: String): JsValue = {
        return Json.parse(input)
    }

    def part1(json: JsValue): Int = {
        var total = 0

        def sumKeys(j: JsValue): Unit = {
            j match {
                case i: JsNumber => total += i.value.toInt
                case o: JsObject => o.values.map(sumKeys(_))
                case a: JsArray  => a.value.map(sumKeys(_))
                case _           => total += 0
            }
        }

        sumKeys(json)

        return total
    }

    def part2(json: JsValue): Int = {
        var total = 0

        def sumKeys(j: JsValue): Unit = {
            j match {
                case i: JsNumber => total += i.value.toInt
                case o: JsObject => {
                    if (o.values.toList.contains(JsString("red"))) {
                        return
                    }
                    o.values.map(sumKeys(_))
                }
                case a: JsArray  => a.value.map(sumKeys(_))
                case _           => total += 0
            }
        }

        sumKeys(json)

        return total
    }
}

