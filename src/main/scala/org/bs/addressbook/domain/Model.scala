package org.bs.addressbook.domain

import java.time.LocalDate
import java.util.UUID

sealed trait Gender

case object Male extends Gender

case object Female extends Gender

case class Person(val userId: String = UUID.randomUUID().toString,
                  val name: String,
                  val gender: Gender,
                  val birthDate: LocalDate)

class AddressBook(addressRepository: AddressService) {

  def numberOfMales() : Int = {
    ???
  }

  def oldestPerson() : String = {
    ???
  }

  def birthDateDiffInDays(first: Person, second: Person) : Int = {
    ???
  }
}