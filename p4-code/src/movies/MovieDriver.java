package movies;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class MovieDriver {

  private static final String FILE_PATH = "/Users/akashsancheti/Downloads/p6-longer_list_movies.csv";

  /**
   * The main method of the program. Reads the movies from the file, sorts them and prints them.
   *
   * @param args the command line arguments
   * @throws FileNotFoundException if the file is not found
   */
  public static void main(String[] args) throws FileNotFoundException {
    MovieLinkedList movieList = readMoviesFromFile(FILE_PATH);

    if (movieList != null) {
      System.out.println("Before Sorting:");
      printMovies(movieList);

      // Sorting using Insertion Sort
      movieList.insertionSort();

      System.out.println("\nAfter Sorting:");
      printMovies(movieList);
    } else {
      System.out.println("Failed to read movies from the file.");
    }


  }

  /**
   * Reads the movies from the file and returns a list of movies.
   *
   * @param fileName the name of the file to read
   * @return a list of movies
   * @throws FileNotFoundException if the file is not found
   */
  private static MovieLinkedList readMoviesFromFile(String fileName) throws FileNotFoundException {
    MovieLinkedList movies = new MovieLinkedList();

    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = reader.readLine()) != null) {
        System.out.println("Read line: " + line);
        // Parse the line and create Movie objects
        String[] parts = line.split(",");
        if (parts.length == 3) {
          String title = parts[0].trim().toLowerCase();
          String directorName = parts[1].trim();
          int year = Integer.parseInt(parts[2].trim());
          String[] directorParts = directorName.split(" ");
          if (directorParts.length == 2) {
            String firstName = directorParts[0].trim().toLowerCase();
            String lastName = directorParts[1].trim().toLowerCase();
            Person director = new Person(firstName, lastName);
            Movie movie = new Movie(title, director, year);
            movies.add(movies.size(), movie);
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
      return null; // Return null to indicate failure
    }

    return movies;
  }


  /**
   * Prints the movies in the list.
   *
   * @param movies the list of movies to print
   */
  private static void printMovies(MovieLinkedList movies) {
    System.out.println("Entered printMovies:" + movies.size());

    for (Movie movie : movies) {
      System.out.println(movie);
    }
  }

}
