package org.bs.addressbook.domain

import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.UUID

import scala.util.{Failure, Success}

sealed trait Gender

case object Male extends Gender

case object Female extends Gender

case class Person(val userId: String = UUID.randomUUID().toString,
                  val name: String,
                  val gender: Gender,
                  val birthDate: LocalDate) {

  def howManyDaysOlderThan(other: Person): Long = ChronoUnit.DAYS.between(other.birthDate, birthDate)
}

class AddressBook(addressService: AddressService) {

  implicit val localDateOrdering: Ordering[LocalDate] = Ordering.by(_.toEpochDay)

  def numberOfMales(): Either[String, Int] = {
    addressService.findAll() match {
      case Success(people) => Right(people.count(_.gender == Male))
      case Failure(t) => Left(t.getMessage)
    }
  }

  def oldestPerson(): Either[String, Person] = {
    addressService.findAll() match {
      case Success(people) => Right(people.minBy(_.birthDate))
      case Failure(t) => Left(t.getMessage)
    }
  }
}