/**
 * CS231 Final Project 10
 * Jiahao Ye
 * Prof. Layton
 */

 import java.util.PriorityQueue;
 import java.util.ArrayList;

public class Graph
 {
    private ArrayList<Vertex> vertices;

   public Graph()
   {
      vertices = new ArrayList<>();
   }

    public int vertexCount()
    {
        return vertices.size();
    }

    /** add v1 and v2 to the graph and add an edge connecting v2 to v2 via direction fir and a second edge connecting v2 to v1 in the opposite direction */
    public void addEdge(Vertex v1, Direction dir, Vertex v2)
    {
      if (vertices.contains(v1) == false)
         vertices.add(v1);
      if (vertices.contains(v2) == false)
         vertices.add(v2);
      v1.connect(v2, dir);
      v2.connect(v1, Vertex.opposite(dir));
    }
    
    public void shortestPath(Vertex v0)
    {
      //Given: a graph G and starting vertex v0 in G
      
      //Initialize all vertices in G to be unmarked and have infinite cost
      for (Vertex v: vertices){
         v.setMarked(false);
         v.setCost(1000000000);
      }
      //Create a priority queue, q, that orders vertices by lowest cost
      PriorityQueue<Vertex> q = new PriorityQueue<>(vertices.size(), new VertexComparator());
      v0.setCost(0);
      q.add(v0);
     
      while (q.isEmpty() != true)
      {
         Vertex v = q.remove();
         v.setMarked(true);
         for (Vertex w: v.getNeighbors())
         {
            if (w.marked() != true && (v.getCost()+1) < w.getCost())
            {
               w.setCost(v.getCost()+1);
               q.add(w);
            }
         }
      }

      // for (Vertex v: vertices)
      //    System.out.println(v.toString());
                                 
    }

 }