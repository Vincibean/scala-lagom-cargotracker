package sample.cargotracker.shipping.api

import akka.Done
import com.lightbend.lagom.scaladsl.api.transport.Method
import com.lightbend.lagom.scaladsl.api.{Descriptor, Service, ServiceCall}

trait ShippingService extends Service {

  def createItinerary(): ServiceCall[Itinerary, Done]

  def addLeg(id: String): ServiceCall[Leg, Done]

  override def descriptor: Descriptor = {
    import Service._
    named("shippingService").withCalls(
      restCall(Method.POST, "/api/itinerary", createItinerary _).withAutoAcl(true),
      restCall(Method.POST, "/api/itinerary/:itinerary/leg", addLeg _).withAutoAcl(true)
    )
  }
}
