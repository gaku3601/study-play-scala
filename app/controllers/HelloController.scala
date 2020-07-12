package controllers

import javax.inject.Inject
import play.api.i18n.{I18nSupport, Messages}
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}

class HelloController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with I18nSupport{
  def get(name: Option[String]) = Action {implicit request: Request[AnyContent] =>
    Ok {
      name.map(s => Messages("hello", s))
        .getOrElse(Messages("noQuery"))
    }
  }
}
