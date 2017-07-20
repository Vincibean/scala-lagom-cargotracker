package sample.cargotracker.registration.impl

import akka.{Done, NotUsed}
import akka.stream.scaladsl.Source
import com.lightbend.lagom.scaladsl.persistence.PersistentEntityRegistry
import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.lightbend.lagom.scaladsl.persistence.cassandra.{CassandraReadSide, CassandraSession}
import com.lightbend.lagom.scaladsl.pubsub.PubSubRegistry
import sample.cargotracker.registration.api.{Cargo, RegistrationService}

class RegistrationServiceImpl(topics: PubSubRegistry, persistentEntityRegistry: PersistentEntityRegistry,
                              readSide: CassandraReadSide, db: CassandraSession) extends RegistrationService {

  override def register(): ServiceCall[Cargo, Done] = ???

  override def getLiveRegistrations(): ServiceCall[NotUsed, Source[Cargo, NotUsed]] = ???

  override def getAllRegistrations(): ServiceCall[NotUsed, Seq[Cargo]] = ???

  override def getRegistration(): ServiceCall[String, Cargo] = ???
}
