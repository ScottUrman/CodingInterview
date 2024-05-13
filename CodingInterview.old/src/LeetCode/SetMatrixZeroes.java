package LeetCode;

import java.util.Arrays;
import java.util.HashSet;

// https://leetcode.com/problems/set-matrix-zeroes/description/

public class SetMatrixZeroes {
  void printMatrix(int[][] matrix) {
    System.out.println(Arrays.deepToString(matrix));
  }
  public void setZeroes(int[][] matrix) {
    int numRows = matrix.length;
    int numColumns = matrix[0].length;

    HashSet<Integer> zeroRows = new HashSet<>();
    HashSet<Integer> zeroColumns = new HashSet<>();

    for (int row = 0; row < numRows; row++) {
      for (int column = 0; column < numColumns; column++) {
        if (matrix[row][column] == 0) {
          zeroRows.add(row);
          zeroColumns.add(column);
        }
      }
    }

    for (int row = 0; row < numRows; row++) {
      for (int column = 0; column < numColumns; column++) {
        if (zeroRows.contains(row) || zeroColumns.contains(column)) {
          matrix[row][column] = 0;
        }
      }
    }
  }

  public static void main(String[] args) {
    int[][] matrix = new int[][]{{1,1,1},{1,0,1},{1,1,1}};
    SetMatrixZeroes smz = new SetMatrixZeroes();
    smz.printMatrix(matrix);
    smz.setZeroes(matrix);
    smz.printMatrix(matrix);

    matrix = new int[][]{{0,1,2,0},{3,4,5,2},{1,3,1,5}};
    smz.printMatrix(matrix);
    smz.setZeroes(matrix);
    smz.printMatrix(matrix);

  }
}
