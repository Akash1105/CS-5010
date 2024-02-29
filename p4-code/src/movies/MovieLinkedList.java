/**
 * MovieLinkedList is a class that implements the MovieList interface using a LinkedList. It
 * provides methods to add, remove, and filter movies.
 */
package movies;

import java.util.Iterator;
import java.util.LinkedList;

public class MovieLinkedList implements MovieList, Iterable<Movie> {

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

  /**
   * Sorts the movies using the Insertion Sort algorithm.
   * This method invokes the private insertionSort method to perform the sorting operation.
   */
  public void insertionSort() {
    insertionSort(movies);
  }

  /**
   * Performs Insertion Sort on the provided linked list of movies.
   * Insertion Sort is used to sort the movies based on their natural ordering (compareTo method).
   *
   * @param list the linked list of movies to be sorted
   */
  private void insertionSort(LinkedList<Movie> list) {
    int n = list.size();
    for (int i = 1; i < n; ++i) {
      Movie key = list.get(i);
      int j = i - 1;

      // Move elements of list[0..i-1] that are greater than key
      // to one position ahead of their current position
      while (j >= 0 && key.compareTo(list.get(j)) < 0) {
        list.set(j + 1, list.get(j));
        j = j - 1;
      }
      list.set(j + 1, key);
    }
  }

  /**
   * Returns an iterator over the movies in this MovieLinkedList in proper sequence.
   *
   * @return an iterator over the movies in this list in proper sequence
   */
  @Override
  public Iterator<Movie> iterator() {
    return movies.iterator();
  }

}
