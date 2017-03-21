package org.bs.addressbook.infrastructure.driver

import java.io.InputStream

import org.bs.addressbook.app.AddressEntityAssembler
import org.bs.addressbook.domain.{AddressService, Person}

import scala.io.Source
import scala.util.Try

class AddressFileReaderService(val file: InputStream,
                               val addrAssembler: AddressEntityAssembler) extends AddressService {

  override def findAll(): Try[Iterator[Person]] = {
    Try {
      Source
        .fromInputStream(file)
        .getLines()
        .flatMap(addrAssembler.toDomain)
    }
  }

}

