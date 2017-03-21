package org.bs.addressbook.app

import java.time.LocalDate

import org.bs.addressbook.domain.{Female, Gender, Male}
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.prop.TableDrivenPropertyChecks._

class AddressEntityAssemblerSpec extends WordSpec with Matchers {

  private val rowsWithCorrectData =
    Table(
      ("row", "expectedName", "expGender", "expBirthDate"),
      ("Bill McKnight, Male, 16/03/77", "Bill McKnight", Male, LocalDate.of(1977, 3, 16)),
      ("Sarah Stone, Female, 20/09/80", "Sarah Stone", Female, LocalDate.of(1980, 9, 20)),
      ("John Doe, Male, 14/08/15", "John Doe", Male, LocalDate.of(2015, 8, 14))
    )

  private val rowsWithIncorrectData =
    Table(
      ("row", "expectedName", "expGender", "expBirthDate"),
      ("Bill McKnight, ??, 16/03/77", "Bill McKnight", Male, LocalDate.of(1977, 3, 16)),
      ("Sarah Stone, Female, 1980/09/20", "Sarah Stone", Female, LocalDate.of(1980, 9, 20)),
      ("Sarah Stone, Female, 1980/09/20, foobar", "Sarah Stone", Female, LocalDate.of(1980, 9, 20))
    )

  "Address assembler" should {
    "successfully create Person objects from the input string" in {
      val assembler = new AddressEntityAssembler

      forAll(rowsWithCorrectData) { (row: String, expectedName: String, expGender: Gender, expBirthDate: LocalDate) =>
        val result = assembler.toDomain(row)

        result.isDefined shouldBe true
        result.get should have(
          'name (expectedName),
          'gender (expGender),
          'birthDate (expBirthDate)
        )
      }
    }

    "return none for invalid rows" in {
      val assembler = new AddressEntityAssembler

      forAll(rowsWithIncorrectData) { (row: String, _: String, _: Gender, _: LocalDate) =>
        val result = assembler.toDomain(row)

        result.isDefined shouldBe false
      }
    }
  }
}
