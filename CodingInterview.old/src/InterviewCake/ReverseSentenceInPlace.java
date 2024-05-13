package InterviewCake;

import org.junit.Test;

public class ReverseSentenceInPlace {

  public static void reverseStringInPlace(char[] stringToReverse, int start, int end) {
    while (end > start) {
      char swap = stringToReverse[end];
      stringToReverse[end] = stringToReverse[start];
      stringToReverse[start] = swap;
      end--;
      start++;
    }
  }

  public static void reverseSentence(char[] sentence) {
    // First reverse the entire string
    reverseStringInPlace(sentence, 0, sentence.length - 1);

    // Now loop through the string and reverse the words delineated by spaces
    int start = 0;
    int end = 0;
    while (end < sentence.length) {
      if (sentence[end] != ' ') {
        end++;
      } else {
        // Found word, reverse it back
        reverseStringInPlace(sentence, start, end - 1);
        end++;
        start = end;
      }
    }

    // Reverse last word
    reverseStringInPlace(sentence, start, end -1);
  }

  @Test
  public void test() {
    char[] input = "The quick brown fox jumps over the lazy dog".toCharArray();
    reverseSentence(input);
    System.out.println(input);
  }
}
