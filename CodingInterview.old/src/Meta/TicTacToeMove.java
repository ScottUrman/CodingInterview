package Meta;

/*
 * This was problem #2 given to me in a Meta phone screen.
 *
 * Define and implement library method move(row, column, player) for a TicTacToe game.
 * This method should make a move for the player and return whether the player had won after the move.
 * It should be used by a program which is building UI for the game.
 * Game rules:
 *   - The game is played on a grid that's N squares by N squares.
 *   - One player is an X, another one is O. Players take turns putting their marks in empty squares.
 *   - The first player to get N of their marks in a row (horizontal, vertical or diagonally) is the winner.
 *   - When all squares are full, the game is over.
*/

import org.junit.Test;

import static org.junit.Assert.*;

public class TicTacToeMove {
  public char[][] matrix;

  public TicTacToeMove() {
    matrix = null;
  }

  // Returns true if player has won
  public boolean move(int row, int column, char player) {
    //System.out.println("move entry for space " + matrix[row][column]);
    if (row < 0 || row > matrix.length) {
      throw new IllegalStateException("Row out of range");
    }

    if (column < 0 || column > matrix.length) {
      throw new IllegalStateException("Column out of range");
    }

    if (matrix[row][column] != '.') {
      throw new IllegalStateException("Space is not blank");
    }

    if (player != 'X' && player != 'O') {
      throw new IllegalStateException("Invalid player");
    }

    boolean winner = true;
    // Check for winning column
    for (int rowIdx = 0; rowIdx < matrix.length; rowIdx++) {
      if (rowIdx != row && matrix[rowIdx][column] != player) {
        winner = false;
        break;
      }
    }

    if (winner) {
      System.out.println("Winning column");
      return true;
    }

    winner = true;
    // Check for winning row
    for (int columnIdx = 0; columnIdx < matrix[0].length; columnIdx++) {
      if (columnIdx != column && matrix[row][columnIdx] != player) {
        winner = false;
        break;
      }
    }

    if (winner) {
      System.out.println("Winning row");
      return true;
    }

    // Check for winning downward right diagonal
    if (row == column) {
      winner = true;
      for (int val = 0; val < matrix.length; val++) {
        if (val != row && matrix[val][val] != player) {
          winner = false;
          break;
        }
      }

      if (winner) {
        System.out.println("Winning downward right diagonal");
        return true;
      }
    }

    // Check for winning downward left diagonal
    // length 3
    // (0, 2) (1, 1) (2, 0)   c = length - r -1
    // val 0.    -> 2
    // val 1.    -> 1
    // val 2.    -> 0
    if (column == matrix.length - row - 1) {
      winner = true;
      for (int rowIdx = 0; rowIdx < matrix.length; rowIdx++) {
        int columnIdx = matrix.length - rowIdx - 1;
        if (rowIdx != row && columnIdx != column && matrix[rowIdx][columnIdx] != player) {
          winner = false;
          break;
        }
      }

      if (winner) {
        System.out.println("Winning downward left diagonal");
      }
    }

    return winner;

  }

  @Test
  public void WinningMove() {
    // Initial board:
    // X . O
    // . X .
    // O . .
    char[][] matrix = {{'X', '.', 'O'}, {'.', 'X', '.'}, {'O', '.', '.'}};
    TicTacToeMove tttm = new TicTacToeMove();
    tttm.matrix = matrix;

    // Final board:
    // X . O
    // . X .
    // O . X
    assertTrue(tttm.move(2, 2, 'X'));
  }

  @Test
  public void WinningDownLeft() {
    // X . O
    // . . .
    // O . X
    char[][] matrix = {{'X', '.', 'O'}, {'.', '.', '.'}, {'O', '.', 'X'}};
    TicTacToeMove tttm = new TicTacToeMove();
    tttm.matrix = matrix;
    assertTrue(tttm.move(1, 1, 'O'));
  }

  @Test
  public void FailingMove() {
    // Initial board:
    // X . O
    // . X .
    // O . .
    char[][] matrix = {{'X', '.', 'O'}, {'.', 'X', '.'}, {'O', '.', '.'}};
    TicTacToeMove tttm = new TicTacToeMove();
    tttm.matrix = matrix;

    // Final board:
    // X . O
    // . X .
    // O . O
    assertFalse(tttm.move(2, 2, 'O'));
  }

  @Test
  public void InvalidPlayer() {
    char[][] matrix = {{'X', '.', 'O'}, {'.', 'X', '.'}, {'O', '.', '.'}};
    try {
      TicTacToeMove tttm = new TicTacToeMove();
      tttm.matrix = matrix;
      tttm.move(2, 2, 'Z');
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid player");
    }
  }

  @Test
  public void InvalidMove() {
    char[][] matrix = {{'X', '.', 'O'}, {'.', 'X', '.'}, {'O', '.', '.'}};
    try {
      TicTacToeMove tttm = new TicTacToeMove();
      tttm.matrix = matrix;
      tttm.move(0, 0, 'X');
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Space is not blank");
    }
  }

  @Test
  public void InvalidBoard() {
    char[][] matrix = {{'X', '.', 'O'}, {'.', 'Z', '.'}, {'O', '.', '.'}};
    try {
      TicTacToeMove tttm = new TicTacToeMove();
      tttm.matrix = matrix;
      tttm.move(2, 2, 'X');
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Invalid board");
    }
  }

  @Test
  public void RowOutOfRange() {
    char[][] matrix = {{'X', '.', 'O'}, {'.', 'X', '.'}, {'O', '.', '.'}};
    try {
      TicTacToeMove tttm = new TicTacToeMove();
      tttm.matrix = matrix;
      tttm.move(-1, 2, 'O');
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Row out of range");
    }
  }

  @Test
  public void ColumnOutOfRange() {
    char[][] matrix = {{'X', '.', 'O'}, {'.', 'X', '.'}, {'O', '.', '.'}};
    try {
      TicTacToeMove tttm = new TicTacToeMove();
      tttm.matrix = matrix;
      tttm.move(2, 50, 'X');
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Column out of range");
    }
  }
}




