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
  public int getInDegree(T vertex) {
    int InDegree=0;
    
    for(Edge<T> edge :edges){
      if(edge.returnDestination().equals(vertex)){InDegree++;
      }
    }
    return InDegree;
  }

  public Set<T> getRoots() {
    // TODO: Task 1.
   
     Set<T> x=new HashSet<>();
    
    
        for(T verticie :verticies){
          int y=0;
           y=getInDegree(verticie);

            
          
          if(y==0){
            x.add(verticie);
          }

        }
      
       


        
      
        return x;
      }
     
      
    

   

  public boolean isReflexive() {
    int x=0;
    for(Edge<T> edge :edges){
      if(edge.returnDestination().equals(edge.returnSource())){
        x++;
    }}
    if(x==verticies.size()){return true;}
    else{return false;}
  }

  public boolean isSymmetric() {
    // TODO: Task 1.
    int x=0;
    for(Edge<T> edge :edges){
      
      for (Edge<T> edge1 :edges){
        if( edge.returnDestination().equals(edge1.returnSource())&&edge.returnSource().equals(edge1.returnDestination())){
         /* System.out.println(edge.returnSource());
          System.out.println(edge.returnDestination());
          System.out.println("x");
          System.out.println(edge1.returnSource());
          System.out.println(edge1.returnDestination());
          System.out.println("z");*/
          x++;
        }

      }}

      
      if(x==edges.size()){
        return true;
    }
  else{return false;}

    

  }

  public boolean isTransitive() {
    int x =0;
    int y=0;
    for(Edge<T> edge :edges){
      for (Edge<T> edge1 :edges){
       
        if(edge.returnDestination().equals(edge1.returnSource())&&!edge1.returnDestination().equals(edge1.returnSource())){
          x++;
        
          
            
            
            for(Edge<T> edge3:edges){
              if(edge.returnSource().equals(edge3.returnSource())&&edge1.returnDestination().equals(edge3.returnDestination())){y++;}
              else{}
              
            }

          
        
          }
        }
        
      }
      if(x==y){
        return true;}
        else{
          return false;
        }
  }

  public boolean isAntiSymmetric() {
    int x=0;
    int y=0;
    for(Edge<T> edge :edges){

      for (Edge<T> edge1 :edges){
       
        if( edge.returnDestination().equals(edge1.returnSource())&&edge.returnSource().equals(edge1.returnDestination())){
y++;
          if( edge.equals(edge1)){
            x++;

           
          }

          if(!edge.returnDestination().equals(edge1.returnSource())&&edge.returnSource().equals(edge1.returnDestination())){}
          
        }
        else{
}

      }}

        
      if(y==x){
        return true;
    }
  else{
    
    return false;}

    
  }

  public boolean isEquivalence() {
    if(isReflexive() && isSymmetric() && isTransitive() ){
      return true;
    }
    else{
      return false;
    }
  }

  public Set<T> getEquivalenceClass(T vertex) {
    Set<T> x=new HashSet<>();
    if (!isEquivalence()) {
      return x;
    }
    else{
      for(Edge<T> edge :edges){
        if(edge.returnSource().equals(vertex)){
          x.add(edge.returnDestination());
        }
        else if(edge.returnDestination().equals(vertex)){
          x.add(edge.returnSource());
        }
        else{}
      }
      return x;
    }
    // TODO: Task 1.
    
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
