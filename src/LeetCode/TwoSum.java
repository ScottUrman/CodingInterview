package LeetCode;

public class TwoSum {
  // https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/?envType=study-plan-v2&envId=top-interview-150
  public int[] twoSum2(int[] numbers, int target) {
    for (int i = 0; i < numbers.length; i++) {
      for (int j = i+1; j < numbers.length; j++) {
        if (numbers[i] + numbers[j] == target) {
          return new int[]{i + 1, j + 1};
        }
      }
    }

    return null;
  }
}
