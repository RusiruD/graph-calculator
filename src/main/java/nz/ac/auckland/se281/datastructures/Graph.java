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

    List<T> verticiesList = new ArrayList<T>(verticies);

    Set<T> rootsSet = new LinkedHashSet<>();

    // sorts the verticies in the array list in  ascending order
    verticiesList.sort(Comparator.comparingInt(vertex -> Integer.parseInt(vertex.toString())));

    // checks if there are any verticies with an indegree of 0 and adds them to the roots set
    for (T verticie : verticiesList) {

      int inDegree = getInDegree(verticie);

      if (inDegree == 0) {

        counter++;

        rootsSet.add(verticie);
      }
    }

    // if there are no verticies with an indegree of 0, then the roots are found from the
    // equivalence classes
    if (counter == 0) {

      // if the equivalence class is not empty, then the first value of the equivalence class is
      // added to the roots set
      Set<T> initialEquivalenceClassSet = getEquivalenceClass(verticies.iterator().next());
      if (!initialEquivalenceClassSet.isEmpty()) {
        T firstValueT = initialEquivalenceClassSet.iterator().next();
        rootsSet.add(firstValueT);
      }

      // checks if the first value of the equivalence class of each vertex is the same as the first
      // equivalence class
      // if it is not, then the first value of the equivalence class is added to the roots set
      if (!initialEquivalenceClassSet.isEmpty()) {
        for (T verticie : verticies) {
          Set<T> equivalenceClassSet = getEquivalenceClass(verticie);

          if (equivalenceClassSet.iterator().next() != rootsSet.iterator().next()) {
            rootsSet.add(equivalenceClassSet.iterator().next());
          }
        }
      }
    }

    return rootsSet;
  }

  /**
   * Checks whether the graph is reflexive.
   *
   * @return true if the graph is reflexive, false otherwise.
   */
  public boolean isReflexive() {
    int reflexiveEdgeCounter = 0;
    // checks if the source and destination of each edge is the same and increments the counter if
    // it is
    for (Edge<T> edge : edges) {
      if (edge.returnDestination().equals(edge.returnSource())) {
        reflexiveEdgeCounter++;
      }
    }
    // if the counter is equal to the number of verticies, then the graph is reflexive
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
    // checks if there is an edge with a  destination that is the same as the source of another edge
    // and checkes if the source of the first edge is the same as the destination of the second edge
    // increments the counter if both conditions are true
    for (Edge<T> edge : edges) {

      for (Edge<T> edge1 : edges) {
        if (edge.returnDestination().equals(edge1.returnSource())
            && edge.returnSource().equals(edge1.returnDestination())) {

          symmetricEdgeCounter++;
        }
      }
    }

    // if the counter is equal to the number of edges, then the graph is symmetric
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
    // checks if there is an edge with a destination that is the same as the source of another
    // edge-edge1
    for (Edge<T> edge : edges) {
      // increments the counter if the destination of the first edge is the same as the source of
      // the second edge
      // and if the destination of the second edge is not the same as the source of the second edge
      for (Edge<T> edge1 : edges) {

        if (edge.returnDestination().equals(edge1.returnSource())
            && !edge1.returnDestination().equals(edge1.returnSource())) {
          possibleTransitiveEdgeCounter++;
          // checks if there is an edge-edge3 that comes from the same source the first edge did
          // and goes to the same destination the second edge did
          for (Edge<T> edge3 : edges) {
            if (edge.returnSource().equals(edge3.returnSource())
                && edge1.returnDestination().equals(edge3.returnDestination())) {
              transitiveEdgeCounter++;
            }
          }
        }
      }
    }
    // if the counter is equal to the number of edges, then the graph is transitive
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
    // checks if there is an edge with a destination that is the same as the source of another
    // edge-edge1
    for (Edge<T> edge : edges) {

      for (Edge<T> edge1 : edges) {
        // increments the counter if the destination of the first edge is the same as the source of
        // the second edge
        if (edge.returnDestination().equals(edge1.returnSource())
            && edge.returnSource().equals(edge1.returnDestination())) {
          possibleAntiSymmetricEdgeCounter++;
          // increments different counter if the edges equal each other
          if (edge.equals(edge1)) {
            antisymmetricEdgeCounter++;
          }
        }
      }
    }
    // if the counter is equal to the number of edges, then the graph is anti-symmetric
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
    // checks if the graph has an equivalence relation by checking if its reflexive, symmetric, and
    // transitive
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
    // checks if the graph is an equivalence relation if itisnt then it returns the empty set
    if (!isEquivalence()) {
      return equivalenceClass;
    } else {

      // for every edge in the graph, if the source equals the vertex, then the destination is added
      // to the equivalence class
      // if the destination equals the vertex its source is added to the equivalence class
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

    List<T> children = new ArrayList<T>();

    // for every edge in the graph, if the source equals the vertex and the destination doesnt equal
    // the vertex
    // add the destination to the list of children
    for (Edge<T> edge : edges) {
      if (edge.returnSource().equals(vertex) && !edge.returnDestination().equals(vertex)) {

        children.add(edge.returnDestination());
      }
    }
    children.sort(Comparator.comparingInt(z -> Integer.parseInt(z.toString())));

    return children;
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

    // for every root in the graph, add it to the queue and remove it from the set of roots
    for (int i = 0; i < getRoots().size(); i++) {
      queue.add(rootsSet.iterator().next());
      rootsSet.remove(rootsSet.iterator().next());

      // while the queue is not empty, remove the first value from the queue and add it to the
      // visited list
      while (!queue.isEmpty()) {

        T firstQueueValue = queue.remove(0);

        visited.add(firstQueueValue);

        // for every child of the first value in the queue, if it hasnt been visited and isnt in the
        // queue, add it to the queue
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

    // for every root in the graph, add it to the stack and remove it from the set of roots
    for (int i = 0; i < getRoots().size(); i++) {
      stack.push(rootsSet.iterator().next());
      rootsSet.remove(rootsSet.iterator().next());
      // while the stack is not empty, remove the top value from the stack and add it to the visited
      // list
      while (!stack.isEmpty()) {
        T topStackValue = stack.pop();
        visited.add(topStackValue);

        List<T> childrenList = getChildren(topStackValue);
        int left = 0;
        int right = childrenList.size() - 1;
        // reverse the children list
        while (left < right) {
          T temp = childrenList.get(left);
          childrenList.set(left, childrenList.get(right));
          childrenList.set(right, temp);

          left++;
          right--;
        }

        // for every child of the top value in the stack, if it hasnt been visited and isnt in the
        // stack, add it to the stack
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

    // order the edges by source and then destination from lowest to highest
    //  then reverse it so its highest to lowest
    Comparator<Edge<T>> edgeComparator =
        Comparator.comparing((Edge<T> edge) -> edge.returnSource())
            .thenComparing((Edge<T> edge) -> edge.returnDestination());
    Comparator<Edge<T>> oppositeEdgeComparator = edgeComparator.reversed();
    sortedEdges.sort(oppositeEdgeComparator);

    // for every edge in the graph, if the source equals the
    // source vertex and the destination doesnt equal the vertex
    // and the siblings list doesnt already contain the destination
    // add the destination to the list of siblings
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

    // Remove the first vertex from the queue
    T vertex = queue.poll();
    visited.add(vertex);

    List<T> siblings = returnSiblings(vertex, visited);

    // If there are no siblings, add all the children of the visited vertices to the queue
    if (siblings.isEmpty()) {
      for (T visitedVertex : visited) {
        List<T> children = getChildren(visitedVertex);
        for (T child : children) {
          if (!visited.contains(child) && !queue.contains(child)) {
            queue.add(child);
          }
        }
      }
    }
    // If all the siblings have been visited, add all the children of the visited vertices to the
    // queue
    else if (visited.containsAll(siblings)) {
      for (T visitedVertex : visited) {
        List<T> children = getChildren(visitedVertex);
        for (T child : children) {
          if (!visited.contains(child) && !queue.contains(child)) {
            queue.add(child);
          }
        }
      }
    }
    // Otherwise, add all the siblings to the queue
    else {
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

    // Add all the roots to the queue
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
    // If the vertex has no children, add it to the visited list
    if (getChildren(vertex).isEmpty()) {
      if (!visited.contains(vertex)) {
        visited.add(vertex);
      }
      return visited;
    }
    // Otherwise, add the vertex to the visited list and recursively call rdfs on its children
    else {
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
    int rootsAmount = roots.size();
    // For every root, call rdfs on it
    for (int i = 0; i < rootsAmount; i++) {
      visited = rdfs(roots.iterator().next(), visited);
      roots.remove(roots.iterator().next());
    }
    return visited;
  }
}
