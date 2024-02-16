/**
 * Vector3DMain is the main class.
 */

class Vector3DMain {

  public static void main(String[] args) {
    Vector3D vector = new Vector3D(1.0, 2.0, 3.0);
    /**
     * Calling all the methods from the Vector3D class and printing the output.
     */
    System.out.println("Value of X: " + vector.getX());
    System.out.println("Value of Y: " + vector.getY());
    System.out.println("Value of Z: " + vector.getZ());
    System.out.println("Magnitude: " + vector.getMagnitude());
    System.out.println("Vector: " + vector);
    System.out.println("Normalized Vector: " + vector.Normalize());
  }
}
