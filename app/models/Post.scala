package models

import play.api.libs.json._

case class Post(id: Int, body: String)

object Post {
  implicit val writes: Writes[Post] = Json.writes[Post]

  def apply(body: String): Post =
    Post(0, body)
}
