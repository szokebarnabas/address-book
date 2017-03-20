package org.bs.addressbook.domain

trait AddressRepository {
  def findAll(): Seq[AddressEntity]
}
