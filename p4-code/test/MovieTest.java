import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
  @Before
  public void setUp() {
    this.billyWilder = new Person("Billy", "Wilder");
    this.theApartment = new Movie("The Apartment", this.billyWilder, 1960);
    this.ShahrukhKhan = new Person("Shahrukh", "Khan");
    this.Don2 = new Movie("Don2", this.ShahrukhKhan, 2011);
  }

  /**
   * Test the title of the movie.
   */
  @Test
  public void testTitle() {
    assertEquals("The Apartment", theApartment.getTitle());
  }

  /**
   * Test the director of the movie.
   */
  @Test
  public void testDirector() {
    assertEquals("Billy Wilder", theApartment.getDirector().toString());
  }

  /**
   * Test the year of the movie.
   */
  @Test
  public void testYear() {
    assertEquals(1960, theApartment.getYear());
  }

  /**
   * Test toString method.
   */
  @Test
  public void testToString() {
    assertEquals("The Apartment (Billy Wilder, 1960)", theApartment.toString());
  }

  /**
   * Test compareTo method with same movie.
   */
  @Test
  public void testCompareToSameMovie() {
    Person director = new Person("Christopher", "Nolan");
    Movie movie1 = new Movie("Inception", director, 2010);
    Movie movie2 = new Movie("Inception", director, 2010);

    assertEquals(0, movie1.compareTo(movie2));
  }

  /**
   * Test compareTo method with different title.
   */
  @Test
  public void testCompareToDifferentTitle() {
    Person director = new Person("Christopher", "Nolan");
    Movie movie1 = new Movie("Inception", director, 2010);
    Movie movie2 = new Movie("Interstellar", director, 2014);

    assertNotEquals(0, movie1.compareTo(movie2));
  }

  /**
   * Test compareTo method with different director.
   */
  @Test
  public void testCompareToDifferentDirector() {
    Person director1 = new Person("Christopher", "Nolan");
    Person director2 = new Person("Quentin", "Tarantino");
    Movie movie1 = new Movie("Inception", director1, 2010);
    Movie movie2 = new Movie("Inception", director2, 2010);

    assertNotEquals(0, movie1.compareTo(movie2));
  }

  /**
   * Test compareTo method with different year.
   */
  @Test
  public void testCompareToDifferentYear() {
    Person director = new Person("Christopher", "Nolan");
    Movie movie1 = new Movie("Inception", director, 2010);
    Movie movie2 = new Movie("Inception", director, 2014);

    assertNotEquals(0, movie1.compareTo(movie2));
  }

  /**
   * Test compareTo method with mixed differences.
   */
  @Test
  public void testCompareToMixedDifferences() {
    Person director1 = new Person("Christopher", "Nolan");
    Person director2 = new Person("Quentin", "Tarantino");
    Movie movie1 = new Movie("Inception", director1, 2010);
    Movie movie2 = new Movie("Interstellar", director2, 2014);

    assertNotEquals(0, movie1.compareTo(movie2));
  }

}
