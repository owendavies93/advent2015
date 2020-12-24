package advent2015

import org.scalatest.funsuite.AnyFunSuite

class Day11Spec extends AnyFunSuite {

    test("inc") {
        var pass = new Password("vzbxkghb")
        assertResult("vzbxkghc") {
            pass.inc()
            pass.get()
        }

        pass = new Password("vzbxkghz")
        assertResult("vzbxkgia") {
            pass.inc()
            pass.get()
        }
    }

    test("validate") {
        var pass = new Password("hijklmmn")
        assert(!pass.validate())

        pass = new Password("abbceffg")
        assert(!pass.validate())

        pass = new Password("abcdffaa")
        assert(pass.validate())

        pass = new Password("ghjaabcc")
        assert(pass.validate())
    }

    test("incUntilValid") {
        var pass = new Password("abcdefgh")
        assertResult("abcdffaa") {
            pass.incUntilValid()
            pass.get()
        }

        pass = new Password("ghijklmn")
        assertResult("ghjaabcc") {
            pass.incUntilValid()
            pass.get()
        }
    }

}
