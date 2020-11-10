package rj.a10_Questions.d20_Dynamic_Programming

import org.scalatest._


/*
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

Example:

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:

Although the above answer is in lexicographical order, your answer
could be in any order you want.
 */
class a08_Letter_Combination_of_Phone_Number_Q17 extends FunSuite with Matchers {


  test("-"){
    println(letterCombinations("234"))
  }

  def letterCombinations(digits: String): List[String] = {
    val dny = Map(
      2 -> List('a', 'b', 'c'),
      3 -> List('d', 'e', 'f'),
      4 -> List('g', 'h', 'i'),
      5 -> List('j', 'k', 'l'),
      6 -> List('m', 'n', 'o'),
      7 -> List('p', 'q', 'r', 's'),
      8 -> List('t', 'u', 'v'),
      9 -> List('w', 'x', 'y', 'z'))

    val keyCombos = digits.map{ char: Char =>
      println("char -> " + char)
      dny.get(char.toString.toInt) match {
        case bnd @ Some(lst: List[Char]) => bnd
        case _ => None
      }
    }.filter(_ != None).map(_.get).toList
    println("keyComobos -> " + keyCombos)
    recur(keyCombos)
  }

  def recur(lst: List[List[Char]], str: String = ""): List[String] = {
    lst match {
      case head :: Nil =>
        println("Last nesting")
        head.map{ char =>
          s"${str}${char}"
        }
      case head :: tail =>
        head.flatMap { char: Char =>
          recur(tail, s"${str}${char}")
        }
      case Nil =>
        println("This is Nil")
        List()
    }
  }



}

