package controllers

import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._

import scala.concurrent.Future


class Application extends Controller {
  Greeting.greetingJson

  def greeting = Action { implicit request =>
    val json = Json.toJson(Greeting(1, "Hello, World!"))
    Ok(json)
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

  object Greeting {
    implicit val greetingJson = Json.writes[Greeting]
  }

}
