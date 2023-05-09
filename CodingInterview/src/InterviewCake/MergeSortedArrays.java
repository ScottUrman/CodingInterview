package InterviewCake;

import org.junit.Test;

import java.util.Arrays;

public class MergeSortedArrays {
  public int[] mergeArrays(int[] firstArray, int[] secondArray) {
    int[] result = new int[firstArray.length + secondArray.length];

    int firstIndex = 0;
    int secondIndex = 0;

    for (int resultIndex = 0; resultIndex < result.length; resultIndex++) {
      if (firstIndex >= firstArray.length && secondIndex < secondArray.length) {
        result[resultIndex] = secondArray[secondIndex++];
      } else if (secondIndex >= secondArray.length && firstIndex < firstArray.length) {
        result[resultIndex] = firstArray[firstIndex++];
      } else if (firstArray[firstIndex] < secondArray[secondIndex]) {
        result[resultIndex] = firstArray[firstIndex++];
      } else {
        result[resultIndex] = secondArray[secondIndex++];
      }
    }
    return result;
  }

  @Test
  public void test() {
    int[] myArray = new int[]{3, 4, 6, 10, 11, 15};
    int[] alicesArray = new int[]{1, 5, 8, 12, 14, 19};
    System.out.println(Arrays.toString(mergeArrays(myArray, alicesArray)));
  }
}
