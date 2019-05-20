package solution

case class Train(codeName: TrainCode, stations: List[Station]) {
  def interchangeStationsWith(tht: Train): List[Station] = {
    stations.filter { ths =>
      tht.stations.exists(_.fullName == ths.fullName)
    }
  }
}

case class Station(
    code: TrainCode,
    number: Int,
    fullName: String,
    fromDate: String
)
case class Hop(from: Station, to: Station)

case class TrainCode(v: String) extends AnyVal

object TrainCodeOps {
  implicit def stringToTrainCode(s: String): TrainCode =
    TrainCode(s)
}
