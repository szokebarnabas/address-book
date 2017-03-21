package org.bs.addressbook.domain

import scala.util.Try

trait AddressService {
  def findAll(): Try[Iterator[Person]]
}
