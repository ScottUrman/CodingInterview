package LeetCode;
//https://leetcode.com/problems/is-subsequence/?envType=study-plan-v2&envId=top-interview-150
public class IsSubsequence {
  public boolean isSubsequence(String s, String t) {
    char[] sArray = s.toCharArray();
    char[] tArray = t.toCharArray();

    if (sArray.length == 0) {
      return true;
    }
    int sIndex = 0;
    for (char c : tArray) {
      if (sArray[sIndex] == c) {
        if (++sIndex == sArray.length) {
          return true;
        }
      }
    }

    return false;
  }

  public static void main(String[] args) {
    System.out.println(new IsSubsequence().isSubsequence("abc", "ahbgdc"));  // true
  }
}
