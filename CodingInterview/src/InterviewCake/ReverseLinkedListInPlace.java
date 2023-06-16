package BoxCodingInterview;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReverseLinkedListInPlace {

  public static class LinkedListNode {

    public int value;
    public LinkedListNode next;

    public LinkedListNode(int value) {
      this.value = value;
    }
  }

  public static LinkedListNode reverse(LinkedListNode headOfList) {

    // reverse the linked list in place
    LinkedListNode node1 = headOfList;
    LinkedListNode node2;
    LinkedListNode node3;

    if (headOfList == null) {
      return null;
    };

    if ((node2 = node1.next) == null) {
      return headOfList;
    }

    if ((node3 = node2.next) == null) {
      // Two nodes only
      node2.next = node1;
      node1.next = null;
      return node2;
    }

    // At least three nodes
    node1.next = null;
    while (node3 != null) {
      node2.next = node1;
      node1 = node2;
      node2 = node3;
      node3 = node3.next;
    }

    // Swap final link
    node2.next = node1;
    return node2;
  }


  // tests

  @Test
  public void shortLinkedListTest() {
    final LinkedListNode[] nodes = valuesToLinkedListNodes(new int[] {1, 2});
    final LinkedListNode result = reverse(nodes[0]);
    assertTrue(isListReversed(result, nodes));
  }

  @Test
  public void longLinkedListTest() {
    final LinkedListNode[] nodes = valuesToLinkedListNodes(new int[] {1, 2, 3, 4, 5, 6});
    final LinkedListNode result = reverse(nodes[0]);
    assertTrue(isListReversed(result, nodes));
  }

  @Test
  public void oneElementLinkedListTest() {
    final LinkedListNode node = new LinkedListNode(1);
    final LinkedListNode result = reverse(node);
    assertSame(node, result);
  }

  @Test
  public void emptyLinkedListTest() {
    final LinkedListNode result = reverse(null);
    assertNull(result);
  }

  private static LinkedListNode[] valuesToLinkedListNodes(int[] values) {
    final LinkedListNode[] nodes = new LinkedListNode[values.length];
    for (int i = 0; i < values.length; i++) {
      nodes[i] = new LinkedListNode(values[i]);
      if (i > 0) {
        nodes[i - 1].next = nodes[i];
      }
    }
    return nodes;
  }

  private static boolean isListReversed(LinkedListNode list, LinkedListNode[] originalNodes) {
    int i = originalNodes.length - 1;
    while (list != null && i >= 0) {
      if (originalNodes[i] != list) {
        return false;
      }
      list = list.next;
      i--;
    }
    return list == null && i == -1;
  }

}