import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;

/**
 * JUnit test class for the Point2D class.
 */
public class Point2DTest {

  /**
   * Test creating a point and getting its coordinates.
   */
  @Test
  public void testCreatePoint() {
    Point2D point = new Point2D(3, 4);

    assertEquals(3, point.x);
    assertEquals(4, point.y);
  }

  /**
   * Test getting a valid dimension of the point.
   */
  @Test
  public void testGetValidDimension() {
    Point2D point = new Point2D(3, 4);

    assertEquals(3, point.get(0));
    assertEquals(4, point.get(1));
  }

  /**
   * Test getting an invalid dimension of the point (IllegalArgumentException expected).
   */
  @Test
  public void testGetInvalidDimension() {
    Point2D point = new Point2D(3, 4);

    assertThrows(IllegalArgumentException.class, () -> point.get(2));
  }

  /**
   * Test calculating the square distance between two points.
   */
  @Test
  public void testSqrDist() {
    Point2D point1 = new Point2D(1, 2);
    Point2D point2 = new Point2D(4, 6);

    assertEquals(25, point1.sqrDist(point2));
  }

  /**
   * Test calculating the Euclidean distance between two points.
   */
  @Test
  public void testDistance() {
    Point2D point1 = new Point2D(1, 2);
    Point2D point2 = new Point2D(4, 6);

    assertEquals(5, point1.distance(point2), 0);
  }

  /**
   * Test equality between two points.
   */
  @Test
  public void testEquals() {
    Point2D point1 = new Point2D(3, 4);
    Point2D point2 = new Point2D(3, 4);
    Point2D point3 = new Point2D(5, 6);

    assertEquals(point1, point2);
    assertNotEquals(point1, point3);
  }

  /**
   * Test the hashcode for a point.
   */
  @Test
  public void testHashCode() {
    Point2D point1 = new Point2D(3, 4);
    Point2D point2 = new Point2D(3, 4);
    Point2D point3 = new Point2D(5, 6);

    assertEquals(point1.hashCode(), point2.hashCode());
    assertNotEquals(point1.hashCode(), point3.hashCode());
  }
}
