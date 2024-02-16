//public class MyDate {
//
//  private int day, month, year;
//
//  public MyDate(int day, int month, int year) throws IllegalArgumentException {
//    this.day = day;
//    this.month = month;
//    this.year = year;
//
//    if (this.day < 1 || this.month < 1 || this.year < 1) {
//      throw new IllegalArgumentException("Value must be greater than zero");
//    }
//
//    if (this.month < 1 || this.month > 12) {
//      throw new IllegalArgumentException("Month must between 1 and 12");
//    }
//
//    if (this.month == 1 || this.month == 3 || this.month == 5 || this.month == 7 || this.month == 8
//        || this.month == 10 || this.month == 12 && this.day > 31) {
//      throw new IllegalArgumentException("Day cannot be greater than 31");
//    } else if (this.month == 11 || this.month == 4 || this.month == 6
//        || this.month == 9 && this.day > 30) {
//      throw new IllegalArgumentException("Day cannot be greater than 30");
//    } else if (this.month == 2 && this.year % 4 == 0 && this.year % 100 != 0 || this.year % 400 == 0 && this.day > 29) {
//      throw new IllegalArgumentException("Day cannot be greater than 29");
//    }
//
//    if (this.month == 2 && this.day > 28) {
//      throw new IllegalArgumentException("Day cannot be greater than 28");
//    }
//  }
//
//  public void advance(int days) {
//    this.day += days;
//
//    //if (this.month){}
//    // adjust days
//    // adjust months
//    // adjust years
//
//  }
//
//  public String toString() {
//    return "Date: " + Integer.toString(this.day) + "/" + Integer.toString(this.month) + "/"
//        + Integer.toString(
//        this.year);
//  }
//
//}

/**
 * This MyDate class represents a date. The date consists of day, month and year.
 */

public class MyDate {
  private static final int[] DAYS_IN_MONTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

  private int day;
  private int month;
  private int year;


  /**
   * Constructs a MyDate object and initializes it to the given day, month and year.
   *
   * @param day      the given day of the date.
   * @param month    the given month of the date.
   * @param year     the given year of the date.
   */

  public MyDate(int day, int month, int year) throws IllegalArgumentException {
    if (year < 0 || month < 1 || month > 12 || day < 1 || day > daysInMonth(month, year)) {
      throw new IllegalArgumentException("Invalid date");
    }
    this.day = day;
    this.month = month;
    this.year = year;
  }

  /**
   * Takes year as the input.
   *
   * @return boolean according to if the conditions are met or not.
   */

  private static boolean isLeapYear(int year) {
    return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
  }


  /**
   * Takes year as the input.
   *
   * @return integer value, which is the total number of day in the given year.
   */
  private static int daysInYear(int year) {
    return isLeapYear(year) ? 366 : 365;
  }


  /**
   * Takes month, year as the input.
   *
   * @return integer value, depending on the criteria met.
   */
  private static int daysInMonth(int month, int year) {
    if (month == 2) {
      return isLeapYear(year) ? 29 : 28;
    }
    return DAYS_IN_MONTH[month - 1];
  }


  /** Checks if the year  is less than zero.
   *
   * Clamps the date to 1/1/0.
   */
  private void clampDate() {
    if (year < 0) {
      year = 0;
      month = 1;
      day = 1;
    }
  }


  /**
   * Takes days as the input.
   *
   * Sets year, month and day to new value.
   */

  public void advance(int days) {
    int totalDays = day + days;

    while (totalDays < 1) {
      month--;
      if (month < 1) {
        month = 12;
        year--;
      }
      totalDays += daysInMonth(month, year);
    }

    while (totalDays > daysInYear(year)) {
      totalDays -= daysInYear(year);
      year++;
    }

    while (totalDays > daysInMonth(month, year)) {
      totalDays -= daysInMonth(month, year);
      month++;
      if (month > 12) {
        month = 1;
        year++;
      }
    }

    day = totalDays;

    clampDate();
  }


  /**
   * overrides the default toString method.
   *
   * @return a string that reports this date in the format YYYY-MM-DD.
   */

  @Override
  public String toString() {
    //return String.format("%04d-%02d-%02d", year, month, day);
    if (year == 0) {
      return "Date: " + Integer.toString(day) + "/" + Integer.toString(month) + "/"
          + "0000";
    }
    return "Date: " + Integer.toString(day) + "/" + Integer.toString(month) + "/"
        + Integer.toString(year);
  }
}
