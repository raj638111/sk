package rj.a00_scala

import org.scalatest._


class z00_Flatten_Nested_Structure extends FunSuite with Matchers {

  test("Flatten Nested structure") {
    def flatten(input: Array[_]): Array[Int] = {
      input.flatMap{ element =>
        element match {
          case x: Int =>
            List(x)
          case x: Array[_] =>
            flatten(x)
        }
      }
    }
    val input = Array(1, 2, Array(3, 4, Array(9, Array(5, 6), 10)))
    val res = flatten(input)
    res.foreach(x => println("Res -> " + x))
  }
}

