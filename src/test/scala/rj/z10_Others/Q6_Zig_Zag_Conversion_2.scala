package rj.a00_scala

import org.scalatest._



class Q6_Zig_Zag_Conversion_2 extends FunSuite with Matchers {

  //The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows
  // like this: (you may want to display this pattern in a fixed font for better legibility)
  //
  //P   A   H   N
  //A P L S I I G
  //Y   I   R
  //And then read line by line: "PAHNAPLSIIGYIR"
  //
  //Write the code that will take a string and make this conversion given a number of rows:
  //
  //string convert(string s, int numRows);
  //Example 1:
  //
  //Input: s = "PAYPALISHIRING", numRows = 3
  //Output: "PAHNAPLSIIGYIR"
  //Example 2:
  //
  //Input: s = "PAYPALISHIRING", numRows = 4
  //Output: "PINALSIGYAHRPI"
  //Explanation:
  //
  //P     I    N
  //A   L S  I G
  //Y A   H R
  //P     I

  /*
    Approach: If numRows = 3, then we create 3 ListBuffer and start storing
    each character in the input string `s` in each ListBuffer in a zig zag
    way
    here, bookMark: (Int, Boolean)
      Int -> Is used to identify the list buffer to store the character
      Boolean -> Is used to control the zig zag direction
   */

  test("Zig_Zag_String"){

    def convert(s: String, numRows: Int) = {
      import scala.collection.mutable.ListBuffer
      var bookMark: (Int, Boolean) = (0, true)
      val result = (1 to numRows).toList.map{_ =>
        println("hello")
        ListBuffer[Char]()
      }
      println("result.size" -> result.size)
      s.foreach { char: Char =>
        result(bookMark._1).append(char)
        numRows == 1 match {
          case true =>
          case false =>
            bookMark._2 == true match {
              case true =>
                bookMark._1 + 1 == numRows match {
                  case true =>
                    bookMark = (bookMark._1 - 1, false)
                  case false =>
                    bookMark = (bookMark._1 + 1, true)
                }
              case false =>
                bookMark._1 - 1 < 0 match {
                  case true =>
                    bookMark = (bookMark._1 + 1, true)
                  case false =>
                    bookMark = (bookMark._1 - 1, false)
                }
            }
        }
      }
      result.flatMap(_.toList).mkString("")
    }
    println(convert("AB", 1))
  }



}

