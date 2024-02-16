package movies;

/**
 * This interface provides a list of movies. Movies can be added and removed. We can also get a
 * subset of these movies.
 */
public interface MovieList {

  /**
   * Add a Movie to the list at the specified position.
   *
   * @param index index at which the movie is to be inserted
   * @param movie movie to be inserted
   * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size())
   */
  void add(int index, Movie movie) throws IndexOutOfBoundsException;

  /**
   * Removes the first occurrence of the specified movie from this list, if it is present. If this
   * list does not contain the movie, it is unchanged.
   *
   * @param movie movie to be removed from this list, if present
   * @return true if the movie was found; false otherwise
   */
  boolean remove(Movie movie);

  /**
   * Create a list of movies made in a certain period (before, during or after a certain year).
   *
   * @param timeIndicator indicates if the movies we want were made before, during or after a year
   * @param year          the year
   * @return the list of movies made during that period
   */
  MovieList moviesMade(TimeIndicator timeIndicator, int year);

  /**
   * Create a list of movies made by a particular director.
   *
   * @param director the director
   * @return the list of movies made by that director
   */
  MovieList moviesDirectedBy(Person director);
}
