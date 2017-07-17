package sample.cargotracker.registration.impl

import com.lightbend.lagom.scaladsl.persistence.AggregateEventTag

object RegistrationEventTag {

  val INSTANCE: AggregateEventTag[RegistrationEvent] = AggregateEventTag[RegistrationEvent]

}
