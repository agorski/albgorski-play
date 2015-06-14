import controllers.GreeterGc
import org.scalatestplus.play._
import play.api.mvc._
import play.api.test.Helpers._
import play.api.test._

class GreeterControllerSpec extends PlaySpec with Results {

  trait WithControllerAndRequest {
    val testController = new Controller with GreeterGc

    def fakeRequest(method: String = "GET", route: String = "/") = FakeRequest(method, route)
      .withHeaders(
        ("Date", "2014-10-05T22:00:00"),
        ("Authorization", "username=bob;hash=foobar==")
      )
  }

  "REST API" should {
    "should respond on /greeter" in new WithControllerAndRequest {
      val request = fakeRequest("GET", "/greeting")
      val apiResult = call(testController.greeting, request)
      status(apiResult) mustEqual OK
      val jsonResult = contentAsJson(apiResult)
      println(jsonResult)
      (jsonResult \ "id").as[Int] mustBe 1
      (jsonResult \ "content").as[String] mustBe "Hello, World!"
    }
  }
}