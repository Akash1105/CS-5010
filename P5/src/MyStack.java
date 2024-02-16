/**
 * MyStack class represents a stack data structure
 *
 * @param <T> the type of the items in the stack
 */

import java.util.ArrayList;

class MyStack<T> {

  private ArrayList<T> stack;

  /**
   * Constructor
   */
  public MyStack() {
    this.stack = new ArrayList<T>();
  }

  /**
   * Push method adds an item to the top of the stack
   * @param item the item to add
   */
  public void push(T item) {
    stack.add(item);
  }

  /**
   * Pop method removes and returns the item from the top of the stack
   * @return the item from the top of the stack
   * @throws IllegalStateException if the stack is empty
   */
  public T pop() throws IllegalStateException {
    if (stack.isEmpty()) {
      throw new IllegalStateException("Stack is empty");
    }
    return stack.remove(stack.size() - 1);
  }

  /**
   * Peek method returns the item from the top of the stack without removing it
   * @return the item from the top of the stack
   * @throws IllegalStateException if the stack is empty
   */
  public T peek() throws IllegalStateException {
    if (stack.isEmpty()) {
      throw new IllegalStateException("Stack is empty");
    }
    return stack.get(stack.size() - 1);
  }

  /**
   * Check if the stack is empty
   * @return true if the stack is empty, false otherwise
   */

  public boolean isEmpty() {
    return stack.isEmpty();
  }

  /**
   * Get the size of the stack
   * @return the size of the stack
   */
  public int getSize() {
    return stack.size();
  }

}
