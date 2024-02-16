/**
 * MyStackInheritanceTest A JUnit test class for the MyStackInheritance class. Tests the push, pop,
 * peek, isEmpty, and getSize methods. Also tests the behavior of the stack when it is empty.
 */

import static org.junit.Assert.*;

import org.junit.Test;

public class MyStackInheritanceTest {

  /**
   * Test the push method.
   */
  @Test
  public void testPush() {
    MyStackInheritance<Integer> stack = new MyStackInheritance<>();

    assertTrue(stack.isEmpty());
    assertEquals(0, stack.getSize());

    stack.push(1);
    stack.push(2);

    assertFalse(stack.isEmpty());
    assertEquals(2, stack.getSize());
    assertEquals(2, (int) stack.peek());
  }

  /**
   * Test the pop method.
   */
  @Test
  public void testPop() {
    MyStackInheritance<Integer> stack = new MyStackInheritance<>();

    stack.push(1);
    stack.push(2);
    stack.push(3);

    assertEquals(3, (int) stack.pop());
    assertEquals(2, (int) stack.pop());
    assertEquals(1, (int) stack.pop());

    assertTrue(stack.isEmpty());
    assertEquals(0, stack.getSize());
  }

  /**
   * Test the behavior of the stack when it is empty.
   */
  @Test
  public void testPopEmptyStack() {
    MyStackInheritance<String> stack = new MyStackInheritance<>();

    assertTrue(stack.isEmpty());

    // Attempting to pop from an empty stack should throw an exception
    assertThrows(IllegalStateException.class, stack::pop);
  }

  /**
   * Test the peek method.
   */
  @Test
  public void testPeek() {
    MyStackInheritance<Integer> stack = new MyStackInheritance<>();

    stack.push(5);
    stack.push(10);

    assertEquals(10, (int) stack.peek());

    stack.pop();

    assertEquals(5, (int) stack.peek());
  }

  /**
   * Test the behavior of the stack when it is empty.
   */
  @Test
  public void testPeekEmptyStack() {
    MyStackInheritance<Double> stack = new MyStackInheritance<>();

    assertTrue(stack.isEmpty());

    // Attempting to peek at an empty stack should throw an exception
    assertThrows(IllegalStateException.class, stack::peek);
  }

  /**
   * Test the isEmpty method.
   */
  @Test
  public void testIsEmpty() {
    MyStackInheritance<String> stack = new MyStackInheritance<>();

    assertTrue(stack.isEmpty());

    stack.push("Hello");

    assertFalse(stack.isEmpty());

    stack.pop();

    assertTrue(stack.isEmpty());
  }

  /**
   * Test the getSize method.
   */
  @Test
  public void testSize() {
    MyStackInheritance<Character> stack = new MyStackInheritance<>();

    assertEquals(0, stack.getSize());

    stack.push('A');
    stack.push('B');
    stack.push('C');

    assertEquals(3, stack.getSize());

    stack.pop();

    assertEquals(2, stack.getSize());
  }
}
