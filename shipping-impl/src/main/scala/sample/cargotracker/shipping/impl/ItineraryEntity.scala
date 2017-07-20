package sample.cargotracker.shipping.impl

import java.time.LocalDateTime

import akka.Done
import com.lightbend.lagom.scaladsl.persistence.PersistentEntity
import sample.cargotracker.shipping.api.{Itinerary, Leg}

class ItineraryEntity extends PersistentEntity {

  override type Command = ShippingCommand
  override type Event = ShippingEvent
  override type State = ItineraryState

  override def initialState: ItineraryState = ItineraryState("", "", "", "", Seq.empty[Leg], LocalDateTime.now())

  override def behavior: Behavior = { _ =>
    Actions().onCommand[ShippingCommand, Done]{
      case (CreateItinerary(Itinerary(id, cargoId, origin, destination, legs)), ctx, state) =>
        ctx.thenPersist(ItineraryCreated(id, cargoId, origin, destination))(_ => ctx.reply(Done.getInstance()))
      case (AddLeg(Leg(id, cargoId, location, arrivalTime, departureTime)), ctx, state) =>
        ctx.thenPersist(LegAdded(id, cargoId, location, arrivalTime, departureTime))(_ => ctx.reply(Done.getInstance()))
    }.onEvent{
      case (LegAdded(id, cargoId, location, arrivalTime, departureTime), state) =>
        val leg: Leg = Leg(id, cargoId, location, arrivalTime, departureTime)
        state.copy(legs = state.legs :+ leg)
      case (ItineraryCreated(id, cargoId, origin, destination), state) =>
        ItineraryState(id, cargoId, origin, destination, state.legs, LocalDateTime.now())
    }
  }
}
