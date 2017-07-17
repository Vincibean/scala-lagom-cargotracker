package sample.cargotracker.registration.impl

import akka.Done
import com.lightbend.lagom.scaladsl.persistence.PersistentEntity
import com.lightbend.lagom.serialization.{CompressedJsonable, Jsonable}
import sample.cargotracker.registration.api.Cargo

sealed trait RegistrationCommand extends Jsonable

case class RegisterCargo(cargo: Cargo) extends RegistrationCommand with CompressedJsonable with PersistentEntity.ReplyType[Done]
