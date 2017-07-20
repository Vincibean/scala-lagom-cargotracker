package sample.cargotracker.shipping.impl

import java.util.Date

import com.lightbend.lagom.serialization.Jsonable
import play.api.libs.json.{Format, Json}

sealed trait ShippingEvent extends Jsonable

case class ItineraryCreated(id: String, cargoId: String, origin: String, destination: String) extends ShippingEvent

case class LegAdded(id: String, cargoId: String, location: String, arrivalTime: Date, departureTime: Date) extends ShippingEvent

object ItineraryCreated {
  lazy val format: Format[ItineraryCreated] = Json.format[ItineraryCreated]
}

object LegAdded {
  lazy val format: Format[LegAdded] = Json.format[LegAdded]
}
