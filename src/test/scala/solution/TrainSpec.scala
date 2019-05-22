package solution

import org.scalatest.{Matchers, WordSpec}
import DateOps._
import java.util.Date

class TrainSpec extends WordSpec with Matchers {
  "codeName" should {
    "equal to EE" in {
      new Train(TrainCode("EE"), List()).codeName shouldEqual TrainCode("EE")
    }
  }
  "interchangeStationsWithTrain" should {
    "return all interchange stations between EW and CC" in {
      val today: Date = "23 May 2018"
      val trains      = RoutePlanner.instance(today).trains
      val ew          = trains.filter(_.codeName == TrainCode("EW")).head
      val cc          = trains.filter(_.codeName == TrainCode("CC")).head
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
