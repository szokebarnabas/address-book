package org.bs.addressbook.domain

import org.bs.addressbook.Fixture._
import org.scalatest.{Matchers, WordSpec}

class PersonSpec extends WordSpec with Matchers {

  "Person" should {
    "calculate a negative day diff value if the other person is younger" in {
      val result = BillyBob howManyDaysOlderThan BillyJane

      result shouldBe -7
    }

    "calculate a positive day diff value if the other person is older" in {
      val result = JaneDoe howManyDaysOlderThan BillyJane

      result shouldBe 3586
    }
  }


}
