package advent2015

import annotation.tailrec
import Math.sqrt
import scala.collection.mutable.ListBuffer

object Factorise {

    def primeFactors(n: Int): List[Int] = {
        @tailrec
        def primeFactors_(args: (Int, Int, ListBuffer[Int], Int)): List[Int] = {
            args match {
                case (1, _, list, _)                   => list.toList
                case (n, k, list, _) if (n % k == 0)   =>
                    primeFactors_((n / k, k, list += k, sqrt(n / k).toInt))
                case (n, k, list, sqrt) if (k < sqrt)  =>
                    primeFactors_(n, k + 1, list, sqrt)
                case (n, k, list, sqrt) if (k >= sqrt) =>
                    primeFactors_((1, k, list += n, 0))
            }
        }
        return primeFactors_((n, 2, ListBuffer[Int](), sqrt(n).toInt))
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
