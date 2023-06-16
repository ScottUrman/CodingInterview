package LeetCode;

import java.util.PriorityQueue;

public class KthLargestElement {

  /**
   * General idea:
   * priority queue with at most k elements, sorted so that the lowest number is at the front of the queue
   * int minimum
   * foreach number:
   *   if queue.size < k
   *     queue.add(number)
   *     if number > minimum
   *       minimum = number;
   *   else
   *     if number < minimum
   *       break; // This number is smaller than the minimum we've seen, so we know there are k values bigger than it
   *     else
   *       if number > queue.peek()
   *         queue.poll()  // removes element
   *         queue.add(number)
   *         if number > minimum
   *           minimum = number;
   *
   *  return queue.peek()
   */
  public int findKthLargest(int[] nums, int k) {
    PriorityQueue<Integer> queue = new PriorityQueue<>();

    for (int n : nums) {
      if (queue.size() < k) {
        queue.add(n);
      } else if (n > queue.peek()) {
        queue.poll();
        queue.add(n);
      }
    }

    return queue.peek();
  }

  public static void main(String[] args) {
    System.out.println("hello world");

    KthLargestElement k = new KthLargestElement();

    System.out.println(k.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
    System.out.println(k.findKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 4));
  }
}
