package nz.ac.auckland.se281.datastructures;

/**
 * A Stack implementation using LinkedList.
 *
 * @param <T> The type of elements in the stack.
 */
class Stack<T> {
  private LinkedListClass<T> stack;

  public Stack() {
    stack = new LinkedListClass<>();
  }

  public void push(T element) {
    stack.addFirst(element);
  }

  public T pop() {
    return stack.removeFirst();
  }

  public boolean isEmpty() {
    return stack.isEmpty();
  }

  public boolean contains(T element) {
    return stack.contains(element);
  }
}
