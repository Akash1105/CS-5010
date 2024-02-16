import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a patient monitor. It monitors some blood pressure records, specifically to
 * see how many of them are going into hypertensive crisis.
 */

public class PatientMonitor implements Monitor {

  private List<BloodPressureRecord> bpRecordList;

  public PatientMonitor() {
    this.bpRecordList = new ArrayList<BloodPressureRecord>();
  }


  /**
   * Add a blood pressure record to the monitor.
   *
   * @param t the blood pressure record to add
   */
  @Override
  public void add(BloodPressureRecord t) {
    bpRecordList.add(t);
  }

  /**
   * Remove a blood pressure record from the monitor.
   *
   * @param t the blood pressure record to remove
   */
  @Override
  public void remove(BloodPressureRecord t) {
    bpRecordList.remove(t);
  }

  /**
   * Return the number of blood pressure records in the monitor.
   *
   * @return the number of blood pressure records in the monitor
   */
  @Override
  public int getNumberOfRecords() {
    return bpRecordList.size();
  }

  /**
   * Return whether there is an emergency situation in the monitor.
   *
   * @return whether there is an emergency situation in the monitor
   */
  @Override
  public boolean emergency() {
    int count = 0;
    for (BloodPressureRecord t : bpRecordList) {
      if ((t.getSystolicReading() > 180) || (t.getDiastolicReading() > 120)) {
        count = 1;
        break;
      }
    }
    if (count == 0) {
      return false;
    }
    return true;
  }

}
