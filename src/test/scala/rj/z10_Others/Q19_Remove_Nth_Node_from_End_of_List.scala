package rj.a00_scala.h10_Others

import org.scalatest._


class Q19_Remove_Nth_Node_from_End_of_List extends FunSuite with Matchers {

  /*
    Given a linked list, remove the n-th node from the end of list and return its head.

    Example:

    Given linked list: 1->2->3->4->5, and n = 2.

    After removing the second node from the end, the linked list becomes 1->2->3->5.
    Note:

    Given n will always be valid.

    Follow up:

    Could you do this in one pass?

  */

  // Definition for singly-linked list.
  class ListNode(var _x: Int = 0) {
    var next: ListNode = null
    var x: Int = _x
  }

  def removeNthFromEnd(head: ListNode, n: Int): ListNode = {
    println("head = " + head)
    val mapped = addToMap(head)
    val size = mapped.size
    // 1->2->3->4->5, and n = 2.
    val deleteIndex = (size + 1) - n
    println("deleteIndex -> " + deleteIndex)
    var prevElement = mapped.getOrElse((deleteIndex - 1), null)
    println("prevElement -> " + prevElement)
    var elementToDelete = mapped(deleteIndex)
    println("elementToDelete -> " + elementToDelete)
    var nextElement = mapped.getOrElse((deleteIndex + 1), null)
    println("nextElement -> " + nextElement)
    (prevElement, nextElement) match {
      case (null, null) =>
        println("null, null")
        null
      case (_, null) =>
        println("_, null")
        prevElement.next = null
        head
      case (null, _) =>
        println("null, _")
        nextElement
      case (_, _) =>
        println("_, _")
        prevElement.next = nextElement
        head
    }
  }

  def addToMap(head: ListNode): Map[Int, ListNode] = {
    var ptr = head
    var index = 1
    import scala.collection.mutable.HashMap
    val mapped = HashMap[Int, ListNode]()
    while(ptr != null){
      mapped.put(index, ptr)
      index = index + 1
      ptr = ptr.next
    }
    mapped.toMap
  }
}

