package nz.ac.auckland.se281.datastructures;

public interface ListInterface<T> {
  public void append(T item);

  public void prepend(T item);

  public T fetch(int pos);

  public void insert(int pos, T data);

  public void remove(int pos);

  public int size();
}
