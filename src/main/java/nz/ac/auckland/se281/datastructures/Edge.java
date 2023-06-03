package nz.ac.auckland.se281.datastructures;

/**
 * An edge in a graph that connects two verticies.
 *
 * <p>You must NOT change the signature of the constructor of this class.
 *
 * @param <T> The type of each vertex.
 */
public class Edge<T> implements Comparable<Edge<T>> {
  private T destination;
  private T source;

  public Edge(T source, T destination) {
    this.destination = destination;
    this.source = source;
  }

  @Override
  public int compareTo(Edge<T> other) {
    // Compare source numbers
    int sourceComparison = Integer.compare(source.hashCode(), other.source.hashCode());
    if (sourceComparison != 0) {
      return sourceComparison;
    }

    // Source numbers are the same, compare destination numbers
    return Integer.compare(destination.hashCode(), other.destination.hashCode());
  }

  public T returnDestination() {
    return this.destination;
  }

  public T returnSource() {
    return this.source;
  }

  @Override
  public String toString() {
    // Build a string representation of the Edge
    return "Edge(" + source + " -> " + destination + ")";
  }
}
