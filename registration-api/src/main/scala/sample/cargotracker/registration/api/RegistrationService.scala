package sample.cargotracker.registration.api

import akka.{Done, NotUsed}
import akka.stream.scaladsl.Source
import com.lightbend.lagom.scaladsl.api.{Descriptor, Service, ServiceCall}
import com.lightbend.lagom.scaladsl.api.transport.Method

trait RegistrationService extends Service {

  def register(): ServiceCall[Cargo, Done]

  def getLiveRegistrations(): ServiceCall[NotUsed, Source[Cargo, NotUsed]]

  def getAllRegistrations(): ServiceCall[NotUsed, Seq[Cargo]]

  // TODO Should have a String ID
  def getRegistration(): ServiceCall[NotUsed, Cargo]

  override def descriptor: Descriptor = {
    import Service._
    named("registrationService").withCalls(
      restCall(Method.POST, "/api/registration", register _),
      pathCall("/api/registration/live", getLiveRegistrations _),
      restCall(Method.GET, "/api/registration/all", getAllRegistrations _),
      restCall(Method.GET, "/api/registration/:id", getRegistration _)
    ).withAutoAcl(true)
  }

}
