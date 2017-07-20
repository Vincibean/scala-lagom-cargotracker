package sample.cargotracker.shipping.impl

import com.google.inject.AbstractModule
import sample.cargotracker.registration.api.RegistrationService
import sample.cargotracker.shipping.api.ShippingService

class ShippingServiceModule extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[ShippingService]).to(classOf[ShippingServiceImpl])
    bind(classOf[RegistrationService])
  }
}
