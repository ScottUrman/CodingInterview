package InterviewCake;

import java.util.*;

public class Xfs {
  static class Node {
    String data;
    ArrayList<Node> children;

    Node(String data) {
      this.data = data;
      children = new ArrayList<>();
    }

    Node(String data, ArrayList<Node>children) {
      this.data = data;
      this.children = children;
    }
  }

  enum SearchType {BREADTH_FIRST, DEPTH_FIRST};

  static void xfs(Node root, SearchType searchType) {

    Deque<Node> nodeDeque = new ArrayDeque<>();

    // BFS uses a queue, DFS uses a stack
    if (searchType == SearchType.BREADTH_FIRST) {
      nodeDeque.addLast(root);
    } else {
      nodeDeque.addFirst(root);
    }

    while (!nodeDeque.isEmpty()) {
      Node currentNode = nodeDeque.removeFirst();
      System.out.println("Visiting " + currentNode.data);
      for (Node child : currentNode.children) {
        if (searchType == SearchType.BREADTH_FIRST) {
          nodeDeque.addLast(child);
        } else {
          nodeDeque.addFirst(child);
        }
      }
    }
  }

  public static Node generateTree() {
    HashMap<String, Node> nodeMap = new HashMap<>();

    nodeMap.put("L", new Node("L"));
    nodeMap.put("J", new Node("J", new ArrayList<>(Collections.singletonList(nodeMap.get("L")))));
    nodeMap.put("K", new Node("K"));
    nodeMap.put("F", new Node("F", new ArrayList<>(Arrays.asList(nodeMap.get("J"), nodeMap.get("K")))));
    nodeMap.put("G", new Node("G"));
    nodeMap.put("H", new Node("H"));
    nodeMap.put("I", new Node("I"));
    nodeMap.put("E", new Node("E", new ArrayList<>(Arrays.asList(nodeMap.get("G"), nodeMap.get("H"), nodeMap.get("I")))));
    nodeMap.put("C", new Node("C", new ArrayList<>(Arrays.asList(nodeMap.get("E"), nodeMap.get("F")))));
    nodeMap.put("B", new Node("B"));
    nodeMap.put("D", new Node("D"));
    nodeMap.put("A", new Node("A", new ArrayList<>(Arrays.asList(nodeMap.get("B"), nodeMap.get("C"), nodeMap.get("D")))));
    return nodeMap.get("A");
  }

  public static void main(String[] args) {
    Node tree = generateTree();
    System.out.println("Breadth first:");
    xfs(tree, SearchType.BREADTH_FIRST);
    System.out.println("Depth first:");
    xfs(tree, SearchType.DEPTH_FIRST);
  }
}
