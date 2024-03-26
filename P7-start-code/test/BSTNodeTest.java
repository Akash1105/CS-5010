
import org.junit.Test;
import solution.BSTNode;
import solution.BSTEmptyNode;

import java.util.*;
import java.util.List;

import static org.junit.Assert.*;

import solution.*;

/**
 * The type Bst node test.
 */
public class BSTNodeTest {

  /**
   * Test insertions.
   */
  @Test
  public void testInsertions() {
    BSTNode<Integer> root = new BSTEmptyNode<>();
    Set<Integer> expected = new TreeSet<Integer>();

    for (int i = 0; i < 1000; i++) {
      expected.add((int) (Math.random() * 2000 - 1000));
    }

    for (Integer e : expected) {
      root = root.insert(e);
    }

    StringBuilder sb = new StringBuilder();
    for (Integer e : expected) {
      sb.append(e + " ");

    }
    String output = sb.toString();
    output = output.substring(0, output.length() - 1);

    assertEquals(output, root.toString());
  }

  /**
   * Test min max.
   */
  @Test
  public void testMinMax() {
    BSTNode<Integer> root = new BSTEmptyNode<Integer>();
    TreeSet<Integer> expected = new TreeSet<Integer>();

    for (int i = 0; i < 1000; i++) {
      expected.add((int) (Math.random() * 2000 - 1000));
    }

    for (Integer e : expected) {
      root = root.insert(e);
    }

    assertEquals(expected.first(), root.minimum());
    assertEquals(expected.last(), root.maximum());
  }

  /**
   * Test min when empty.
   */
  @Test(expected = NothingThereException.class)
  public void testMinWhenEmpty() {
    new BSTEmptyNode<Integer>().minimum();
  }

  /**
   * Test max when empty.
   */
  @Test(expected = NothingThereException.class)
  public void testMaxWhenEmpty() {
    new BSTEmptyNode<Integer>().maximum();
  }

  /**
   * Test contains.
   */
  @Test
  public void testContains() {
    BSTNode<Integer> root = new BSTEmptyNode<>();
    List<Integer> expected = new ArrayList<Integer>();

    for (int i = 0; i < 1000; i++) {
      expected.add((int) (Math.random() * 2000 - 1000));
    }

    for (Integer e : expected) {
      root = root.insert(e);
    }

    for (int i = -1000; i <= 1000; i++) {
      assertEquals(expected.contains(i), root.contains(i));
    }
  }

  /*
                              10
                   5                   15
           1             7                      20
                2     6.      8               19
   */

  /**
   * Test preorder.
   */
  @Test
  public void testPreorder() {
    BSTNode<Integer> root = new BSTEmptyNode<>();

    root = root.insert(10);
    root = root.insert(15);
    root = root.insert(5);
    root = root.insert(1);
    root = root.insert(2);
    root = root.insert(7);
    root = root.insert(6);
    root = root.insert(8);
    root = root.insert(20);
    root = root.insert(19);

    List<Integer> expectedList = Arrays.asList(10, 5, 1, 2, 7, 6, 8, 15, 20, 19);

    List<Integer> actualList = new ArrayList<Integer>();

    root.preorder(i -> actualList.add(i));

    assertEquals(expectedList, actualList);


  }


  /**
   * Test postorder.
   */
/*
                                10
                     5                   15
             1             7                      20
                  2     6.      8               19
     */
  @Test
  public void testPostorder() {
    BSTNode<Integer> root = new BSTEmptyNode<>();

    root = root.insert(10);
    root = root.insert(15);
    root = root.insert(5);
    root = root.insert(1);
    root = root.insert(2);
    root = root.insert(7);
    root = root.insert(6);
    root = root.insert(8);
    root = root.insert(20);
    root = root.insert(19);

    List<Integer> expectedList = Arrays.asList(2, 1, 6, 8, 7, 5, 19, 20, 15, 10);

    List<Integer> actualList = new ArrayList<Integer>();

    root.postorder(i -> actualList.add(i));

    assertEquals(expectedList, actualList);


  }

