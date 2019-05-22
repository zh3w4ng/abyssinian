package solution

case class Train(codeName: TrainCode, stations: List[Station]) {
  def interchangeStationsWith(thatTrain: Train): List[Hop] = {
    require(thatTrain.codeName != codeName, s"that train can't be the same")
    for {
      thisStation <- stations
      thatStation <- thatTrain.stations
      if thisStation.fullName == thatStation.fullName
    } yield Hop(thisStation, thatStation)
  }
}

case class Station(
    code: TrainCode,
    number: Int,
    fullName: String,
    fromDate: String
) {
  def ->(to: Station): Hop = Hop(this, to)
}
case class Hop(from: Station, to: Station) {
  override def toString: String = {
    s"${from.code.v}${from.number}(${from.fullName}) -> ${to.code.v}${to.number}(${to.fullName})"
  }
}

case class TrainCode(v: String) extends AnyVal

object TrainCodeOps {
  implicit def stringToTrainCode(s: String): TrainCode =
    TrainCode(s)
}
