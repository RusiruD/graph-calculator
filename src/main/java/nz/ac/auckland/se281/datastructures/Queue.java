package nz.ac.auckland.se281.datastructures;

/**
 * A Queue implementation using LinkedList.
 *
 * @param <T> The type of elements in the queue.
 */
class Queue<T> {
  private LinkedListClass<T> queue;

  /** Constructs an empty queue. */
  public Queue() {
    queue = new LinkedListClass<>();
  }

  /**
   * Adds an element to the end of the queue.
   *
   * @param element The element to be added.
   */
  public void add(T element) {
    queue.append(element);
  }

  /**
   * Checks if the queue contains a specific element.
   *
   * @param element The element to search for.
   * @return {@code true} if the element is found, {@code false} otherwise.
   */
  public boolean contains(T element) {
    return queue.contains(element);
  }

  /**
   * Removes and returns the first element from the queue.
   *
   * @return The first element in the queue.
   */
  public T poll() {
    return queue.removeFirst();
  }

  /**
   * Checks if the queue is empty.
   *
   * @return {@code true} if the queue is empty, {@code false} otherwise.
   */
  public boolean isEmpty() {
    return queue.isEmpty();
  }
}
