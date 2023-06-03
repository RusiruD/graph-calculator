package nz.ac.auckland.se281.datastructures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

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

  public Graph(Set<T> verticies, Set<Edge<T>> edges) {
    this.verticies = verticies;
    this.edges = edges;
  }

  public int getInDegree(T vertex) {
    int InDegree = 0;

    // calculates indegree by counting the number of edges that have the vertex as the destination
    for (Edge<T> edge : edges) {
      if (edge.returnDestination().equals(vertex)) {
        InDegree++;
      }
    }
    return InDegree;
  }

  public Set<T> getRoots() {

    int counter = 0;
    Set<T> initialEquivalenceClassSet = new HashSet<>();
    Set<T> rootsSet = new HashSet<>();
    List<T> x = new ArrayList<T>(verticies);
    Set<T> orderedSet = new LinkedHashSet<>();
    // x.sort((a, b) -> a.compareTo(b));
    // Collections.sort(x, (a, b) -> a.compareTo(b));
    x.sort(Comparator.comparingInt(vertex -> Integer.parseInt(vertex.toString())));
    System.out.println(x);

    for (T verticie : x) {
      // System.out.println(verticie);
      int inDegree = 0;
      inDegree = getInDegree(verticie);

      if (inDegree == 0) {

        counter++;

        orderedSet.add(verticie);
      }
    }

    if (counter == 0) {

      initialEquivalenceClassSet = getEquivalenceClass(verticies.iterator().next());
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
            } else {
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

          if (!edge.returnDestination().equals(edge1.returnSource())
              && edge.returnSource().equals(edge1.returnDestination())) {}

        } else {
        }
      }
    }

    if (possibleAntiSymmetricEdgeCounter == antisymmetricEdgeCounter) {
      return true;
    } else {

      return false;
    }
  }

  public boolean isEquivalence() {
    if (isReflexive() && isSymmetric() && isTransitive()) {
      return true;
    } else {
      return false;
    }
  }

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

  public List<T> getChildren(T vertex) {
    List<T> childrenList = new ArrayList<>();
    for (Edge<T> edge : edges) {
      if (edge.returnSource().equals(vertex) && !edge.returnDestination().equals(vertex)) {

        childrenList.add(edge.returnDestination());
      }
    }
    Collections.sort(childrenList, (a, b) -> a.compareTo(b));

    return childrenList;
  }

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
        Collections.reverse(childrenList);

        for (T child : childrenList) {
          if (!visited.contains(child) && !stack.contains(child)) {
            stack.push(child);
          }
        }
      }
    }

    return visited;
  }

  public List<T> returnSiblings(T vertex, List<T> visited) {
    ArrayList siblings = new ArrayList<>();
    for (Edge<T> edge : edges) {
      if (edge.returnSource().equals(vertex) && !visited.contains(edge.returnDestination())) {

        siblings.add(edge.returnDestination());
      }
    }
    Collections.sort(siblings);
    return siblings;
  }

  public List<T> rbfs(Queue<T> queue, List<T> visited) {
    if (queue.isEmpty()) {
      return visited;
    }

    T vertex = queue.poll();

    if (!visited.contains(vertex)) {
      visited.add(vertex);

      List<T> neighbors = new ArrayList<>();

      neighbors.addAll(returnSiblings(vertex, visited));

      queue.addAll(neighbors);

      rbfs(queue, visited);
      return visited;
    }
    return visited;
  }

  public List<T> recursiveBreadthFirstSearch() {
    List<T> visited = new ArrayList<>();
    Queue<T> queue = new LinkedList<>();
    Set<T> roots = getRoots();
    int rootAmount = getRoots().size();

    for (int i = 0; i < rootAmount; i++) {

      queue.add(roots.iterator().next());
      roots.remove(roots.iterator().next());

      rbfs(queue, visited);
    }

    return visited;
  }

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

  public List<T> recursiveDepthFirstSearch() {
    Set<T> roots = getRoots();
    List<T> visited = new ArrayList<>();
    int l = roots.size();

    for (int i = 0; i < l; i++) {

      visited = (rdfs(roots.iterator().next(), visited));
      roots.remove(roots.iterator().next());
    }
    return visited;
  }
}
