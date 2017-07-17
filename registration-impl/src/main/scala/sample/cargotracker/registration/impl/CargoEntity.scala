package sample.cargotracker.registration.impl

import java.time.LocalDateTime

import akka.Done
import com.lightbend.lagom.scaladsl.persistence.PersistentEntity
import sample.cargotracker.registration.api.Cargo

class CargoEntity extends PersistentEntity {

  override type Command = RegistrationCommand
  override type Event = RegistrationEvent
  override type State = CargoState

  override def initialState: CargoState = CargoState(Cargo("", "", "", "", ""), LocalDateTime.now())

  override def behavior: Behavior = {
    case CargoState(cargo, timeStamp) => Actions().onCommand[RegisterCargo, Done]{
      case (RegisterCargo(cargoToRegister), ctx, state) =>
        if (cargoToRegister.name == null || cargoToRegister.name.equals("")) {
          ctx.invalidCommand("Name must be defined")
          ctx.done
        } else {
          ctx.thenPersist(CargoRegistered(entityId, cargoToRegister))(_ => ctx.reply(Done.getInstance()))
        }

    }.onEvent{
      case (CargoRegistered(id, registeredCargo), state) => CargoState(registeredCargo, LocalDateTime.now())
    }
  }
}
