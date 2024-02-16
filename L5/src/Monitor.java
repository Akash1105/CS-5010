public interface Monitor {

  /**
   * Add a blood pressure record to the monitor.
   * @param t the blood pressure record to add
   */
  public void add(BloodPressureRecord t);

  /**
   * Remove a blood pressure record from the monitor.
   * @param t the blood pressure record to remove
   */
  public void remove(BloodPressureRecord t);

  /**
   * Return the number of blood pressure records in the monitor.
   * @return the number of blood pressure records in the monitor
   */

  public int getNumberOfRecords();

  /**
   * Return whether there is an emergency situation in the monitor.
   * @return whether there is an emergency situation in the monitor
   */

  public boolean emergency();

}
