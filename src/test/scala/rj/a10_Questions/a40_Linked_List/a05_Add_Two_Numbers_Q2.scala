package rj.a10_Questions.a40_Linked_List

import org.apache.log4j.Logger
import org.scalatest._
import rj.a10_spark.z10_common.Log

class a05_Add_Two_Numbers_Q2 extends FunSuite with Matchers {

  val log: Logger = Log.getLogger(this.getClass.getName.dropRight(1))

  //  You are given two non-empty linked lists representing two non-negative integers.
  //  The digits are stored in reverse order and each of their nodes contain a single digit.
  //  Add the two numbers and return it as a linked list.
  //
  //You may assume the two numbers do not contain any leading zero, except the number 0 itself.
  //
  //Example:
  //
  //Input:  [9, 9]
  //        [9, 9, 9]
  //Output: [8, 9, 0, 1]
  //Explanation: 342 + 465 = 807.
  test("2. Add Two Numbers"){
    class ListNode(var _x: Int = 0) {
       var next: ListNode = null
       var x: Int = _x
    }
    def addTwoNumbers(l1: ListNode, l2: ListNode): ListNode = {
      var head: ListNode = null
      var cur: ListNode = null
      var iter1 = l1
      var iter2: ListNode = l2
      var quotient: Int = 0
      var reminder: Int = 0
      import scala.util.Try
      while(iter1 != null || iter2 != null){
        val sum = Try(iter1.x).getOrElse(0)  + Try(iter2.x).getOrElse(0) + quotient
        quotient = sum / 10
        reminder = sum % 10
        if(head == null){
          cur = new ListNode(reminder)
          head = cur
        }else{
          cur.next = new ListNode(reminder)
          cur = cur.next
        }
        iter1 = Try(iter1.next).getOrElse(null)
        iter2 = Try(iter2.next).getOrElse(null)
      }
      if(quotient != 0){
        cur.next = new ListNode(quotient)
      }
      head
    }
  }
}

