package rj.a00_scala

import org.scalatest._


class Q20_Valid_Parentheses extends FunSuite with Matchers {

  /*
    Given a string containing just the characters '(', ')', '{', '}', '[' and ']',
    determine if the input string is valid.

    An input string is valid if:

    Open brackets must be closed by the same type of brackets.
    Open brackets must be closed in the correct order.
    Note that an empty string is also considered valid.
   */

  // This is not working for some cases because of my misunderstanding with
  // the output. But this trait based approach is good enough to solve the issue
  test("-"){

    def isValid(s: String): Boolean = {
      val map = Map(
        '(' -> ')',
        '[' -> ']',
        '{' -> '}')
      val status = s.foldLeft(ExpectOpen: Status){ (status, char)=>
        println("char -> " + char)
        println("status -> " + status)
        status match {
          case ExpectOpen =>
            map.get(char) match {
              case Some(expecting) =>
                println("Expecting -> " + expecting)
                ExpectOpenOrClose(List(expecting))
              case None =>
                println("Not found in key. Invalid")
                Invalid
            }
          case ExpectOpenOrClose(expectList: List[Char]) =>
            char == expectList.head match {
              case true =>
                println(s"This is closing: $char, ${expectList.head}")
                expectList.tail.size >= 1 match {
                  case true =>
                    ExpectClose(expectList.tail)
                  case false =>
                    ExpectOpen
                }
              case false =>
                println("This is opening")
                map.get(char) match {
                  case Some(expecting) =>
                    println("Expecting -> " + expecting)
                    ExpectOpenOrClose(expecting :: expectList)
                  case None =>
                    println(".Not found in Key. Invalid")
                    Invalid
                }

            }
          case ExpectClose(expectList) =>
            char == expectList.head match {
              case true =>
                expectList.tail.size match {
                  case size if size >= 1 =>
                    ExpectClose(expectList.tail)
                  case 0 =>
                    ExpectOpen
                }
              case false =>
                Invalid
            }
          case Invalid => Invalid
        }
      }
      status match {
        case ExpectOpen => true
        case _ => false
      }

    }

    trait Status
    object ExpectOpen extends Status
    case class ExpectOpenOrClose(lst: List[Char]) extends Status
    case class ExpectClose(lst: List[Char]) extends Status
    object Invalid extends Status

    //println(isValid("()"))
    println(isValid("([)]"))
  }


}

