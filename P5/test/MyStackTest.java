/**
 * Test file for MyStack class Tests the push, pop, peek, isEmpty, and getSize methods of the
 * MyStack class
 */

import static org.junit.Assert.*;

import org.junit.Test;

public class MyStackTest {

  /**
   * Test the push method
   */
  @Test
  public void testPush() {
    MyStack<Integer> stack = new MyStack<>();

    assertTrue(stack.isEmpty());
    assertEquals(0, stack.getSize());

    stack.push(1);
    stack.push(2);

    assertFalse(stack.isEmpty());
    assertEquals(2, stack.getSize());
    assertEquals(2, (int) stack.peek());
  }

  /**
   * Test the pop method
   */
  @Test
  public void testPop() {
    MyStack<Integer> stack = new MyStack<>();

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
   * Test popping from an empty stack
   */
  @Test
  public void testPopEmptyStack() {
    MyStack<String> stack = new MyStack<>();

    assertTrue(stack.isEmpty());

    // Attempting to pop from an empty stack should throw an exception
    assertThrows(IllegalStateException.class, stack::pop);
  }

  /**
   * Test the peek method
   */
  @Test
  public void testPeek() {
    MyStack<Integer> stack = new MyStack<>();

    stack.push(5);
    stack.push(10);

    assertEquals(10, (int) stack.peek());

    stack.pop();

    assertEquals(5, (int) stack.peek());
  }

  /**
   * Test peeking at an empty stack
   */
  @Test
  public void testPeekEmptyStack() {
    MyStack<Double> stack = new MyStack<>();

    assertTrue(stack.isEmpty());

    // Attempting to peek at an empty stack should throw an exception
    assertThrows(IllegalStateException.class, stack::peek);
  }

  /**
   * Test the isEmpty method
   */
  @Test
  public void testIsEmpty() {
    MyStack<String> stack = new MyStack<>();

    assertTrue(stack.isEmpty());

    stack.push("Hello");

    assertFalse(stack.isEmpty());

    stack.pop();

    assertTrue(stack.isEmpty());
  }

  /**
   * Test the size method
   */
  @Test
  public void testSize() {
    MyStack<Character> stack = new MyStack<>();

    assertEquals(0, stack.getSize());

    stack.push('A');
    stack.push('B');
    stack.push('C');

    assertEquals(3, stack.getSize());

    stack.pop();

    assertEquals(2, stack.getSize());
  }
}
