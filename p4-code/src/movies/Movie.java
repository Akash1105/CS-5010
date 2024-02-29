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
   * Compares this movie to another movie based on title, director, and year of release. The
   * comparison is performed in the following order: Title: Titles are compared in a
   * case-insensitive manner. Director: Director names are compared in a case-insensitive manner.
   * Year: Release years are compared.
   *
   * @param o the movie to compare to
   * @return a negative integer, zero, or a positive integer if this movie is less than, equal to,
   * or greater than the specified movie based on the defined criteria.
   */
  @Override
  public int compareTo(Movie o) {
    // Compare based on title
    int titleComparison = this.getTitle().compareToIgnoreCase(o.getTitle());
    if (titleComparison != 0) {
      //System.out.println("Title comparison: " + titleComparison);
      return titleComparison;
    }

    // If titles are the same, compare based on director's name
    int directorComparison = this.getDirector().toString()
        .compareToIgnoreCase(o.getDirector().toString());
    if (directorComparison != 0) {
      return directorComparison;
    }

    // If titles and director names are the same, compare based on year
    return Integer.compare(this.getYear(), o.getYear());
  }

}
