package solution;

import java.util.function.Consumer;

/**
 * This class represents a data-containing node of the binary search tree
 * It mutates on all relevant operations
 * @param <T>    the type parameter
 */
public class BSTElementNode<T extends Comparable<T>> implements BSTNode<T> {
  private BSTNode<T> left;
  private BSTNode<T> right;
  private final T data;

  /**
   * Instantiates a new Bst element node.
   *
   * @param data the data
   * @param left the left
   * @param right the right
   */
public BSTElementNode(T data,BSTNode<T> left,BSTNode<T> right) {
    this.data = data;
    this.left = left;
    this.right = right;
  }
  @Override
  public BSTNode insert(T data) {
    if (data.compareTo(this.data)<0) {
      this.left = this.left.insert(data);
    } else if (data.compareTo(this.data)>0) {
      this.right = this.right.insert(data);
    }
    return this;
  }

  @Override
  public T minimum() {
    T minimum;

    try {
      minimum = this.left.minimum();
    }
    catch (NothingThereException e) {
      minimum = this.data;
    }
    return minimum;
  }

  @Override
  public T maximum() {
    T maximum;

    try {
      maximum = this.right.maximum();
    }
    catch (NothingThereException e) {
      maximum = this.data;
    }

    return maximum;
  }

  @Override
  public boolean contains(T data) {
    int compareResult = data.compareTo(this.data);

    if (compareResult==0)  {
      return true;
    }
    else if (compareResult<0) {
      return this.left.contains(data);
    }
    else {
      return this.right.contains(data);
    }
  }

  @Override
  public String toString() {
    String left,right,middle;

    middle = this.data.toString();
    left = this.left.toString();
    right = this.right.toString();
    if (!left.isEmpty()) left = left + " ";
    if (!right.isEmpty()) right = " " + right;
    return left + middle + right;
  }

  @Override
  public void preorder(Consumer<T> consumer) {
    // Visit the current node
    consumer.accept(data);

    // Recursively traverse the left subtree
    if (left != null) {
      left.preorder(consumer);
    }

    // Recursively traverse the right subtree
    if (right != null) {
      right.preorder(consumer);
    }
  }


  @Override
  public void postorder(Consumer<T> consumer) {
    // Recursively traverse the left subtree
    if (left != null) {
      left.postorder(consumer);
    }

    // Recursively traverse the right subtree
    if (right != null) {
      right.postorder(consumer);
    }

    // Visit the current node
    consumer.accept(data);
  }

  @Override
  public BSTNode<T> copy() {
    // Create a new instance of BSTElementNode with the same data
    BSTElementNode<T> newNode = new BSTElementNode<>(data, null, null);

    // Recursively copy the left and right subtrees
    if (left != null) {
      newNode.left = left.copy();
    }
    if (right != null) {
      newNode.right = right.copy();
    }

    return newNode;
  }

  @Override
  public boolean same(BSTNode<T> other) {
    if (!(other instanceof BSTElementNode)) {
      return false;
    }

    BSTElementNode<T> otherNode = (BSTElementNode<T>) other;

    // Check if the data of the current node and the other node are equal
    if (!this.data.equals(otherNode.data)) {
      return false;
    }

    // Recursively check if the left subtrees are the same
    if (this.left == null && otherNode.left != null ||
      this.left != null && otherNode.left == null ||
      this.left != null && !this.left.same(otherNode.left)) {
      return false;
    }

    // Recursively check if the right subtrees are the same
    if (this.right == null && otherNode.right != null ||
      this.right != null && otherNode.right == null ||
      this.right != null && !this.right.same(otherNode.right)) {
      return false;
    }

    return true;
  }
}
