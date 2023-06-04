package nz.ac.auckland.se281.datastructures;

/** This package provides data structures for various purposes. */

/**
 * An interface that defines common operations for a list.
 *
 * @param <T> The type of elements in the list.
 */
public interface ListInterface<T> {
  /**
   * Appends an item to the end of the list.
   *
   * @param item The item to be appended.
   */
  public void append(T item);

  /**
   * Prepends an item to the start of the list.
   *
   * @param item The item to be prepended.
   */
  public void prepend(T item);

  /**
   * Fetches the value of the node at a given position in the list.
   *
   * @param pos The position of the node to fetch.
   * @return The value at the specified position.
   * @throws IndexOutOfBoundsException if the position is invalid.
   */
  public T fetch(int pos);

  /**
   * Inserts a node with specified data at a given position in the list.
   *
   * @param pos The position at which to insert the node.
   * @param data The value of the node to be inserted.
   * @throws IndexOutOfBoundsException if the position is invalid.
   */
  public void insert(int pos, T data);

  /**
   * Removes the node at a given position in the list.
   *
   * @param pos The position of the node to be removed.
   * @throws IndexOutOfBoundsException if the position is invalid.
   */
  public void remove(int pos);

  /**
   * Returns the size of the list.
   *
   * @return The size of the list.
   */
  public int size();
}
