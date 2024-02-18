import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


/**
 * This class represents a set of points using a KD-tree.
 */
public class PointKDTree implements SetOfPoints {

  /**
   * The root of the KD-tree.
   */
  private KDTreeNode root;

  /**
   * Create a new PointKDTree from a list of points.
   *
   * @param points the list of points
   * @throws IllegalArgumentException if the list of points is null or empty
   */
  public PointKDTree(List<Point2D> points) {
    if (points == null || points.isEmpty()) {
      throw new IllegalArgumentException("List of points cannot be null or empty");
    }

    List<Point2D> px = new ArrayList<>(points);
    List<Point2D> py = new ArrayList<>(points);

    root = recursiveBuildKDTree(points, px, py, 0);
  }

  /**
   * Recursively build a KD-tree from a list of points.
   *
   * @param points the list of points
   * @param px     the list of points sorted by x-coordinate
   * @param py     the list of points sorted by y-coordinate
   * @param depth  the depth of the current node in the KD-tree
   * @return the root of the KD-tree
   */
  private KDTreeNode recursiveBuildKDTree(List<Point2D> points, List<Point2D> px, List<Point2D> py,
      int depth) {
    if (px.size() <= 0) {
      return null;
    }

    int medianIndex;
    if (depth % 2 == 0) {
      px.sort(Comparator.comparingDouble(p -> p.get(0)));
      medianIndex = px.size() / 2;
    } else {
      py.sort(Comparator.comparingDouble(p -> p.get(1)));
      medianIndex = py.size() / 2;
    }

    Point2D medianPoint = px.get(medianIndex);

    List<Point2D> pxBefore = new ArrayList<>();
    List<Point2D> pxAfter = new ArrayList<>();
    List<Point2D> on = new ArrayList<>();
    List<Point2D> pyBefore = new ArrayList<>();
    List<Point2D> pyAfter = new ArrayList<>();

    for (Point2D p : px) {
      double sd = signedDistance(p, medianPoint, depth % 2 == 0);
      if (sd < 0) {
        pxBefore.add(p);
      } else if (sd > 0) {
        pxAfter.add(p);
      } else {
        on.add(p);
      }
    }

    for (Point2D p : py) {
      double sd = signedDistance(p, medianPoint, depth % 2 == 1);
      if (sd < 0) {
        pyBefore.add(p);
      } else if (sd > 0) {
        pyAfter.add(p);
      }
    }

    KDTreeNode left = recursiveBuildKDTree(points, pxBefore, pyBefore, depth + 1);
    KDTreeNode right = recursiveBuildKDTree(points, pxAfter, pyAfter, depth + 1);

    return new KDTreeNode(left, right, medianPoint, on, depth % 2 == 0);
  }

  /**
   * Return the signed distance between a point and a line.
   *
   * @param point     the point
   * @param linePoint the point on the line
   * @param useX      whether to use the x-coordinate of the point
   * @return the signed distance
   */
  private double signedDistance(Point2D point, Point2D linePoint, boolean useX) {
    return useX ? point.get(0) - linePoint.get(0) : point.get(1) - linePoint.get(1);
  }

  /**
   * Add a point to the set of points.
   *
   * @param point the point to add
   */
  @Override
  public void add(Point2D point) {
    root = add(root, point, 0);
  }

  /**
   * Recursively add a point to the KD-tree.
   *
   * @param node  the current node
   * @param point the point to add
   * @param depth the depth of the current node in the KD-tree
   * @return the root of the KD-tree
   */
  private KDTreeNode add(KDTreeNode node, Point2D point, int depth) {
    if (node == null) {
      List<Point2D> on = new ArrayList<>();
      on.add(point);
      return new KDTreeNode(null, null, point, on, depth % 2 == 0);
    }

    int cmp = comparePoints(point, node.linePoint, depth % 2 == 0);
    if (cmp < 0) {
      node.left = add(node.left, point, depth + 1);
    } else if (cmp > 0) {
      node.right = add(node.right, point, depth + 1);
    } else {
      node.onLinePoints.add(point);
    }

    return node;
  }

