package solution

import misc.IO._
import TrainCodeOps._

object RoutePlanner {
  val instance = new RoutePlanner()
}

class RoutePlanner() {
  val trains: Set[Train] = {
    // group by 2-character code (e.g. EW) and construct station and train objects from there
    val trainMapRaw: Map[String, List[Array[String]]] =
      loadInput().toList.groupBy(f => f(0))
    val trainMap: Map[String, List[Station]] = trainMapRaw.transform { (k, v) =>
      v.map {
        case Array(line, number: String, name: String, date: String) =>
          Station(line, number.toInt, name, date)
      }
    }
    trainMap.map {
      case ((k, v)) =>
        Train(k, v)
    }.toSet
  }
  val allStations: Map[String, List[Station]] = {
    val stationList =
      trains.foldLeft(List[Station]())((acc, x) => acc ::: x.stations)
    stationList.groupBy(_.fullName)
  }

  def allRoutesFor(from: String, to: String): List[List[List[Hop]]] = {
    require(allStations.contains(from), s"no source station: $from")
    require(allStations.contains(to), s"no destination station: $to")

    def allSubRoutesFor(
        from: Station,
        to: Station,
        subRoute: List[List[Hop]]
    ): List[List[Hop]] = {
      if (from.code == to.code)
        List(List(Hop(from, to)))
      else
        List(List())
    }

    for {
      f <- allStations.getOrElse(from, List())
      t <- allStations.getOrElse(to, List())
    } yield allSubRoutesFor(f, t, List[List[Hop]]()).toList
  }

}
