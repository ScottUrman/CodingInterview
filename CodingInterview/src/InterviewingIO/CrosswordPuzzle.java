package InterviewingIO;

// Facebook harder interview question
/*
Crossword puzzle:
[
  [W W W W]
  [W B W B]
  [B W W B]
]

[[0, 0, 'A', 4]]
[[0, 0, 'D', 2]]
[[0, 2, 'D', 3]]
[[2, 1, 'A', 2]]
 */

import java.util.ArrayList;
import java.util.Arrays;

public class CrosswordPuzzle {

  public static ArrayList<String[]> crosswordPuzzle(Character[][] matrix) {
    ArrayList<String[]> retVal = new ArrayList<String[]>();

    System.out.println("Rows!");
    for (int row = 0; row < matrix.length; row++) {
      boolean foundWord = false;
      int startColumn = 0;
      int length = 0;
      for (int column = 0; column < matrix[0].length; column++) {
        System.out.println(String.format("row: %d column: %d val: %c", row, column, matrix[row][column]));
        if (matrix[row][column] == 'W') {
          System.out.println("word! foundWord: " + foundWord);
          if (foundWord) {
            length++;
          } else {
            foundWord = true;
            length = 1;
            startColumn = column;
          }
        } else {
          System.out.println("blank! foundWord: " + foundWord);
          if (foundWord) {
            if (length > 1) {
              retVal.add(new String[]{String.valueOf(row), String.valueOf(startColumn), "A", String.valueOf(length)});
            }
            foundWord = false;
            length = 0;
          }
        }
      }
      if (foundWord && length > 1) {
        retVal.add(new String[]{String.valueOf(row), String.valueOf(startColumn), "A", String.valueOf(length)});
      }
    }

    System.out.println("Columns!");
    for (int column = 0; column < matrix[0].length; column++) {
      boolean foundWord = false;
      int startRow = 0;
      int length = 0;
      for (int row = 0; row < matrix.length; row++) {
        System.out.println(String.format("row: %d column: %d val: %c", row, column, matrix[row][column]));
        if (matrix[row][column] == 'W') {
          System.out.println("word! foundWord: " + foundWord);
          if (foundWord) {
            length++;
          } else {
            foundWord = true;
            length = 1;
            startRow = row;
          }
        } else {
          System.out.println("blank! foundWord: " + foundWord);
          if (foundWord) {
            if (length > 1) {
              retVal.add(new String[]{String.valueOf(startRow), String.valueOf(column), "D", String.valueOf(length)});
            }
            foundWord = false;
          }
          length = 0;
        }
      }
      if (foundWord && length > 1) {
        retVal.add(new String[]{String.valueOf(startRow), String.valueOf(column), "D", String.valueOf(length)});
      }
    }

    return retVal;
  }

  public static void main(String[] args) {
    Character[][] inputMatrix = {{'W', 'W', 'W', 'W'}, {'W', 'B', 'W', 'B'}, {'B', 'W', 'W', 'B'}};
    ArrayList<String[]> output = crosswordPuzzle(inputMatrix);
    for (String[] o: output) {
      System.out.println(Arrays.deepToString(o));
    }


  }
}

