package rj.a00_scala

import org.scalatest._


class Q9_Palindrome_Number extends FunSuite with Matchers {

  test("Palindrom Number") {
    def isPalindrome(x: Int): Boolean = {
        x.toString.reverse == x.toString
    }
  }
}

