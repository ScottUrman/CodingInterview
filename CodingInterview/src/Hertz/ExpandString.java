package Hertz;




// 2[a]3[bd] -> aabdbdbd
// 2[2[a]3[bd]] -> aabdbdbdaabdbdbd
// 2[2[]
// abc -> abc
// 123 -> 123

// Main class should be named 'Solution' and should not be public.
class ExpandString {

  public static String expandString(String input) {
    System.out.println("expandString entry for " + input);
    StringBuilder retval = new StringBuilder();

    char[] inputArray = input.toCharArray();
    boolean inNumber = false;
    StringBuilder currentMultiplierBuilder = new StringBuilder();
    int currentMultiplier = 0;

    int i = 0;
    while (i < inputArray.length) {
      char currentChar = inputArray[i];
      System.out.printf("Top of main loop i = %d currentChar = %c\n", i, currentChar);
      if (currentChar != '[' && currentChar != ']') {
        if (!Character.isDigit(currentChar)) {
          if (inNumber) {
            retval.append(currentMultiplierBuilder);
            currentMultiplierBuilder = new StringBuilder();
            inNumber = false;
          }
          retval.append(currentChar);
        } else {
          inNumber = true;
          currentMultiplierBuilder.append(currentChar);
        }
      } else if (currentChar == '[') {
        currentMultiplier = Integer.parseInt(currentMultiplierBuilder.toString());
        inNumber = false;
        currentMultiplierBuilder = new StringBuilder();
        int bracketCount = 1;
        for (int j = i+1; j < inputArray.length; j++) {
          if (inputArray[j] == '[') {
            bracketCount++;
          } else if (inputArray[j] == ']') {
            bracketCount--;
          }
          if (bracketCount == 0) {
            String toBeExpanded = input.substring(i + 1, j);
            System.out.println("toBeExpanded: " + toBeExpanded);
            String expanded = expandString(toBeExpanded);
            retval.append(expanded.repeat(Math.max(0, currentMultiplier)));

            i = j;
            break;
          }
        }
      }
      i++;
    }

    if (inNumber) {
      retval.append(currentMultiplierBuilder);
    }

    return retval.toString();
  }

  public static void main(String[] args) {
    System.out.println("Hello, World");
    System.out.println(expandString("2[ab2[xx]c]3[def]"));
    System.out.println(expandString("2[2[a]3[bd7]]"));
  }
}
