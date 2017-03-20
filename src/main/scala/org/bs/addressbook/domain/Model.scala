package org.bs.addressbook.domain

import java.time.LocalDate

sealed trait Gender

case object Male extends Gender

case object Female extends Gender

case class AddressEntity(val userId: String, val name: String, val gender: Gender, val birthDate: LocalDate)

class AddressBook(addressRepository: AddressRepository) {

}