package controllers

import javax.inject.{Inject, Singleton}
import models.{Meta, Post, Response}
import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.I18nSupport
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import repositories.PostRepository

case class APIRequest(body: String)

@Singleton
class APIController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with I18nSupport {
  private[this] val form = Form(
    mapping(
      "post" -> text(minLength = 1, maxLength = 10)
    )(APIRequest.apply)(APIRequest.unapply))

  def get: Action[AnyContent] = Action { implicit request =>
    Ok(Json.toJson(Response(Meta(200), Some(Json.toJson(PostRepository.findAll)))))
  }

  /*
  curl -X POST -H "Content-Type: application/json" -d '{"post":"sensu"}' localhost:9000/api
   */
  def post: Action[AnyContent] = Action { implicit request =>
    form.bindFromRequest().fold(
      error => {
        val errorMessage = error.errors("post").head.message
        BadRequest(Json.toJson(Response(Meta(400, Some(errorMessage)))))
      },
      postRequest => {
        val post = Post(postRequest.body)
        PostRepository.add(post)
        Ok(Json.toJson(Response(Meta(200))))
      }
    )
  }
}
