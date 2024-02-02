// A Java / A Java program for Bellman-Ford's single source shortest
// path algorithm.
 
import java.io.*;
import java.lang.*;
import java.util.*;
 
// A class to represent a connected, directed and weighted
// graph
class Graph {
   
    // A class to represent a weighted edge in graph
    class Edge {
        int src, dest, weight;
        Edge() { src = dest = weight = 0; }
    };
 
    int V, E;
    Edge edge[];
 
    // Creates a graph with V vertices and E edges
    Graph(int v, int e)
    {
        V = v;
        E = e;
        edge = new Edge[e];
        for (int i = 0; i < e; ++i)
            edge[i] = new Edge();
    }

    int extraiMin(int resp[], boolean visitados[] ){
        int minimo = Integer.MAX_VALUE;
        int min_index = -1;

       

        for (int i = 0; i < resp.length; i++) {
            if(minimo>=resp[i] && visitados[i]==false){
                minimo=resp[i];
                min_index = i;
            }
            
        }
        return min_index;
    }

    int[] Dijkstra(Graph grafo , int origem){
        int V = grafo.V;
        int E = grafo.E;
        int resp[] = new int[V];
        boolean visitados[] = new boolean[V];

        for (int i = 0; i <V; i++) {
            resp[i] = Integer.MAX_VALUE;
            visitados[i] = false;
        }
        resp[origem] = 0;


        for (int i = 0; i < V-1; i++) {
            int u = extraiMin(resp,visitados); // pega minimo da "lista"
            visitados[u] = true;
        for (int v = 0; v < V; v++){
            
                if (!visitados[v] && resp[u] + grafo.edge[v].weight < resp[v])
                    resp[v] = resp[u] + grafo.edge[v].weight ;
        }


        }

        for (int i = 0; i < resp.length; i++) {
            System.out.println(resp[i]);
        }

        return resp;




    }
 
    
 
    // Driver's code
    public static void main(String[] args)
    {
        int V = 5; // Number of vertices in graph
        int E = 8; // Number of edges in graph
 
        Graph graph = new Graph(V, E);
 
        // add edge 0-1 (or A-B in above figure)
        graph.edge[0].src = 0;
        graph.edge[0].dest = 1;
        graph.edge[0].weight = 1;
 
        // add edge 0-2 (or A-C in above figure)
        graph.edge[1].src = 0;
        graph.edge[1].dest = 2;
        graph.edge[1].weight = 4;
 
        // add edge 1-2 (or B-C in above figure)
        graph.edge[2].src = 1;
        graph.edge[2].dest = 2;
        graph.edge[2].weight = 3;
 
        // add edge 1-3 (or B-D in above figure)
        graph.edge[3].src = 1;
        graph.edge[3].dest = 3;
        graph.edge[3].weight = 2;
 
        // add edge 1-4 (or B-E in above figure)
        graph.edge[4].src = 1;
        graph.edge[4].dest = 4;
        graph.edge[4].weight = 2;
 
        // add edge 3-2 (or D-C in above figure)
        graph.edge[5].src = 3;
        graph.edge[5].dest = 2;
        graph.edge[5].weight = 5;
 
        // add edge 3-1 (or D-B in above figure)
        graph.edge[6].src = 3;
        graph.edge[6].dest = 1;
        graph.edge[6].weight = 1;
 
        // add edge 4-3 (or E-D in above figure)
        graph.edge[7].src = 4;
        graph.edge[7].dest = 3;
        graph.edge[7].weight = 3;

        graph.Dijkstra(graph, 0);
    }
}
// Contributed by Aakash Hasija



