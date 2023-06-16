package InterviewCake;

public class HighestProduct {

  public static int highestProduct(int[] arrayOfInts) {
    if (arrayOfInts.length < 3) {
      throw new IllegalArgumentException("array not large enough");
    }

    int candidate1 = arrayOfInts[0];
    int candidate2 = arrayOfInts[1];
    int candidate3 = arrayOfInts[2];
    int highestProduct = candidate1 * candidate2 * candidate3;

    for (int i = 3; i < arrayOfInts.length; i++) {
      int candidate = arrayOfInts[i];
       if (candidate * candidate2 * candidate3 > highestProduct) {
        candidate1 = candidate;
        highestProduct = candidate * candidate2 * candidate3;
      }
      if (candidate1 * candidate * candidate3 > highestProduct) {
        candidate2 = candidate;
        highestProduct = candidate1 * candidate * candidate3;
      }
      if (candidate1 * candidate2 * candidate > highestProduct) {
        candidate3 = candidate;
        highestProduct = candidate1 * candidate2 * candidate;
      }
    }

    return highestProduct;
  }

  public static void main(String[] args) {
    System.out.println(highestProduct(new int[]{1, 3, 5, 7}));
  }
}
