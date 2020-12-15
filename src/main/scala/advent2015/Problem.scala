package advent2015

object Problem {
    def parseInputLineToList(res: String, split: String) = io.Source
        .fromResource(res)
        .getLines()
        .next()
        .split(split)
        .toList
    
    def parseInputToListOfIntArray(res: String, split: String) = io.Source
        .fromResource(res)
        .getLines()
        .toList
        .map { _.split(split).toArray.map(_.toInt) }

    def parseInputToList(res: String) = io.Source
        .fromResource(res)
        .getLines()
        .toList

    def parseInputToString(res: String) = io.Source
        .fromResource(res)
        .getLines()
        .next
}
