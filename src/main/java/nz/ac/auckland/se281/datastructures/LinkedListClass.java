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
  public void prepend(T data) {
    Node<T> newNode = new Node<>(data);
    newNode.setNext(head);
    head = newNode;
    size++;
  }

  /**
   * This method adds a node with specified data as the end node of the list
   *
   * @param data: the value of the Node
   */
  public void append(T data) {
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
      prepend(data);
    } else if (pos == size) {
      append(data);
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
}
