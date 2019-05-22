package solution
import org.scalatest.{Matchers, WordSpec}

class RoutePlannerSpec extends WordSpec with Matchers {
  val planner = RoutePlanner.instance
  def stationByName(name: String, code: String): Station = {
    planner.allStations(name).filter(_.code == TrainCode(code)).head
  }
  "trains" should {
    "have 8 trains" in {
      planner.trains.map(_.codeName) shouldEqual Set(
        TrainCode("CE"),
        TrainCode("NS"),
        TrainCode("TE"),
        TrainCode("NE"),
        TrainCode("CC"),
        TrainCode("CG"),
        TrainCode("DT"),
        TrainCode("EW")
      )
    }
    "have 166 stops in total" in {
      planner.trains.foldLeft(0)((acc, x) => acc + x.stations.size) shouldEqual 166
    }
  }

  "allStations" should {
    "have 136 distinct stations" in {
      planner.allStations.size shouldEqual 136
    }
    "have 166 stations including interchanges for different lines" in {
      planner.allStations.values
        .foldLeft(0)((acc, x: List[Station]) => acc + x.size) shouldEqual 166
    }
  }

  "allRoutesFor" should {
    "find a direct route between Pasir Ris and City Hall" in {
      val route = planner
        .allRoutesFor("Pasir Ris", "City Hall")
        .head
      val pasirR = stationByName("Pasir Ris", "EW")
      val cityH  = stationByName("City Hall", "EW")
      route.distance shouldEqual 12
      route.hops shouldEqual List(pasirR -> cityH)
    }

    "find a one-transfer route between Holland Village to Bugis" in {
      val route = planner
        .allRoutesFor("Holland Village", "Bugis")
        .head
      route.distance shouldEqual 8
      val hollandV  = stationByName("Holland Village", "CC")
      val bugis     = stationByName("Bugis", "DT")
      val botanicCC = stationByName("Botanic Gardens", "CC")
      val botanicDT = stationByName("Botanic Gardens", "DT")
      route.hops shouldEqual List(
        botanicDT -> bugis,
        botanicCC -> botanicDT,
        hollandV  -> botanicCC
      )
    }
  }
}
