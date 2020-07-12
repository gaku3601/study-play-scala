package controllers

import javax.inject.Inject
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}

class HelloController @Inject()(cc: ControllerComponents) extends AbstractController(cc){
  def get(name: Option[String]) = Action {implicit request: Request[AnyContent] =>
    Ok {
      name.map(s => s"Hello, $s!")
        .getOrElse("""Please give a name as a query parameter named "name".""")
    }
  }
}
