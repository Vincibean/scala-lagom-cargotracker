package sample.cargotracker.registration.api

import akka.{Done, NotUsed}
import akka.stream.scaladsl.Source
import com.lightbend.lagom.scaladsl.api.{Service, ServiceCall}
import com.lightbend.lagom.scaladsl.api.transport.Method

trait RegistrationService extends Service {

  def register(): ServiceCall[Cargo, Done]

  def getLiveRegistrations(): ServiceCall[NotUsed, Source[Cargo, NotUsed]]

  def getAllRegistrations(): ServiceCall[NotUsed, Seq[Cargo]]

  // TODO Should have a String ID
  def getRegistration(): ServiceCall[NotUsed, Cargo]

  override def descriptor = {
    import Service._
    named("registrationService").withCalls(
      restCall(Method.POST, "/api/registration", register()),
      pathCall("/api/registration/live", getLiveRegistrations()),
      restCall(Method.GET, "/api/registration/all", getAllRegistrations()),
      restCall(Method.GET, "/api/registration/:id", getRegistration())
    ).withAutoAcl(true)
  }

}
