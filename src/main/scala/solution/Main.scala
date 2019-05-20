package solution

object Main extends App {

  val planner = RoutePlanner.instance
  val trains  = planner.trains
  // for {
  //   train   <- trains
  //   station <- train.stations
  // } println(s"${train.codeName}, ${station.fullName}")
  val routesList = planner.allRoutesFor("Pasir Ris", "City Hall")
  for {
    routes <- routesList
    route  <- routes
    hop    <- route
  } println(s"${hop}")

  def msg = "hello"
}
