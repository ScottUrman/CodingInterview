package LeetCode;

// https://leetcode.com/problems/rotating-the-box/

import java.util.Arrays;
import java.util.Objects;

public class RotatingTheBox {
  private static final char STONE = '#';
  private static final char OBSTACLE = '*';
  private static final char EMPTY = '.';

  static char[][] rotate(char[][] box) {
    int numRows = box.length;
    int numColumns = box[0].length;
    char[][] retVal = new char[numColumns][numRows];

    // [r,c] -> [c, numRows - 1 - r]
    for (int row = 0; row < numRows; row++) {
      for (int column = 0; column < numColumns; column++) {
        retVal[column][numRows - 1 - row] = box[row][column];
      }
    }
    return retVal;
  }

  static void gravity(char [][] matrix) {
    int numRows = matrix.length;
    int numColumns = matrix[0].length;

    for (int column = 0; column < numColumns; column++) {
      int bottom = numRows - 1;
      int numStones = 0;
      for (int row = numRows - 1; row >=0; row--) {
        // Count the number of stones until we reach an obstacle or the top
        switch(matrix[row][column]) {
          case STONE:
            numStones++;
            break;
          case EMPTY:
            break;
          case OBSTACLE:
            for (int row2 = bottom; row2 > row; row2--) {
              if (numStones > 0) {
                matrix[row2][column] = STONE;
                numStones--;
              } else {
                matrix[row2][column] = EMPTY;
              }
            }
            bottom = row - 1;
            break;
        }
      }
      // Fill in above the last obstacle
      for (int row = bottom; row >=0; row--) {
        if (numStones-- > 0) {
          matrix[row][column] = STONE;
        } else {
          matrix[row][column] = EMPTY;
        }
      }
    }

  }

  public char[][]rotateTheBox(char[][] box) {
    char[][] retVal = rotate(box);
    gravity(retVal);
    return retVal;
  }

  public static void main(String[] args) {
    char[][]box = new char[][]{{STONE, EMPTY, STONE}};
    System.out.println(Arrays.deepToString(box));
    System.out.println(Arrays.deepToString(new RotatingTheBox().rotateTheBox(box)));

    box = new char[][]{{STONE, EMPTY, OBSTACLE, EMPTY}, {STONE, STONE, OBSTACLE, EMPTY}};
    System.out.println(Arrays.deepToString(box));
    System.out.println(Arrays.deepToString(new RotatingTheBox().rotateTheBox(box)));

    box = new char[][]{{STONE, EMPTY, OBSTACLE, EMPTY, STONE, EMPTY}};
    System.out.println(Arrays.deepToString(box));
    System.out.println(Arrays.deepToString(new RotatingTheBox().rotateTheBox(box)));

  }
}
