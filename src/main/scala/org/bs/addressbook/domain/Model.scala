package org.bs.addressbook.domain

import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.UUID

import scala.util.{Failure, Success}

sealed trait Gender

case object Male extends Gender

case object Female extends Gender

case class Person(userId: String = UUID.randomUUID().toString,
                  name: String,
                  gender: Gender,
                  birthDate: LocalDate) {

  def howManyDaysOlderThan(other: Person): Long = ChronoUnit.DAYS.between(other.birthDate, birthDate)
}

class AddressBook(addressRepository: AddressRepository) {

  implicit val localDateOrdering: Ordering[LocalDate] = Ordering.by(_.toEpochDay)

  private val people = addressRepository.findAll()

  def numberOfMales(): Either[String, Int] = {
    people match {
      case Success(list) => Right(list.count(_.gender == Male))
      case Failure(t) => Left(t.getMessage)
    }
  }

  def oldestPerson(): Either[String, Person] = {
    people match {
      case Success(list) => Right(list.minBy(_.birthDate))
      case Failure(t) => Left(t.getMessage)
    }
  }

  def findPersonByName(name: String) : Option[Person] = {
    people match {
      case Success(list) => list.find(_.name == name)
      case Failure(_) => None
    }
  }
}

object AddressBook {
  def apply(addressRepository: AddressRepository): AddressBook = new AddressBook(addressRepository)
}