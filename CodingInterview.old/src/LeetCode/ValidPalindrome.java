package LeetCode;

public class ValidPalindrome {
  private boolean isAlphaNumeric(char c) {
    return (c >= 'a' && c <= 'z') ||
        (c >= 'A' && c <= 'Z') ||
        (c >= '0' && c <= '9');
  }

  public boolean isPalindrome(String s) {
    char[] stringArray = s.toLowerCase().toCharArray();
    int front = 0;
    int back = stringArray.length - 1;

    while (front < back) {
      if (!isAlphaNumeric(stringArray[front])) {
        front++;
      } else if (!isAlphaNumeric(stringArray[back])) {
        back--;
      } else if (stringArray[front] != stringArray[back]) {
        return false;
      } else {
        front++;
        back--;
      }
    }

    return true;
  }

}
