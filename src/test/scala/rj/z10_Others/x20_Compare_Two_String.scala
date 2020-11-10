package rj.a00_scala

import org.scalatest._

import scala.collection.mutable.ListBuffer

/*
  Asked in Udemy
 */
class x20_Compare_Two_String extends FunSuite with Matchers {

  /**
   * Return false if there is more than 1 difference
   * Return true if there is less than 1 difference
   */
  test("-") {
    println(compare("abc", "abcdd"))
  }


  def compare(str1: String, str2: String): Boolean = {
    if(Math.abs(str1.size - str2.size) >= 2) {
      false
    }else {
      val size = str1.size > str2.size match {
        case true => str1.size
        case _ => str2.size
      }
      println("Max size is " + size)
      var index = 0
      var diffCount = 0
      while(index < size){
        import scala.util.Try
        val isEqual = Try(str1(0) == str2(0)).getOrElse(false)
        if(isEqual == true){
        }else {
          diffCount = diffCount + 1
          if(diffCount >= 2){
            println("Difference is greater than or equal to 2")
            index = size
          }
        }
        index = index + 1
      }
      diffCount <= 2
    }
  }

}

