import java.util.Objects;

public class SingleBloodPressureRecord implements BloodPressureRecord {

  private String id;
  private double sys;
  private double dia;

  /**
   * Construct a new blood pressure record.
   *
   * @param id  the ID of the blood pressure record
   * @param sys the systolic reading of the blood pressure record
   * @param dia the diastolic reading of the blood pressure record
   * @throws IllegalArgumentException if the ID is null or empty, or if the systolic or diastolic
   *                                  readings are negative
   */
  public SingleBloodPressureRecord(String id, double sys, double dia) {
    if (id == null) {
      throw new IllegalArgumentException("ID cannot be null");
    }
    if (id.isEmpty()) {
      throw new IllegalArgumentException("ID cannot be empty");
    }
    if (sys < 0) {
      throw new IllegalArgumentException("Systolic reading cannot be negative");
    }
    if (sys > 300) {
      throw new IllegalArgumentException("Systolic reading cannot be greater than 300");
    }
    if (dia < 0) {
      throw new IllegalArgumentException("Diastolic reading cannot be negative");
    }
    if (dia > 300) {
      throw new IllegalArgumentException("Systolic reading cannot be greater than 300");
    }

    this.id = id;
    this.sys = sys;
    this.dia = dia;
  }

  /** equality method for part-1
   *
   */

//  public boolean equality(SingleBloodPressureRecord o) {
//    boolean result = false;
//    if (this.id == o.id && this.sys - o.sys < 1.0 && this.dia - o.dia < 1.0) {
//      result = true;
//    }
//    return result;
//  }


  /**
   * equality method for part-2
   */
  public boolean equality(SingleBloodPressureRecord o) {
    boolean result = false;
    if ((this.id.equals(o.id)) && Math.ceil(this.sys) - Math.ceil(o.sys) == 0.0
        && Math.ceil(this.dia) - Math.ceil(o.dia) == 0.0) {
      result = true;
    }
    return result;
  }

  /**
   * Return the ID of the blood pressure record.
   *
   * @return the ID of the blood pressure record
   */
  @Override
  public String getID() {
    return this.id;
  }

  /**
   * Return the systolic reading of the blood pressure record.
   *
   * @return the systolic reading of the blood pressure record
   */
  @Override
  public double getSystolicReading() {
    return this.sys;
  }

  /**
   * Return the diastolic reading of the blood pressure record.
   *
   * @return the diastolic reading of the blood pressure record
   */
  @Override
  public double getDiastolicReading() {
    return this.dia;
  }

  /**
   * Update the systolic reading of the blood pressure record.
   *
   * @param sys the new systolic reading
   * @throws IllegalArgumentException if the new systolic reading is less than 0
   */
  @Override
  public void updateSystolicReading(double sys) throws IllegalArgumentException {
    this.sys = sys;
  }

  /**
   * Update the diastolic reading of the blood pressure record.
   *
   * @param dia the new diastolic reading
   * @throws IllegalArgumentException if the new diastolic reading is less than 0
   */
  @Override
  public void updateDiastolicReading(double dia) throws IllegalArgumentException {
    this.dia = dia;
  }
}
