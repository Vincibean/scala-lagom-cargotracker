package sample.cargotracker.registration.impl

import akka.{Done, NotUsed}
import akka.stream.scaladsl.Source
import com.lightbend.lagom.scaladsl.persistence.PersistentEntityRegistry
import com.lightbend.lagom.scaladsl.api.ServiceCall
import sample.cargotracker.registration.api.{Cargo, RegistrationService}

class RegistrationServiceImpl(persistentEntityRegistry: PersistentEntityRegistry) extends RegistrationService {
  override def register(): ServiceCall[Cargo, Done] = ???

  override def getLiveRegistrations(): ServiceCall[NotUsed, Source[Cargo, NotUsed]] = ???

  override def getAllRegistrations(): ServiceCall[NotUsed, Seq[Cargo]] = ???

  override def getRegistration(): ServiceCall[NotUsed, Cargo] = ???
}
