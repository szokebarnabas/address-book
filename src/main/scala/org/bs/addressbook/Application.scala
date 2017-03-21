package org.bs.addressbook

import org.bs.addressbook.app.AddressAppService

object Application extends App {

  val appService = new AddressAppService

  appService.numberOfMales()

  appService.oldestPerson()

}
