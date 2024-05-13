package LeetCode;

// https://leetcode.com/problems/remove-duplicates-from-sorted-array/?envType=study-plan-v2&envId=top-interview-150
// https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/?envType=study-plan-v2&envId=top-interview-150

import java.util.Arrays;

public class RemoveDuplicatesFromSortedArray {
  public int removeDuplicates(int[] nums) {
    int lastUniqueIndex = 0;
    int currentValue = Integer.MIN_VALUE;

    if (nums.length <= 1) {
      return nums.length;
    }

    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != currentValue) {
        nums[lastUniqueIndex++] = nums[i];
        currentValue = nums[i];
      }
    }

    return lastUniqueIndex;
  }

  public int removeDuplicates2(int[] nums) {
    int lastUniqueIndex = 0;
    int currentValue = Integer.MIN_VALUE;
    int numDuplicates = 0;

    if (nums.length <= 1) {
      return nums.length;
    }

    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != currentValue) {
        nums[lastUniqueIndex++] = nums[i];
        currentValue = nums[i];
        numDuplicates = 1;
      } else if (numDuplicates == 1) {
        nums[lastUniqueIndex++] = nums[i];
        numDuplicates = 2;
      }
    }
    return lastUniqueIndex;
  }


  public static void main(String[] args) {
    int[] nums = new int[]{1,1,2};
    System.out.println("Original: " + Arrays.toString(nums));
    System.out.println("Return val: " + new RemoveDuplicatesFromSortedArray().removeDuplicates(nums));
    System.out.println("Modified: " + Arrays.toString(nums));

    nums = new int[]{0,0,1,1,1,2,2,3,3,4};
    System.out.println("Original: " + Arrays.toString(nums));
    System.out.println("Return val: " + new RemoveDuplicatesFromSortedArray().removeDuplicates(nums));
    System.out.println("Modified: " + Arrays.toString(nums));

    nums = new int[]{1,1,1,2,2,3};
    System.out.println("Original: " + Arrays.toString(nums));
    System.out.println("Return val (2): " + new RemoveDuplicatesFromSortedArray().removeDuplicates2(nums));
    System.out.println("Modified: " + Arrays.toString(nums));

    nums = new int[]{0,0,1,1,1,1,2,3,3};
    System.out.println("Original: " + Arrays.toString(nums));
    System.out.println("Return val (2): " + new RemoveDuplicatesFromSortedArray().removeDuplicates2(nums));
    System.out.println("Modified: " + Arrays.toString(nums));
  }
}
