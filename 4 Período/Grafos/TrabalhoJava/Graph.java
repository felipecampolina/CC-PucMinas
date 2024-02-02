import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Graph {
    int vertices;
    List<List<Edge>> adjList;

    Graph(int V) {
        vertices = V;
        adjList = new ArrayList<>(V);
        for (int i = 0; i < V; ++i) {
            adjList.add(new ArrayList<>());
        }
    }

    void addEdge(int src, int dest, double weight) {
        Edge edge = new Edge(src, dest, weight);
        adjList.get(src).add(edge);
    }

    static class Edge {
        int source, destination;
        double weight;

        Edge(int src, int dest, double w) {
            source = src;
            destination = dest;
            weight = w;
        }
    }
}
