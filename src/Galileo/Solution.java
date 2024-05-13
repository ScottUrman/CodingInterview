package Galileo;

import java.util.*;

public class Solution {

  /*
   * Completed string partitioning problem by Scott Urman.
   *
   * The problem with the original code is that we only went through the string once, to fix that we would have ended
   * up with an O(n squared) algorithm (with n being the length of the input string)
   *
   * The new approach is to first build a sorted list of tuples where each tuple contains the character, first, and
   * last offsets.  Then we can go through our sorted list in a single pass and calculate the substrings.
   *
   * I opted to set up a hash of tuples, keyed by the character (this way it's easy to determine if we've seen
   * the character before.  We then need to sort the hash, so we have an O(n log(n)) algorithm (the time to sort the list).
   * Note that technically we are only dealing with the number of unique characters (call it m), so it's really
   * O(m log(m)).  But in the worst case m = n.
   *
   * We could probably get it down to O(m) if we built the sorted list from the start, but that would require
   * maintaining both the hash of characters we've found so far, along with the current sorted list.
   *
   * Note also that we are currently printing out each substring (See the "Current substring" output) - we could
   * store these and return them along with or instead of the lengths.
   */

  /* Helper class to represent the tuple */
  static class CharacterPosition implements Comparable<CharacterPosition> {
    Character character;
    Integer firstOffset;
    Integer lastOffset;

    public CharacterPosition(char character, int firstOffset, int lastOffset) {
      this.character = character;
      this.firstOffset = firstOffset;
      this.lastOffset = lastOffset;
    }

    @Override
    public String toString() {
      return String.format("%c: [%d, %d]", character, firstOffset, lastOffset);
    }

    // Needed so the sort() call works properly
    @Override
    public int compareTo(CharacterPosition cp) {
      return this.firstOffset.compareTo(cp.firstOffset);
    }
  }

  // Return the length of each partition
  static List<Integer> partitionLabels(String S) {
    HashMap<Character, CharacterPosition> characterPositionsHash = new HashMap<>();

    char[] charArray = S.toCharArray();

    // First populate characterPositions
    for (int offset = 0; offset < charArray.length; offset++) {
      char currentChar = charArray[offset];
      CharacterPosition cp = characterPositionsHash.get(currentChar);
      if (cp == null) {
        // First time encountering this character
        characterPositionsHash.put(currentChar, new CharacterPosition(currentChar, offset, -1));
      } else {
        cp.lastOffset = offset;
      }
    }

    ArrayList<CharacterPosition> sortedCharacterPositions = new ArrayList<>(characterPositionsHash.values());
    Collections.sort(sortedCharacterPositions);

    System.out.println(sortedCharacterPositions);

    // Now we can use our sortedCharacterPositions to determine the result.
    List<Integer> retval = new ArrayList<>();
    int startingCharacterPositionsOffset = 0;

    System.out.println("charArray length " + charArray.length);

    while (startingCharacterPositionsOffset < sortedCharacterPositions.size()) {
      System.out.println("starting cp offset: " + startingCharacterPositionsOffset);
      int currentStartOffset = -1;
      int currentEndOffset = -1;

      // After this loop, currentStartOffset and currentEndOffset will represent the start and end of
      // the current substring
      for (int i = startingCharacterPositionsOffset; i < sortedCharacterPositions.size(); i++) {
        CharacterPosition currentCharacterPosition = sortedCharacterPositions.get(i);

        // Setup for next iteration of outer loop
        startingCharacterPositionsOffset++;

        System.out.println("Considering characterPosition " + currentCharacterPosition);
        System.out.println("current start offset: " + currentStartOffset);
        System.out.println("current end offset: " + currentEndOffset);
        System.out.println("i: " + i);

        if (currentStartOffset == -1) {
          // First character
          currentStartOffset = currentCharacterPosition.firstOffset;
          currentEndOffset = (currentCharacterPosition.lastOffset == -1) ? currentStartOffset : currentCharacterPosition.lastOffset;
        } else {
          // Not the first character, check to see if it is within our current substring
          if (currentCharacterPosition.firstOffset > currentEndOffset) {
            // This character doesn't appear until after the end of our current substring, therefore
            // we can return it
            startingCharacterPositionsOffset--;  // Reconsider current character for next iteration
            break;

          } else {
            // Current character first appears within the current substring, check where it ends
            if (currentCharacterPosition.lastOffset != -1) {
              currentEndOffset = Math.max(currentEndOffset, currentCharacterPosition.lastOffset);
            }
          }
        }
      }

      // Add the current length
      System.out.println("Returning start offset: " + currentStartOffset);
      System.out.println("Returning end offset: " + currentEndOffset);
      System.out.println("Current substring: " + S.substring(currentStartOffset, currentEndOffset + 1));
      retval.add(currentEndOffset - currentStartOffset + 1);
    }

    return retval;
  }

  public static void main(String[] args) throws Exception {
    //Scanner scanner = new Scanner(System.in);
    //String input = scanner.next();
    //scanner.close();
    //System.out.println(partitionLabels(input));

    /*
     * Sample input: abcdaadhijjjieeefffffe
     *               0123456789012345678901
     *
     * Tuple list:
     * a: 0, 5
     * b: 1
     * c: 2
     * d: 3
     * h: 7
     * i: 8, 12
     * j: 9, 11
     * e: 13, 21
     * f: 16, 20
     *
     * Substrings: abcdaad h ijjji eeefffffe
     * Lengths:    7       1 5     9
     */

    String input = "abcdaadhijjjieeefffffe";
    System.out.println("Input: " + input);

    // Expected output: 7,1,5,9
    System.out.println("partitions: " + partitionLabels(input));
  }
}

