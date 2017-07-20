package sample.cargotracker.shipping.impl

import akka.Done
import com.lightbend.lagom.scaladsl.persistence.PersistentEntity
import com.lightbend.lagom.serialization.{CompressedJsonable, Jsonable}
import play.api.libs.json.{Format, Json}
import sample.cargotracker.shipping.api.{Itinerary, Leg}

sealed trait ShippingCommand extends Jsonable with CompressedJsonable with PersistentEntity.ReplyType[Done]

case class CreateItinerary(itinerary: Itinerary) extends ShippingCommand

case class AddLeg(leg: Leg) extends ShippingCommand

object CreateItinerary {
  val format: Format[CreateItinerary] = Json.format[CreateItinerary]
}

object AddLeg {
  val format: Format[AddLeg] = Json.format[AddLeg]
}