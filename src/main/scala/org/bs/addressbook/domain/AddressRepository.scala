package org.bs.addressbook.domain

import scala.util.Try

trait AddressRepository {
  def findAll(): Try[Iterator[Person]]
}
