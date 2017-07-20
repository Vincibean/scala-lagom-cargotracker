package sample.cargotracker.shipping.impl

import javax.inject.Inject

import akka.Done
import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.lightbend.lagom.scaladsl.persistence.PersistentEntityRegistry
import com.softwaremill.macwire._
import sample.cargotracker.registration.api.RegistrationService
import sample.cargotracker.shipping.api.{Itinerary, Leg, ShippingService}

class ShippingServiceImpl @Inject() (persistentEntityRegistry: PersistentEntityRegistry, registrationService: RegistrationService) extends ShippingService {
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
