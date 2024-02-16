/**
 * Vector3D class represents a vector which has three components: x, y and z.
 */

public class Vector3D {

  private double x;
  private double y;
  private double z;

  /**
   * Constructs a Vector3D object and initializes the x, y and z..
   *
   * @param x the value of x component.
   * @param y the value of y component.
   * @param z the value of z component.
   */
  public Vector3D(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  /**
   * Return the x component of this vector.
   *
   * @return a double.
   */
  public double getX() {
    return x;
  }

  /**
   * Return the y component of this vector.
   *
   * @return a double.
   */
  public double getY() {
    return y;
  }

  /**
   * Return the z component of this vector.
   *
   * @return a double.
   */
  public double getZ() {
    return z;
  }

  /**
   * Return the magnitude of this vector.
   *
   * @return a double.
   */
  public double getMagnitude() {
    double Magnitude;
    Magnitude = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    return Magnitude;
  }

  /**
   * overrides the default toString method.
   *
   * @return a string that reports this vector in the format (x, y, z).
   */

  @Override
  public String toString() {
    return "(" + String.format("%.2f", x) + ", " + String.format("%.2f", y)
        + ", " + String.format("%.2f", z) + ")";
  }

  /**
   * Return the normalized version of this vector.
   *
   * @return a Vector3D object that has normalized values.
   */
  public Vector3D Normalize() throws IllegalStateException {
    double Magnitude = getMagnitude();
    if (Magnitude <= 0) {
      throw new IllegalStateException("Magnitude is Zero.");
    }
    return new Vector3D(x / Magnitude, y / Magnitude, z / Magnitude);
  }



}
