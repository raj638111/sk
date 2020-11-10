package rj.a10_Questions.a55_Stack

import org.scalatest._

import scala.collection.mutable


class a05_Daily_Temperatures_Q739 extends FunSuite with Matchers {

  test("1"){
    /*Given a list of daily temperatures T, return a list such that, for each
    day in the input, tells you how many days you would have to wait until a
    warmer temperature. If there is no future day for which this is possible, put 0 instead.

    For example, given the list of temperatures T = [73, 74, 75, 71, 69,
    72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].

    Note: The length of temperatures will be in the range [1, 30000]. Each
    temperature will be an integer in the range [30, 100].*/
    println(dailyTemperatures(Array(73, 74, 75, 71, 69, 72, 76, 73)).mkString(","))
  }

  def dailyTemperatures(t: Array[Int]): Array[Int] = {
    println(s"${t.mkString(",")}")
    val zipped = t.zipWithIndex.toList
    recur(zipped).toArray
  }

  def recur(sorted: List[(Int, Int)]): List[Int] = {
    sorted match {
      case Nil => Nil
      case _ :: Nil =>
        0 :: Nil
      case n1 :: rest =>
        val days = rest.collectFirst{
          case (temp, index) if temp > n1._1 => index - n1._2
        }.getOrElse(0)
        days :: recur(rest)
    }
  }

  test("2"){
    //val input = Array(73, 74, 75, 71, 69, 72, 76, 73)
    //val input = Array(73, 69, 71, 74)
    val input = Array(73,75,74)
    val res = dailyTemperaturesV2(input)
    println(input.zip(res).mkString(","))
  }

  def dailyTemperaturesV2(temperatures: Array[Int]): Array[Int] = {
    val m: Int = temperatures.length
    val ans = new Array[Int](m)
    val stack = new mutable.Stack[Int]()
    println(temperatures.mkString(","))
    for (i <- 0 until m) {
      println(s"\nindex -> $i, value -> ${temperatures(i)}")
      while (!stack.isEmpty && temperatures(stack.top) < temperatures(i)) {
        println(s"ans(${stack.top}) -> ${i - stack.top}")
        ans(stack.top) = i - stack.pop
      }
      stack.push(i)
      stack.foreach(println _)
    }
    ans
  }

}

