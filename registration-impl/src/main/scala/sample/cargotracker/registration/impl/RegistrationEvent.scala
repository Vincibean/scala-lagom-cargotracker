package sample.cargotracker.registration.impl

import com.lightbend.lagom.scaladsl.persistence.{AggregateEvent, AggregateEventTag}
import com.lightbend.lagom.serialization.Jsonable
import sample.cargotracker.registration.api.Cargo

sealed trait RegistrationEvent extends Jsonable with AggregateEvent[RegistrationEvent]

case class CargoRegistered(id: String, cargo: Cargo) extends RegistrationEvent {

  override def aggregateTag: AggregateEventTag[RegistrationEvent] = AggregateEventTag[RegistrationEvent]

}
