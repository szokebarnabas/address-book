package org.bs.addressbook.infrastructure.assembler

import java.time.LocalDate
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField

import org.bs.addressbook.domain.{Female, Male, Person}

import scala.util.{Failure, Success, Try}

class PersonAssembler {

  def toDomain(row: String): Option[Person] = {
    Try {
      row.split(", ") match {
        case Array(nameOfPerson, gender, birthDate) =>

          val formatter = new DateTimeFormatterBuilder()
            .appendPattern("dd/MM/")
            .appendValueReduced(ChronoField.YEAR_OF_ERA, 2, 2, LocalDate.now().minusYears(80))
            .toFormatter()

          val gen = gender match {
            case "Male" => Male
            case "Female" => Female
          }

          Person(name = nameOfPerson, gender = gen, birthDate = LocalDate.parse(birthDate, formatter))

      }
    } match {
      case Success(person) => Some(person)
      case Failure(_) => None
    }
  }

}
