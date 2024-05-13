package LeetCode;

import java.util.Arrays;
import java.util.HashSet;

// https://leetcode.com/problems/remove-element/description/?envType=study-plan-v2&envId=top-interview-150
public class RemoveElement {
  public int removeElement(int[] nums, int val) {
    int front = 0;
    int back = nums.length - 1;

    if (nums.length == 0) {
      return 0;
    }

    while (back > front) {
      if (nums[front] != val) {
        front++;
      } else {
        if (nums[back] != val) {
          nums[front] = nums[back];
        }
        back--;
      }
    }

    // back == front, check its value to determine what to return
    if (nums[back] == val) {
      return front;
    } else {
      return front + 1;
    }
  }

  public static void main(String[] args) {
    int[] nums = new int[]{3,2,2,3};
    System.out.println("Original: " + Arrays.toString(nums));
    System.out.println(new RemoveElement().removeElement(nums, 3));
    System.out.println("Modified: " + Arrays.toString(nums));

    nums = new int[]{0,1,2,2,3,0,4,2};
    System.out.println("Original: " + Arrays.toString(nums));
    System.out.println(new RemoveElement().removeElement(nums, 2));
    System.out.println("Modified: " + Arrays.toString(nums));
  }
}
