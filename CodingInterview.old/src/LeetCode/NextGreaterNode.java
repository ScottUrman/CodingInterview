package LeetCode;

// https://leetcode.com/problems/next-greater-node-in-linked-list/

import org.junit.Test;

import java.util.Arrays;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class NextGreaterNode {
   static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

  public static int[] nextLargerNodes(ListNode head) {
    int n = 0;
    for (ListNode node = head; node != null; node = node.next) {
      n++;
    }

    int[] retVal = new int[n];
    int lowValue = 0;
    int lowIndex = 0;
    int currentIndex = 0;
    for (ListNode node = head; node != null; node = node.next) {
      if (node.next != null) {
        // Not at the end of the list yet
        if (node.val < node.next.val) {
          // We've reached a new strictly higher value
          for (int i = lowIndex; i < currentIndex; i++) {
            retVal[i] = node.val;
          }
        }
        lowValue = 0;
      }

      currentIndex++;


      if (lowValue == 0) {
        lowValue = node.val;
      } else if (node.val > lowValue) {
        for (int i = lowIndex; i < currentIndex; i++) {
          retVal[i] = node.val;
        }
        lowValue = node.val;
        lowIndex = currentIndex;
      }
      currentIndex++;
    }
    return retVal;
  }

  public static void main(String[] args) {
    ListNode[] nodes = new ListNode[3];
    nodes[2] = new ListNode(5, null);
    nodes[1] = new ListNode(1, nodes[2]);
    nodes[0] = new ListNode(2, nodes[1]);

    System.out.println(Arrays.toString(nextLargerNodes(nodes[0])));

    nodes = new ListNode[5];
    nodes[4] = new ListNode(5, null);
    nodes[3] = new ListNode(3, nodes[4]);
    nodes[2] = new ListNode(4, nodes[3]);
    nodes[1] = new ListNode(7, nodes[2]);
    nodes[0] = new ListNode(2, nodes[1]);

    System.out.println(Arrays.toString(nextLargerNodes(nodes[0])));

    //[1,7,5,1,9,2,5,1]
    nodes = new ListNode[8];
    nodes[7] = new ListNode(1, null);
    nodes[6] = new ListNode(5, nodes[7]);
    nodes[5] = new ListNode(2, nodes[6]);
    nodes[4] = new ListNode(9, nodes[5]);
    nodes[3] = new ListNode(1, nodes[4]);
    nodes[2] = new ListNode(5, nodes[3]);
    nodes[1] = new ListNode(7, nodes[2]);
    nodes[0] = new ListNode(1, nodes[1]);

    System.out.println(Arrays.toString(nextLargerNodes(nodes[0])));


  }
}