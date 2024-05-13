package Meta;

import java.util.ArrayList;
import java.util.HashSet;

/*
  This was problem #1 given to me in a Meta coding screen.
  Imagine we are a telephone company, and we have two arrays of customer phone numbers:
  One with all phone numbers and one with all phone numbers for which the telephone number bill has been paid.
  Figure out the phone numbers for which the bill has not been paid.
 */
public class PaidPhoneBill {

  public static ArrayList<Integer> findNotPaid(int[] allNumbers, int[] paidNumbers) {
    HashSet<Integer> paidSet = new HashSet<>();

    for (int paid : paidNumbers) {
      paidSet.add(paid);
    }

    ArrayList<Integer> retVal = new ArrayList<>();

    for (int candidate : allNumbers) {
      if (!paidSet.contains(candidate)) {
        retVal.add(candidate);
      }
    }

    return retVal;
  }

  public static void main(String[] args) {
    int[] allNumbers = {12345, 11111, 22222, 54321, 99999};
    int[] paidNumbers = {11111, 54321};
    int[] empty = {};
    System.out.println(findNotPaid(allNumbers, paidNumbers));
    System.out.println(findNotPaid(allNumbers, empty));
    System.out.println(findNotPaid(empty, paidNumbers));
  }
}

