import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class PointKDTree implements SetOfPoints {

  private List<Point2D> points = new ArrayList<>()
  public PointKDTree(List<Point2D> points) {
    if (points == null) {
      throw new IllegalArgumentException("List of points cannot be null");
    }
    if (points.isEmpty()) {
      throw new IllegalArgumentException("List of points cannot be empty");
    }
    List<Point2D> Px = new ArrayList<>(points);
    List<Point2D> Py = new ArrayList<>(points);
    Px.sort(Comparator.comparingInt(p -> p.x));
    Py.sort(Comparator.comparingInt(p -> p.y));

    recursiveBuildKdtree(points,Px,Py,2,0);
  }

  private void recursiveBuildKdtree(List<Point2D> points, List<Point2D> Px, List<Point2D> Py, int threshold, int depth) {
    if (points.size() <= threshold) {
      //return leaf node with points in Px (or Py)

      return;
    }
    int median = points.size() / 2;
    List<Point2D> left = points.subList(0, median);
    List<Point2D> right = points.subList(median, points.size());
    if (depth % 2 == 0) {
      left.sort(Comparator.comparingInt(p -> p.x));
      right.sort(Comparator.comparingInt(p -> p.x));
    } else {
      left.sort(Comparator.comparingInt(p -> p.y));
      right.sort(Comparator.comparingInt(p -> p.y));
    }
    recursiveBuildKdtree(left, Px, Py, depth + 1, threshold);
    recursiveBuildKdtree(right, Px, Py, depth + 1, threshold);
  }

  @Override
  public void add(Point2D point) {

    //Px, indices that give P sorted by x-coordinate
    //Py, indices that give P sorted by y-coordinate
    // threshold = maximum number of points in a node, if more than threshold, split
    //build2DTree(Px, Py, 0, threshold, 0);
    points.add(point);
  }

  @Override
  public List<Point2D> getPoints() {
    return null;
  }

  @Override
  public List<Point2D> allPointsWithinCircle(Point2D center, double radius) {
    return null;
  }

  @Override
  public Point2D closestPoint(Point2D from) {
    return null;
  }
}
