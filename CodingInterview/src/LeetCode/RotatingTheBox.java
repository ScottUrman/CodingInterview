package LeetCode;

// https://leetcode.com/problems/rotating-the-box/

public class RotatingTheBox {
  private static final char STONE = '#';
  private static final char OBSTACLE = '*';
  private static final char EMPTY = '.';

  static char[][] rotate(char[][] box) {
    int inputXLength = box[0].length;
    int inputYLength = box.length;
    System.out.println("inputXLength: " + inputXLength);
    System.out.println("inputYLength: " + inputYLength);

    // [0,0] -> [4,0]
    // [1,0] -> [4,1]
    // [2,0] -> [4, 2]
    // [0,1] -> [3, 0]

    char[][] rotatedBox = new char[inputYLength][inputXLength];
    for (int x = 0; x < inputXLength; x++) {
      for (int y = 0; y < inputYLength; y++) {
        int rotatedX = (y + inputYLength) % inputYLength;
        int rotatedY = x;

        System.out.printf("Setting rotatedBox[%d][%d] = box[%d][%d] %c\n", rotatedX, rotatedY, x, y, box[x][y]);
        rotatedBox[rotatedX][rotatedY] = box[x][y];
      }
    }

    return rotatedBox;
  }

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
    char[][]box = new char[5][3];

    box[0] = new char[]{'a', 'b', 'c'};
    box[1] = new char[]{'d', 'e', 'f'};
    box[2] = new char[]{'g', 'h', 'i'};
    box[3] = new char[]{'j', 'k', 'l'};
    box[4] = new char[]{'m', 'n', 'o'};

    System.out.println("box[2][3]: " + box[3][2]);
    System.out.println("Original:");
    printMatrix(box);
    System.out.println("Rotated:");
    printMatrix(rotate(box));
  }
}
