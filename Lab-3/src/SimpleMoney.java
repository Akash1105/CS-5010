/**
 * SimpleMoney represents a basic implementation of the Money interface. It encapsulates a monetary
 * amount with dollars and cents.
 */

class SimpleMoney implements Money {

  private int dollars, cents, dollarsOther, centsOther;

  /**
   * Constructs a SimpleMoney object representing a valid monetary amount.
   *
   * @param dollars The dollar amount. Must be non-negative.
   * @param cents   The cent amount. Must be non-negative and less than 100.
   * @throws IllegalArgumentException if the provided dollar or cent amount is negative or cents are
   *                                  greater than or equal to 100.
   */

  public SimpleMoney(int dollars, int cents) throws IllegalArgumentException {
    if (dollars < 0 || cents < 0) {
      throw new IllegalArgumentException("Negative amount not allowed.");
    } else if (cents >= 100) {
      throw new IllegalArgumentException("Cents greater than 100 not allowed.");
    }
    this.dollars = dollars;
    this.cents = cents;
  }

  /**
   * Adds two money amounts.
   *
   * @param other The other money amount to add.
   * @return A new Money object representing the sum of the two amounts.
   */
  @Override
  public Money add(Money other) {
    double otherValue = other.getDecimalValue() * 100;
    dollarsOther = (int) (otherValue / 100);
    centsOther = (int) (otherValue % 100);
    int newDollar = this.dollars + dollarsOther;
    int newCents = this.cents + centsOther;
    if (newCents >= 100) {
      newCents = newCents - 100;
      newDollar++;
    }
    return new SimpleMoney(newDollar, newCents);
  }

  /**
   * Adds a money amount with another given as separate dollar and cent values.
   *
   * @param dollars The dollar amount to add.
   * @param cents   The cent amount to add.
   * @return A new Money object representing the sum.
   * @throws IllegalArgumentException if the provided dollar or cent amount is negative.
   */
  @Override
  public Money add(int dollars, int cents) throws IllegalArgumentException {
    if (dollars < 0 || cents < 0) {
      throw new IllegalArgumentException("Negative amount not allowed.");
    } else if (cents > 100) {
      throw new IllegalArgumentException("Cents greater than 100 not allowed.");
    }

    int newDollar = this.dollars + dollars;
    int newCents = this.cents + cents;
    if (newCents >= 100) {
      newCents = newCents - 100;
      newDollar++;
    }
    return new SimpleMoney(newDollar, newCents);
  }

  /**
   * Returns the decimal value of the money amount in the format "xx.yy".
   *
   * @return The decimal value of the money amount.
   */
  @Override
  public double getDecimalValue() {
    return ((dollars * 100.0) + cents) / 100;
  }

  /**
   * overrides the default toString method.
   *
   * @return A string in the format "$xx.yy" representing the dollar and cent amounts.
   */
  public String toString() {
    return String.format("$%2d.%02d", this.dollars, this.cents);
  }

}
