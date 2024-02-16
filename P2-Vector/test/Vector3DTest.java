import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test class for the Vector3D class.
 */

public class Vector3DTest {

  private Vector3D vector, vector1;

  /**
   * Setting up two vector objects to be used in testing.
   */
  @Before
  public void setUp() {
    vector = new Vector3D(1.0, 2.0, 3.0);
    vector1 = new Vector3D(0, 0, 0);
  }

  /**
   * Testing the return value from getX() with the expected value.
   */

  @Test
  public void testGetX() {
    assertEquals(1.0, vector.getX(), 1.0);
  }

  /**
   * Testing the return value from getY() with the expected value.
   */

  @Test
  public void testGetY() {
    assertEquals(2.0, vector.getY(), 2.0);
  }

  /**
   * Testing the return value from getZ() with the expected value.
   */

  @Test
  public void testGetZ() {
    assertEquals(3.0, vector.getZ(), 3.0);
  }

  /**
   * Testing the return value from getMagnitude() with the expected value.
   */
  @Test
  public void testGetMagnitude() {
    Assert.assertEquals(Math.sqrt(14.0), vector.getMagnitude(), 0.0001);
  }

  /**
   * Testing the return value from vector.toString() with the expected string output.
   */

  @Test
  public void testTestToString() {
    Assert.assertEquals("(1.00, 2.00, 3.00)", vector.toString());
  }

  /**
   * Testing the return value from Normalize() with the expected value.
   */

  @Test
  public void testNormalize() {
    Vector3D normalizedVector = vector.Normalize();
    Assert.assertEquals("(0.27, 0.53, 0.80)", normalizedVector.toString());
  }

  /**
   * Testing the IllegalStateExcepttion for Normalize() when the Magnitude is 0.
   */

  @Test
  public void testNormalizeIllegalStateExcepttion() {

    Throwable exception = assertThrows(
        IllegalStateException.class, () -> {
          Vector3D normalizedVector = vector1.Normalize();
        }
    );
    assertEquals("Magnitude is Zero.", exception.getMessage());
  }
}
