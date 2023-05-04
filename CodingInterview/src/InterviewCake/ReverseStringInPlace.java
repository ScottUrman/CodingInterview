package InterviewCake;

import org.junit.Test;

public class ReverseStringInPlace {
  public static void reverseString(char[] stringToReverse) {

    int start = 0;
    int end = stringToReverse.length - 1;

    while (end > start) {
      char swap = stringToReverse[end];
      stringToReverse[end] = stringToReverse[start];
      stringToReverse[start] = swap;
      end--;
      start++;
    }
  }

  @Test
  public void test1() {
    char[] input = "Hello World".toCharArray();
    reverseString(input);
    System.out.println(input);
  }
}
