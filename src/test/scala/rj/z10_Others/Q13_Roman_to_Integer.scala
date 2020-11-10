package rj.a00_scala

import org.scalatest._


class Q13_Roman_to_Integer extends FunSuite with Matchers {

  /*Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

  Symbol       Value
  I             1
  V             5
  X             10
  L             50
  C             100
  D             500
  M             1000

  For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.

  Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

  I can be placed before V (5) and X (10) to make 4 and 9.
  X can be placed before L (50) and C (100) to make 40 and 90.
  C can be placed before D (500) and M (1000) to make 400 and 900.
  Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.

  Example 1:

  Input: "III"
  Output: 3
  Example 2:

  Input: "IV"
  Output: 4
  Example 3:

  Input: "IX"
  Output: 9
  Example 4:

  Input: "LVIII"
  Output: 58
  Explanation: L = 50, V= 5, III = 3.
  Example 5:

  Input: "MCMXCIV"
  Output: 1994
  Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
   ***Refer solution 2. That is more elegant; Avoids the
   corner case hardcoding like CM, CD, XC, XL */

  test("Solution 1") {

    def romanToInt(s: String): Int = {
      def romanToInt(s: String, returnValue: Int): Int = {
        if (s.isEmpty) returnValue
        else {
          (s.head, s.drop(1).headOption) match {
            case ('M', _) => romanToInt(s.tail, returnValue + 1000)
            case ('D', _) => romanToInt(s.tail, returnValue + 500)
            case ('C', Some('M')) => romanToInt(s.drop(2), returnValue + 900)
            case ('C', Some('D')) => romanToInt(s.drop(2), returnValue + 400)
            case ('C', _) => romanToInt(s.tail, returnValue + 100)
            case ('L', _) => romanToInt(s.tail, returnValue + 50)
            case ('X', Some('C')) => romanToInt(s.drop(2), returnValue + 90)
            case ('X', Some('L')) => romanToInt(s.drop(2), returnValue + 40)
            case ('X', _) => romanToInt(s.tail, returnValue + 10)
            case ('V', _) => romanToInt(s.tail, returnValue + 5)
            case ('I', Some('X')) => romanToInt(s.drop(2), returnValue + 9)
            case ('I', Some('V')) => romanToInt(s.drop(2), returnValue + 4)
            case ('I', _) => romanToInt(s.tail, returnValue + 1)
          }
        }
      }

      romanToInt(s, 0)
    }

  }



  test("Solution 2"){

    def readIndex(x:Char) : Int = {
      x match {
        case 'I' => 1
        case 'V' => 5
        case 'X' => 10
        case 'L' => 50
        case 'C' => 100
        case 'D' => 500
        case 'M' => 1000
        case _ => 0
      }
    }

    def romanToInt(s: String): Int = {
      def worker(l:List[Int] = s.toList.map(readIndex)):Int ={
        l match{
          case Nil => 0
          case s1::s2::ss if s1 >= s2 => s1 + worker(s2::ss)
          case s1::s2::ss if s1 < s2 => s2 - s1 + worker(ss)
          case s1::Nil => s1
        }
      }

      worker()
    }


  }

}

