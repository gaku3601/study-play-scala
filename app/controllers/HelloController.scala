package controllers

import javax.inject.{Inject, Singleton}
import play.api.Logger
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents, Request}

@Singleton
class HelloController @Inject()(cc: ControllerComponents) extends AbstractController(cc){
  val logger: Logger = play.api.Logger("play")
  def get(name: Option[String]): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    logger.info(s"name parameter: $name")
    Ok {
      name.map(s => s"Hello, $s!")
        .getOrElse("""Please give a name as a query parameter named "name".""")
    }
  }
}
