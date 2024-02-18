import java.util.List;

public interface SetOfPoints {

  /**
   * Add a point to the set of points.
   *
   * @param point the point to add
   */
  public void add(Point2D point);

  /**
   * Return all the points in the set.
   *
   * @return a list of all the points in the set
   */
  public List<Point2D> getPoints();

  /**
   * Return all the points in the set that are within a given radius of a given center.
   *
   * @param center the center of the circle
   * @param radius the radius of the circle
   * @return a list of all the points in the set that are within the given radius of the given
   * center
   */
  public List<Point2D> allPointsWithinCircle(Point2D center, double radius);

  /**
   * Return the point in the set that is closest to a given point.
   *
   * @param from the point from which to measure the distance
   * @return the point in the set that is closest to the given point
   */
  public Point2D closestPoint(Point2D from);

}
