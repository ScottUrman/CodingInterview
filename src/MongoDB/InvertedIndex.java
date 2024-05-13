package MongoDB;

import java.io.*;
import java.util.*;

public class InvertedIndex {
/*
 * Click `Run` to execute the snippet below!
 */



// insert(doc: str) # stores a doc in the index
// search(term: str) # returns all docs that contain term (not as a substring), dont care about order of documents right now
// delete(doc: str) # remove a doc from the index
// advSearch(operator: str, termList: List<str>)

// # insert("Coffee is good")
// # insert("I am at a coffee shop")
// # search("coffee") -> "I am at a coffee shop", "Coffee is good"



  static Hashtable<String, ArrayList<String>> tokenIndex = new Hashtable<>();

  static void insert(String document) {
    if (document == null) {
      return;
    }

    for (String token : document.split(" ")) {
      token = token.toUpperCase();
      System.out.println("Found token " + token);

      ArrayList<String> docList = tokenIndex.get(token);
      if (docList != null) {
        docList.add(document);
      } else {
        tokenIndex.put(token, new ArrayList<>(Arrays.asList(document)));
      }
    }
  }

  static ArrayList<String> search(String token) {
    if (token == null) {
      return new ArrayList<>();
    }

    return tokenIndex.get(token.toUpperCase());
  }

  static ArrayList<String> advSearch(String operator, ArrayList<String>tokenList) {
    ArrayList<String> retVal = new ArrayList<>();

    boolean firstToken = true;
    for (String token : tokenList) {
      token = token.toUpperCase();
      ArrayList<String> tokenDocs = new ArrayList<>();
      for (String document : search(token)) {
        if (operator.equals("OR")) {
          if (!retVal.contains(document)) {
            retVal.add(document);
          }
        } else {
          tokenDocs.add(document);
        }
      }

      System.out.println("Before remove check on token " + token);
      System.out.println("tokenDocs: " + tokenDocs);
      System.out.println("retVal: " + retVal);

      if (operator.equals("AND")) {
        if (firstToken) {
          retVal = tokenDocs;
        } else {
          for (String candidateDoc : retVal) {
            System.out.println("Checking canidateDoc " + candidateDoc);
            if (!tokenDocs.contains(candidateDoc)) {
              System.out.println("Removing candidate " + candidateDoc);
              retVal.remove(candidateDoc);
            }
          }
        }
      }

      firstToken = false;
      System.out.println("End of loop on token " + token + " current retVal: " + retVal);
    }

    return retVal;
  }

  static void delete (String document) {
    if (document == null) {
      return;
    }

    for (String token : document.split(" ")) {
      token = token.toUpperCase();
      //System.out.println("Found token " + token);

      ArrayList<String> docList = tokenIndex.get(token);
      if (docList != null) {
        docList.remove(document);
        if (docList.isEmpty()) {
          tokenIndex.remove(token);
        }
      }
    }
  }

  public static void main(String[] args) {
    insert("Coffee is good");
    insert("I am at a coffee shop");
    insert("Coffee is expensive");
    System.out.println(tokenIndex);

    //delete("I am at a coffee shop");
    //System.out.println("After delete");
    //System.out.println(tokenIndex);

    //System.out.println(search("coffee"));
    System.out.println(advSearch("AND", new ArrayList<>(Arrays.asList("coffee", "good", "expensive"))));
  }
}