  /**
   * Test copy.
   */
/*
                                10
                     5                   15
             1             7                      20
                  2     6.      8               19
     */
  @Test
  public void testCopy() {
    BSTNode<Integer> root = new BSTEmptyNode<>();

    root = root.insert(10);
    root = root.insert(15);
    root = root.insert(5);
    root = root.insert(1);
    root = root.insert(2);
    root = root.insert(7);
    root = root.insert(6);
    root = root.insert(8);
    root = root.insert(20);
    root = root.insert(19);

    List<Integer> expectedList = Arrays.asList(2, 1, 6, 8, 7, 5, 19, 20, 15, 10);

    List<Integer> actualList = new ArrayList<Integer>();

    BSTNode<Integer> copy = root.copy();

    copy.postorder(i -> actualList.add(i));

    assertEquals(expectedList, actualList);


  }

  /**
   * Test sameness.
   */
/*
                                10
                     5                   15
             1             7                      20
                  2     6.      8               19
     */
  @Test
  public void testSameness() {
    BSTNode<Integer> root = new BSTEmptyNode<>();
    BSTNode<Integer> anotherRoot = new BSTEmptyNode<Integer>();

    root = root.insert(10);
    root = root.insert(15);
    root = root.insert(5);
    root = root.insert(1);
    root = root.insert(2);
    root = root.insert(7);
    root = root.insert(6);
    root = root.insert(8);
    root = root.insert(20);
    root = root.insert(19);

    anotherRoot = anotherRoot.insert(10);
    anotherRoot = anotherRoot.insert(15);
    anotherRoot = anotherRoot.insert(5);
    anotherRoot = anotherRoot.insert(1);
    anotherRoot = anotherRoot.insert(2);
    anotherRoot = anotherRoot.insert(7);
    anotherRoot = anotherRoot.insert(6);
    anotherRoot = anotherRoot.insert(8);
    anotherRoot = anotherRoot.insert(20);
    anotherRoot = anotherRoot.insert(19);

    List<Integer> expectedList = Arrays.asList(2, 1, 6, 8, 7, 5, 19, 20, 15, 10);

    List<Integer> actualList = new ArrayList<Integer>();

    BSTNode<Integer> copy = root.copy();

    assertTrue(root.same(anotherRoot));
    assertTrue(root.same(copy));


  }

  /**
   * Test empty trees.
   */
  @Test
  public void testEmptyTrees() {
    BSTNode<Integer> root1 = new BSTEmptyNode<>();
    BSTNode<Integer> root2 = new BSTEmptyNode<>();

    assertTrue(root1.same(root2));
  }

  /**
   * Test different structures same data.
   */
  @Test
  public void testDifferentStructuresSameData() {
    BSTNode<Integer> root1 = new BSTEmptyNode<>();
    BSTNode<Integer> root2 = new BSTEmptyNode<>();

    root1 = root1.insert(2);
    root1 = root1.insert(1);
    root1 = root1.insert(3);

    root2 = root2.insert(2);
    root2 = root2.insert(3);
    root2 = root2.insert(1);

    assertTrue(root1.same(root2));
  }

  /**
   * Test same structure different data.
   */
  @Test
  public void testSameStructureDifferentData() {
    BSTNode<Integer> root1 = new BSTEmptyNode<>();
    BSTNode<Integer> root2 = new BSTEmptyNode<>();

    root1 = root1.insert(2);
    root1 = root1.insert(1);
    root1 = root1.insert(3);

    root2 = root2.insert(3);
    root2 = root2.insert(1);
    root2 = root2.insert(2);

    assertFalse(root1.same(root2));
  }

  /**
   * Test large trees.
   */
  @Test
  public void testLargeTrees() {
    BSTNode<Integer> root1 = new BSTEmptyNode<>();
    BSTNode<Integer> root2 = new BSTEmptyNode<>();

    Set<Integer> data = new HashSet<>();
    Random random = new Random();

    for (int i = 0; i < 1000; i++) {
      int value = random.nextInt(10000);
      data.add(value);
      root1 = root1.insert(value);
      root2 = root2.insert(value);
    }

    assertTrue(root1.same(root2));

    // Change one data element in root2
    int oldValue = data.iterator().next();
    int newValue = oldValue;
    while (newValue == oldValue) {
      newValue = random.nextInt(10000);
    }
    root2 = root2.insert(newValue);

    assertFalse(root1.same(root2));
  }
}
