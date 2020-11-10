package rj.a00_scala

import org.scalatest._

import scala.collection.mutable.ListBuffer
import scala.util.Try

/*
  Asked in Udemy
 */
class x15_Sum_of_3_prev_digits extends FunSuite with Matchers {

  test("-") {
    getSum(List(1, 1, 1), 7)
  }

  def getSum(inits: List[Int], count: Int): Unit = {
    val result = ListBuffer[Int]() ++ inits
    (1 to (count - 3)) foreach { _ =>
      result.append(getLast3Sum(result))
    }
    println(result.mkString(","))
  }

  def getLast3Sum(lst: ListBuffer[Int]): Int = {
    val size = lst.size
    val sum  = lst.takeRight(3).reduce(_ + _)
    println(s"Sum = $sum")
    sum
  }

  /*
    Output
    1,1,1,3,5,9,17
   */

}

