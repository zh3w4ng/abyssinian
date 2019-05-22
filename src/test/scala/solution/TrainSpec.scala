package solution

import org.scalatest.{Matchers, WordSpec}

class TrainSpec extends WordSpec with Matchers {
  "codeName" should {
    "equal to EE" in {
      new Train(TrainCode("EE"), List()).codeName shouldEqual TrainCode("EE")
    }
  }
  "interchangeStationsWithTrain" should {
    "return all interchange stations between EW and CC" in {
      val trains = RoutePlanner.instance.trains
      val ew     = trains.filter(_.codeName == TrainCode("EW")).head
      val cc     = trains.filter(_.codeName == TrainCode("CC")).head
      ew.interchangeStationsWith(cc).map(_.from.fullName) shouldEqual List(
        "Paya Lebar",
        "Buona Vista"
      )
      ew.interchangeStationsWith(cc).map(_.to.fullName) shouldEqual List(
        "Paya Lebar",
        "Buona Vista"
      )
    }
  }
}
