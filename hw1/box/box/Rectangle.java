package box;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an axis-aligned rectangle with integer coordinates.
 * The rectangle is defined by its lower-left corner (x, y), width (w), and height (h).
 */
class Rectangle {

  private int x, y, w, h;

  /**
   * Constructs a new Rectangle with the specified coordinates, width, and height.
   *
   * @param x The x-coordinate of the lower-left corner.
   * @param y The y-coordinate of the lower-left corner.
   * @param w The width of the rectangle.
   * @param h The height of the rectangle.
   */
  public Rectangle(int x, int y, int w, int h) {
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getWidth() {
    return w;
  }

  public int getHeight() {
    return h;
  }

  /**
   * Checks whether this rectangle overlaps with another rectangle. Two rectangles overlap if they
   * share a non-zero area.
   *
   * @param other The other rectangle to check for overlap.
   * @return {@code true} if the rectangles overlap, {@code false} otherwise.
   */
  public boolean overlaps(Rectangle other) {
    return (x < other.x + other.w && x + w > other.x &&
        y < other.y + other.h && y + h > other.y);
  }

  /**
   * Calculates the contained difference between this rectangle and another rectangle. If the
   * rectangles do not overlap, a list containing this rectangle is returned. If they overlap, the
   * method splits this rectangle into smaller rectangles based on the overlap with the other
   * rectangle and returns the list of resulting rectangles.
   *
   * @param other The rectangle to subtract from this rectangle.
   * @return A list of rectangles representing the contained difference.
   */
  public List<Rectangle> containedDifference(Rectangle other) {
    List<Rectangle> result = new ArrayList<>();
    if (!overlaps(other)) {
      result.add(new Rectangle(x, y, w, h));
      return result;
    }

    // Handle top split
    if (y < other.y) {
      result.add(new Rectangle(x, y, w, other.y - y));
    }

    // Handle bottom split
    if (y + h > other.y + other.h) {
      result.add(new Rectangle(x, other.y + other.h, w, y + h - (other.y + other.h)));
    }

    // Handle left split
    if (x < other.x) {
      result.add(
          new Rectangle(x, Math.max(y, other.y), other.x - x, Math.min(h, other.y + other.h - y)));
    }

    // Handle right split
    if (x + w > other.x + other.w) {
      result.add(new Rectangle(other.x + other.w, Math.max(y, other.y), x + w - (other.x + other.w),
          Math.min(h, other.y + other.h - y)));
    }

    return result;
  }
}
