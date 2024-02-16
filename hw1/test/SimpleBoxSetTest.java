import box.SimpleBoxSet;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class SimpleBoxSetTest {

  @Test
  public void testAdd() {
    SimpleBoxSet boxSet = new SimpleBoxSet();

    // Test adding rectangles
    boxSet.add(10, 20, 50, 30);
    boxSet.add(5, 15, 20, 40);
    int[][] result = boxSet.getBoxes();
    assertEquals(2, boxSet.size());
    assertEquals(2, result.length);

    // Test adding rectangles with negative dimensions (should throw an exception)
    assertThrows(IllegalArgumentException.class, () -> boxSet.add(10, 20, -5, 30));
    assertThrows(IllegalArgumentException.class, () -> boxSet.add(10, 20, 50, -30));
  }

  @Test
  public void testSubtract() {
    SimpleBoxSet boxSet = new SimpleBoxSet();

    // Test subtracting rectangles
    boxSet.add(10, 20, 50, 30);
    boxSet.add(5, 15, 20, 40);
    boxSet.subtract(20, 25, 30, 20);
    int[][] result = boxSet.getBoxes();
    assertEquals(7, boxSet.size());
    assertEquals(7, result.length);
    assertEquals(5, result[0][0]); // Check the x-coordinate of the remaining rectangle

    // Test subtracting rectangles with negative dimensions (should throw an exception)
    assertThrows(IllegalArgumentException.class, () -> boxSet.subtract(10, 20, -5, 30));
    assertThrows(IllegalArgumentException.class, () -> boxSet.subtract(10, 20, 50, -30));
  }

  @Test
  public void testGetBoxes() {
    SimpleBoxSet boxSet = new SimpleBoxSet();

    // Test getting rectangles
    boxSet.add(10, 20, 50, 30);
    boxSet.add(5, 15, 20, 40);
    int[][] result = boxSet.getBoxes();
    assertEquals(2, result.length);
  }

  @Test
  public void testSize() {
    SimpleBoxSet boxSet = new SimpleBoxSet();

    // Test size after adding and subtracting rectangles
    assertEquals(0, boxSet.size());
    boxSet.add(10, 20, 50, 30);
    boxSet.add(5, 15, 20, 40);
    assertEquals(2, boxSet.size());
    boxSet.subtract(20, 25, 30, 20);
    assertEquals(7, boxSet.size());
  }
}
