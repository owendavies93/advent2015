package advent2015

import org.scalatest._
import funsuite.AnyFunSuite
import matchers.should._

class FactoriseSpec extends AnyFunSuite with Matchers {

    test("primeFactors") {
        Factorise.primeFactors(4)    should equal ( List(2, 2) )
        Factorise.primeFactors(1)    should equal ( List() )
        Factorise.primeFactors(3514) should equal ( List(251, 7, 2) )
    }

    test("factors") {
        Factorise.factors(4)    should equal ( List(1, 2, 4) )
        Factorise.factors(1)    should equal ( List(1) )
        Factorise.factors(2)    should equal ( List(1, 2) )
        Factorise.factors(3514) should equal (
            List(1, 2, 7, 14, 251, 502, 1757, 3514)
        )
    }
}
