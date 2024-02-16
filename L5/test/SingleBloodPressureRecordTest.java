import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class SingleBloodPressureRecordTest {

  private static SingleBloodPressureRecord bp1;
  private static SingleBloodPressureRecord bp2;
  private static SingleBloodPressureRecord bp3;
  private static SingleBloodPressureRecord bp4;

  @BeforeAll
  static void setUp() {
    bp1 = new SingleBloodPressureRecord("A", 125.6, 80);
    bp2 = new SingleBloodPressureRecord("A", 125.1, 80);
    bp3 = new SingleBloodPressureRecord("1", 120.9, 80);
    bp4 = new SingleBloodPressureRecord("A", 105, 60);
  }

  /**
   * Test the constructor.
   */
  @Test
  void testConstructor() {
    // Test valid constructor parameters
    SingleBloodPressureRecord record1 = new SingleBloodPressureRecord("ID123", 120, 80);
    assertEquals("ID123", record1.getID());
    assertEquals(120, record1.getSystolicReading());
    assertEquals(80, record1.getDiastolicReading());

    // Test constructor with the maximum allowed systolic and diastolic readings
    SingleBloodPressureRecord record3 = new SingleBloodPressureRecord("ID789", 300, 300);
    assertEquals("ID789", record3.getID());
    assertEquals(300, record3.getSystolicReading());
    assertEquals(300, record3.getDiastolicReading());

    // Test constructor with the minimum allowed systolic and diastolic readings
    SingleBloodPressureRecord record4 = new SingleBloodPressureRecord("ID101", 0, 0);
    assertEquals("ID101", record4.getID());
    assertEquals(0, record4.getSystolicReading());
    assertEquals(0, record4.getDiastolicReading());

    // Test constructor with a negative systolic reading
    assertThrows(IllegalArgumentException.class,
        () -> new SingleBloodPressureRecord("ID102", -10, 80));

    // Test constructor with a negative diastolic reading
    assertThrows(IllegalArgumentException.class,
        () -> new SingleBloodPressureRecord("ID103", 120, -10));

    // Test constructor with an empty ID
    assertThrows(IllegalArgumentException.class, () -> new SingleBloodPressureRecord("", 120, 80));

    // Test constructor with a null ID
    assertThrows(IllegalArgumentException.class,
        () -> new SingleBloodPressureRecord(null, 120, 80));

    // Test constructor with a systolic reading greater than the maximum allowed value
    assertThrows(IllegalArgumentException.class,
        () -> new SingleBloodPressureRecord("ID104", 310, 80));

    // Test constructor with a diastolic reading greater than the maximum allowed value
    assertThrows(IllegalArgumentException.class,
        () -> new SingleBloodPressureRecord("ID105", 120, 310));
  }

  /**
   * Test the equality method for part 1.
   */
//  @Test
//  void testEqualityPart1() {
//    // Test equality for records with the same ID, systolic, and diastolic readings
//    assertTrue(bp1.equality(bp2));
//
//    // Test equality for records with different IDs
//    assertFalse(bp1.equality(bp3));
//
//    // Test equality for records with slightly different systolic and diastolic readings
//    assertFalse(bp1.equality(bp4));
//  }

  /**
   * Test the equality method for part 2.
   */
  @Test
  void testEqualityPart2() {
    SingleBloodPressureRecord record1 = new SingleBloodPressureRecord("ID123", 120, 80);
    SingleBloodPressureRecord record2 = new SingleBloodPressureRecord("ID123", 120, 80);
    SingleBloodPressureRecord record3 = new SingleBloodPressureRecord("ID456", 130, 90);

    // Test equality for records with the same ID, systolic, and diastolic readings
    assertTrue(record1.equality(record2));

    // Test equality for records with different IDs
    assertFalse(record1.equality(record3));

    // Test equality for records with slightly different systolic readings
    assertFalse(record1.equality(new SingleBloodPressureRecord("ID123", 121, 80)));

    // Test equality for records with slightly different diastolic readings
    assertFalse(record1.equality(new SingleBloodPressureRecord("ID123", 120, 81)));
  }

  /**
   * Test the getID method.
   */
  @Test
  void getID() {
    assertEquals("A", bp1.getID());
  }

  /**
   * Test the getSystolicReading method.
   */
  @Test
  void getSystolicReading() {
    assertEquals(125.6, bp1.getSystolicReading());
  }

  /**
   * Test the getDiastolicReading method.
   */
  @Test
  void getDiastolicReading() {
    assertEquals(80, bp1.getDiastolicReading());
  }

  /**
   * Test the updateSystolicReading method.
   */
  @Test
  void updateSystolicReading() {
    bp1.updateSystolicReading(115);
    assertEquals(115, bp1.getSystolicReading());
  }

  /**
   * Test the updateDiastolicReading method.
   */
  @Test
  void updateDiastolicReading() {
    bp2.updateDiastolicReading(70);
    assertEquals(70, bp2.getDiastolicReading());
  }
}
