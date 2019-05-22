package solution
import DateTimeOps._

object Main extends App {

  override def main(args: Array[String]): Unit = {
    require(
      args.length == 3,
      "Program needs two parameters: \"FROM\", \"TO\", \"DATETIME\"."
    )
    val planner       = RoutePlanner.instance(args(2))
    val trains        = planner.trains
    val routes        = planner.allRoutesFor(args(0), args(1))
    val shortestRoute = routes.head
    println(s"Found ${routes.size} route(s).")
    println(s"Shortest distance is ${shortestRoute.distance}")
    for {
      hop <- shortestRoute.hops.reverse
    } println(hop)

  }
  def msg = "hello"
}
