package Toast;

public class Interview {
  /******

  public class DP { // a.k.a. DocumentPosition
    public int docId;
    public int pos;
    public DP(int docId, int pos) {...} // already implemented
  }

  public interface Cursor { // a seekable, forward-only iterator

    // gets the current location of the cursor
    public DP get();

    // advance to next occurrence of the term by scanning
    // along next positions of a document; when it reaches
    // the end of a document, this automatically moves to the
    // next document and keeps looking for term until it is
    // found, where it will stop, or runs out of docs
    public void advance();

    // true if Cursor points at a valid location
    public boolean isValid();

    // move cursor to target
    // if target contains term, stay there and return true
    // if target does not contain term, advances cursor to next
    // occurrence of the term after target, return false
    public boolean seek(DP target);
  }

  public class CursorImpl implements Cursor {
    // new cursor, positioned at first occurrence of the term
    public CursorImpl(String term) { } // already implemented

    // ... methods already implemented
  }

  /**
   // sample dataset (Documents are arranged in order from 0-N)

   Document 0
   to be or not to be
   0  1  2  3   4  5
   Document 1
   to thine own self be true
   0  1     2   3   4     5
   Document 2
   I have to go to bed
   0 1    2  3  4  5
   Document 3
   I will be at the show
   0 1    2  3  4   5
   Document 4
   be to be to to be
   0  1  2  3  4  5

   // sample cursor
   Cursor c = new CursorImpl("to");
   c.isValid() // true, positioned at first instance of "to"
   c.get() // DP(0,0), first "to"
   c.seek (new DP(0, 2)) // false, third position is "or"
   c.get() // DP(0, 4) // after previous seek, moved to next occurrence of "to"
   c.advance()
   c.get() // DP(1,0)
   c.advance()
   c.isValid() // false, no more "to" terms


// Implement a method that prints out docIds for documents that contain a term.
//
// printDocIds("to")    // 0 1
// printDocIds("self")  // 1
// printDocIds("romeo") //

  public void printDocIds(String term) {
    int currentDocId = -1;
    // implement
    Cursor c = new CursorImpl(term);
    while (c.isValid()) {
      DP currentPosition = c.get();
      if (currentPosition.getDocId() != currentDocId) {
        currentDocId = currentPosition.getDocId();
        System.out.println(currentDocId);
        c.seek(new DP(currentDocID + 1, 0));
      } else {
        c.advance();
      }
    }
  }

  /**
   * Consider printDocIds(“to”, “be”) on the following documents:
   * to be -> is a match
   * to not be -> not a match ("not" in the middle)
   * be to -> not a match (terms in wrong order)
   * to to be be -> match because the inner two words make the phrase
   *
   * Implement a method that prints out docIds for documents that contain the two terms matching as described above.
   */

 /*
Document 0
to be or not to be
0  1  2  3   4  5
Document 1
to thine own self be true
0  1     2   3   4     5
Document 2
I have to go to bed
0 1    2  3  4  5
Document 3
I will be at the show
0 1    2  3  4   5
Document 4
be to be to to be
0  1  2  3  4  5

   c1 (to): (X, X)
   c2 (be): (X, X)
   currentDocId: 4

   Print: 0 4

  public void printDocIds(String term1, String term2) {
    //implement
    int currentDocId = -1;
    Cursor c1 = new CursorImpl(term1);
    Cursor c2 = new CursorImpl(term2);

    while (c1.isValid() && c2.isValid()) {
      DP c1Position = c1.get();
      DP c2Position = c2.get();
      if (c1Position.getDocId() == c2Position.getDocId()) {
        if (c2Position.getPos() -1 == c1Position.getPos()) {
          currentDocId = c1Position.getDocId());
          System.out.println(currentDocId);
          c1.seek(currentDocId + 1, 0);
          c2.seek(currentDocId + 1, 0);
        } else {
          if (c1Position.getPos() < c2Position.getPos()) {
            c1.advance();
          } else {
            c2.advance()
          }
        }
      } else if (c1Position.getDocId() < c2Position.getDocId()) {
        c1.seek(c1Position.getDocId + 1, 0);
      } else {
        c2.seek(c2Position.getDocId + 1, 0);
      }
    }
  }
  *****/
}
