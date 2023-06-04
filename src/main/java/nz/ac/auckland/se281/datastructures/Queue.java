package nz.ac.auckland.se281.datastructures;

/**
 * A Queue implementation using LinkedList.
 *
 * @param <T> The type of elements in the queue.
 */
class Queue<T> {
  private LinkedListClass<T> queue;

  public Queue() {
    queue = new LinkedListClass<>();
  }

  public void add(T element) {
    queue.append(element);
  }

  public boolean contains(T element) {
    return queue.contains(element);
  }

  public T poll() {
    return queue.removeFirst();
  }

  public boolean isEmpty() {
    return queue.isEmpty();
  }
}
