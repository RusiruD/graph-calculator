package nz.ac.auckland.se281.datastructures;

/**
 * A Stack implementation using LinkedList.
 *
 * <p>/** A Stack implementation using LinkedList.
 *
 * @param <T> The type of elements in the stack.
 */
class Stack<T> {
  private LinkedListClass<T> stack;

  /** Constructs an empty stack. */
  public Stack() {
    stack = new LinkedListClass<>();
  }

  /**
   * Pushes an element onto the top of the stack.
   *
   * @param element The element to be pushed onto the stack.
   */
  public void push(T element) {
    stack.addFirst(element);
  }

  /**
   * Removes and returns the top element from the stack.
   *
   * @return The top element in the stack.
   */
  public T pop() {
    return stack.removeFirst();
  }

  /**
   * Checks if the stack is empty.
   *
   * @return {@code true} if the stack is empty, {@code false} otherwise.
   */
  public boolean isEmpty() {
    return stack.isEmpty();
  }

  /**
   * Checks if the stack contains a specific element.
   *
   * @param element The element to search for.
   * @return {@code true} if the element is found, {@code false} otherwise.
   */
  public boolean contains(T element) {
    return stack.contains(element);
  }
}
