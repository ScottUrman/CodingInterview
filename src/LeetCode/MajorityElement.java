package LeetCode;

// https://leetcode.com/problems/majority-element/?envType=study-plan-v2&envId=top-interview-150
public class MajorityElement {
  public int majorityElement(int[] nums) {
    int count = 0;
    int candidate = 0;

    for (int num : nums) {
      if (count == 0) {
        candidate = num;
        count++;
      } else if (num == candidate) {
        count++;
      } else {
        count--;
      }
    }

    return candidate;
  }

}
