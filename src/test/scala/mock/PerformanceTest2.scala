package mock

import com.intuit.karate.gatling.PreDef._
import io.gatling.core.Predef._

import scala.concurrent.duration._
import scala.util.Random
import scala.collection.mutable.ListBuffer
import io.gatling.http.Predef._


class PerformanceTest2 extends Simulation {

  MockUtils.startServer(0)

  val protocol = karateProtocol()

  val feedercsv = csv("users.csv").circular // Use the CSV feeder
  val createRequest = scenario("CreateRequest")
  .feed(feedercsv)
  .exec(http("CreateUserRequest")
    .post("http://localhost:5240/Test/CreateRequet")
    .body(StringBody(
      """
      {
        "RequestBy":{
        "Email": "${email}"
        }
      }
      """
    ))
    .asJson
    .check(status.is(200))
    .check(bodyString.saveAs("responseBody")) // Save the response body
    )
    .exec(session => {
      val responseBody = session("responseBody").as[String]
      println(s"Response body: $responseBody")
      session
    })

      protocol.nameResolver = (req, ctx) => req.getHeader("karate-name")
      protocol.runner.karateEnv("perf")

  setUp(
    createRequest.inject(rampUsers(500) during (80 seconds))
  ).protocols(protocol)
}
