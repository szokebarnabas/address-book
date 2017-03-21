package org.bs.addressbook

import java.time.LocalDate

import org.bs.addressbook.domain.{Female, Male, Person}

object Fixture {
  val JohnDoe = Person("john", "John Doe", Male, LocalDate.of(1984, 5, 12))
  val JaneDoe = Person("jane", "Jane Doe", Female, LocalDate.of(1988, 2, 3))
  val BillyBob = Person("bob", "Billy Bob", Male, LocalDate.of(1978, 4, 3))
  val BillyJane = Person("bjane", "Billy Jane", Male, LocalDate.of(1978, 4, 10))
}
