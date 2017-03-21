package org.bs.addressbook.integration

import org.bs.addressbook.domain.AddressBook
import org.bs.addressbook.infrastructure.adapter.driver.AddressFileReaderRepository
import org.bs.addressbook.infrastructure.assembler.PersonAssembler
import org.scalatest.{Matchers, WordSpec}


class AddressBookISpec extends WordSpec with Matchers {

  "AddressBook" should {

    val stream = getClass.getClassLoader().getResourceAsStream("AddressBook.txt")
    val addressBook = new AddressBook(
      new AddressFileReaderRepository(inputStream = stream, addrAssembler = new PersonAssembler)
    )

    "find the number of males in the book" in {
      val numberOfMales = addressBook.numberOfMales()

      numberOfMales shouldBe Right(3)
    }

    "find the oldest person in the book" in {
      val oldestPerson = addressBook.oldestPerson()

      oldestPerson.map(_.name) shouldBe Right("Wes Jackson")
    }

    "return the difference in days of the birth day of Bill and Paul" in {
      val bill = addressBook.findPersonByName("Bill McKnight")
      val paul = addressBook.findPersonByName("Paul Robinson")

      val diff = bill.map(_.howManyDaysOlderThan(paul.get))

      diff shouldBe Some(-2862)

    }

  }

}
