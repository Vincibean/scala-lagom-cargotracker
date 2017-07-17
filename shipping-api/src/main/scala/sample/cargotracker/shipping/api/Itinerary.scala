package sample.cargotracker.shipping.api

import play.api.libs.json.{Format, Json}

object Itinerary {

  implicit val format: Format[Itinerary] = Json.format[Itinerary]

}

case class Itinerary(id: String, cargoId: String, origin: String, destination: String, legs: Seq[Leg])
