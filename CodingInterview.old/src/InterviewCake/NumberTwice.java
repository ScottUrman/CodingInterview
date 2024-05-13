package InterviewCake;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.HashSet;

import static org.junit.Assert.*;

public class NumberTwice {

  public static int findRepeat(int[] numbers) {
    // find the number that appears twice
    HashSet<Integer> numberHash = new HashSet<>();

    for (Integer i : numbers) {
      if (numberHash.contains(i)) {
        return i;
      } else {
        numberHash.add(i);
      }
    }


    return 0;
  }


  // tests

  @Test
  public void shortArrayTest() {
    final int[] numbers = {1, 2, 1};
    final int expected = 1;
    final int actual = findRepeat(numbers);
    assertEquals(expected, actual);
  }

  @Test
  public void mediumArrayTest() {
    final int[] numbers = {4, 1, 3, 4, 2};
    final int expected = 4;
    final int actual = findRepeat(numbers);
    assertEquals(expected, actual);
  }

  @Test
  public void longArrayTest() {
    final int[] numbers = {1, 5, 9, 7, 2, 6, 3, 8, 2, 4};
    final int expected = 2;
    final int actual = findRepeat(numbers);
    assertEquals(expected, actual);
  }
}
