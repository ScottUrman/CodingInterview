package LeetCode;

import java.util.Arrays;

//https://leetcode.com/problems/merge-sorted-array/
public class MergeSortedArrays {
  public void merge(int[] nums1, int m, int[] nums2, int n) {
    // Sort in reverse into nums1 that way we won't overwrite anything (he says confidently)
    int i = m - 1;
    int j = n - 1;
    int currentIndex = m + n -1;
    while (i >= 0 || j >= 0) {
      if (i < 0) {
        nums1[currentIndex--] = nums2[j--];
      } else if (j < 0) {
        nums1[currentIndex--] = nums1[i--];
      } else if (nums1[i] < nums2[j]) {
        nums1[currentIndex--] = nums2[j--];
      } else {
        nums1[currentIndex--] = nums1[i--];
      }
    }
  }

  public static void main(String[] args) {
    int[] nums1 = {1,2,3,0,0,0};
    int[] nums2 = {2,5,6};
    new MergeSortedArrays().merge(nums1, 3, nums2, 3);
    System.out.println(Arrays.toString(nums1));

    nums1 = new int[]{0};
    nums2 = new int[]{1};
    new MergeSortedArrays().merge(nums1, 0, nums2, 1);
    System.out.println(Arrays.toString(nums1));

  }
}
