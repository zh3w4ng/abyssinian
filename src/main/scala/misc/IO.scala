package misc

import scala.io.Source

object IO {
  private val filePath    = "src/main/resources/StationMap.csv"
  private val codePattern = """(\w{2})(\d{1,2})""".r

  def loadInput(): Iterator[Array[String]] = {
    // drop the first line of csv, which is headers
    Source.fromFile(filePath).getLines.drop(1).map { line =>
      {
        line.split(",") match {
          case Array(code, name, date) => {
            val codePattern(line, num) = code
            Array(line, num, name, date)
          }
        }
      }
    }
  }
}
