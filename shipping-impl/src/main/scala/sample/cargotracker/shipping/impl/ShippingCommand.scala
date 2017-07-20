package sample.cargotracker.shipping.impl

import com.lightbend.lagom.serialization.Jsonable
import play.api.libs.json.{Format, Json}
import sample.cargotracker.shipping.api.{Itinerary, Leg}

sealed trait ShippingCommand extends Jsonable

case class CreateItinerary(itinerary: Itinerary) extends ShippingCommand

case class AddLeg(leg: Leg) extends ShippingCommand

object CreateItinerary {
  val format: Format[CreateItinerary] = Json.format[CreateItinerary]
}

object AddLeg {
  val format: Format[AddLeg] = Json.format[AddLeg]
}