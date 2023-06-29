package Snowflake;


/*
Given a binary tree and linked list, check if the tree has a path covering the linked list to the downward path.

       1
     /   \
    4     7
  /   \     \
 2     5     8
 |   /   \   |
 3  9     6  10

 4->5->6 (true)
 6->5->4 (false)
 7->8 (true)
 8->7 (false)
 4->1->7 (false)
 */

import java.io.*;
    import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  static class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;

    public TreeNode(int value, TreeNode left, TreeNode right) {
      this.value = value;
      this.left = left;
      this.right = right;
    }

    public TreeNode(int value) {
      this.value = value;
      this.left = null;
      this.right = null;
    }
  }

  static class ListNode {
    int value;
    ListNode next;

    public ListNode(int value, ListNode next) {
      this.value = value;
      this.next = next;
    }

    public ListNode(int value) {
      this.value = value;
      this.next = null;
    }
  }

  public static boolean listWithinTree(TreeNode treeRoot, ListNode listRoot, ListNode currentListNode) {

    if (treeRoot == null) {
      return false;
    }

    if (treeRoot.value != currentListNode.value) {
      if (listWithinTree(treeRoot.left, listRoot, listRoot)) {
        return true;
      } else {
        return listWithinTree(treeRoot.right, listRoot, listRoot);
      }
    } else {
      if (currentListNode.next == null) {
        return true;
      }
      if (listWithinTree(treeRoot.left, listRoot, currentListNode.next)) {
        return true;
      } else {
        return listWithinTree(treeRoot.right, listRoot, currentListNode.next);
      }
    }
  }

  public static void main(String[] args) {
    System.out.println("Hello World");

    /*
       1
     /   \
    4     7
  /   \     \
 2     5     8
 |   /   \   |
 3  9     6  10

  */

    TreeNode[] treeNodes = new TreeNode[11];
    int i;
    for (i = 0; i <= 10; i++) {
      treeNodes[i] = new TreeNode(i);
    }

    treeNodes[1].left = treeNodes[4];
    treeNodes[1].right = treeNodes[7];
    treeNodes[4].left = treeNodes[2];
    treeNodes[4].right = treeNodes[5];
    treeNodes[7].left = treeNodes[8];
    treeNodes[2].left = treeNodes[3];
    treeNodes[5].left = treeNodes[9];
    treeNodes[5].right = treeNodes[6];
    treeNodes[8].left = treeNodes[10];

    ListNode[] listNodes = new ListNode[3];
    //  4 -> 6
    listNodes[2] = new ListNode(5);
    listNodes[1] = new ListNode(5);
    listNodes[0] = new ListNode(4, listNodes[1]);

    TreeNode treeRoot = treeNodes[1];
    ListNode listRoot = listNodes[0];

    if (listRoot == null) {
      if (treeRoot != null) {
        System.out.println("list null, tree not null: false");
      } else {
        System.out.println("list null, tree null: true");
      }
    }

    boolean result = listWithinTree(treeRoot, listRoot, listRoot);
    System.out.println(String.format("list not null, tree not null: %s",
        result ? "true" : "false"));
  }
}
