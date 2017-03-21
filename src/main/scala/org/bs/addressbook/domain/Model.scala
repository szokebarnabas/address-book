package org.bs.addressbook.domain

import java.time.LocalDate
import java.util.UUID

import scala.util.{Failure, Success}

sealed trait Gender

case object Male extends Gender

case object Female extends Gender

case class Person(val userId: String = UUID.randomUUID().toString,
                  val name: String,
                  val gender: Gender,
                  val birthDate: LocalDate)

class AddressBook(addressService: AddressService) {

  def numberOfMales() : Either[String, Int] = {
    addressService.findAll() match {
      case Success(people) => Right(people.count(_.gender == Male))
      case Failure(t) => Left(t.getMessage)
    }
  }

  def oldestPerson() : String = {
    ???
  }

  def birthDateDiffInDays(first: Person, second: Person) : Int = {
    ???
  }
}