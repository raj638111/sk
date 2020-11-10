package rj.a10_Questions.a20_Map

import org.apache.log4j.Logger
import org.scalatest._
import rj.a10_spark.z10_common.Log

class a05_Two_Sum_Q1 extends FunSuite with Matchers {

  val log: Logger = Log.getLogger(this.getClass.getName.dropRight(1))

  //  Given an array of integers, return indices of the two numbers such that they
  //  add up to a specific target. You may assume that each input would have exactly one solution,
  //  and you may not use the same element twice.
  //    Example:
  //    Given nums = [2, 7, 11, 15], target = 9,
  //    Because nums[0] + nums[1] = 2 + 7 = 9,
  //    return [0, 1].
  test("Two Sum"){
    def twoSum(nums: Array[Int], target: Int): Array[Int] = {
      // Store the number -> index in a map
      println("Input -> " + nums.mkString(","))
      val numberNIndex = nums.zipWithIndex.map(x => (x._1, x._2)).toMap
      println("numberNIndex -> " + numberNIndex)
      val res = nums.zipWithIndex.foldLeft((-1, -1)){ case (partRes, (number, index)) =>
        println(s"number -> $number, index -> $index")
        val shortOff = target - number
        println(s"I am short off -> $shortOff")
        numberNIndex.get(shortOff) match {
          case Some(anotherIndex) if index == anotherIndex =>
            println(s"Not comparing the same number: $target")
            partRes
          case Some(anotherIndex) =>
            println(s"Awesome. We have found the answer! $index, $anotherIndex")
            return Array(index, anotherIndex)
          case None =>
            println("No answer found. Keep moving...")
            partRes
        }
      }
      throw new Exception(s"Not possible to get that sum $target. Bye!")
    }
    twoSum(Array(3, 3), 6) should contain theSameElementsAs Array(0, 1)
  }


  test("Two Sum 2: Using procedural programming"){
    def twoSum(nums: Array[Int], target: Int): Array[Int] = {
      println("Input -> " + nums.mkString(","))
      val numberNIndex = nums.zipWithIndex.map(x => (x._1, x._2)).toMap
      println("numberNIndex -> " + numberNIndex)
      var index = 0
      var res = Array[Int]()
      while(index != -1){
        // Get number from index
        val num = nums(index)
        println("Number = " + num)
        val required = target - num
        println("required = " + required)
        numberNIndex.get(required) match {
          case Some(requiredIndex) if index != requiredIndex =>
            println("Required available in index " + requiredIndex)
            res = Array(index, requiredIndex)
            index = -1
          case Some(requiredIndex) if index == requiredIndex =>
            println(".Cannot use the same index")
            index = index + 1
            if(index == nums.size) {
              println(".End of index")
              index = -1
            }
          case None =>
            println("Required not available")
            index = index + 1
            if(index == nums.size) {
              println("End of index")
              index = -1
            }
        }
      }
      res
    }
    //twoSum(Array(3, 3), 6) should contain theSameElementsAs Array(0, 1)
  }


}

