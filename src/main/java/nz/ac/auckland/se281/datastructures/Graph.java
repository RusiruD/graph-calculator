package nz.ac.auckland.se281.datastructures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import javax.print.attribute.standard.Destination;

import org.apache.http.impl.conn.SystemDefaultRoutePlanner;



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
int z=0; 
     Set<T> x=new HashSet<>();
     Set<T> s=new HashSet<>();
    
    
        for(T verticie :verticies){
          int y=0;
           y=getInDegree(verticie);
          
         
            
          
          if(y==0){
            System.out.println(verticie);
            System.out.println("x");
            
            z++;
            s.add(verticie);
          }

        }
         
        
          
        
          
        
       if(z==0){

        x= getEquivalenceClass(verticies.iterator().next());
       if(!x.isEmpty()){
        T d = x.iterator().next();
        s.add(d);
        System.out.println(d);
       }
       
        
        
 
        if(!x.isEmpty()){
        for (T verticie :verticies){
          Set<T> r = getEquivalenceClass(verticie);
          System.out.println(r);
          if(r.iterator().next()!=s.iterator().next()){
            s.add(r.iterator().next());
            System.out.println(r.iterator().next());
          }
        }
      }}
       


        
      
        return s;
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
  public List<T>getChildren(T vertex){
    List<T> x=new ArrayList<>();
    for(Edge<T> edge :edges){
      if(edge.returnSource().equals(vertex) && !edge.returnDestination().equals(vertex) ){
        
        x.add(edge.returnDestination());
      }
    }
    Collections.sort(x);
  
    return x;
  }
  public List<T> iterativeBreadthFirstSearch() {
    //implementing BFS using queue
    List<T> visited = new ArrayList<>();
    List<T> queue = new ArrayList<>();
    Set<T> z = getRoots();
    for(int i=0; i<getRoots().size();i++){
    queue.add(z.iterator().next());
    z.remove(z.iterator().next());
    
    
   
    while(!queue.isEmpty()){
      
      T d=queue.remove(0);
      
      visited.add(d);
      //System.out.println(visited);
      List<T> x=getChildren(d);
      
      for(T y:x){
        if(!visited.contains(y)&&!queue.contains(y)){
          //System.out.println("contains");
         
          queue.add(y);
         
        }
      }
    }}
   return visited;
  }

  public List<T> iterativeDepthFirstSearch() {
    //implementing iterative DFS using stack
    List<T> visited = new ArrayList<>();
    Stack <T> stack = new Stack<>();
    Set<T> z = getRoots();
    
    for(int i=0; i<getRoots().size();i++){
    stack.push(z.iterator().next());
    z.remove(z.iterator().next());
    while(!stack.isEmpty()){
     
      T d=stack.pop();
      visited.add(d);
      
      List<T> x=getChildren(d);
      Collections.reverse(x); 
      
      for(T y:x){
        if(!visited.contains(y)&&!stack.contains(y)){
          stack.push(y);
          
        }
      }
    }}

 
    
    
  
   
    return visited;
    
   
   }
   public List<T> nb( T vertex, List<T> visited) {
    ArrayList t = new ArrayList<>();
  for (Edge<T> edge : edges) {
    if (edge.returnSource().equals(vertex)&&!visited.contains(edge.returnDestination())) {
      System.out.println(edge.returnDestination());
     t.add(edge.returnDestination());
  
     
  }}
   Collections.sort(t);
    return t;}
   

   public List<T> rbfs(Queue<T> queue, List<T> visited) {
    if (queue.isEmpty()) {
      return visited;
  }
 
  T vertex = queue.poll();
 // System.out.println(vertex);
  
 if(!visited.contains(vertex)){
  visited.add(vertex);
 
  List<T> neighbors = new ArrayList<>();
  System.out.println(vertex);
  neighbors.addAll(nb(vertex, visited));
  //Collections.sort(neighbors);
  queue.addAll(neighbors);

  rbfs(queue, visited);
  return visited;}
  return visited;
}


   public List<T> recursiveBreadthFirstSearch() {
    List<T> visited = new ArrayList<>();
        Queue<T> queue = new LinkedList<>();
    Set<T> f = getRoots();
    int o=getRoots().size();
        //for (T vertex : verticies) {
         
           // if (!visited.contains(vertex)) {
              for(int i=0; i<o;i++){
                
                queue.add(f.iterator().next());
               f.remove(f.iterator().next());
                
                rbfs(queue, visited);
              }
            //}
       // }

        return visited;


  }
  public List<T>rdfs(T vertex, List<T> visited) {
   
   
     
    if(getChildren(vertex).isEmpty()){
      if(!visited.contains(vertex)){
        System.out.println("dd");
      visited.add(vertex);}
      return visited;
    }
    else{
      if(!visited.contains(vertex)){
      visited.add(vertex);
      System.out.println("x");
      System.out.println(vertex);
      for(T y:getChildren(vertex)){
        
        rdfs(y,visited);
        
      }
      
    }
    return visited;}
    
  }
  
  public List<T> recursiveDepthFirstSearch() {
    Set<T> z = getRoots();
    List<T> visited = new ArrayList<>();
   int l=z.size();

    for(int i=0; i<l;i++){
      System.out.println(z.iterator().next());
    visited=(rdfs(z.iterator().next(),visited));
    z.remove(z.iterator().next());
  }
    return visited;
    
    //return visited;
    
    
  }}
       
    
    
  

