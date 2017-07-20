package sample.cargotracker.shipping.impl

import com.lightbend.lagom.scaladsl.persistence.cassandra.CassandraPersistenceComponents
import com.lightbend.lagom.scaladsl.server.{LagomApplication, LagomApplicationContext, LagomServer}
import play.api.libs.ws.ahc.AhcWSComponents
import sample.cargotracker.registration.api.RegistrationService
import sample.cargotracker.shipping.api.ShippingService
import com.softwaremill.macwire._

abstract class ShippingServiceModule(context: LagomApplicationContext)
  extends LagomApplication(context)
    with AhcWSComponents
    with CassandraPersistenceComponents {

  override lazy val lagomServer: LagomServer = serverFor[ShippingService](wire[ShippingServiceImpl])
  lazy val registrationService: RegistrationService = serviceClient.implement[RegistrationService]
  // lazy val persistentEntityRegistry: PersistentEntityRegistry = serviceClient.implement[PersistentEntityRegistry]

  persistentEntityRegistry.register(wire[ItineraryEntity])

}
