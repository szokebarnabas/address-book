package org.bs.addressbook.infrastructure.adapter.driver

import java.io.InputStream

import org.bs.addressbook.domain.{AddressRepository, Person}
import org.bs.addressbook.infrastructure.assembler.PersonAssembler

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

