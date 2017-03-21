package org.bs.addressbook.infrastructure.driver

import java.io.InputStream

import org.bs.addressbook.app.PersonAssembler
import org.bs.addressbook.domain.{AddressRepository, Person}

import scala.io.Source
import scala.util.Try

class AddressFileReaderRepository(val file: InputStream,
                                  val addrAssembler: PersonAssembler) extends AddressRepository {

  override def findAll(): Try[Iterator[Person]] = {
    Try {
      Source
        .fromInputStream(file)
        .getLines()
        .flatMap(addrAssembler.toDomain)
    }
  }

}

