package movies;

import java.util.Locale;
import java.util.Objects;

/**
 * This class represents a person, the person has a first name and last name.
 */
public class Person {

  private final String firstName;
  private final String lastName;

  /**
   * Constructs a Person object and initializes it to the given first name and last name.
   *
   * @param firstName the first name of this person
   * @param lastName  the last name of this person
   */
  public Person(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  /**
   * Get the first name of this person.
   *
   * @return the first name of this person
   */
  public String getFirstName() {
    return this.firstName;
  }

  /**
   * Return the last name of this person.
   *
   * @return the last name of this person
   */
  public String getLastName() {
    return this.lastName;
  }

  /**
   * Returns a string representation of the person, including the first name and last name.
   *
   * @return a string representation of the person
   */
  @Override
  public String toString() {
    return this.getFirstName() + " " + this.getLastName();
  }

}
