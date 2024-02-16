import static org.junit.Assert.assertEquals;

import movies.Movie;
import movies.Person;
import org.junit.Before;
import org.junit.Test;

/**
 * A JUnit test class for the Movie class.
 */
public class MovieTest {
  private Person billyWilder;
  private Movie theApartment;
  private Person ShahrukhKhan;
  private Movie Don2;

  /**
   * Set up movies to use for our tests.
   */
  @Before public void setUp() {
    this.billyWilder = new Person("Billy", "Wilder");
    this.theApartment = new Movie("The Apartment", this.billyWilder, 1960);
    this.ShahrukhKhan = new Person("Shahrukh", "Khan");
    this.Don2 = new Movie("Don2", this.ShahrukhKhan, 2011);
  }

  /**
   * Test the title of the movie.
   */
  @Test public void testTitle() {
    assertEquals("The Apartment", theApartment.getTitle());
  }

  /**
   * Test the director of the movie.
   */
  @Test public void testDirector() {
    assertEquals("Billy Wilder", theApartment.getDirector().toString());
  }

  /**
   * Test the year of the movie.
   */
  @Test public void testYear() {
    assertEquals(1960, theApartment.getYear());
  }

  /**
   * Test toString method.
   */
  @Test public void testToString() {
    assertEquals("The Apartment (Billy Wilder, 1960)", theApartment.toString());
  }

  /**
   * Test compareTo method, when the movie is the same.
   */
  @Test public void testCompareToReturnsZero() {
    assertEquals(0, theApartment.compareTo(theApartment));
  }

  /**
   * Test compareTo method, when the movie is different.
   */
  @Test public void testCompareToReturnsMinusOne() {
    assertEquals(-1, theApartment.compareTo(Don2));
  }

}
