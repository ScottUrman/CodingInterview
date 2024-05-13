package InterviewCake;

import org.junit.Test;

import static org.junit.Assert.*;

public class AppleStocks {

  public static int getMaxProfit(int[] stockPrices) {

    // calculate the max profit
    // Find the highest and lowest values, and return the difference
    int lowest = Integer.MAX_VALUE;
    int profit = 0;

    if (stockPrices.length < 3) {
      throw new IllegalArgumentException("stockPrices too short");
    }

    for (int i = 0; i < stockPrices.length; i++) {
      int price = stockPrices[i];
      if (price < lowest) {
        lowest = price;
      }

      if (i == 0) {
        continue;
      } else if (i == 1) {
        profit = price - stockPrices[0];
      } else if (price - lowest > profit) {
        profit = price - lowest;
      }
    }

    return profit;
  }


  // tests

  @Test
  public void priceGoesUpThenDownTest() {
    final int actual = getMaxProfit(new int[]{1, 5, 3, 2});
    final int expected = 4;
    assertEquals(expected, actual);
  }

  @Test
  public void priceGoesDownThenUpTest() {
    final int actual = getMaxProfit(new int[]{7, 2, 8, 9});
    final int expected = 7;
    assertEquals(expected, actual);
  }

  @Test
  public void bigIncreaseThenSmallIncreaseTest() {
    final int actual = getMaxProfit(new int[]{2, 10, 1, 4});
    final int expected = 8;
    assertEquals(expected, actual);
  }

  @Test
  public void priceGoesUpAllDayTest() {
    final int actual = getMaxProfit(new int[]{1, 6, 7, 9});
    final int expected = 8;
    assertEquals(expected, actual);
  }

  @Test
  public void priceGoesDownAllDayTest() {
    final int actual = getMaxProfit(new int[]{9, 7, 4, 1});
    final int expected = -2;
    assertEquals(expected, actual);
  }

  @Test
  public void priceStaysTheSameAllDayTest() {
    final int actual = getMaxProfit(new int[]{1, 1, 1, 1});
    final int expected = 0;
    assertEquals(expected, actual);
  }

  @Test(expected = Exception.class)
  public void exceptionWithOnePriceTest() {
    getMaxProfit(new int[]{5});
  }

  @Test(expected = Exception.class)
  public void exceptionWithEmptyPricesTest() {
    getMaxProfit(new int[]{});
  }
}
