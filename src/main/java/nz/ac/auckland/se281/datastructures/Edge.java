package nz.ac.auckland.se281.datastructures;

/**
 * An edge in a graph that connects two verticies.
 *
 * <p>You must NOT change the signature of the constructor of this class.
 *
 * @param <T> The type of each vertex.
 */
public class Edge<T> {
  private T destination;
  private T source;

  public Edge(T source, T destination) {
    this.destination = destination;
    this.source=source;
  }
  public T returnDestination() {
    return this.destination;
  }
  public T returnSource() {
    return this.source;
  }

}
