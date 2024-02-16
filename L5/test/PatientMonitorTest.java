import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PatientMonitorTest {

  private static PatientMonitor monitor;
  private static PatientMonitor patientMonitor;
  private static PatientMonitor monitorAdd;

  @BeforeAll
  static void setUp() {
    monitor = new PatientMonitor();
    patientMonitor = new PatientMonitor();
    monitorAdd = new PatientMonitor();

  }

  /**
   * Test the add method.
   */
  @Test
  void add() {
    monitorAdd.add(new SingleBloodPressureRecord("A", 125.6, 80));
    assertEquals(1, monitorAdd.getNumberOfRecords());
  }

  /**
   * Test the remove method.
   */
  @Test
  void remove() {
    monitorAdd.remove(new SingleBloodPressureRecord("A", 125.6, 80));
    assertEquals(0, monitorAdd.getNumberOfRecords());
  }

  /**
   * Test the getNumberOfRecords method.
   */
  @Test
  void getNumberOfRecords() {
    monitor.add(new SingleBloodPressureRecord("A", 125.6, 80));
    assertEquals(1, monitor.getNumberOfRecords());
  }

  /**
   * Test the emergency method.
   */
  @Test
  void emergency() {
    monitor.add(new SingleBloodPressureRecord("A", 185.6, 80));
    assertTrue(monitor.emergency());
  }

  /**
   * Test the emergency method when there is no record which will cause emergency.
   */
  @Test
  void emergencyWhenNoEmergency() {
    monitor.add(new SingleBloodPressureRecord("A", 125.6, 80));
    assertFalse(monitor.emergency());
  }

  /**
   * Test the emergency method when there is no record.
   */
  @Test
  void emergencyWhenNoRecords() {
    assertFalse(patientMonitor.emergency());
  }
}
