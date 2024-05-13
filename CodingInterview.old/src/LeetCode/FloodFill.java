package LeetCode;

import java.util.*;

// https://leetcode.com/problems/flood-fill/
public class FloodFill {

  public int[][] floodFill(int[][] image, int sr, int sc, int color) {

    int numRows = image.length;
    int numColumns = image[0].length;

    class Cell {
      final int row;
      final int column;
      Cell(int row, int column) {
        this.row = row;
        this.column = column;
      }
    }

    Deque<Cell> stack = new ArrayDeque<>();
    int originalColor = image[sr][sc];
    if (originalColor != color) {
      stack.add(new Cell(sr,sc));
    }

    while (!stack.isEmpty()) {
     Cell currentCell = stack.pop();
     System.out.printf("floodFill top of loop for [%d][%d] = %d\n", currentCell.row, currentCell.column, image[currentCell.row][currentCell.column]);
     image[currentCell.row][currentCell.column] = color;

     if (currentCell.row > 0 && image[currentCell.row - 1][currentCell.column] == originalColor) {
       stack.add(new Cell(currentCell.row - 1, currentCell.column));
     }
     if (currentCell.row < numRows - 1 && image[currentCell.row + 1][currentCell.column] == originalColor) {
       stack.add(new Cell(currentCell.row + 1, currentCell.column));
     }

     if (currentCell.column > 0 && image[currentCell.row][currentCell.column - 1] == originalColor) {
       stack.add(new Cell(currentCell.row, currentCell.column - 1));
     }
     if (currentCell.column < numColumns - 1 && image[currentCell.row][currentCell.column + 1] == originalColor) {
       stack.add(new Cell(currentCell.row, currentCell.column + 1));
     }
    }

    return image;
  }

  public static void main(String[] args) {
    int[][] image = new int[][]{{1,1,1},{1,1,0},{1,0,1}};
    System.out.println(Arrays.deepToString(image));

    new FloodFill().floodFill(image, 1, 1, 2);
    System.out.println(Arrays.deepToString(image));
  }
}
