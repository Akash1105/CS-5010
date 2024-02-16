/**
 * MovieLinkedList is a class that implements the MovieList interface using a LinkedList.
 * It provides methods to add, remove, and filter movies.
 */
package movies;

import java.util.LinkedList;

public class MovieLinkedList implements MovieList {

  private LinkedList<Movie> movies;

  /**
   * Constructs a new MovieLinkedList object.
   */
  public MovieLinkedList() {
    this.movies = new LinkedList<>();
  }

  /**
   * Adds a movie to the list at the specified index.
   *
   * @param index the index at which the movie is to be added
   * @param movie the movie to be added
   * @throws IndexOutOfBoundsException if the index is invalid
   */
  @Override
  public void add(int index, Movie movie) throws IndexOutOfBoundsException {
    if (index < 0 || index > movies.size()) {
      throw new IndexOutOfBoundsException("Invalid index: " + index);
    }
    movies.add(index, movie);
  }

  /**
   * Removes a movie from the list.
   *
   * @param movie the movie to be removed
   * @return true if the movie is removed, false otherwise
   */
  @Override
  public boolean remove(Movie movie) {
    return movies.remove(movie);
  }

  /**
   * Filters the movies in the list based on the time indicator and the year.
   *
   * @param timeIndicator the time indicator
   * @param year the year to filter the movies
   * @return a list of movies that match the filter
   */
  @Override
  public MovieList moviesMade(TimeIndicator timeIndicator, int year) {
    MovieLinkedList result = new MovieLinkedList();
    for (Movie movie : movies) {
      int movieYear = movie.getYear();
      if ((timeIndicator == TimeIndicator.BEFORE && movieYear < year) ||
          (timeIndicator == TimeIndicator.DURING && movieYear == year) ||
          (timeIndicator == TimeIndicator.AFTER && movieYear > year)) {
        result.add(result.size(), movie);
      }
    }
    return result;
  }

/**
   * Filters the movies in the list based on the director.
   *
   * @param director the director to filter the movies
   * @return a list of movies that match the filter
   */
  @Override
  public MovieList moviesDirectedBy(Person director) {
    MovieLinkedList result = new MovieLinkedList();
    for (Movie movie : movies) {
      if (movie.getDirector().equals(director)) {
        result.add(result.size(), movie);
      }
    }
    return result;
  }

  /**
   * Returns the size of the list.
   *
   * @return the size of the list
   */
  public int size() {
    return movies.size();
  }

}
