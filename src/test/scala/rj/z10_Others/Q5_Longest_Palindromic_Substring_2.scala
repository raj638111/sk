package rj.a00_scala

import org.apache.log4j.Logger
import org.scalatest._
import rj.a10_spark.z10_common.Log

import scala.collection.mutable.ListBuffer

class Q5_Longest_Palindromic_Substring_2 extends FunSuite with Matchers {


  // https://www.youtube.com/watch?v=y2BD4MJqV20
  // Using left <- | -> right pointers from center

  /*
  Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

  Example 1:

  Input: "babad"
  Output: "bab"
  Note: "aba" is also a valid answer.
  Example 2:

  Input: "cbbd"
  Output: "bb"
  */

  test("Longest_Palindromic_Substring") {
    println(longestPalindrome("babar"))
  }


  // Got memory limit exceeded for one of the input...but still a valid answer
  def longestPalindrome(s: String): String = {
    import scala.collection.mutable.ListBuffer
    import scala.util.Try
    var palindromes = ListBuffer[String]()
    s.foldLeft(List[String]()) { (holder: List[String], char: Char) =>
      char.toString :: holder.map { elem: String =>
        elem + char
      } match {
        case holder =>
          val newlyIdentifiedPm = holder.filter { elem =>
            elem == elem.reverse
          }
          palindromes.appendAll(newlyIdentifiedPm)
          holder
      }
    }
    Try(palindromes.maxBy(_.size)).getOrElse("")
  }


  /*
    babar
    [b]
      holder = b
      palindromes = []

    [a]
      holder = ba
                a
      palindromes = []

     [b]
      holder = bab
                ab
                 b
      palindromes = [bab]

      [a]
      holder = baba
                aba
                 ba
                  a
      palindromes = [bab][aba]

      [r]
      holder = babar
             =  abar
             =   bar
             =    ar
             =     r


   */


}

