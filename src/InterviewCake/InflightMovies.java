package InterviewCake;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class InflightMovies {

  public static boolean canTwoMoviesFillFlight(int[] movieLengths, int flightLength) {
    // determine if two movies add up to the flight length
    /*
     * General idea: Hash table with the key being flightLength - movieLength for each entry in
     * movieLengths.  1 pass to setup the hash table, then another pass to find a match
     */
    class Movie {
      public final int movieIndex;
      public final int movieLength;

      Movie(int movieIndex, int movieLength) {
        this.movieIndex = movieIndex;
        this.movieLength = movieLength;
      }
    }

    HashMap<Integer, Movie> movieHash = new HashMap<>();
    for (int movieIndex = 0; movieIndex < movieLengths.length; movieIndex++) {
      Movie movie = new Movie(movieIndex, movieLengths[movieIndex]);
      if (movie.movieLength < flightLength) {
        movieHash.put(flightLength - movie.movieLength, movie);
      }
    }

    if (movieHash.isEmpty()) {
      return false;
    }

    for (int movieIndex = 0; movieIndex < movieLengths.length; movieIndex++) {
      if (movieLengths[movieIndex] < flightLength) {
        Movie movie = movieHash.get(movieLengths[movieIndex]);
        if (movie != null && movie.movieIndex != movieIndex) {
          return true;
        }
      }
    }
    return false;
  }


  // tests

  @Test
  public void shortFlightTest() {
    final boolean result = canTwoMoviesFillFlight(new int[]{2, 4}, 1);
    assertFalse(result);
  }

  @Test
  public void longFlightTest() {
    final boolean result = canTwoMoviesFillFlight(new int[]{2, 4}, 6);
    assertTrue(result);
  }

  @Test
  public void onlyOneMovieHalfFlightLenghtTest() {
    final boolean result = canTwoMoviesFillFlight(new int[]{3, 8}, 6);
    assertFalse(result);
  }

  @Test
  public void twoMoviesHalfFlightLengthTest() {
    final boolean result = canTwoMoviesFillFlight(new int[]{3, 8, 3}, 6);
    assertTrue(result);
  }

  @Test
  public void lotsOfPossiblePairsTest() {
    final boolean result = canTwoMoviesFillFlight(new int[]{1, 2, 3, 4, 5, 6}, 7);
    assertTrue(result);
  }

  @Test
  public void notUsingFirstMovieTest() {
    final boolean result = canTwoMoviesFillFlight(new int[]{4, 3, 2}, 5);
    assertTrue(result);
  }

  @Test
  public void multipleMoviesShorterThanFlightTest() {
    final boolean result = canTwoMoviesFillFlight(new int[]{5, 6, 7, 8}, 9);
    assertFalse(result);
  }

  @Test
  public void oneMovieTest() {
    final boolean result = canTwoMoviesFillFlight(new int[]{6}, 6);
    assertFalse(result);
  }

  @Test
  public void noMoviesTest() {
    final boolean result = canTwoMoviesFillFlight(new int[]{}, 6);
    assertFalse(result);
  }

  @Test
  public void twoHalfMoviesTest() {
    final boolean result = canTwoMoviesFillFlight(new int[]{4, 8, 4}, 8);
    assertTrue(result);
  }
}