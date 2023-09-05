package InterviewingIO;

// Came from a mock interview - first facebook question he was asked

/*
Candy Crush:
input: "aaaab" -> output: "b"
input: "abccba" -> "abba" -> "aa" -> output: ""
input: "aaabccc" -> "bccc" -> "b"
*/

import java.util.ArrayDeque;
import java.util.Deque;

public class CandyCrush {

  static String recursiveCandyCrush(String input) {
    if (input.length() == 0) {
      return input;
    }

    char[] inputArray = input.toCharArray();
    StringBuilder outputBuilder = new StringBuilder();
    char previousChar = inputArray[0];
    boolean foundDuplicates = false;
    boolean currentDuplicate = false;
    for (int idx = 1; idx < inputArray.length; idx++) {
      if (inputArray[idx] != previousChar) {
        if (!currentDuplicate) {
          outputBuilder.append(previousChar);
        }
        currentDuplicate = false;
        previousChar = inputArray[idx];
      } else {
        foundDuplicates = true;
        currentDuplicate = true;
      }
    }

    if (!currentDuplicate) {
      outputBuilder.append(previousChar);
    }
    //System.out.println("End of function currentDuplicate: " + currentDuplicate);
    //System.out.println("End of function foundDuplicates: " + foundDuplicates);

    if (foundDuplicates) {
      return recursiveCandyCrush((outputBuilder.toString()));
    } else {
      return outputBuilder.toString();
    }
  }

  static public String stackCandyCrush(String input) {
    // Implementation using a stack instead of recursion
    // O(n) time and space
    char[] inputArray = input.toCharArray();
    Deque<Character> stack = new ArrayDeque<>();

    boolean foundDuplicate = false;

    /*
     Input: abccbdef
     Stack: a b
     Idx: 4
     Duplicate: true

     */
    int idx = 0;
    while (idx < inputArray.length) {
      char currentChar = inputArray[idx];

      if (stack.isEmpty()) {
        stack.push(currentChar);
        idx++;
      } else {
        char previousChar = stack.peekFirst();
        if (currentChar == previousChar) {
          foundDuplicate = true;
          idx++;
        } else {
          if (foundDuplicate) {
            stack.removeFirst();
            foundDuplicate = false;
          } else {
            stack.addFirst(currentChar);
            idx++;
          }
        }
      }
    }

    if (foundDuplicate) {
      stack.removeFirst();
    }
    StringBuilder retVal = new StringBuilder();
    while (!stack.isEmpty()) {
      retVal.append(stack.removeLast());
    }
    return retVal.toString();
  }

  //. a d b
  // duplicate: true
  public static void main(String[] args) {
    //String input = "abccbdef";
    //String input = "abccbaaa";
    String input = "aabba";  // Should output be "a" (removed left to right) or "b" (removed right to left)

    System.out.println("recursive output: " + recursiveCandyCrush(input));
    System.out.println("stack output: " + stackCandyCrush(input));
  }
}

