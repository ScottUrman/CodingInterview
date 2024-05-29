package Hertz;


/*
 * Write a function String expandString(String input)
 * The input string contains numbers and strings representing how many times substrings should be
 * expanded, for example:
 *
 * expandString("2[a]3[bd]") -> "aabdbdbd"
 * expandString("2[2[a]3[bd]]") -> "aabdbdbdaabdbdbd"
 * expandString("2[2[]]") -> ""
 * expandString("abc") -> "abc"
 * expandString("7abc") -> "7abc"
 * expandString("123") -> "123"
 * expandString("12[3]") -> "333333333333"
 * expandString("5[2[a]]") -> "aaaaaaaaaa"
 * expandString("xyz2[abc]xyz") -> "xyzabcabcxyz"
 */

/*
import re
from pprint import pprint

def expandString(input):
    # non-greedy regex, will match the (shortest – generally innermost) expressions first!
    find_groups = re.compile(r'(\d+)\[([^\[\]]*?)\]')

    while find_groups.search(input):
        # inside out, replace the innermost expressions with their expansions
        input = find_groups.sub(lambda match: match.group(2) * int(match.group(1)), input)

    return input

pprint(expandString("2[a]"))
pprint(expandString("2[a]3[b]"))
pprint(expandString("2[2[ab]3[xy]]3[g]"))
pprint(expandString("abc"))
pprint(expandString("2[2[]]"))

The program prints out this:

'aa'
'aabbb'
'ababxyxyxyababxyxyxyggg'
'abc'
''
 */
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
