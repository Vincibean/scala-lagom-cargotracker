package sample.cargotracker.registration.impl

import java.time.LocalDateTime
import sample.cargotracker.registration.api.Cargo
import play.api.libs.json.{Format, Json}

object CargoState {

  implicit val format: Format[CargoState] = Json.format[CargoState]

}

case class CargoState(cargo: Cargo, timeStamp: LocalDateTime)