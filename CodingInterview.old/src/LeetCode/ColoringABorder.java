package LeetCode;

// https://leetcode.com/problems/coloring-a-border/

import java.util.ArrayList;
import java.util.Arrays;

public class ColoringABorder {

  static class Cell {
    int row;
    int column;

    Cell(int row, int column) {
      this.row = row;
      this.column = column;
    }

    // Cells are equal if their rows and column are
    @Override
    public boolean equals(Object anotherObject) {
      if (!(anotherObject instanceof Cell)) {
        return false;
      }

      Cell anotherCell = (Cell)anotherObject;

      return this.row == anotherCell.row && this.column == anotherCell.column;
    }
  }

  ArrayList<Cell> adjacentCells(Cell cell, int numRows, int numColumns) {
    ArrayList<Cell> retVal = new ArrayList<>();

    if (cell.row > 0) {
      retVal.add(new Cell(cell.row - 1, cell.column));
    }
    if (cell.row < numRows - 1) {
      retVal.add(new Cell(cell.row + 1, cell.column));
    }
    if (cell.column > 0) {
      retVal.add(new Cell(cell.row, cell.column - 1));
    }
    if (cell.column < numColumns - 1) {
      retVal.add(new Cell(cell.row, cell.column + 1));
    }
    return retVal;
  }

  boolean isBorder(int[][] grid, Cell cell, int numRows, int numColumns) {
    // boundary of grid
    if (cell.row == 0 || cell.row == numRows - 1 || cell.column == 0 || cell.column == numColumns - 1) {
      return true;
    }

    // At least 1 adjacent cell is a different value
    for (Cell adjacentCell : adjacentCells(cell, numRows, numColumns)) {
      if (grid[adjacentCell.row][adjacentCell.column] != grid[cell.row][cell.column]) {
        return true;
      }
    }

    return false;
  }

  ArrayList<Cell> borderCells;
  ArrayList<Cell> visitedCells;

  void colorBorderHelper(int[][] grid, Cell cell, int numRows, int numColumns) {
    // Visit the current cell
    visitedCells.add(cell);
    //System.out.printf("Visiting grid[%d][%d] = %d\n", cell.row, cell.column, grid[cell.row][cell.column]);

    if (isBorder(grid, cell, numRows, numColumns)) {
      borderCells.add(cell);
    }

    for (Cell adjacentCell : adjacentCells(cell, numRows, numColumns)) {
      if (grid[adjacentCell.row][adjacentCell.column] == grid[cell.row][cell.column] && !visitedCells.contains(adjacentCell)) {
        colorBorderHelper(grid, adjacentCell, numRows, numColumns);
      }
    }
  }

  public int[][] colorBorder(int[][] grid, int row, int col, int color) {
    borderCells = new ArrayList<>();
    visitedCells = new ArrayList<>();

    int numRows = grid.length;
    int numColumns = grid[0].length;

    // Recurse through the cells in the connected component and build up the borderCell list
    colorBorderHelper(grid, new Cell(row, col), numRows, numColumns);

    // Color the border cells
    for (Cell borderCell : borderCells) {
      grid[borderCell.row][borderCell.column] = color;
    }

    return grid;
  }

  public static void main(String[] args) {
    int[][] grid = new int[][]{{1,1},{1,2}};
    System.out.println(Arrays.deepToString(grid));
    new ColoringABorder().colorBorder(grid, 0, 0, 3);
    System.out.println(Arrays.deepToString(grid));

    grid = new int[][]{{1,1,1}, {1,1,1}, {1,1,1}};
    System.out.println(Arrays.deepToString(grid));
    new ColoringABorder().colorBorder(grid, 1, 1, 2);
    System.out.println(Arrays.deepToString(grid));
  }
}
