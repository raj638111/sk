package rj.a10_Questions.a45_Array

import org.scalatest._


class a05_Product_of_Array_Except_Self_Q238 extends FunSuite with Matchers {

  /*
  Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

  Example:

  Input:  [1,2,3,4]
  Output: [24,12,8,6]
  Constraint: It's guaranteed that the product of the elements of any prefix or suffix of the array (including the whole array) fits in a 32 bit integer.

  Note: Please solve it without division and in O(n).

  Follow up:
  Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
 */

  test("-"){
    productExceptSelf(Array(1,2,3,4))
  }

  def productExceptSelf(nums: Array[Int]): Array[Int] = { // The length of the input array
    val length: Int = nums.length

    // The left and right arrays as described in the algorithm
    val L = new Array[Int](length)
    val R = new Array[Int](length)

    // Final answer array to be returned
    val answer = new Array[Int](length)

    // L[i] contains the product of all the elements to the left
    // Note: for the element at index '0', there are no elements to the left,
    // so L[0] would be 1
    L(0) = 1
    for (i <- 1 until length) { // L[i - 1] already contains the product of elements to the left of 'i - 1'
      // Simply multiplying it with nums[i - 1] would give the product of all
      // elements to the left of index 'i'
      L(i) = nums(i - 1) * L(i - 1)
    }

    // R[i] contains the product of all the elements to the right
    // Note: for the element at index 'length - 1', there are no elements to the right,
    // so the R[length - 1] would be 1
    R(length - 1) = 1
    for (i <- length - 2 to 0 by -1) { // R[i + 1] already contains the product of elements to the right of 'i + 1'
      // Simply multiplying it with nums[i + 1] would give the product of all
      // elements to the right of index 'i'
      R(i) = nums(i + 1) * R(i + 1)
    }
    // Constructing the answer array
    for (i <- 0 until length) { // For the first element, R[i] would be product except self
      // For the last element of the array, product except self would be L[i]
      // Else, multiple product of all elements to the left and to the right
      answer(i) = L(i) * R(i)
    }
    answer
  }

  // Solution 2: This exceeds time limit
  /*def productExceptSelf(nums: Array[Int]): Array[Int] = {
    import scala.collection.mutable._
    println(s"${nums.mkString(",")}")
    val input = ListBuffer[Int]() ++ nums
    val res = for{
      i <- 0 to input.size - 1
      no = input.remove(0)
      product = input.product
      _ = input.append(no)
    }yield {
      product
    }
    println(s"${res.mkString(",")}")
    res.toArray
  }*/

  // Solution 1: Exceeds memory limit
  /*def productExceptSelf(nums: Array[Int]): Array[Int] = {
    println(s"${nums.mkString(",")}")
    val result = for{
      i <- 0 to nums.size - 1
      rest = nums.diff(Array(nums(i)))
      sum = rest.reduce(_ * _)
    }yield {
      sum
    }
    println(s"${result.mkString(",")}")
    result.toArray
  }*/


}

