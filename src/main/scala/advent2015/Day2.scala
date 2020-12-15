package advent2015

object Day2 {
    def main(args: Array[String]) = {
        val dims = Problem.parseInputToListOfIntArray("day2", "x")

        println(part1(dims))
        println(part2(dims))
    }

    def part1(dims: List[Array[Int]]): Int = {
        var total = 0

        for (pres <- dims) {
            val l = pres(0)
            val w = pres(1)
            val h = pres(2)

            total += (2*l*w + 2*w*h + 2*h*l)

            scala.util.Sorting.quickSort(pres)

            total += (pres(0) * pres(1))
        }

        return total
    }

    def part2(dims: List[Array[Int]]): Int = {
        var total = 0

        for (pres <- dims) {
            scala.util.Sorting.quickSort(pres)

            total += (2*pres(0) + 2*pres(1))
            total += pres.product
        }

        return total
    }
}
