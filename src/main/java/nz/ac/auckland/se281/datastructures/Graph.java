package nz.ac.auckland.se281.datastructures;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.print.attribute.standard.Destination;



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
    this.verticies=verticies;
    this.edges=edges;
  }
  
  public Set<T> getRoots() {
    // TODO: Task 1.
   
     Set<T> x=new HashSet<>();
     
    for (Edge<T> edge : edges) {
      int y=0;
      for (Edge<T> z : edges) {

        if (!edge.returnSource().equals(z.returnDestination())) {
          y++;
           
        }}
        if(y==edges.size()){x.add(edge.returnSource());
        }


        }
        return x;
      }
     
      
    

   

  public boolean isReflexive() {
    // TODO: Task 1.
    throw new UnsupportedOperationException();
  }

  public boolean isSymmetric() {
    // TODO: Task 1.
    throw new UnsupportedOperationException();
  }

  public boolean isTransitive() {
    // TODO: Task 1.
    throw new UnsupportedOperationException();
  }

  public boolean isAntiSymmetric() {
    // TODO: Task 1.
    throw new UnsupportedOperationException();
  }

  public boolean isEquivalence() {
    // TODO: Task 1.
    throw new UnsupportedOperationException();
  }

  public Set<T> getEquivalenceClass(T vertex) {
    // TODO: Task 1.
    throw new UnsupportedOperationException();
  }

  public List<T> iterativeBreadthFirstSearch() {
    // TODO: Task 2.
    throw new UnsupportedOperationException();
  }

  public List<T> iterativeDepthFirstSearch() {
    // TODO: Task 2.
    throw new UnsupportedOperationException();
  }

  public List<T> recursiveBreadthFirstSearch() {
    // TODO: Task 3.
    throw new UnsupportedOperationException();
  }

  public List<T> recursiveDepthFirstSearch() {
    // TODO: Task 3.
    throw new UnsupportedOperationException();
  }
}
