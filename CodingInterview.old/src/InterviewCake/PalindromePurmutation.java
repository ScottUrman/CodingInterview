package InterviewCake;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PalindromePurmutation {

  public static boolean hasPalindromePermutation(String theString) {
    // check if any permutation of the input is a palindrome
    // vcciv
    int[] characterCounts = new int[26];
    for (int index = 0; index < 26; index++) {
      characterCounts[index] = 0;
    }

    for (char c : theString.toCharArray()) {
      int index = (int) c - (int) 'a';
      switch (characterCounts[index]) {
        case 0:
          characterCounts[index] = 1;
          break;
        case 1:
          characterCounts[index] = 2;
          break;
        case 2:
          characterCounts[index] = 0;
      }
    }

    boolean foundOne = false;

    for (int index = 0; index < 26; index++) {
      if (characterCounts[index] == 1) {
        if (foundOne) {
          return false;
        } else {
          foundOne = true;
        }
      }
    }

    return true;
  }

  // tests

  @Test
  public void permutationWithOddNumberOfCharsTest() {
    final boolean result = hasPalindromePermutation("aabcbcd");
    assertTrue(result);
  }

  @Test
  public void permutationWithEvenNumberOfCharsTest() {
    final boolean result = hasPalindromePermutation("aabccbdd");
    assertTrue(result);
  }

  @Test
  public void noPermutationWithOddNumberOfChars() {
    final boolean result = hasPalindromePermutation("aabcd");
    assertFalse(result);
  }

  @Test
  public void noPermutationWithEvenNumberOfCharsTest() {
    final boolean result = hasPalindromePermutation("aabbcd");
    assertFalse(result);
  }

  @Test
  public void emptyStringTest() {
    final boolean result = hasPalindromePermutation("");
    assertTrue(result);
  }

  @Test
  public void oneCharacterStringTest() {
    final boolean result = hasPalindromePermutation("a");
    assertTrue(result);
  }
}
