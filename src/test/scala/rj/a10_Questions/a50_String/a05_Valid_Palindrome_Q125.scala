package rj.a10_Questions.a50_String

import org.scalatest._


class a05_Valid_Palindrome_Q125 extends FunSuite with Matchers {

  /*
  Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

  Note: For the purpose of this problem, we define empty string as valid palindrome.

  Example 1:

  Input: "A man, a plan, a canal: Panama"
  Output: true
  Example 2:

  Input: "race a car"
  Output: false
   */
  test("--"){
    isPalindrome("A man, a plan, a canal: Panama")
  }

  def isPalindrome(s: String): Boolean = {
    val regex = "[^a-zA-Z0-9]"
    val result = s.replaceAll(regex, "").toLowerCase
    println("Result -> " + result)
    result == result.reverse
  }


}

