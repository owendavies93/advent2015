package advent2015

import scala.collection.mutable.ArrayBuffer

class CellulaAutomata
    ( private val grid: ArrayBuffer[Boolean]
    , private val width: Int
    , private val height: Int
    ) {

    private val on  = "#"
    private val off = "."
    private val neighbourList = List(
        (-1, -1), (-1, 0), (0, -1), (1, -1),
        (1, 1), (-1, 1), (1, 0), (0, 1)
    )

    def checkBounds(x: Int, y: Int): Boolean = {
        val xMatch = x match {
            case i if 0 until width contains x => true
            case _                             => false
        }

        val yMatch = y match {
            case i if 0 until height contains i => true
            case _                              => false
        }

        return xMatch && yMatch
    }

    def get(x: Int, y: Int): Boolean = {
        return if (checkBounds(x, y)) grid(y * width + x) else false
    }

    def neighbours(x: Int, y: Int): List[(Int, Int)] = {
        return neighbourList.map(n => (x + n._1, y + n._2))
                            .filter(n => checkBounds(n._1, n._2))
    }

    def step(stepFn: (Int, Int) => Boolean): CellulaAutomata = {
        val nextGrid = ArrayBuffer.fill(height * width)(false)

        for (y <- 0 until width) {
            for (x <- 0 until height) {
                nextGrid(y * width + x) = stepFn(x, y)
            }
        }

        return new CellulaAutomata(nextGrid, width, height)
    }

    override def toString(): String = {
        val sb = new StringBuilder

        for (y <- 0 until width) {
            for (x <- 0 until height) {
                sb.append(if(get(x, y)) on else off)
            }

            if (y < width - 1) {
                sb.append("\n")
            }
        }

        return sb.toString()
    }
}

object CAUtils {

    @throws(classOf[EmptyInputException])
    @throws(classOf[IncorrectSizeException])
    def from2DCharArray
        ( arr: List[String]
        , onChar: Char)
        : CellulaAutomata = {

        if (arr.isEmpty) {
            throw new EmptyInputException("Can't make CA from empty grid")
        }

        val height = arr.size
        val width  = arr(0).size

        val buff = ArrayBuffer.fill(height * width)(false)

        arr.zipWithIndex.foreach {
            case (line, y) => {
                if (line.size != width) {
                    throw new IncorrectSizeException("Incorrect line size")
                }

                line.zipWithIndex.foreach {
                    case (c, x) => {
                        val index = y * width + x
                        buff(index) = c == onChar
                    }
                }
            }
        }

        return new CellulaAutomata(buff, width, height)
    }

}

final case class EmptyInputException
    ( private val message: String = ""
    , private val cause: Throwable = None.orNull
    ) extends Exception(message, cause)

final case class IncorrectSizeException
    ( private val message: String = ""
    , private val cause: Throwable = None.orNull
    ) extends Exception(message, cause)

