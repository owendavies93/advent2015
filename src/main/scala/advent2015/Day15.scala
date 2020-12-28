package advent2015

object Day15 {

    def main(args: Array[String]) = {
        val input = Problem.parseInputToList("day15")
        val mappings = parseInput(input)

        println(part1(mappings))
        println(part2(mappings, 500))
    }

    def part1(mappings: Map[String, List[Int]]): Int = {
        val stats    = mappings.values
        val possible = getPossibles(mappings.keys.size)

        return possible.map(poss => getTotal(stats, poss)).max
    }

    def part2(mappings: Map[String, List[Int]], calories: Int): Int = {
        val stats    = mappings.values
        val possible = getPossibles(mappings.keys.size)

        return possible.map(poss => {
            val sum = getProduct(stats, poss, 4)
            if (sum == calories) getTotal(stats, poss) else 0
        }).max
    }

    private def getPossibles(count: Int) =
        List.fill(count)(0 to 100).flatten
            .combinations(count).filter(_.sum == 100)
            .flatMap(_.permutations)

    private def getTotal
        ( stats: Iterable[List[Int]]
        , candidate: List[Int])
        : Int = {

        var total = 1
        for (i <- 0 to 3) {
            val sum = getProduct(stats, candidate, i)
            if (sum > 0) total *= sum else total = 0
        }
        return total
    }

    private def getProduct
        ( stats: Iterable[List[Int]]
        , candidate: List[Int]
        , index: Int): Int = {

        val stat = stats.map(_(index))
        val zip = candidate zip stat
        return zip.map(z => z._1 * z._2).sum
    }

    def parseInput(input: List[String]): Map[String, List[Int]] = {
        val parser = """^(\w+): capacity ([-\d]+), durability ([-\d]+), flavor ([-\d]+), texture ([-\d]+), calories (\d+)$""".r

        return input.map(line => {
            val parser(ing, cap, dur, flav, text, cals) = line
            (ing -> List(cap, dur, flav, text, cals).map(_.toInt))
        }).toMap
    }
}
