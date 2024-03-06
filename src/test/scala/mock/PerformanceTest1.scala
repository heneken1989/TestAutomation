package mock

import com.intuit.karate.gatling.PreDef._
import io.gatling.core.Predef._

import scala.concurrent.duration._
import scala.util.Random
import scala.collection.mutable.ListBuffer
import io.gatling.http.Predef._


class PerformanceTest1 extends Simulation {

  MockUtils.startServer(0)

  val protocol = karateProtocol()

  val feedercsv = csv("users.csv").circular // Use the CSV feeder

  val feeder = (1 to 501).iterator.map(i => Map("name" -> s"$i@gmail.com"))
  
 val feederToKarate = scenario("feederToKarate")
    .feed(feeder)
    .exec(karateSet("name", session => session("name").as[String]))

  val createUser = scenario("CreateUser")
    .exec(feederToKarate)
    .exec(karateFeature("classpath:mock/catscreate.feature@name=CreateUser"))

  protocol.nameResolver = (req, ctx) => req.getHeader("karate-name")
  protocol.runner.karateEnv("perf")

  setUp(
    createUser.inject(rampUsers(500) during (80 seconds))
  ).protocols(protocol)
}
