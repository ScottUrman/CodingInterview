package LeetCode;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class ContainerWithMostWater {
  public int maxArea(int[] height) {
    int x;
    int y;
    int maxArea = 0;

    for (x = 0; x < height.length - 1; x++) {
      for (y = x + 1; y < height.length; y++) {
        int area = (y -x) * Math.min(height[x], height[y]);
        if (area > maxArea) {
          maxArea = area;
        }
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
