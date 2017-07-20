package sample.cargotracker.registration.impl

import javax.inject.Inject

import akka.stream.scaladsl.Source
import akka.{Done, NotUsed}
import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.lightbend.lagom.scaladsl.persistence.cassandra.{CassandraReadSide, CassandraSession}
import com.lightbend.lagom.scaladsl.persistence.{PersistentEntityRef, PersistentEntityRegistry}
import com.lightbend.lagom.scaladsl.pubsub.{PubSubRef, PubSubRegistry, TopicId}
import org.slf4j.{Logger, LoggerFactory}
import sample.cargotracker.registration.api.{Cargo, RegistrationService}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class RegistrationServiceImpl @Inject() (topics: PubSubRegistry, persistentEntityRegistry: PersistentEntityRegistry,
                              readSide: CassandraReadSide, db: CassandraSession) extends RegistrationService {

  private[this] val log: Logger = LoggerFactory.getLogger(this.getClass)

  override def register(): ServiceCall[Cargo, Done] = ServiceCall { request =>
    val topic: PubSubRef[Cargo] = topics.refFor(TopicId[Cargo]("topic"))
    topic.publish(request)
    log.info("Cargo ID: {}.", request.id)
    val ref: PersistentEntityRef[RegistrationCommand] = persistentEntityRegistry.refFor[CargoEntity](request.id)
    ref.ask(RegisterCargo(request))
  }

  override def getLiveRegistrations(): ServiceCall[NotUsed, Source[Cargo, NotUsed]] = ServiceCall { request =>
    val topic: PubSubRef[Cargo] = topics.refFor(TopicId[Cargo]("topic"))
    Future.successful(topic.subscriber)
  }

  override def getAllRegistrations(): ServiceCall[NotUsed, Seq[Cargo]] = ServiceCall { _ =>
    db.selectAll("SELECT cargoid, name, description, owner, destination FROM cargo").map { rows =>
      rows.map { r =>
        Cargo(
          r.getString("cargoid"),
          r.getString("name"),
          r.getString("description"),
          r.getString("owner"),
          r.getString("destination")
        )
      }
    }
  }

  override def getRegistration(): ServiceCall[String, Cargo] = ServiceCall { request =>
    //TODO Implement meaningful
    ???
  }

}
