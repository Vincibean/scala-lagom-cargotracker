package sample.cargotracker.shipping.api

import java.util.Date

import play.api.libs.json.{Format, Json}

object Leg {

  implicit val format: Format[Leg] = Json.format[Leg]

}

case class Leg(id: String, cargoId: String, location: String, arrivalTime: Date, departureTime: Date)