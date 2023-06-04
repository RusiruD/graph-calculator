package nz.ac.auckland.se281.datastructures;

public class LinkedListClass<T> implements ListInterface<T> {
  private Node<T> head;
  private int size;

  public LinkedListClass() {
    head = null;
    size = 0;
  }

  // Key methods of the List interface

  /**
   * This method adds a node with specified data as the start node of the list
   *
   * @param data: the value of the Node
   */

  /**
   * This method adds a node with specified data as the end node of the list
   *
   * @param data: the value of the Node
   */
  public void push(T data) {
    Node<T> newNode = new Node<>(data);
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

  /**
   * This method fetches the value of a node at a given position
   *
   * @param pos: the position
   * @return the value at the position pos
   * @throws IndexOutOfBoundsException if the position is invalid
   */
  public T fetch(int pos) {
    if (pos < 0 || pos >= size) {
      throw new IndexOutOfBoundsException("Invalid position");
    }
    Node<T> current = head;
    for (int i = 0; i < pos; i++) {
      current = current.getNext();
    }
    return current.getValue();
  }

  /**
   * This method inserts a node with specified data at a given position
   *
   * @param pos: the position
   * @param data: the value of the Node
   * @throws IndexOutOfBoundsException if the position is invalid
   */
  public void insert(int pos, T data) {
    if (pos < 0 || pos > size) {
      throw new IndexOutOfBoundsException("Invalid position");
    }
    if (pos == 0) {
      addFirst(data);
    } else if (pos == size) {
      push(data);
    } else {
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
   * This method removes a node at a given position
   *
   * @param pos: the position
   * @throws IndexOutOfBoundsException if the position is invalid
   */
  public void remove(int pos) {
    if (pos < 0 || pos >= size) {
      throw new IndexOutOfBoundsException("Invalid position");
    }
    if (pos == 0) {
      head = head.getNext();
    } else {
      Node<T> current = head;
      for (int i = 0; i < pos - 1; i++) {
        current = current.getNext();
      }
      current.setNext(current.getNext().getNext());
    }
    size--;
  }

  /**
   * This method returns the size of the list
   *
   * @return the size of the list
   */
  public int size() {
    return size;
  }

  /**
   * This method checks if the list is empty
   *
   * @return true if the list is empty, false otherwise
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * This method retrieves and removes the first element from the list
   *
   * @return the first element in the list
   * @throws IllegalStateException if the list is empty
   */
  public T poll() {
    if (isEmpty()) {
      throw new IllegalStateException("List is empty");
    }
    T data = head.getValue();
    head = head.getNext();
    size--;
    return data;
  }

  /**
   * This method adds an element to the end of the list
   *
   * @param data the element to be added
   */
  public void add(T data) {
    push(data);
  }

  /**
   * This method checks if the list contains the specified element
   *
   * @param element the element to be checked
   * @return true if the element is found in the list, false otherwise
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
   * This method retrieves and removes the last element from the list
   *
   * @return the last element in the list
   * @throws IllegalStateException if the list is empty
   */
  public T pop() {
    if (isEmpty()) {
      throw new IllegalStateException("List is empty");
    }
    if (size == 1) {
      T data = head.getValue();
      head = null;
      size = 0;
      return data;
    }
    Node<T> current = head;
    while (current.getNext().getNext() != null) {
      current = current.getNext();
    }
    T data = current.getNext().getValue();
    current.setNext(null);
    size--;
    return data;
  }

  /**
   * This method adds an element to the start of the list
   *
   * @param data the element to be added
   */
  public void addFirst(T data) {
    Node<T> newNode = new Node<>(data);
    newNode.setNext(head);
    head = newNode;
    size++;
  }

  /**
   * This method removes the first element from the list
   *
   * @return the removed element
   * @throws IllegalStateException if the list is empty
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
   * This method retrieves and removes the first element from the list
   *
   * @return the first element in the list
   * @throws IllegalStateException if the list is empty
   */
  public T pollFirst() {
    return removeFirst();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    Node<T> current = head;
    while (current != null) {
      sb.append(current.getValue()).append(" ");
      current = current.getNext();
    }
    return sb.toString().trim();
  }

  @Override
  public void append(T item) {
    push(item);
  }

  @Override
  public void prepend(T item) {
    Node<T> newNode = new Node<>(item);
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
}
