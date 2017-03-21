package org.bs.addressbook.domain

import org.bs.addressbook.Fixture._
import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{Matchers, WordSpec}

import scala.util.{Failure, Try}

class AddressBookSpec extends WordSpec with Matchers with MockitoSugar {

  "AddressBook" should {

    "count the number of males" in {
      val addressService = mock[AddressRepository]
      when(addressService.findAll()).thenReturn(Try(Seq(JohnDoe, JaneDoe, BillyBob)))
      val addressBook = new AddressBook(addressService)

      val result = addressBook.numberOfMales()

      result shouldBe Right(2)
    }

    "return the error message if an exception was thrown in the male counter" in {
      val addressService = mock[AddressRepository]
      val addressBook = new AddressBook(addressService)
      when(addressService.findAll()).thenReturn(Failure(new RuntimeException("error message")))

      val result = addressBook.numberOfMales()

      result shouldBe Left("error message")
    }

    "return the oldest person from the list" in {
      val addressService = mock[AddressRepository]
      when(addressService.findAll()).thenReturn(Try(Seq(JohnDoe, JaneDoe, BillyBob)))
      val addressBook = new AddressBook(addressService)

      val result = addressBook.oldestPerson()

      result shouldBe Right(BillyBob)
    }

    "returns the error message if an exception was thrown in the oldest person finder" in {
      val addressService = mock[AddressRepository]
      val addressBook = new AddressBook(addressService)
      when(addressService.findAll()).thenReturn(Failure(new RuntimeException("error message")))

      val result = addressBook.oldestPerson()

      result shouldBe Left("error message")
    }

    "find person by name" in {
      val addressService = mock[AddressRepository]
      when(addressService.findAll()).thenReturn(Try(Seq(JohnDoe, JaneDoe, BillyBob)))
      val addressBook = new AddressBook(addressService)

      val result = addressBook.findPersonByName("John Doe")

      result shouldBe Some(JohnDoe)
    }

    "return none when the person is not found" in {
      val addressService = mock[AddressRepository]
      when(addressService.findAll()).thenReturn(Try(Seq(JohnDoe, JaneDoe, BillyBob)))
      val addressBook = new AddressBook(addressService)

      val result = addressBook.findPersonByName("FooBar")

      result shouldBe None
    }
  }
}