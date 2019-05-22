package solution

import misc.IO._
import scala.math
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

  def allRoutesFor(from: String, to: String): List[Route] = {
    require(allStations.contains(from), s"no source station: $from")
    require(allStations.contains(to), s"no destination station: $to")

    def allSubRoutesFor(
        current: Station,
        to: Station,
        incompleteRoute: Route
    ): Set[Route] = {
      if (current.code == to.code) // destination on the same line
        Set(Route(Hop(current, to) :: incompleteRoute.hops, true))
      else {
        val thisTrain: Train = trains.filter(_.codeName == current.code).head
        val candidateNextTrains: Set[Train] = (trains - thisTrain).filter(
          train =>
            incompleteRoute.hops
              .forall(
                hop =>
                  hop.to.code != train.codeName && hop.from.code != train.codeName
              )
        )
        val moreRoutes: Set[Route] = for {
          nextTrain <- candidateNextTrains
          nextHop   <- thisTrain.interchangeStationsWith(nextTrain)
          route <- allSubRoutesFor(
            nextHop.to,
            to,
            Route(
              nextHop :: Hop(current, nextHop.from) :: incompleteRoute.hops,
              false
            )
          )
        } yield route
        moreRoutes
      }
    }

    val list = for {
      f     <- allStations.getOrElse(from, Nil)
      t     <- allStations.getOrElse(to, Nil)
      route <- allSubRoutesFor(f, t, Route(Nil, false))
    } yield route
    list.filter(_.successful).sorted
  }
}

case class Route(hops: List[Hop], successful: Boolean) extends Ordered[Route] {
  val distance: Int = hops
    .filter(hop => hop.from.code == hop.to.code)
    .foldLeft(0)((acc, x) => acc + math.abs(x.from.number - x.to.number)) + hops
    .filter(hop => hop.from.code != hop.to.code)
    .size

  override def compare(that: Route): Int =
    (this.distance - that.distance)
}
