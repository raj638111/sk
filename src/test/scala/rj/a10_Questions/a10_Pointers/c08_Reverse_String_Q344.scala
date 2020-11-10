package rj.a10_Questions.a10_Pointers

import org.scalatest.FunSuite

class c08_Reverse_String_Q344 extends FunSuite{

  /*
  Write a function that reverses a string. The input string is given as an array of
  characters char[].

  Do not allocate extra space for another array, you must do this by modifying
  the input array in-place with O(1) extra memory.

  You may assume all the characters consist of printable ascii characters.



  Example 1:

  Input: ["h","e","l","l","o"]
  Output: ["o","l","l","e","h"]
  Example 2:

  Input: ["H","a","n","n","a","h"]
  Output: ["h","a","n","n","a","H"]

  5/2 = 2
  4/2 = 2

   */

  test("-"){
    reverseString(Array('h','e','l','l','o'));
  }

  def reverseString(s: Array[Char]): Unit = {
    val quotient = s.size / 2
    val size = s.size
    var char: Option[Char] = None;
    var revOffset = 0;
    //println(s.mkString(","))
    for(offset <- 0 until quotient){
      revOffset = size - offset - 1
      char = Some(s(offset))
      s(offset) = s(revOffset)
      s(revOffset) = char.get
    }
    //println(s.mkString(","))
  }


}
