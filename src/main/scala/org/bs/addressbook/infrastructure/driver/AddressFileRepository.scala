package org.bs.addressbook.infrastructure.driver

import org.bs.addressbook.app.Dto.AddressRow
import org.bs.addressbook.domain.{AddressEntity, AddressRepository}

class AddressFileRepository(addrAssembler: AddressRow => AddressEntity) extends AddressRepository {
  override def findAll(): Seq[AddressEntity] = ???
}
