package advent2015

object Day16 {

    val test = Map(
        "children"    -> 3,
        "cats"        -> 7,
        "samoyeds"    -> 2,
        "pomeranians" -> 3,
        "akitas"      -> 0,
        "vizslas"     -> 0,
        "goldfish"    -> 5,
        "trees"       -> 3,
        "cars"        -> 2,
        "perfumes"    -> 1
    )

    def main(args: Array[String]) = {
        val lines = Problem.parseInputToList("day16")
        val sues = parseInput(lines)
        println(part1(sues))
        println(part2(sues))
    }

    def part1(sues: List[Map[String, Int]]): Map[String, Int] =
        sues.find(!_.exists(attr => test(attr._1) != attr._2)).get

    def part2(sues: List[Map[String, Int]]): Map[String, Int] = {
        return sues.find(!_.exists(attr => attr._1 match {
            case "cats"        => attr._2 <= test(attr._1)
            case "trees"       => attr._2 <= test(attr._1)
            case "pomeranians" => attr._2 >= test(attr._1)
            case "goldfish"    => attr._2 >= test(attr._1)
            case _             => attr._2 != test(attr._1)
        })).get
    }

    def parseInput(sues: List[String]): List[Map[String, Int]] =
        sues.map(
            _.replaceFirst("""Sue \d+: """, "")
             .replace(":", "")
             .replace(",", "")
             .split(" ")
        ).map(s => {
            s.sliding(2, 2).map(a => (a(0), a(1).toInt)).toMap
        })
}
