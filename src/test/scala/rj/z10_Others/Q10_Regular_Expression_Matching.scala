package rj.a00_scala

import org.scalatest._


class Q10_Regular_Expression_Matching extends FunSuite with Matchers {

  // Probably not the right solution. Seems like we need to implement the regex our self
  test("Regular_Expression_Matching") {
    def isMatch(s: String, p: String): Boolean = {
      val rgx = p.r
      s match {
        case rgx() => true
        case _ => false
      }
    }
  }
}

