package advent2015

object Day17 {

    def main(args: Array[String]) = {
        val containers = Problem.parseInputToList("day17").map(_.toInt)
        println(part1(containers, 150))
        println(part2(containers, 150))
    }

    def part1(cs: List[Int], target: Int) = getPoss(cs, target).size

    def part2(cs: List[Int], target: Int): Int = {
        val poss = getPoss(cs, target)
        val min  = poss.map(_.size).min
        return poss.filter(_.size == min).size
    }

    private def getPoss(cs: List[Int], target: Int) =
        (1 to cs.size).flatMap(cs.zipWithIndex.combinations(_))
                      .filter(_.map(_._1).sum == target)
}
