package box;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * SimpleBoxSet: Represents a set of non-overlapping rectangles.
 * Manages a set of rectangles, ensuring that no two rectangles in the set overlap.
 * Provides methods to add, subtract, and retrieve rectangles, maintaining this non-overlapping constraint.
 */

public class SimpleBoxSet implements BoxSet {

  private Set<Rectangle> rectangles;

  /**
   * Constructs an empty SimpleBoxSet.
   * Initializes the set of rectangles using a HashSet.
   */
  public SimpleBoxSet() {
    this.rectangles = new HashSet<>();
  }

  /**
   * Add a given rectangle to this set, and make this set the result.
   *
   * @param x      the x-coordinate of the rectangle to be added
   * @param y      the y-coordinate of the rectangle to be added
   * @param width  the width of the rectangle to be added
   * @param height the height of the rectangle to be added
   * @throws IllegalArgumentException if the width or height of the rectangle are not positive
   */
  @Override
  public void add(int x, int y, int width, int height) throws IllegalArgumentException {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Invalid rectangle dimensions");
    }

    Rectangle newRect = new Rectangle(x, y, width, height);
    Set<Rectangle> result = new HashSet<>();

    for (Rectangle rect : rectangles) {
      result.addAll(rect.containedDifference(newRect));
    }

    rectangles.add(newRect);
    rectangles.removeAll(result);
  }

  /**
   * Subtract the given rectangle from this set, and make this set the result.
   *
   * @param x      the x-coordinate of the rectangle to be subtracted
   * @param y      the y-coordinate of the rectangle to be subtracted
   * @param width  the width of the rectangle to be subtracted
   * @param height the height of the rectangle to be subtracted
   * @throws IllegalArgumentException if the width or height of the rectangle are not positive
   */
  @Override
  public void subtract(int x, int y, int width, int height) throws IllegalArgumentException {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Invalid rectangle dimensions");
    }

    Rectangle subtractRect = new Rectangle(x, y, width, height);
    Set<Rectangle> result = new HashSet<>();

    for (Rectangle rect : rectangles) {
      if (!rect.overlaps(subtractRect)) {
        result.add(rect);
      } else {
        result.addAll(rect.containedDifference(subtractRect));
      }
    }

    rectangles = result;
  }

  /**
   * Get all the rectangles in this set.
   *
   * @return an array with each element containing exactly four numbers: the x, y, width and height
   * of the rectangle in that order. For example, if there are two rectangles in this set, then the
   * first rectangle would be (arr[0][0],arr[0][1],arr[0][2],arr[0][3]) and the second rectangle
   * would be (arr[1][0],arr[1][1],arr[1][2],arr[1][3])
   */
  @Override
  public int[][] getBoxes() {
    int[][] arr = new int[rectangles.size()][4];
    int i = 0;

    for (Rectangle rect : rectangles) {
      arr[i][0] = rect.getX();
      arr[i][1] = rect.getY();
      arr[i][2] = rect.getWidth();
      arr[i][3] = rect.getHeight();
      i++;
    }

    return arr;
  }

  /**
   * @return size of rectangles
   */
  @Override
  public int size() {
    return rectangles.size();
  }
}
