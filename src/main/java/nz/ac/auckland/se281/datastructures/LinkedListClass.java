package nz.ac.auckland.se281.datastructures;

/**
 * A generic implementation of a singly linked list.
 *
 * @param <T> The type of elements in the linked list.
 */
public class LinkedListClass<T> implements ListInterface<T> {
  private Node<T> head;
  private int size;

  /** Constructs an empty linked list. */
  public LinkedListClass() {
    head = null;
    size = 0;
  }

  // Key methods of the List interface

  /**
   * Adds a node with the specified data as the start node of the list.
   *
   * @param data The value of the node to be added.
   */
  public void push(T data) {
    // Create a new node with the specified data
    Node<T> newNode = new Node<>(data);
    // Set the next node of the new node to be the current head
    if (head == null) {
      head = newNode;
    }
    // Set the head to be the new node

    else {
      Node<T> current = head;
      while (current.getNext() != null) {
        current = current.getNext();
      }
      current.setNext(newNode);
    }
    size++;
  }

  /**
   * Adds a node with the specified data as the end node of the list.
   *
   * @param data The value of the node to be added.
   */
  public void append(T data) {
    push(data);
  }

  /**
   * Inserts a node with the specified data at the given position in the list.
   *
   * @param pos The position at which to insert the node.
   * @param data The value of the node to be inserted.
   * @throws IndexOutOfBoundsException if the position is invalid.
   */
  public void insert(int pos, T data) {
    // if the position is invalid a exception is thrown
    if (pos < 0 || pos > size) {
      throw new IndexOutOfBoundsException("Invalid position");
    }
    // if the position is 0 the node is added to the start of the list
    if (pos == 0) {
      addFirst(data);
    }
    // if the position is the size of the list the node is added to the end of the list
    else if (pos == size) {
      push(data);
    }
    // otherwise the node is added to the specified position
    else {
      Node<T> newNode = new Node<>(data);
      Node<T> current = head;
      for (int i = 0; i < pos - 1; i++) {
        current = current.getNext();
      }
      newNode.setNext(current.getNext());
      current.setNext(newNode);
      size++;
    }
  }

  /**
   * Removes the node at the given position from the list.
   *
   * @param pos The position of the node to be removed.
   * @throws IndexOutOfBoundsException if the position is invalid.
   */
  public void remove(int pos) {
    // if the position is invalid a exception is thrown
    if (pos < 0 || pos >= size) {
      throw new IndexOutOfBoundsException("Invalid position");
    }
    // if the position is 0 the head is set to the next element
    if (pos == 0) {
      head = head.getNext();
    }
    // otherwise the next element of the previous element is set to the next element
    else {
      Node<T> current = head;
      for (int i = 0; i < pos - 1; i++) {
        current = current.getNext();
      }
      current.setNext(current.getNext().getNext());
    }
    size--;
  }

  /**
   * Returns the size of the list.
   *
   * @return The size of the list.
   */
  public int size() {
    return size;
  }

  /**
   * Checks if the list is empty.
   *
   * @return {@code true} if the list is empty, {@code false} otherwise.
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Retrieves and removes the first element from the list.
   *
   * @return The first element in the list.
   * @throws IllegalStateException if the list is empty.
   */
  public T pollFirst() {
    // if the queue is empty a exception is thrown
    if (isEmpty()) {
      throw new IllegalStateException("List is empty");
    }
    // the head is set to the next element
    // and the original head is returned
    T data = head.getValue();
    head = head.getNext();
    size--;
    return data;
  }

  /**
   * Adds an element to the start of the list.
   *
   * @param data The element to be added.
   */
  public void addFirst(T data) {
    Node<T> newNode = new Node<>(data);
    newNode.setNext(head);
    head = newNode;
    size++;
  }

  /**
   * Removes the first element from the list.
   *
   * @return The removed element.
   * @throws IllegalStateException if the list is empty.
   */
  public T removeFirst() {
    if (isEmpty()) {
      throw new IllegalStateException("List is empty");
    }
    T data = head.getValue();
    head = head.getNext();
    size--;
    return data;
  }

  /**
   * Retrieves and removes the first element from the list.
   *
   * @return The first element in the list.
   * @throws IllegalStateException if the list is empty.
   */
  public T poll() {
    return removeFirst();
  }

  /**
   * Adds an element to the end of the list.
   *
   * @param data The element to be added.
   */
  public void add(T data) {
    push(data);
  }

  /**
   * Checks if the list contains the specified element.
   *
   * @param element The element to be checked.
   * @return {@code true} if the element is found in the list, {@code false} otherwise.
   */
  public boolean contains(T element) {
    Node<T> current = head;
    while (current != null) {
      if (current.getValue().equals(element)) {
        return true;
      }
      current = current.getNext();
    }
    return false;
  }

  /**
   * Retrieves and removes the last element from the list.
   *
   * @return The last element in the list.
   * @throws IllegalStateException if the list is empty.
   */
  public T pop() {
    // if the queue is empty a exception is thrown
    if (isEmpty()) {
      throw new IllegalStateException("List is empty");
    }
    // if the queue has only one element, the head is set to null
    if (size == 1) {
      T data = head.getValue();
      head = null;
      size = 0;
      return data;
    }
    // if the queue has more than one element, the last element is removed
    Node<T> current = head;
    while (current.getNext().getNext() != null) {
      current = current.getNext();
    }
    T data = current.getNext().getValue();
    current.setNext(null);
    size--;
    return data;
  }

  @Override
  public String toString() {
    // if the queue is empty, a empty string is returned
    StringBuilder sb = new StringBuilder();
    Node<T> current = head;
    // the elements are appended to the string while the current node is not null
    while (current != null) {
      sb.append(current.getValue()).append(" ");
      current = current.getNext();
    }
    return sb.toString().trim();
  }

  @Override
  public void prepend(T item) {

    Node<T> newNode = new Node<>(item);
    // if the queue is empty, the new node is set as head
    if (head == null) {
      head = newNode;
    } else {
      Node<T> current = head;
      while (current.getNext() != null) {
        current = current.getNext();
      }
      current.setNext(newNode);
    }
    size++;
  }

  @Override
  public T fetch(int pos) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'fetch'");
  }
}
