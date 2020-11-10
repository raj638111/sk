package rj.z10_Others;

/*

A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.

The Linked List is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:

val: an integer representing Node.val
random_index: the index of the node (range from 0 to n-1) where random pointer points to, or null if it does not point to any node.


Example 1:


Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
Example 2:


Input: head = [[1,1],[2,1]]
Output: [[1,1],[2,1]]
Example 3:



Input: head = [[3,null],[3,0],[3,null]]
Output: [[3,null],[3,0],[3,null]]
Example 4:

Input: head = []
Output: []
Explanation: Given linked list is empty (null pointer), so return null.


Constraints:

-10000 <= Node.val <= 10000
Node.random is null or pointing to a node in the linked list.
Number of Nodes will not exceed 1000.

 */

import java.util.HashMap;
import java.util.Map;

class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        Map<Node, Node> pointerMap = new HashMap<>();
        Node current = head;
        Node newHead = null;
        Node newCurrent = null;

        // Copy content to the new list ...
        while (current != null) {
            if (newHead == null) {
                newHead = new Node(current.val);

                newCurrent = newHead;
                current = current.next;

                continue;
            }

            Node newNode = new Node(current.val);

            newCurrent.next = newNode;

            newCurrent = newCurrent.next;
            current = current.next;
        }

        // Map original list to copied list item pointers
        current = head;
        newCurrent = newHead;

        while (current != null) {
            pointerMap.put(current, newCurrent);

            current = current.next;
            newCurrent = newCurrent.next;
        }

        // Create random pointers in the new list using mapping
        current = head;
        newCurrent = newHead;

        while (current != null) {
            Node target = current.random;

            Node newTarget = pointerMap.get(target);

            newCurrent.random = newTarget;

            current = current.next;
            newCurrent = newCurrent.next;
        }

        return newHead;
    }
}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

