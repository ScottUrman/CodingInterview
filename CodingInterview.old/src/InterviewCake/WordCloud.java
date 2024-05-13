package InterviewCake;

import org.junit.Test;

import java.util.*;

public class WordCloud {
  private final HashMap<String, Integer> wordMap = new HashMap<>();
  static final List<Character> delimeters =
      Arrays.asList(' ', '-', ',', '.', '?', '!');

  private void populateWordsToCounts(String inputString) {
    StringBuilder builder = new StringBuilder();

    // count the frequency of each word
    for (char c : inputString.toCharArray()) {
      if (delimeters.contains(c)) {
        String currentString = builder.toString().toLowerCase();
        wordMap.merge(currentString, 1, Integer::sum);
        builder.setLength(0);
      } else {
        builder.append(c);
      }
    }

  }

  public WordCloud(String inputString) {
    populateWordsToCounts(inputString);
  }

  public HashMap<String, Integer> getWordsToCounts() {
    return wordMap;
  }

  public static void main(String[] args) {
    WordCloud wc = new WordCloud("The quick brown fox jumps over the lazy dog");
    System.out.println(wc.getWordsToCounts());
  }
}
