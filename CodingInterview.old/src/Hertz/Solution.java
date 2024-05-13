package Hertz;

import java.io.*;
import java.util.*;
// Question:
// Ava, Mia
// Mia, Oliver
// Emma, William
// Emma, Mia
// Mason, Emma
// Mia, Lucas
// Ethan, Ava
//
// Parameters - Mason, Oliver
// Output:
// Mason invited Emma
// Emma invited Mia
// Mia invited Oliver

// Main class should be named 'Solution' and should not be public.
class Solution {

  static HashMap<String, ArrayList<String>> nodeMap = null;
  static ArrayList<String> outputList = null;

  // Recursive function to implement a DFS traversal of the graph, and builds up ouputList as we pop each
  // call off of the call stack.
  static boolean inviteHelper(String current, String end) {
    //System.out.println("inviteHelper entry for " + current);

    if (current.equals(end)) {
      outputList.add(current);
      return true;
    }
    ArrayList<String> currentFriends = nodeMap.get(current);
    for (String friend : currentFriends) {
      if (inviteHelper(friend, end)) {
        outputList.add(current);
        return true;
      }
    }

    return false;
  }

  static void printInvites(String[] friends, String start, String end) {
    nodeMap = new HashMap<>();
    outputList = new ArrayList<>();

    // First setup nodeMap so we have our adjacency list and can traverse the graph
    for (String friend : friends) {
      String[] split = friend.split(", ");
      String first = split[0];
      String second = split[1];

      if (nodeMap.containsKey(first)) {
        if (nodeMap.containsKey(second)) {
          nodeMap.get(first).add(second);
        } else {
          nodeMap.get(first).add(second);
          nodeMap.put(second, new ArrayList<>());
        }
      } else {
        if (nodeMap.containsKey(second)) {
          nodeMap.put(first, new ArrayList<>(Arrays.asList(second)));
        } else {
          nodeMap.put(second, new ArrayList<>());
          nodeMap.put(first, new ArrayList<>(Arrays.asList(second)));
        }
      }
    }

    System.out.println("Node map:");
    nodeMap.forEach((key, value) -> System.out.printf("%s: %s\n", key, Arrays.deepToString(value.toArray())));

    inviteHelper(start, end);
    System.out.printf("Output path: %s\n", Arrays.deepToString(outputList.toArray()));

    // Now we can iterate in reverse over our path to generate the output
    for (int i = outputList.size() - 1; i > 0; i--) {
      System.out.printf("%s invited %s\n", outputList.get(i), outputList.get(i - 1));
    }
  }


  public static void main(String[] args) {
    System.out.println("Hello, World");

    String[] invites = new String[7];
    invites[0] = "Ava, Mia";
    invites[1] = "Mia, Oliver";
    invites[2] = "Emma, William";
    invites[3] = "Emma, Mia";
    invites[4] = "Mason, Emma";
    invites[5] = "Mia, Lucas";
    invites[6] = "Ethan, Ava";

    printInvites(invites, "Mason", "Oliver");
  }
}
