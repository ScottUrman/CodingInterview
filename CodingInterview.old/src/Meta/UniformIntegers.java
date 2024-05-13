package Meta;

import org.apache.commons.lang3.StringUtils;

//https://www.metacareers.com/profile/coding_puzzles/?puzzle=228269118726856
public class UniformIntegers {
  private long getNextUniform(long U) {
    char[] UString = Long.toString(U).toCharArray();
    long eleven = Long.parseLong(StringUtils.rightPad("", UString.length, String.valueOf(1)));

    if (UString[0] != '9') {
      return U + eleven;
    } else {
      return U + eleven + 1;
    }
  }

  public int getUniformIntegerCountInInterval(long A, long B) {
    // Write your code here
    char[] AString = Long.toString(A).toCharArray();

    int firstDigit = Integer.parseInt(String.valueOf(AString[0]));
    long firstUniform;

    for (int idx = 1; idx < AString.length; idx++) {
      long currentDigit = Integer.parseInt(String.valueOf(AString[idx]));
      if (currentDigit > firstDigit) {
        firstDigit++;
        break;
      } else if (currentDigit < firstDigit) {
        break;
      }
    }

    firstUniform = Long.parseLong(StringUtils.rightPad("", AString.length, String.valueOf(firstDigit)));
    System.out.println("firstUniform: " + firstUniform);
    System.out.println("nextUniform: " + getNextUniform(firstUniform));

    int retVal = 0;
    for (long currentUniform = firstUniform; currentUniform <= B; currentUniform = getNextUniform(currentUniform)) {
      retVal++;
    }

    return retVal;
  }

  public static void main(String[] args) {
    System.out.println(new UniformIntegers().getUniformIntegerCountInInterval(75, 300));
    System.out.println(new UniformIntegers().getUniformIntegerCountInInterval(1, 9));
    System.out.println(new UniformIntegers().getUniformIntegerCountInInterval(999999999999L, 999999999999L));
  }
}
