package sample.cargotracker.shipping.impl

import akka.{Done, NotUsed}
import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.lightbend.lagom.scaladsl.persistence.PersistentEntityRegistry
import sample.cargotracker.registration.api.RegistrationService
import sample.cargotracker.shipping.api.{Itinerary, Leg, ShippingService}
import com.softwaremill.macwire._

class ShippingServiceImpl(persistentEntityRegistry: PersistentEntityRegistry, registrationService: RegistrationService) extends ShippingService {
  persistentEntityRegistry.register(wire[ItineraryEntity])

  override def createItinerary(): ServiceCall[Itinerary, Done] = ServiceCall { request =>
    val itinerary = persistentEntityRegistry.refFor[ItineraryEntity](request.id)
    itinerary.ask(CreateItinerary(request))
  }

  override def addLeg(id: String): ServiceCall[Leg, Done] = ServiceCall { request =>
    val response = registrationService.getRegistration().invoke(request.cargoId)
    val itinerary = persistentEntityRegistry.refFor[ItineraryEntity](id)
    itinerary.ask(AddLeg(request))
  }

}
