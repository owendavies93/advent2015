package advent2015

import annotation.tailrec
import Math.sqrt

object Factorise {

    def primeFactors(n: Int): List[Int] = {
        @tailrec
        def primeFactors_(args: (Int, Int, List[Int], Int)): List[Int] = {
            args match {
                case (1, _, list, _)                   => list
                case (n, k, list, _) if (n % k == 0)   =>
                    primeFactors_((n / k, k, k :: list, sqrt(n / k).toInt))
                case (n, k, list, sqrt) if (k < sqrt)  =>
                    primeFactors_(n, k + 1, list, sqrt)
                case (n, k, list, sqrt) if (k >= sqrt) =>
                    primeFactors_((1, k, n :: list, 0))
            }
        }
        return primeFactors_((n, 2, List[Int](), sqrt(n).toInt))
    }

    def factors(n: Int): List[Int] = {
        if (n == 1) return List(1)

        val factors = primeFactors(n)
        val products = (1 until factors.length).flatMap(i => {
            factors.combinations(i).map(_.product)
        })
        return (List(1, n) ++ products).sorted
    }
}