  /**
   * Compare two points based on a given dimension.
   *
   * @param p1   the first point
   * @param p2   the second point
   * @param useX whether to use the x-coordinate
   * @return the result of the comparison
   */
  private int comparePoints(Point2D p1, Point2D p2, boolean useX) {
    return useX ? Double.compare(p1.get(0), p2.get(0)) : Double.compare(p1.get(1), p2.get(1));
  }

  /**
   * Return all the points in the set.
   *
   * @return a list of all the points in the set
   */
  @Override
  public List<Point2D> getPoints() {
    List<Point2D> allPoints = new ArrayList<>();
    collectPoints(root, allPoints);
    return allPoints;
  }

  /**
   * Recursively collect all the points in the KD-tree.
   *
   * @param node   the current node
   * @param points the list of points
   */
  private void collectPoints(KDTreeNode node, List<Point2D> points) {
    if (node != null) {
      points.addAll(node.onLinePoints);
      collectPoints(node.left, points);
      collectPoints(node.right, points);
    }
  }

  /**
   * Return all the points in the set that are within a given radius of a given center.
   *
   * @param center the center of the circle
   * @param radius the radius of the circle
   * @return a list of all the points in the set that are within the given radius of the given
   * center
   */
  @Override
  public List<Point2D> allPointsWithinCircle(Point2D center, double radius) {
    List<Point2D> pointsWithinCircle = new ArrayList<>();
    searchWithinCircle(root, center, radius, pointsWithinCircle);
    return pointsWithinCircle;
  }

  /**
   * Recursively search for all the points within a circle in the KD-tree.
   *
   * @param node   the current node
   * @param center the center of the circle
   * @param radius the radius of the circle
   * @param result the list of points within the circle
   */
  private void searchWithinCircle(KDTreeNode node, Point2D center, double radius,
      List<Point2D> result) {
    if (node == null) {
      return;
    }

    double sd = signedDistance(center, node.linePoint, node.useX);
    if (sd <= 0) {
      searchWithinCircle(node.left, center, radius, result);
      if (Math.abs(sd) < radius) {
        searchWithinCircle(node.right, center, radius, result);
      }
    } else {
      searchWithinCircle(node.right, center, radius, result);
      if (Math.abs(sd) < radius) {
        searchWithinCircle(node.left, center, radius, result);
      }
    }

    result.addAll(node.onLinePoints);
  }

  /**
   * Return the point in the set that is closest to a given point.
   *
   * @param queryPoint the point from which to measure the distance
   * @return the point in the set that is closest to the given point
   */
  @Override
  public Point2D closestPoint(Point2D queryPoint) {
    ClosestPointData data = new ClosestPointData();
    findClosestPoint(root, queryPoint, data);
    return data.closestPoint != null ? data.closestPoint : null;
  }

  private static class ClosestPointData {

    Point2D closestPoint;
    double minDistance = Double.MAX_VALUE;
  }

  /**
   * Recursively find the closest point to a query point in the KD-tree.
   *
   * @param node       the current node
   * @param queryPoint the query point
   * @param data       the closest point data
   */
  private void findClosestPoint(KDTreeNode node, Point2D queryPoint, ClosestPointData data) {
    if (node == null) {
      return;
    }

    for (Point2D p : node.onLinePoints) {
      double distance = p.distance(queryPoint);
      if (distance < data.minDistance) {
        data.minDistance = distance;
        data.closestPoint = p;
      }
    }

    double sd = signedDistance(queryPoint, node.linePoint, node.useX);
    if (sd < 0) {
      findClosestPoint(node.left, queryPoint, data);
      if (Math.abs(sd) <= data.minDistance) {
        findClosestPoint(node.right, queryPoint, data);
      }
    } else {
      findClosestPoint(node.right, queryPoint, data);
      if (Math.abs(sd) <= data.minDistance) {
        findClosestPoint(node.left, queryPoint, data);
      }
    }
  }

  private static class KDTreeNode {

    KDTreeNode left;
    KDTreeNode right;
    Point2D linePoint;
    List<Point2D> onLinePoints;
    boolean useX;

    public KDTreeNode(KDTreeNode left, KDTreeNode right, Point2D linePoint,
        List<Point2D> onLinePoints, boolean useX) {
      this.left = left;
      this.right = right;
      this.linePoint = linePoint;
      this.onLinePoints = onLinePoints;
      this.useX = useX;
    }
  }
}
