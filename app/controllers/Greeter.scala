package controllers

import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._

import scala.concurrent.Future

class Greeter extends Controller with GreeterGc

trait GreeterGc  {
  this: Controller =>
  val AcceptsMyContentType = Accepting("application/vnd.albgorski+json")

  implicit val greetingJson = Json.writes[Greeting]

  def greeting = Action { implicit request =>
    val json = Json.toJson(Greeting(1, "Hello, World!"))
    // http 127.0.0.1:9000/greeting Accept:application/vnd.albgorski+json
    render {
      case AcceptsMyContentType() => Ok(json)
    }
  }

  val futureGreeting: Future[Greeting] = scala.concurrent.Future {
    Greeting(1, "Hello, World - response with future!")
  }

  def greetingAsync = Action.async {
    futureGreeting.map { greet =>
      val json: JsValue = Json.toJson(greet)
      Ok(json)
    }
  }


  def greetingId(id: Int) = Action { implicit request =>
    val json = Json.toJson(Greeting(id, "Hello, World!"))
    Ok(json)
  }

  case class Greeting(id: Int, content: String)
}

