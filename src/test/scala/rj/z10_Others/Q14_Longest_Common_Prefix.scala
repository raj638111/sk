package rj.a00_scala

import org.scalatest._

import scala.collection.mutable.ListBuffer
import scala.util.Try


class Q14_Longest_Common_Prefix extends FunSuite with Matchers {

  // Write a function to find the longest common prefix string amongst an array of strings.
  //
  //If there is no common prefix, return an empty string "".
  //
  //Example 1:
  //
  //Input: ["flower","flow","flight", "some", "said"]
  //Output: "fl"
  //Example 2:
  //
  //Input: ["dog","racecar","car"]
  //Output: ""
  //Explanation: There is no common prefix among the input strings.
  //Note:
  //
  //All given inputs are in lowercase letters a-z.

  test("") {

    // Solution by user
    def longestCommonPrefix(strs: Array[String]): String = {

      def longestCommonPrefix(index: Int, returnValue: String): String = {
        val distinctValues: Array[Option[Char]] = strs
          .map(value => {
            if (index >= value.length) None
            else Some(value(index))
          })
          .distinct

        if (distinctValues.contains(None)) returnValue
        else if (distinctValues.length > 1) returnValue
        else longestCommonPrefix(index + 1, returnValue + distinctValues(0).get)
      }

      if (strs.length == 0) ""
      else longestCommonPrefix(0, "")
    }

    //--------
    assert(longestCommonPrefix(Array("flower","flow","flight", "sl", "slight")) == "")
    assert(longestCommonPrefix(Array("flower","flow","flight")) == "fl")
  }


}

