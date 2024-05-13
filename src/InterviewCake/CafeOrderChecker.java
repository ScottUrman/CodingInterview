package InterviewCake;

import org.junit.Test;

public class CafeOrderChecker {
  public static boolean verifyFirstComeFirstServed(
      int[] takeOutOrders, int[] dineInOrders, int[] servedOrders) {
    int takeOutOrderIndex = 0;
    int dineInOrderIndex = 0;
    Boolean lastOrderTakeOut = null;

    for (int servedOrder : servedOrders) {
      if (lastOrderTakeOut == null) {
        // First order
        if (servedOrder == takeOutOrders[takeOutOrderIndex]) {
          lastOrderTakeOut = true;
          takeOutOrderIndex++;
        } else if (servedOrder == dineInOrders[dineInOrderIndex]) {
          lastOrderTakeOut = false;
          dineInOrderIndex++;
        } else {
          // First served order not the first dine in or take out
          return false;
        }
      } else if (servedOrder == takeOutOrders[takeOutOrderIndex]) {
        if (lastOrderTakeOut) {
          if (servedOrder < takeOutOrders[takeOutOrderIndex - 1]) {
            return false; // takeout order not in order
          }
          lastOrderTakeOut = true;
          takeOutOrderIndex++;
        }
      } else {
        if (!lastOrderTakeOut) {
          if (servedOrder < dineInOrders[dineInOrderIndex - 1]) {
            return false; // dine in order not in order
          }
        }
        lastOrderTakeOut = false;
        dineInOrderIndex++;
      }
    }
    return true;
  }

  public static void main(String[] argv) {
    int[] takeOutOrders = new int[] {1, 3, 5};
    int[] dineInOrders = new int[] {2, 4, 6, 17};
    int[] servedOrders = new int[] {1, 2, 4, 6, 3, 5};
    System.out.println(verifyFirstComeFirstServed(takeOutOrders, dineInOrders, servedOrders));
  }
}
