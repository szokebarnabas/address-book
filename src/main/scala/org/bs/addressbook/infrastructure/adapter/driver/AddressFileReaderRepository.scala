package org.bs.addressbook.infrastructure.adapter.driver

import java.io.InputStream

import org.bs.addressbook.domain.{AddressRepository, Person}
import org.bs.addressbook.infrastructure.assembler.PersonAssembler

import scala.io.Source
import scala.util.Try

class AddressFileReaderRepository(val inputStream: InputStream,
                                  val addrAssembler: PersonAssembler) extends AddressRepository {

  override def findAll(): Try[Seq[Person]] = {
    Try {
      Source
        .fromInputStream(inputStream)
        .getLines()
        .flatMap(addrAssembler.toDomain)
        .toSeq
    }
  }

}

