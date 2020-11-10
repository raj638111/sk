package rj.a10_Questions.a10_Pointers

import org.scalatest._


class c06_Container_With_Most_Water_Q11 extends FunSuite with Matchers {

  test("Container_With_Most_Water") {
    // Solution understood
  }


  //Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.
  //
  //Note: You may not slant the container and n is at least 2.
  //
  //
  //
  //
  //
  //The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
  //
  //
  //
  //Example:
  //
  //Input: [1,8,6,2,5,4,8,3,7]
  //Output: 49
  import scala.annotation.tailrec
  import scala.math.min

  def maxArea(height: Array[Int]): Int = {

    @tailrec
    def traverse(i: Int, j: Int, maxArea: Int): Int = {
      if(i >= j) {
        maxArea
      } else {
        val area = (j - i) * min(height(i), height(j))
        val newMax = if(area > maxArea) area else maxArea
        val (newI, newJ) = if(height(i) < height(j)) (i + 1, j) else (i, j - 1)
        traverse(newI, newJ, newMax)
      }
    }

    traverse(0, height.length - 1, 0)
  }
}