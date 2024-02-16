import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import org.junit.Test;

/**
 * JUnit test class for the MyDate class.
 */

public class MyDateTest {

  /**
   * Testing valid date creation.
   */

  @Test
  public void testValidDateCreation() {
    MyDate validDate = new MyDate(15, 6, 2022);
    assertEquals("Date: 15/6/2022", validDate.toString());
  }

  /**
   * Testing invalid date creation.
   */

  @Test
  public void testInvalidDateCreation() {
    assertThrows(IllegalArgumentException.class, () -> {
      new MyDate(32, 13, 2022);
    });
  }

  /**
   * Testing valid date creation after advancing positive days.
   */

  @Test
  public void testAdvancePositiveDays() {
    MyDate date = new MyDate(15, 6, 2022);
    date.advance(10);
    assertEquals("Date: 25/6/2022", date.toString());
  }

  /**
   * Testing valid date creation after advancing negative days.
   */

  @Test
  public void testAdvanceNegativeDays() {
    MyDate date = new MyDate(15, 6, 2022);
    date.advance(-5);
    assertEquals("Date: 10/6/2022", date.toString());
  }

  /**
   * Testing valid date creation after advancing positive days so that we advance to a new year.
   */
  @Test
  public void testAdvanceAcrossYears() {
    MyDate date = new MyDate(30, 12, 2022);
    date.advance(5);
    assertEquals("Date: 4/1/2023", date.toString());
  }

  /**
   * Testing valid date creation after advancing negative days, so that the year is 0.
   */

  @Test
  public void testAdvanceBeforeYearZero() {
    MyDate date = new MyDate(1, 1, 1);
    date.advance(-10);
    assertEquals("Date: 22/12/0000", date.toString());
  }

  /**
   * Testing clamping date creation after advancing negative days.
   */

  @Test
  public void testAdvanceBeforeYearClamp() {
    MyDate date = new MyDate(1, 1, 1);
    date.advance(-500);
    assertEquals("Date: 1/1/0000", date.toString());
  }

  /**
   * Testing valid date creation after advancing negative days, so that the year is 0.
   */
  @Test
  public void testAdvanceBeforeYearZeroAndThenPositive() {
    MyDate date = new MyDate(1, 1, 1);
    date.advance(-10);
    date.advance(19);
    assertEquals("Date: 10/1/1", date.toString());
  }

}
