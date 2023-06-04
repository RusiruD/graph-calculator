package nz.ac.auckland.se281.datastructures;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * A graph that is composed of a set of verticies and edges.
 *
 * <p>You must NOT change the signature of the existing methods or constructor of this class.
 *
 * @param <T> The type of each vertex, that have a total ordering.
 */
public class Graph<T extends Comparable<T>> {
  private Set<T> verticies;
  private Set<Edge<T>> edges;

  /**
   * Constructs a Graph with the given set of vertices and edges.
   *
   * @param vertices The set of vertices in the graph.
   * @param edges The set of edges in the graph.
   */
  public Graph(Set<T> verticies, Set<Edge<T>> edges) {
    this.verticies = verticies;
    this.edges = edges;
  }

  /**
   * Returns the in-degree of a given vertex.
   *
   * @param vertex The vertex to get the in-degree for.
   * @return The in-degree of the given vertex.
   */
  public int getInDegree(T vertex) {
    int inDegree = 0;

    // calculates indegree by counting the number of edges that have the vertex as the destination
    for (Edge<T> edge : edges) {
      if (edge.returnDestination().equals(vertex)) {
        inDegree++;
      }
    }
    return inDegree;
  }

  /**
   * Returns the set of roots in the graph.
   *
   * @return The set of roots in the graph.
   */
  public Set<T> getRoots() {

    int counter = 0;

    List<T> x = new ArrayList<T>(verticies);
    Set<T> orderedSet = new LinkedHashSet<>();

    x.sort(Comparator.comparingInt(vertex -> Integer.parseInt(vertex.toString())));
    System.out.println(x);

    for (T verticie : x) {
      // System.out.println(verticie);

      int inDegree = getInDegree(verticie);

      if (inDegree == 0) {

        counter++;

        orderedSet.add(verticie);
      }
    }

    if (counter == 0) {

      Set<T> initialEquivalenceClassSet = getEquivalenceClass(verticies.iterator().next());
      if (!initialEquivalenceClassSet.isEmpty()) {
        T firstValueT = initialEquivalenceClassSet.iterator().next();
        orderedSet.add(firstValueT);
      }

      if (!initialEquivalenceClassSet.isEmpty()) {
        for (T verticie : verticies) {
          Set<T> equivalenceClassSet = getEquivalenceClass(verticie);

          if (equivalenceClassSet.iterator().next() != orderedSet.iterator().next()) {
            orderedSet.add(equivalenceClassSet.iterator().next());
          }
        }
      }
    }

    return orderedSet;
  }

