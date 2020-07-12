package controllers

import javax.inject.{Inject, Singleton}
import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import services.PostService

case class PostRequest(body: String)

@Singleton
class TextBoardController @Inject()(cc: ControllerComponents, ps: PostService) extends AbstractController(cc) with I18nSupport {
  private[this] val form = Form(
    mapping(
      "post" -> text(minLength = 1, maxLength = 10)
    )(PostRequest.apply)(PostRequest.unapply))

  def get: Action[AnyContent] = Action { implicit request =>
    Ok(views.html.index(ps.getAll, form))
  }

  def post: Action[AnyContent] = Action { implicit request =>
    form.bindFromRequest().fold(
      formWithErrors => BadRequest(views.html.index(ps.getAll, formWithErrors)),
      postRequest => {
        ps.add(postRequest.body)
        Redirect("/text-board")
      }
    )
  }
}
