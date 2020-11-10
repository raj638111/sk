package rj.a00_scala

import org.scalatest._


class x10_Change_Case extends FunSuite with Matchers {

  test("-") {
    val res = recur("abc2".toList)
    println("Result...")
    res.foreach(println _)
  }

  def recur(str: List[Char]): List[String] = {
    val regex = "[a-zA-Z]{1}"
    str match {
      case char :: Nil =>
        println("char (Nil) -> " + char)
        isNum(char) match {
          case false =>
            List(char.toString.toUpperCase, char.toString.toLowerCase)
          case true =>
            List(char.toString)
        }
      case char :: rest =>
        println("char (rest) ->" + char)
        isNum(char) match {
          case false =>
            List(char.toLower, char.toUpper) flatMap { cased =>
              println("Recursive -> " + cased)
              recur(rest).map { res =>
                cased.toString + res
              }
            }
          case true =>
            List(char.toString)
        }
    }
  }

  def isNum(char: Char): Boolean = {
    val regex = "[a-zA-Z]{1}".r
    char.toString match {
      case regex() => false
      case _ => true
    }
  }

}

