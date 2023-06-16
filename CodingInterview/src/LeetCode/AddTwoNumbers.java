package LeetCode;

import org.junit.Test;

import java.util.ArrayList;

/**
 * <a href="https://leetcode.com/problems/add-two-numbers/">...</a>
 */
public class AddTwoNumbers {

  static class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }

  public static void printList(ListNode l, String message) {
    System.out.print(message + ": ");
    while (l != null) {
      System.out.print(l.val + " ");
      l = l.next;
    }
    System.out.println();
  }

  public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode resultHead = null;
    ListNode resultNode;
    ListNode previousResultNode = null;
    int remainder = 0;
    while (l1 != null || l2 != null) {
      if (l1 == null) {
        resultNode = new ListNode(l2.val + remainder);
      } else if (l2 == null) {
        resultNode = new ListNode(l1.val + remainder);
      } else {
        resultNode = new ListNode(l1.val + l2.val + remainder);
      }
      if (resultNode.val < 10) {
        remainder = 0;
      } else {
        resultNode.val = resultNode.val % 10;
        remainder = 1;
      }
      if (resultHead == null) {
        resultHead = resultNode;
      }
      if (previousResultNode == null) {
        previousResultNode = resultNode;
      } else {
        previousResultNode.next = resultNode;
        previousResultNode = resultNode;
      }

      if (l1 != null) {
        l1 = l1.next;
      }

      if (l2 != null) {
        l2 = l2.next;
      }
    }

    if (remainder == 1) {
      previousResultNode.next = new ListNode(1);
    }
    return resultHead;
  }

  @Test
  public void testOne() {
    ArrayList<ListNode> l1 = new ArrayList<>();
    ListNode node3 = new ListNode(3);
    ListNode node2 = new ListNode(4, node3);
    ListNode node1 = new ListNode(2, node2);
    l1.add(node1);
    l1.add(node2);
    l1.add(node3);

    ArrayList<ListNode> l2 = new ArrayList<>();
    node3 = new ListNode(4);
    node2 = new ListNode(6, node3);
    node1 = new ListNode(5, node2);
    l2.add(node1);
    l2.add(node2);
    l2.add(node3);

    printList(l1.get(0), "list 1");
    printList(l2.get(0), "list 2");

    printList(addTwoNumbers(l1.get(0), l2.get(0)), "total");
  }
}