package nz.ac.auckland.se281.datastructures;

/** This package provides data structures for various purposes. */

/**
 * A node in a linked list.
 *
 * @param <T> The type of value stored in the node.
 */
public class Node<T> {
  private T val;
  private Node<T> next;

  /** Constructs an empty node. */
  public Node() {}

  /**
   * Constructs a node with the specified value and no next node.
   *
   * @param v The value of the node.
   */
  public Node(T v) {
    val = v;
    this.next = null;
  }

  /**
   * Constructs a node with the specified value and next node.
   *
   * @param v The value of the node.
   * @param next The next node in the linked list.
   */
  public Node(T v, Node<T> next) {
    val = v;
    this.next = next;
  }

  /**
   * Sets the next node in the linked list.
   *
   * @param n The next node to be set.
   */
  public void setNext(Node<T> n) {
    next = n;
  }

  /**
   * Retrieves the next node in the linked list.
   *
   * @return The next node in the linked list.
   */
  public Node<T> getNext() {
    return next;
  }

  /**
   * Retrieves the value stored in the node.
   *
   * @return The value stored in the node.
   */
  public T getValue() {
    return val;
  }
}
