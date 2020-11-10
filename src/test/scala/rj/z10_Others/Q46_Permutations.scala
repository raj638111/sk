package rj.a00_scala

import org.scalatest._

/*
Given a collection of distinct integers, return all possible permutations.

Example:

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
 */
class Q46_Permutations extends FunSuite with Matchers {

  test("---") {
    def permute(nums: Array[Int], space: String = ""): List[List[Int]] = {
      if (nums.length == 1) {
        println(s"${space}Size = 1: nums.toList -> " + nums.toList.mkString(","))
        List(nums.toList)
      }
      else if (nums.length == 2) {
        val lst = List(nums.toList, nums.reverse.toList)
        println(s"${space}Size = 2: num.toList -> " + lst.toList.mkString(","))
        lst
      }
      else {
        (for {num <- nums.toList
              _ = println()
              _ = println(s"${space}num -> " + num)
              rest = nums.diff(Array(num)) // https://stackoverflow.com/a/5640727
              _ = println(s"${space}rest -> " + rest.mkString(","))
              prev = permute(rest, s"$space  ")
              _ = println(s"${space}prev -> " + prev.mkString(","))
              res = prev.map(List(num) ++ _)
              _ = println(s"${space}res -> " + res.mkString(","))
              } yield res).flatten
      }
    }

    permute(Array(1, 2, 3, 4)).foreach(println _)
  }


  /* Analysis of the above solution
    Input = 1, 2, 3, 4
    List(1, 2, 3, 4)
    List(1, 2, 4, 3)
    List(1, 3, 2, 4)
    List(1, 3, 4, 2)
    List(1, 4, 2, 3)
    List(1, 4, 3, 2)
    List(2, 1, 3, 4)
    List(2, 1, 4, 3)
    List(2, 3, 1, 4)
    List(2, 3, 4, 1)
    List(2, 4, 1, 3)
    List(2, 4, 3, 1)
    List(3, 1, 2, 4)
    List(3, 1, 4, 2)
    List(3, 2, 1, 4)
    List(3, 2, 4, 1)
    List(3, 4, 1, 2)
    List(3, 4, 2, 1)
    List(4, 1, 2, 3)
    List(4, 1, 3, 2)
    List(4, 2, 1, 3)
    List(4, 2, 3, 1)
    List(4, 3, 1, 2)
    List(4, 3, 2, 1)
   */













  /*
  1, [2, 3]
  ---------
  1, 2, 3
  1, 3, 2

  2, [1, 3]
  ----------
  2, 1, 3
  2, 3, 1

  3, [1, 2]
  -----------
  3, 1, 2
  3, 2, 1
 */

  test("-"){
    def permute(nums: Array[Int]): List[List[Int]] = {
      nums.size match {
        case 1 => List(nums.toList)
        case _ =>
          val zipped = nums.zipWithIndex.toList
          val res = zipped.flatMap{ case bind @ (no, index) =>
            var buffer = zipped.filter(_._2 != index).toBuffer
            val size = buffer.size
            println("Size -> " + size)
            (0 to (size -1)) map { _ =>
              val res = bind :: buffer.toList
              val pop = buffer.remove(0)
              buffer.append(pop)
              res
            }
          }
          res.map(_.map(_._1)) match {
            case res =>
              res.foreach(println _)
              res
          }
      }
    }

    permute(Array(1, 2, 3))
  }




}

