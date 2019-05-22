package solution
import org.scalatest.{Matchers, WordSpec}

class RoutePlannerSpec extends WordSpec with Matchers {
  val planner = RoutePlanner.instance

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

      route.distance shouldEqual 12
      route.hops.size shouldEqual 1
    }

    "find a one-transfer route between Holland Village to Bugis" in {
      val route = planner
        .allRoutesFor("Holland Village", "Bugis")
        .head
      route.distance shouldEqual 8
      route.hops.size shouldEqual 3
    }
  }
}