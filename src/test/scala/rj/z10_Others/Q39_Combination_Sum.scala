package rj.a00_scala

import org.scalatest._


class Q39_Combination_Sum extends FunSuite with Matchers {

  /*
      Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

      The same repeated number may be chosen from candidates unlimited number of times.

      Note:

      All numbers (including target) will be positive integers.
      The solution set must not contain duplicate combinations.
      Example 1:

      Input: candidates = [2,3,6,7], target = 7,
      A solution set is:
      [
        [7],
        [2,2,3]
      ]
      Example 2:

      Input: candidates = [2,3,5], target = 8,
      A solution set is:
      [
        [2,2,2,2],
        [2,3,3],
        [3,5]
      ]



  // 7 / 1 = 7
  // 7 / 2 = 3 -> 1
  // 7 / 3 = 2 -> 1
  // 7 / 4 = 1 => 3
  // 7 / 5 = 1 = 2
  // 7 / 6 = 1 = 1
  // 7 / 7 = 1

   */
  test("-"){
    combinationSum(Array(2,3,6,7), 7)
  }

  def combinationSum(candidates: Array[Int], target: Int): Unit = {
    def recur(target: Int, space: String = ""): List[List[Int]] = {
      (1 to target).toList.flatMap { divisor =>
        val (quo, rem) = (target / divisor, target % divisor)
        println()
        println(s"${space}target -> $target, divisor -> $divisor, quo -> $quo, rem -> $rem")
        rem == 0 match {
          case true =>
            val lst = List((1 to quo).toList map (_ => divisor))
            println(s"${space}lst -> ${lst.mkString(",")}")
            lst
          case false =>
            recur(quo * divisor, s"${space}  ").map{ lst: List[Int] =>
              val res = rem :: lst
              println(s"${space}lst. -> ${res.mkString(",")}")
              res
            }
        }
      }
    }
    val res = recur(target)
    println("Size -> " + res.size)
    res.foreach(x => println("Res -> " + x.mkString(",")))
    val res2 = candidates.toSet match {
      case candidates =>
        res.map(_.sortWith(_ < _)).filter{ lst =>
          lst.filter{ no =>
            candidates.contains(no)
          }.size == lst.size
        }.distinct
    }
    res2.foreach(x => println("Res 2 -> " + x))
  }


}


