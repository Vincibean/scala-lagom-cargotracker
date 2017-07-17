package sample.cargotracker.registration.api

import play.api.libs.json.{Format, Json}

object Cargo {

  implicit val format: Format[Cargo] = Json.format[Cargo]

}

case class Cargo(id: String, name: String, description: String, owner: String, destination: String)
