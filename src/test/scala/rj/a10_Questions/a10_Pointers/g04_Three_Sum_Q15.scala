package rj.a10_Questions.a10_Pointers

import org.scalatest._


class g04_Three_Sum_Q15 extends FunSuite with Matchers {

  /*
     Given an array nums of n integers, are there elements a, b, c in nums
     such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

    Note:

    The solution set must not contain duplicate triplets.

    Example:

    Given array nums = [-1, 0, 1, 2, -1, -4],

    A solution set is:
    [
      [-1, 0, 1],
      [-1, -1, 2]
    ]
    // https://www.geeksforgeeks.org/find-a-triplet-that-sum-to-a-given-value/
  */
  test("Find triplets that sum to 0 ") {
    //threeSum(Array(-1,0,1,2,-1,-4))
    threeSum(Array(-2, 0, 1, 1, 2))
  }

  def threeSum(nums: Array[Int]): List[List[Int]] = {
    import util.control.Breaks._
    val st = nums.sortWith(_ < _)
    var result = Set[List[Int]]()
    breakable {
      for (i <- 0 until nums.size - 2) {
        if(st(i) >= 1){
          break
        }
        var l = i + 1 // Second element index
        var r = nums.size - 1 // Third element index
        while (l < r) {
          val sum = st(i) + st(l) + st(r)
          sum match {
            case _ if sum == 0 =>
              result = result + List(st(i), st(l), st(r))
              l = l + 1
            case _ if sum < 0 =>
              l = l + 1
            case _ if sum > 0 =>
              r = r - 1
          }
        }
      }
    }
    result.toList
  }

}

