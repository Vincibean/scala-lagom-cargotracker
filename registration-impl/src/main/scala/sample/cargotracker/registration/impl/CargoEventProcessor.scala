package sample.cargotracker.registration.impl

import akka.Done
import com.lightbend.lagom.scaladsl.persistence.{AggregateEventTag, ReadSideProcessor}
import com.lightbend.lagom.scaladsl.persistence.cassandra.CassandraReadSide
import com.lightbend.lagom.scaladsl.persistence.cassandra.CassandraSession

class CargoEventProcessor(session: CassandraSession, readSide: CassandraReadSide) extends ReadSideProcessor[RegistrationEvent] {
  override def buildHandler(): ReadSideProcessor.ReadSideHandler[RegistrationEvent] = ???

  override def aggregateTags: Set[AggregateEventTag[RegistrationEvent]] = ???
}
