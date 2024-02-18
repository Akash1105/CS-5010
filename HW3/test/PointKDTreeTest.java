import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * JUnit4 test class for the PointKDTree class.
 */
public class PointKDTreeTest {

  /**
   * Test the construction of PointKDTree with valid input.
   */
  @Test
  public void testWithValidInput() {
    List<Point2D> points = Arrays.asList(new Point2D(1, 2), new Point2D(3, 4), new Point2D(5, 6));
    PointKDTree kdTree = new PointKDTree(points);
    assertNotNull(kdTree);
  }

  /**
   * Test the behavior when null points are provided to the constructor.
   */
  @Test
  public void testWithNullPoints() {
    assertThrows(IllegalArgumentException.class, () -> new PointKDTree(null));
  }

  /**
   * Test the behavior when an empty list of points is provided to the constructor.
   */
  @Test
  public void testWithEmptyPoints() {
    List<Point2D> emptyPoints = new ArrayList<>();
    assertThrows(IllegalArgumentException.class, () -> new PointKDTree(emptyPoints));
  }

  /**
   * Test finding the closest point to a query point in PointKDTree.
   */
  @Test
  public void testClosestPoint() {
    List<Point2D> points = Arrays.asList(new Point2D(1, 2), new Point2D(3, 4), new Point2D(5, 6));

    PointKDTree kdTree = new PointKDTree(points);

    Point2D queryPoint = new Point2D(0, 0);

    Point2D closestPoint = kdTree.closestPoint(queryPoint);

    assertNotNull(closestPoint);
    assertTrue(points.contains(closestPoint));
  }

  /**
   * Test the addition of a point to PointKDTree.
   */
  @Test
  public void testAdd() {
    List<Point2D> points = Arrays.asList(new Point2D(1, 2), new Point2D(3, 4));
    PointKDTree kdTree = new PointKDTree(points);

    Point2D point = new Point2D(5, 6);
    kdTree.add(point);

    assertEquals(3, kdTree.getPoints().size());
    assertTrue(kdTree.getPoints().contains(point));
  }

  /**
   * Test finding all points within a circle in PointKDTree.
   */
  @Test
  public void testAllPointsWithinCircle() {
    List<Point2D> points = Arrays.asList(new Point2D(1, 2), new Point2D(3, 4), new Point2D(5, 6));

    PointKDTree kdTree = new PointKDTree(points);

    Point2D center = new Point2D(0, 0);
    double radius = 5;

    List<Point2D> result = kdTree.allPointsWithinCircle(center, radius);

    assertNotNull(result);
    assertEquals(3, result.size());
  }

  /**
   * Test points on splitting lines (x-axis, y-axis, arbitrary line).
   */
  @Test
  public void testPointsOnSplittingLines() {
    // Creating a KDTree with three points that lie on the x-axis splitting line
    List<Point2D> pointsOnXAxis = Arrays.asList(new Point2D(1, 0), new Point2D(2, 0),
        new Point2D(3, 0));
    PointKDTree kdTreeXAxis = new PointKDTree(pointsOnXAxis);

    // Creating a KDTree with three points that lie on the y-axis splitting line
    List<Point2D> pointsOnYAxis = Arrays.asList(new Point2D(0, 1), new Point2D(0, 2),
        new Point2D(0, 3));
    PointKDTree kdTreeYAxis = new PointKDTree(pointsOnYAxis);

    // Creating a KDTree with three points that lie on an arbitrary splitting line
    List<Point2D> pointsOnArbitraryLine = Arrays.asList(new Point2D(1, 2), new Point2D(2, 1),
        new Point2D(3, 0));
    PointKDTree kdTreeArbitraryLine = new PointKDTree(pointsOnArbitraryLine);

    // Asserting that all points on the splitting lines are within their respective KDTree
    assertEquals(3, kdTreeXAxis.allPointsWithinCircle(new Point2D(0, 0), 5).size());
    assertEquals(3, kdTreeYAxis.allPointsWithinCircle(new Point2D(0, 0), 5).size());
    assertEquals(3, kdTreeArbitraryLine.allPointsWithinCircle(new Point2D(0, 0), 5).size());
  }

  /**
   * Test the behavior for points very close to the query point.
   */
  @Test
  public void testPointsCloseToQueryPoint() {
    // Creating a KDTree with three points close to the query point (1, 1)
    List<Point2D> pointsCloseToQuery = Arrays.asList(new Point2D(1, 0), new Point2D(0, 1),
        new Point2D(1, 1));
    PointKDTree kdTreeCloseToQuery = new PointKDTree(pointsCloseToQuery);

    // Asserting that the closest point to the query point is indeed one of the points in the KDTree
    Point2D closestPoint = kdTreeCloseToQuery.closestPoint(new Point2D(1, 1));
    assertTrue(pointsCloseToQuery.contains(closestPoint));

    // Creating a KDTree with one point exactly at the query point
    List<Point2D> pointAtQuery = Arrays.asList(new Point2D(1, 1));
    PointKDTree kdTreeAtQuery = new PointKDTree(pointAtQuery);

    // Asserting that the closest point to the query point is the only point in the KDTree
    Point2D closestPointAtQuery = kdTreeAtQuery.closestPoint(new Point2D(1, 1));
    assertEquals(pointAtQuery.get(0), closestPointAtQuery);
  }
}
