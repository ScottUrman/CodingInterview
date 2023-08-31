package LeetCode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ContainerWithMostWater {
  public int maxArea(int[] height) {
    int left = 0;
    int right = height.length - 1;
    int maxArea = 0;
    while (right > left) {
      int area = (right - left) * Math.min(height[left], height[right]);
      if (area > maxArea) {
        maxArea = area;
      }
      if (height[left] < height[right]) {
        left++;
      } else {
        right--;
      }
    }

    return maxArea;
  }

  @Test
  public void testOne() {
    int[] height = {1,8,6,2,5,4,8,3,7};
    int maxArea = maxArea(height);
    assertEquals(maxArea, 49);
  }
}
