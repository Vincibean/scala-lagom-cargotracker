package sample.cargotracker.registration.impl

import com.lightbend.lagom.scaladsl.server.{LagomApplication, LagomApplicationContext}
import play.api.libs.ws.ahc.AhcWSComponents
import sample.cargotracker.registration.api.RegistrationService

abstract class RegistrationServiceModule(context: LagomApplicationContext) extends LagomApplication(context) with AhcWSComponents {

  lazy val registrationService: RegistrationService = serviceClient.implement[RegistrationService]

}
