/**
 * Test class for MovieLinkedList
 */

import movies.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieLinkedListTest {

  /**
   * Test case to verify that IndexOutOfBoundsException is thrown when attempting
   * to add a movie at an invalid index.
   */
  @Test
  void testAddInvalidIndex() {
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
  void testAddAndSize() {
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
  void testRemove() {
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
  void testMoviesMade() {
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
  void testMoviesDirectedBy() {
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
}
