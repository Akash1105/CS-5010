public interface BloodPressureRecord {

  /**
   * Return the ID of the blood pressure record.
   *
   * @return the ID of the blood pressure record
   */
  public String getID();

  /**
   * Return the systolic reading of the blood pressure record.
   *
   * @return the systolic reading of the blood pressure record
   */
  public double getSystolicReading();

  /**
   * Return the diastolic reading of the blood pressure record.
   *
   * @return the diastolic reading of the blood pressure record
   */
  public double getDiastolicReading();

  /**
   * Update the systolic reading of the blood pressure record.
   *
   * @param sys the new systolic reading
   * @throws IllegalArgumentException if the new systolic reading is less than 0
   */
  public void updateSystolicReading(double sys) throws IllegalArgumentException;

  /**
   * Update the diastolic reading of the blood pressure record.
   *
   * @param dia the new diastolic reading
   * @throws IllegalArgumentException if the new diastolic reading is less than 0
   */
  public void updateDiastolicReading(double dia) throws IllegalArgumentException;

}
