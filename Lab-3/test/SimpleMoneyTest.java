import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

/**
 * JUnit test class for the SimpleMoney class.
 */

public class SimpleMoneyTest {

  /**
   * Tests creating a SimpleMoney object with negative dollars.
   */
  @Test
  public void createSimpleMoney_negativeDollars() {
    assertThrows(IllegalArgumentException.class, () -> new SimpleMoney(-5, 30));
  }

  /**
   * Tests creating a SimpleMoney object with negative cents.
   */
  @Test
  public void createSimpleMoney_negativeCents() {
    assertThrows(IllegalArgumentException.class, () -> new SimpleMoney(5, -30));
  }

  /**
   * Tests creating a SimpleMoney object with cents greater than 100.
   */
  @Test
  public void createSimpleMoney_centsGreaterThan100() {
    assertThrows(IllegalArgumentException.class, () -> new SimpleMoney(5, 120));
  }

  /**
   * Tests creating a SimpleMoney object with valid dollars and cents.
   */
  @Test
  public void addMoney_validAmount_success() {
    SimpleMoney money1 = new SimpleMoney(10, 50);
    SimpleMoney money2 = new SimpleMoney(5, 25);
    Money result = money1.add(money2);
    assertEquals("$15.75", result.toString());
  }

  /**
   * Tests adding two SimpleMoney objects with carry-over dollars.
   */
  @Test
  public void addMoney_carryOverDollars_success() {
    SimpleMoney money1 = new SimpleMoney(10, 80);
    SimpleMoney money2 = new SimpleMoney(5, 30);
    Money result = money1.add(money2);
    assertEquals("$16.10", result.toString());
  }

  /**
   * Tests adding a negative dollar amount to a SimpleMoney object.
   */
  @Test
  public void addMoney_negativeDollars_2() {
    SimpleMoney money1 = new SimpleMoney(10, 50);
    Throwable exception = assertThrows(IllegalArgumentException.class, () -> money1.add(-5, 34));
    assertEquals("Negative amount not allowed.", exception.getMessage());
  }

  /**
   * Tests adding a negative cent amount to a SimpleMoney object.
   */
  @Test
  public void addMoney_negativeCents_2() {
    SimpleMoney money1 = new SimpleMoney(10, 50);
    Throwable exception = assertThrows(IllegalArgumentException.class, () -> money1.add(3, -5));
    assertEquals("Negative amount not allowed.", exception.getMessage());
  }

  /**
   * Tests adding a cent amount greater than 100 to a SimpleMoney object.
   */

  @Test()
  public void addMoney_centsGreaterThan100_2() {
    SimpleMoney money1 = new SimpleMoney(10, 50);
    Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
      money1.add(10, 140);
    });
    assertEquals("Cents greater than 100 not allowed.", exception.getMessage());
  }

  /**
   * Tests getting the decimal value of a SimpleMoney object with a valid amount.
   */

  @Test
  public void getDecimalValue_validAmount_success() {
    SimpleMoney money = new SimpleMoney(7, 35);
    assertEquals(7.35, money.getDecimalValue(), 0.0);
  }

  /**
   * Tests converting a SimpleMoney object to a string with a valid amount.
   */
  @Test
  public void toString_validAmount_success() {
    SimpleMoney money = new SimpleMoney(3, 20);
    assertEquals("$ 3.20", money.toString());
  }
}
