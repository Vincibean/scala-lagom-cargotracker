package sample.cargotracker.shipping.impl

import java.time.LocalDateTime

import play.api.libs.json.{Format, Json}
import sample.cargotracker.shipping.api.Leg

object ItineraryState {

  implicit val format: Format[ItineraryState] = Json.format[ItineraryState]

}

case class ItineraryState(id: String, cargoId: String, origin: String, destination: String, legs: Seq[Leg], timestamp: LocalDateTime)
