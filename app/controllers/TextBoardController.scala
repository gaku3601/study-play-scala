package controllers

import javax.inject.{Inject, Singleton}
import models.Post
import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import repositories.PostRepository

case class PostRequest(body: String)

@Singleton
class TextBoardController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with I18nSupport {
  private[this] val form = Form(
    mapping(
      "post" -> text(minLength = 1, maxLength = 10)
    )(PostRequest.apply)(PostRequest.unapply))

  def get: Action[AnyContent] = Action { implicit request =>
    Ok(views.html.index(PostRepository.findAll, form))
  }

  def post: Action[AnyContent] = Action { implicit request =>
    form.bindFromRequest().fold(
      formWithErrors => BadRequest(views.html.index(PostRepository.findAll, formWithErrors)),
      postRequest => {
        val post = Post(postRequest.body)
        PostRepository.add(post)
        Redirect("/text-board")
      }
    )
  }
}
