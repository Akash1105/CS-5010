/**
 * Test class for MovieLinkedList
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import movies.*;
import org.junit.Test;


public class MovieLinkedListTest {

  /**
   * Test case to verify that IndexOutOfBoundsException is thrown when attempting to add a movie at
   * an invalid index.
   */
  @Test
  public void testAddInvalidIndex() {
    MovieLinkedList movieList = new MovieLinkedList();

    // Adding movies to the list
    Movie movie1 = new Movie("Movie1", new Person("Director1", "LastName1"), 2000);
    Movie movie2 = new Movie("Movie2", new Person("Director2", "LastName2"), 2010);

    movieList.add(0, movie1);

    // Attempting to add a movie at an invalid index
    assertThrows(IndexOutOfBoundsException.class, () -> {
      movieList.add(5, movie2); // Index 5 is out of bounds for a list of size 1
    });
  }

  /**
   * Test case for adding movies to the list and checking the size.
   */
  @Test
  public void testAddAndSize() {
    MovieLinkedList movieList = new MovieLinkedList();

    // Adding movies to the list
    Movie movie1 = new Movie("Movie1", new Person("Director1", "LastName1"), 2000);
    Movie movie2 = new Movie("Movie2", new Person("Director2", "LastName2"), 2010);

    movieList.add(0, movie1);
    movieList.add(1, movie2);

    // Checking the size of the list
    assertEquals(2, movieList.size());
  }

  /**
   * Test case for removing a movie from the list and checking the size after removal.
   */
  @Test
  public void testRemove() {
    MovieLinkedList movieList = new MovieLinkedList();

    // Adding movies to the list
    Movie movie1 = new Movie("Movie1", new Person("Director1", "LastName1"), 2000);
    Movie movie2 = new Movie("Movie2", new Person("Director2", "LastName2"), 2010);

    movieList.add(0, movie1);
    movieList.add(1, movie2);

    // Removing a movie from the list
    assertTrue(movieList.remove(movie1));

    // Checking the size after removal
    assertEquals(1, movieList.size());
  }

  /**
   * Test case for retrieving movies made in a specific year.
   */
  @Test
  public void testMoviesMade() {
    MovieLinkedList movieList = new MovieLinkedList();

    // Adding movies to the list
    Movie movie1 = new Movie("Movie1", new Person("Director1", "LastName1"), 2000);
    Movie movie2 = new Movie("Movie2", new Person("Director2", "LastName2"), 2010);
    Movie movie3 = new Movie("Movie3", new Person("Director3", "LastName3"), 2010);

    movieList.add(0, movie1);
    movieList.add(1, movie2);
    movieList.add(2, movie3);

    // Checking movies made in 2010
    MovieList moviesIn2010 = movieList.moviesMade(TimeIndicator.DURING, 2010);
    assertEquals(2, ((MovieLinkedList) moviesIn2010).size());
  }

  /**
   * Test case for retrieving movies directed by a particular person.
   */
  @Test
  public void testMoviesDirectedBy() {
    MovieLinkedList movieList = new MovieLinkedList();

    // Adding movies to the list
    Person director1 = new Person("Director1", "LastName1");
    Person director2 = new Person("Director2", "LastName2");

    Movie movie1 = new Movie("Movie1", director1, 2000);
    Movie movie2 = new Movie("Movie2", director2, 2010);
    Movie movie3 = new Movie("Movie3", director1, 2015);

    movieList.add(0, movie1);
    movieList.add(1, movie2);
    movieList.add(2, movie3);

    // Checking movies directed by Director1
    MovieList moviesByDirector1 = movieList.moviesDirectedBy(director1);
    assertEquals(2, ((MovieLinkedList) moviesByDirector1).size());
  }

  /**
   * Test case for sorting an empty list.
   */
  @Test
  public void testEmptyList() {
    MovieLinkedList movieList = new MovieLinkedList();
    movieList.insertionSort();

    assertEquals(0, movieList.size());
  }

  /**
   * Test case for sorting a single movie.
   */
  @Test
  public void testSingleMovie() {
    MovieLinkedList movieList = new MovieLinkedList();
    Person director = new Person("Christopher", "Nolan");
    Movie movie = new Movie("Inception", director, 2010);
    movieList.add(0, movie);

    movieList.insertionSort();

    assertEquals(1, movieList.size());
    assertEquals(Arrays.asList(movie), toList(movieList));
  }

  /**
   * Test case for sorting a list of movies that are already sorted.
   */
  @Test
  public void testSortedList() {
    MovieLinkedList movieList = new MovieLinkedList();
    Person director1 = new Person("Christopher", "Nolan");
    Person director2 = new Person("Quentin", "Tarantino");
    Movie movie1 = new Movie("Inception", director1, 2010);
    Movie movie2 = new Movie("Pulp Fiction", director2, 1994);
    movieList.add(0, movie1);
    movieList.add(1, movie2);

    movieList.insertionSort();

    assertEquals(2, movieList.size());
    assertEquals(Arrays.asList(movie1, movie2), toList(movieList));
  }

  /**
   * Test case for sorting an unsorted list of movies.
   */
  @Test
  public void testUnsortedList() {
    MovieLinkedList movieList = new MovieLinkedList();
    Person director1 = new Person("Christopher", "Nolan");
    Person director2 = new Person("Quentin", "Tarantino");
    Movie movie1 = new Movie("Inception", director1, 2010);
    Movie movie2 = new Movie("Pulp Fiction", director2, 1994);
    movieList.add(0, movie2);
    movieList.add(1, movie1);

    movieList.insertionSort();

    assertEquals(2, movieList.size());
    assertEquals(Arrays.asList(movie1, movie2), toList(movieList));
  }

  /**
   * Helper method to convert the MovieLinkedList to a List for easier comparison.
   *
   * @param movieList The MovieLinkedList to convert.
   * @return The equivalent List of movies.
   */
  private List<Movie> toList(MovieLinkedList movieList) {
    List<Movie> list = new ArrayList<>();
    for (Movie movie : movieList) {
      list.add(movie);
    }
    return list;
  }
}
