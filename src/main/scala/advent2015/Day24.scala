package advent2015

object Day24 {
    def main (args: Array[String]) {
        val input = Problem.parseInputToList("day24").map(_.toInt)
        println(part1(input))
        println(part2(input))
    }

    def part1(in: List[Int]): BigInt = {
        return getQuantumEntanglement(in, 3)
    }

    def part2(in: List[Int]): BigInt = {
        return getQuantumEntanglement(in, 4)
    }

    private def getQuantumEntanglement(in: List[Int], groups: Int): BigInt = {
        val candidates = (1 to in.length / groups).map(
            i => in.combinations(i).find(l => l.sum == in.sum / groups)
        ).filter(_ != None).map(i => i.get.map(BigInt(_)))

        val smallest = candidates.map(_.size).min
        return candidates.filter(_.size == smallest).map(_.product).min
    }
}
