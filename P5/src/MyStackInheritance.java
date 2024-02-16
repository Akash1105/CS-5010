/**
 * MyStackInheritance is a generic class that implements a stack using inheritance from the
 * ArrayList class. It has the following methods: - push: adds an item to the top of the stack -
 * pop: removes and returns the item from the top of the stack - peek: returns the item from the top
 * of the stack without removing it - isEmpty: checks if the stack is empty - getSize: returns the
 * size of the stack
 */

import java.util.ArrayList;

public class MyStackInheritance<T> extends ArrayList<T> {

  /** Constructor
   * Create a new stack
   */
  public MyStackInheritance() {
    super();
  }

  /** Push method adds an item to the top of the stack
   *
   * @param item
   */
  public void push(T item) {
    add(item);
  }

  /** Pop method removes and returns the item from the top of the stack
   *  @return the item from the top of the stack
   */
  public T pop() throws IllegalStateException {
    if (isEmpty()) {
      throw new IllegalStateException("Stack is empty");
    }
    return remove(size() - 1);
  }

  /** Peek method returns the item from the top of the stack without removing it
   *  @return the item from the top of the stack
   */
  public T peek() {
    if (isEmpty()) {
      throw new IllegalStateException("Stack is empty");
    }
    return get(size() - 1);
  }

  /**
   * Check if the stack is empty
   * @return true if the stack is empty, false otherwise
   */
  public boolean isEmpty() {
    return size() == 0;
  }

  /**
   * Get the size of the stack
   * @return the size of the stack
   */
  public int getSize() {
    return super.size();
  }

}
