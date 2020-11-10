package rj.a00_scala

import org.scalatest._


class Q7_Reverse_Integer extends FunSuite with Matchers {

  test("Reverse_Integer"){
    def reverse(x: Int): Int = {
      import scala.util.Try
      val rgx = "^[-]{1}(.*)$".r
      x.toString match {
        case rgx(no) =>
          Try(s"-${no.reverse}".toInt).getOrElse(0)
        case positiveNo: String =>
          Try(positiveNo.reverse.toInt).getOrElse(0)
      }
    }
    println(reverse(5))
    println(reverse(-5))
    println(reverse(-234))
    println(reverse(756))
  }
}

