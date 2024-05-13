package LeetCode;

import java.util.HashSet;

// https://leetcode.com/problems/valid-sudoku/
public class ValidSudoku {
  private boolean checkRow(char[][] board, int rowNum) {
    HashSet<Integer> numbersHash = new HashSet<>();
    for (int column = 0; column < 9; column ++) {
      char candidateCell = board[rowNum][column];
      if (candidateCell == '.') {
        continue;
      }
      int candidate = Character.getNumericValue(candidateCell);
      if (numbersHash.contains(candidate)) {
        return false;
      }
      numbersHash.add(candidate);
    }
    return true;
  }

  private boolean checkColumn(char[][] board, int columnNum) {
    HashSet<Integer> numbersHash = new HashSet<>();
    for (int row = 0; row < 9; row++) {
      char candidateCell = board[row][columnNum];
      if (candidateCell == '.') {
        continue;
      }
      int candidate = Character.getNumericValue(candidateCell);
      if (numbersHash.contains(candidate)) {
        return false;
      }
      numbersHash.add(candidate);
    }
    return true;
  }

  private boolean checkBox(char[][] board, int boxRow, int boxColumn) {
    HashSet<Integer> numbersHash = new HashSet<>();
    for (int row = boxRow; row < boxRow + 3; row++) {
      for (int column = boxColumn; column < boxColumn + 3; column++) {
        System.out.printf("Checking board[%d][%d] = %c\n", row, column, board[row][column]);
        char candidateCell = board[row][column];
        if (candidateCell == '.') {
          continue;
        }
        int candidate = Character.getNumericValue(candidateCell);
        if (numbersHash.contains(candidate)) {
          return false;
        }
        numbersHash.add(candidate);
      }
    }
    return true;
  }

  public boolean isValidSudoku(char[][] board) {
    for (int row = 0; row < 9; row++) {
      if (!checkRow(board, row)) {
        return false;
      }
    }

    for (int column = 0; column < 9; column++) {
      if (!checkColumn(board, column)) {
        return false;
      }
    }

    if (!checkBox(board, 0, 0)) {
      return false;
    }
    if (!checkBox(board, 0, 3)) {
      return false;
    }
    if (!checkBox(board, 0, 6)) {
      return false;
    }

    if (!checkBox(board, 3, 0)) {
      return false;
    }
    if (!checkBox(board, 3, 3)) {
      return false;
    }
    if (!checkBox(board, 3, 6)) {
      return false;
    }

    if (!checkBox(board, 6, 0)) {
      return false;
    }
    if (!checkBox(board, 6, 3)) {
      return false;
    }
    if (!checkBox(board, 6, 6)) {
      return false;
    }
    return true;
  }

  public static void main(String[] args) {
    char[][] board = new char[][]
        {
            {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

    System.out.println(new ValidSudoku().isValidSudoku(board));
  }
}
