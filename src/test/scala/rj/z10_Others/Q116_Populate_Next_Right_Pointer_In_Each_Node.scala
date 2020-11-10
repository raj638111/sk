package rj.a00_scala.a35_Tree

import org.scalatest._

import scala.collection.mutable.ListBuffer

/*
You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.



Follow up:

You may only use constant extra space.
Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.


Example 1:



Input: root = [1,2,3,4,5,6,7]
Output: [1,#,2,3,#,4,5,6,7,#]
Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.


Constraints:

The number of nodes in the given tree is less than 4096.
-1000 <= node.val <= 1000
 */
class Q116_Populate_Next_Right_Pointer_In_Each_Node extends FunSuite with Matchers {

  def connect(root: Node): Node = {
    if (root == null) return root
    // Initialize a queue data structure which contains
    // just the root of the tree
    import scala.collection.mutable.Queue
    val Q = new Queue[Node]
    Q.enqueue(root)
    // Outer while loop which iterates over
    // each level
    while (Q.size > 0) { // Note the size of the queue
      val size: Int = Q.size
      // Iterate over all the nodes on the current level
      for (i <- 0 until size) { // Pop a node from the front of the queue
        val node: Node = Q.dequeue()
        // This check is important. We don't want to
        // establish any wrong connections. The queue will
        // contain nodes from 2 levels at most at any
        // point in time. This check ensures we only
        // don't establish next pointers beyond the end
        // of a level
        if (i < size - 1) node.next = Q.front
        // Add the children, if any, to the back of
        // the queue
        if (node.left != null) Q.enqueue(node.left)
        if (node.right != null) Q.enqueue(node.right)
      }
    }
    // Since the tree has now been modified, return the root node
    root
  }

}

class Node(var _value: Int) {
  var value: Int = _value
  var left: Node = null
  var right: Node = null
  var next: Node = null
}