package LeetCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class RomanToInteger {
  public static int romanToInt(String s) {
    int result = 0;
    char previousChar = ' ';

    for (char currentChar : s.toCharArray()) {
      switch (currentChar) {
        case 'I':
          result += 1;
          break;
        case 'V':
          if (previousChar == 'I') {
            result += 3; // IV: 1 for I + 3 = 4
          } else {
            result += 5;
          }
          break;
        case 'X':
          if (previousChar == 'I') {
            result += 8;  // IX: 1 for I + 8 = 9
          } else {
            result += 10;
          }
          break;
        case 'L':
          if (previousChar == 'X') {
            result += 30;  // XL: 10 for X + 30 = 40
          } else {
            result += 50;
          }
          break;
        case 'C':
          if (previousChar == 'X') {
            result += 80;  // XC: 10 for X + 80 = 90
          } else {
            result += 100;
          }
          break;
        case 'D':
          if (previousChar == 'C') {
            result += 300;  // CD: 10 for C + 300 = 400
          } else {
            result += 500;
          }
          break;
        case 'M':
          if (previousChar == 'C') {
            result += 800;  // CM: 100 for C + 800 = 900
          } else {
            result += 1000;
          }
          break;
      }
      previousChar = currentChar;
    }

    return result;
  }

  public static void main(String[] args) throws IOException {
    System.out.println("Hello World!");

    BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ) );
    String input = "";
    do {
      System.out.print("Enter roman numeral: ");
      input = reader.readLine();
      System.out.println("Result: " + romanToInt(input));
    } while (input.length() > 0);

  }
}
