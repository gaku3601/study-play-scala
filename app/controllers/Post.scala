package controllers

case class Post(id: Int, body: String)

object Post {
  def apply(body: String): Post =
    Post(0, body)
}
