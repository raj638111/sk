package rj.a10_Questions.a10_Pointers

import org.scalatest._


class g06_Three_Sum_Closest_Q16 extends FunSuite with Matchers {

  /*Given an array nums of n integers and an integer target,
  find three integers in nums such that the sum is closest to target.
  Return the sum of the three integers. You may assume that each input
  would have exactly one solution.

    Example:

    Given array nums = [-1, 2, 1, -4], and target = 1.

  The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
    */
  test("Sorted test"){
    //println(threeSumClosest(Array(1, 1, 1, 0), 100))
    //println(threeSumClosest(Array(-3,-2,-5,3,-4), -1))
  }

  import util.control.Breaks._
  def threeSumClosest(nums: Array[Int], target: Int): Int = {
    val st = nums.sortWith(_ < _)
    var closest: Option[Int] = None
    breakable {
      for (i <- 0 until nums.size - 2) {
        var l = i + 1 // Second element index
        var r = nums.size - 1 // Third element index
        while (l < r) {
          val sum = st(i) + st(l) + st(r)
          if(closest == None){
            closest = Some(sum)
          }
          if(target == sum) {
            closest = Some(target)
            break;
          }
          if((target - sum).abs < (target - closest.get).abs) {
            closest = Some(sum)
          }
          if(sum > target){
            r = r - 1
          }else {
            l = l + 1
          }
        }
      }
    }
    closest.get
  }



}

