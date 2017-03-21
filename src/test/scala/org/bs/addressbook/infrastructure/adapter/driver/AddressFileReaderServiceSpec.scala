package org.bs.addressbook.infrastructure.adapter.driver

import java.time.LocalDate

import org.bs.addressbook.domain.{Male, Person}
import org.bs.addressbook.infrastructure.assembler.PersonAssembler
import org.mockito.Matchers.any
import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{Matchers, WordSpec}

class AddressFileReaderServiceSpec extends WordSpec with Matchers with MockitoSugar {

  private val assemblerMock = mock[PersonAssembler]

  "Address file repository" should {
    "return a success when the file is loaded" in {
      val inputStream = getClass.getClassLoader().getResourceAsStream("AddressBook.txt")
      val repository = new AddressFileReaderRepository(inputStream, assemblerMock)
      when(assemblerMock.toDomain(any[String])).thenReturn(Some(Person("id", "foobar", Male, LocalDate.now())))

      val result = repository.findAll()

      result.isSuccess shouldBe true
      result.get nonEmpty
    }

    "return a failure when the file is not available" in {
      val inputStream = getClass.getClassLoader().getResourceAsStream("Foo.txt")
      val repository = new AddressFileReaderRepository(inputStream, assemblerMock)

      val result = repository.findAll()

      result.isFailure shouldBe true
    }
  }
}