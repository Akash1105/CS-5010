package movies;

/**
 * This class represents a movie. The movie has a title, director, and year of release.
 */
public class Movie implements Comparable<Movie> {

  private String title;
  private Person director;
  private int year;

  /**
   * Constructs a Movie object and initializes it to the movie's title, director and year.
   *
   * @param title    the title of this movie
   * @param director the name of the movie's director
   * @param year     the year the movie was released
   */
  public Movie(String title, Person director, int year) {
    this.title = title;
    this.director = director;
    this.year = year;
  }

  /**
   * Get the title of the movie.
   *
   * @return the title of the movie
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * Get the name of the director of the movie.
   *
   * @return the name of the director of the movie
   */
  public Person getDirector() {
    return this.director;
  }

  /**
   * Get the year of the movie.
   *
   * @return the year of the movie
   */
  public int getYear() {
    return this.year;
  }

  /**
   * Returns a string representation of the movie, it's director and the year it was released.
   *
   * @return a string representation of the movie
   */
  @Override
  public String toString() {
    // TODO
    // Example: The Apartment (Billy Wilder, 1960)
    return getTitle() + " (" + getDirector().toString() + ", " + getYear() + ")";
  }

  /**
   * Compares this movie to another movie. The comparison is based on the title, director and year
   * of release.
   *
   * @param o the movie to compare to
   * @return -1 if the movies are not equal, 0 if the movies are equal
   */

  @Override
  public int compareTo(Movie o) {
    if (this.getTitle().compareTo(o.getTitle()) != 0) {
      return -1;
    } else if (this.getDirector().getFirstName().compareTo(o.getDirector().getFirstName()) != 0 &&
        this.getDirector().getLastName().compareTo(o.getDirector().getLastName()) != 0) {
      return -1;
    } else if (this.getYear() != o.getYear()) {
      return -1;
    }
    return 0;
  }

}
