package LeetCode;

public class InterviewingIOMatrix {

/**
  You are given an m x n matrix of characters box representing a side-view of a box.
  Each cell is one of the following
  - a single rock: "."
  - a stationary obstacle: "#"
  - empty space " "

  Gravity then affects the matrix, each stone falls down until it lands on an obstacle,
  another stone, or the bottom of the box.



  "."            " "
  " "      =>    "."
  "."            "."

  "." " " "."           " " " " "."
  " " "." "#"      =>   " " " " "#"
  " " " " " "           "." "." " "

 */


  static void gravity(char [][] matrix) {
    System.out.println("matrix.length: " + matrix.length);
    for (int x = 0; x < matrix[0].length; x++) {
      int currentTop = matrix.length - 1;
      System.out.println("matrix[x].length: " + matrix[x].length);

      for (int y = matrix.length - 1; y >=0; y--) {
      //  System.out.printf("Considering matrix[%d][%d] = %c\n%n", x, y, matrix[y][x]);

        switch (matrix[y][x]) {
          case ' ':
            break;
          case '#':
            currentTop = y - 1;
            break;
          default:
            if (y != currentTop) {
              matrix[currentTop][x] = '.';
              matrix[y][x] = ' ';
              currentTop = currentTop - 1;
            }
        }

      }
    }
  }

  static void printMatrix(char[][] matrix) {
    System.out.println("---------");
    for (int x = 0; x < matrix.length; x++) {
      for (int y = 0; y< matrix[x].length; y++) {
        System.out.print("|");
        System.out.print(matrix[x][y]);
      }
      System.out.println("|");
    }
    System.out.println("---------");

    return;
  }

  public static void main(String[] args) {
    char[][] matrix2 = new char[5][2];
    char[] row21 = new char[]{'.', '.'};
    char[] row22 = new char[]{' ', ' '};
    char[] row23 = new char[]{' ', '#'};
    char[] row24 = new char[]{'.', ' '};
    char[] row25 = new char[]{' ', ' '};
    matrix2[0] = row21;
    matrix2[1] = row22;
    matrix2[2] = row23;
    matrix2[3] = row24;
    matrix2[4] = row25;

    System.out.println("test[0][1]: " + matrix2[0][1]);
    System.out.println("test[1][1]: " + matrix2[1][1]);
    System.out.println("test[2][1]: " + matrix2[2][1]);

    printMatrix(matrix2);
    gravity(matrix2);
    printMatrix(matrix2);
  }


}

// Your previous Plain Text content is preserved below:

