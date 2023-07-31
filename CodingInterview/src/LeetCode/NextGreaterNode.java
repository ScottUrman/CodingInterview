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
    int lowIndex = 0;
    int lowValue = 0;
    int currentIndex = 0;
    for (ListNode node = head; node != null; node = node.next) {
      if (lowValue == 0) {
        lowValue = node.val;
      } else if (node.val > lowValue) {
        for (int i = lowIndex; i < currentIndex; i++) {
          retVal[i] = node.val;
        }
        lowValue = 0;
        lowIndex = currentIndex + 1;
      } else {
        currentIndex++;
      }
    }
    return retVal;
  }

  public static void main(String[] args) {
    ListNode[] nodes = new ListNode[3];
    nodes[2] = new ListNode(5, null);
    nodes[1] = new ListNode(1, nodes[2]);
    nodes[0] = new ListNode(2, nodes[1]);

    //System.out.println(Arrays.toString(nextLargerNodes(nodes[0])));

    nodes = new ListNode[5];
    nodes[4] = new ListNode(5, null);
    nodes[3] = new ListNode(3, nodes[4]);
    nodes[2] = new ListNode(4, nodes[3]);
    nodes[1] = new ListNode(7, nodes[2]);
    nodes[0] = new ListNode(2, nodes[1]);

    System.out.println(Arrays.toString(nextLargerNodes(nodes[0])));

  }
}