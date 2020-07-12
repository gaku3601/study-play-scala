package models

import play.api.libs.json._

case class Response(meta: Meta, data: Option[JsValue] = None)

object Response {
  implicit def writes: Writes[Response] = Json.writes[Response]
}