  /**
   * Checks whether the graph is reflexive.
   *
   * @return true if the graph is reflexive, false otherwise.
   */
  public boolean isReflexive() {
    int reflexiveEdgeCounter = 0;
    for (Edge<T> edge : edges) {
      if (edge.returnDestination().equals(edge.returnSource())) {
        reflexiveEdgeCounter++;
      }
    }
    if (reflexiveEdgeCounter == verticies.size()) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Checks whether the graph is symmetric.
   *
   * @return true if the graph is symmetric, false otherwise.
   */
  public boolean isSymmetric() {

    int symmetricEdgeCounter = 0;
    for (Edge<T> edge : edges) {

      for (Edge<T> edge1 : edges) {
        if (edge.returnDestination().equals(edge1.returnSource())
            && edge.returnSource().equals(edge1.returnDestination())) {

          symmetricEdgeCounter++;
        }
      }
    }

    if (symmetricEdgeCounter == edges.size()) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Checks whether the graph is transitive.
   *
   * @return true if the graph is transitive, false otherwise.
   */
  public boolean isTransitive() {
    int possibleTransitiveEdgeCounter = 0;
    int transitiveEdgeCounter = 0;
    for (Edge<T> edge : edges) {
      for (Edge<T> edge1 : edges) {

        if (edge.returnDestination().equals(edge1.returnSource())
            && !edge1.returnDestination().equals(edge1.returnSource())) {
          possibleTransitiveEdgeCounter++;

          for (Edge<T> edge3 : edges) {
            if (edge.returnSource().equals(edge3.returnSource())
                && edge1.returnDestination().equals(edge3.returnDestination())) {
              transitiveEdgeCounter++;
            }
          }
        }
      }
    }
    if (possibleTransitiveEdgeCounter == transitiveEdgeCounter) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Checks whether the graph is anti-symmetric.
   *
   * @return true if the graph is anti-symmetric, false otherwise.
   */
  public boolean isAntiSymmetric() {
    int antisymmetricEdgeCounter = 0;
    int possibleAntiSymmetricEdgeCounter = 0;
    for (Edge<T> edge : edges) {

      for (Edge<T> edge1 : edges) {

        if (edge.returnDestination().equals(edge1.returnSource())
            && edge.returnSource().equals(edge1.returnDestination())) {
          possibleAntiSymmetricEdgeCounter++;
          if (edge.equals(edge1)) {
            antisymmetricEdgeCounter++;
          }
        }
      }
    }

    if (possibleAntiSymmetricEdgeCounter == antisymmetricEdgeCounter) {
      return true;
    } else {

      return false;
    }
  }

  /**
   * Checks whether the graph is an equivalence relation.
   *
   * @return true if the graph is an equivalence relation, false otherwise.
   */
  public boolean isEquivalence() {
    if (isReflexive() && isSymmetric() && isTransitive()) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Returns the equivalence class for a given vertex.
   *
   * @param vertex The vertex to get the equivalence class for.
   * @return The equivalence class for the given vertex.
   */
  public Set<T> getEquivalenceClass(T vertex) {
    Set<T> equivalenceClass = new HashSet<>();
    if (!isEquivalence()) {
      return equivalenceClass;
    } else {
      for (Edge<T> edge : edges) {
        if (edge.returnSource().equals(vertex)) {
          equivalenceClass.add(edge.returnDestination());
        } else if (edge.returnDestination().equals(vertex)) {
          equivalenceClass.add(edge.returnSource());
        }
      }
      return equivalenceClass;
    }
  }

  /**
   * Returns the list of children for a given vertex.
   *
   * @param vertex The vertex to get the children for.
   * @return The list of children for the given vertex.
   */
  public List<T> getChildren(T vertex) {

    List<T> x = new ArrayList<T>();

    for (Edge<T> edge : edges) {
      if (edge.returnSource().equals(vertex) && !edge.returnDestination().equals(vertex)) {

        x.add(edge.returnDestination());
      }
    }
    x.sort(Comparator.comparingInt(z -> Integer.parseInt(z.toString())));

    return x;
  }

  /**
   * Performs iterative Breadth-First Search (BFS) on the graph.
   *
   * @return The list of vertices visited during BFS.
   */
  public List<T> iterativeBreadthFirstSearch() {
    // implementing BFS using queue
    List<T> visited = new ArrayList<>();
    List<T> queue = new ArrayList<>();
    Set<T> rootsSet = getRoots();
    for (int i = 0; i < getRoots().size(); i++) {
      queue.add(rootsSet.iterator().next());
      rootsSet.remove(rootsSet.iterator().next());

      while (!queue.isEmpty()) {

        T firstQueueValue = queue.remove(0);

        visited.add(firstQueueValue);

        List<T> childrenList = getChildren(firstQueueValue);

        for (T childNode : childrenList) {
          if (!visited.contains(childNode) && !queue.contains(childNode)) {

            queue.add(childNode);
          }
        }
      }
    }
    return visited;
  }

  /**
   * Performs iterative Depth-First Search (DFS) on the graph.
   *
   * @return The list of vertices visited during DFS.
   */
  public List<T> iterativeDepthFirstSearch() {
    // implementing iterative DFS using stack
    List<T> visited = new ArrayList<>();
    Stack<T> stack = new Stack<>();
    Set<T> rootsSet = getRoots();

    for (int i = 0; i < getRoots().size(); i++) {
      stack.push(rootsSet.iterator().next());
      rootsSet.remove(rootsSet.iterator().next());
      while (!stack.isEmpty()) {
        T topStackValue = stack.pop();
        visited.add(topStackValue);

        List<T> childrenList = getChildren(topStackValue);
        int left = 0;
        int right = childrenList.size() - 1;

        while (left < right) {
          T temp = childrenList.get(left);
          childrenList.set(left, childrenList.get(right));
          childrenList.set(right, temp);

          left++;
          right--;
        }

        for (T child : childrenList) {
          if (!visited.contains(child) && !stack.contains(child)) {
            stack.push(child);
          }
        }
      }
    }

    return visited;
  }

  /**
   * Returns the siblings of a given vertex.
   *
   * @param vertex The vertex to get the siblings for.
   * @param visited The list of visited vertices.
   * @return The list of siblings for the given vertex.
   */
  public List<T> returnSiblings(T vertex, List<T> visited) {
    List<T> siblings = new ArrayList<>();

    // Find the source vertex of the input vertex
    List<T> sourceVertices = new ArrayList<>();
    T sourceVertex = null;

    // Find the source vertices of the input vertex
    for (Edge<T> edge : edges) {
      if (edge.returnDestination().equals(vertex)) {
        sourceVertices.add(edge.returnSource());
      }
    }

    if (!sourceVertices.isEmpty()) {
      sourceVertex = sourceVertices.get(0);
    }

    // Find the vertices that have an edge from the source
    List<Edge<T>> sortedEdges = new ArrayList<>(edges);

    Comparator<Edge<T>> edgeComparator =
        Comparator.comparing((Edge<T> edge) -> edge.returnSource())
            .thenComparing((Edge<T> edge) -> edge.returnDestination());
    Comparator<Edge<T>> oppositeEdgeComparator = edgeComparator.reversed();
    sortedEdges.sort(oppositeEdgeComparator);

    if (sourceVertex != null) {
      for (Edge<T> edge : sortedEdges) {
        if (edge.returnSource().equals(sourceVertex)
            && edge.returnDestination() != vertex
            && !siblings.contains(edge.returnDestination())) {
          siblings.add(edge.returnDestination());
        }
      }
    }

    return siblings;
  }

  /**
   * Recursive Best-First Search (RBFS) helper function.
   *
   * @param queue The queue of vertices.
   * @param visited The list of visited vertices.
   * @return The list of visited vertices during RBFS.
   */
  public List<T> rbfs(Queue<T> queue, List<T> visited) {
    if (queue.isEmpty()) {
      return visited;
    }

    T vertex = queue.poll();
    visited.add(vertex);

    List<T> siblings = returnSiblings(vertex, visited);

    if (siblings.isEmpty()) {
      for (T visitedVertex : visited) {
        List<T> children = getChildren(visitedVertex);
        for (T child : children) {
          if (!visited.contains(child) && !queue.contains(child)) {
            queue.add(child);
          }
        }
      }
    } else if (visited.containsAll(siblings)) {
      for (T visitedVertex : visited) {
        List<T> children = getChildren(visitedVertex);
        for (T child : children) {
          if (!visited.contains(child) && !queue.contains(child)) {
            queue.add(child);
          }
        }
      }
    } else {
      for (T sibling : siblings) {
        if (!visited.contains(sibling) && !queue.contains(sibling)) {
          queue.add(sibling);
        }
      }
    }

    return rbfs(queue, visited);
  }

  /**
   * Performs recursive Breadth-First Search (BFS) on the graph.
   *
   * @return The list of vertices visited during BFS.
   */
  public List<T> recursiveBreadthFirstSearch() {
    List<T> visited = new ArrayList<>();
    Queue<T> queue = new Queue<T>();
    Set<T> roots = getRoots();
    int rootAmount = getRoots().size();

    for (int i = 0; i < rootAmount; i++) {
      queue.add(roots.iterator().next());
      roots.remove(roots.iterator().next());

      rbfs(queue, visited);
    }

    return visited;
  }

  /**
   * Recursive Depth-First Search (RDFS) helper function.
   *
   * @param vertex The vertex to start the search from.
   * @param visited The list of visited vertices.
   * @return The list of visited vertices during RDFS.
   */
  public List<T> rdfs(T vertex, List<T> visited) {
    if (getChildren(vertex).isEmpty()) {
      if (!visited.contains(vertex)) {
        visited.add(vertex);
      }
      return visited;
    } else {
      if (!visited.contains(vertex)) {
        visited.add(vertex);

        for (T child : getChildren(vertex)) {
          rdfs(child, visited);
        }
      }
      return visited;
    }
  }

  /**
   * Performs recursive Depth-First Search (DFS) on the graph.
   *
   * @return The list of vertices visited during DFS.
   */
  public List<T> recursiveDepthFirstSearch() {
    Set<T> roots = getRoots();
    List<T> visited = new ArrayList<>();
    int l = roots.size();

    for (int i = 0; i < l; i++) {
      visited = rdfs(roots.iterator().next(), visited);
      roots.remove(roots.iterator().next());
    }
    return visited;
  }
}
