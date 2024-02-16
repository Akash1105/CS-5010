public interface Money {

  /**
   * Adds two money amounts.
   *
   * @param other The other money amount to add.
   * @return A new Money object representing the sum of the two amounts.
   */
  public Money add(Money other);

  /**
   * Adds a money amount with another given as separate dollar and cent values.
   *
   * @param dollars The dollar amount to add.
   * @param cents   The cent amount to add.
   * @return A new Money object representing the sum.
   * @throws IllegalArgumentException if the provided dollar or cent amount is negative.
   */
  public Money add(int dollars, int cents);

  /**
   * Returns the decimal value of the money amount in the format "xx.yy".
   *
   * @return The decimal value of the money amount.
   */
  public double getDecimalValue();

}
