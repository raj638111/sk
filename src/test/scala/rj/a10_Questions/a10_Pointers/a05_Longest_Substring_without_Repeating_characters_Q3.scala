package rj.a10_Questions.a10_Pointers

import org.apache.log4j.Logger
import org.scalatest._
import rj.a10_spark.z10_common.Log

class a05_Longest_Substring_without_Repeating_characters_Q3 extends FunSuite with Matchers {

  val log: Logger = Log.getLogger(this.getClass.getName.dropRight(1))

  //*** Got time limit exceeded in LeetCode

  // Given a string, find the length of the longest substring without repeating characters.
  // Example 1:
  //
  //Input: "abcabcbb"
  //Output: 3
  //Explanation: The answer is "abc", with the length of 3.
  //Example 2:
  //
  //Input: "bbbbb"
  //Output: 1
  //Explanation: The answer is "b", with the length of 1.
  //Example 3:
  //
  //Input: "pwwkew"
  //Output: 3
  //Explanation: The answer is "wke", with the length of 3.
  //Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

  test("Good solution by someone"){
    lengthOfLongestSubstring("abcabcbb")
  }

  def lengthOfLongestSubstring(s: String): Int = {
    var maxLen = 0
    /* Define a map where key is the character and element is index */
    /* This way we can update the index to where a character is repeated */
    var chars: Map[Char,Int] = Map.empty
    // start: Start of the window
    // i: Specifies the end of window
    // (i - start) provides the length of non repeating characters
    var start = 0
    for (i <- 0 until s.length){
      if (chars contains s(i)){
        /* If character is repeated, move start to one ahead of repeated character. */
        /* Take max to make sure we dont go backwards: */
        start = math.max(chars(s(i)) + 1,start)
        println("start -> " + start)
      }
      chars += (s(i) -> i)
      maxLen = math.max(i - start + 1, maxLen)
    }
    maxLen
  }



}

