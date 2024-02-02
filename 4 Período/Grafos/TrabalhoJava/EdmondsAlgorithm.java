import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class EdmondsAlgorithm {

    static class ContractedGraph {
        int vertices;
        List<List<Edge>> adjList;

        ContractedGraph(int V) {
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

    static List<Edge> edmondsAlgorithm(Graph graph, int root) {
        // Remove edges with destination as the root
        for (int i = 0; i < graph.vertices; ++i) {
            graph.adjList.get(i).removeIf(e -> e.destination == root);
        }

        // Process parallel edges by keeping only the one with the minimum weight
        for (int i = 0; i < graph.vertices; ++i) {
            graph.adjList.get(i).sort(Comparator.comparingDouble(e -> e.weight));

            List<Edge> uniqueEdges = new ArrayList<>();
            for (Edge edge : graph.adjList.get(i)) {
                if (uniqueEdges.isEmpty() || uniqueEdges.get(uniqueEdges.size() - 1).destination != edge.destination) {
                    uniqueEdges.add(edge);
                }
            }

            graph.adjList.set(i, uniqueEdges);
        }

        // Find the edge of lowest weight for each vertex other than the root
        int[] parent = new int[graph.vertices];
        Arrays.fill(parent, -1);
        for (int v = 0; v < graph.vertices; ++v) {
            if (v != root && !graph.adjList.get(v).isEmpty()) {
                parent[v] = graph.adjList.get(v).get(0).source;
            }
        }

        // Check for cycles
        boolean[] visited = new boolean[graph.vertices];
        List<Integer> cycleVertices = new ArrayList<>();
        int current = root;
        while (!visited[current]) {
            visited[current] = true;
            cycleVertices.add(current);
            current = parent[current];
        }

        if (current == root) {
            // No cycles, return the minimum spanning arborescence
            List<Edge> arborescence = new ArrayList<>();
            for (int v = 0; v < graph.vertices; ++v) {
                if (v != root) {
                    arborescence.add(new Edge(parent[v], v, graph.adjList.get(v).get(0).weight));
                }
            }
            return arborescence;
        } else {
            // Contract the cycle and recursively find minimum spanning arborescence
            ContractedGraph contractedGraph = contractCycle(graph, cycleVertices);

            // Recursively find minimum spanning arborescence for the contracted graph
            List<Edge> contractedArborescence = edmondsAlgorithm(contractedGraph, graph.vertices - cycleVertices.size());

            // Expand the contracted arborescence to obtain the final result
            List<Edge> arborescence = expandContractedArborescence(contractedArborescence, cycleVertices, current, graph);

            return arborescence;
        }
    }

    static ContractedGraph contractCycle(Graph graph, List<Integer> cycleVertices) {
        int contractedVertices = graph.vertices - cycleVertices.size() + 1;
        ContractedGraph contractedGraph = new ContractedGraph(contractedVertices);

        for (int i = 0, j = 0; i < graph.vertices; ++i) {
            if (!cycleVertices.contains(i)) {
                contractedGraph.adjList.get(j).addAll(graph.adjList.get(i));
                ++j;
            }
        }

        for (int v : cycleVertices) {
            contractedGraph.adjList.get(contractedVertices - 1).addAll(graph.adjList.get(v));
        }

        for (Edge edge : graph.adjList.get(cycleVertices.get(0))) {
            if (!cycleVertices.contains(edge.destination)) {
                int contractedSource = cycleVertices.size() - 1;
                contractedGraph.addEdge(contractedSource, edge.destination, edge.weight - graph.adjList.get(edge.destination).get(0).weight);
            }
        }

        return contractedGraph;
    }

    static List<Edge> expandContractedArborescence(List<Edge> contractedArborescence, List<Integer> cycleVertices, int root, Graph graph) {
        List<Edge> arborescence = new ArrayList<>();
        int contractedRoot = graph.vertices - cycleVertices.size();

        for (Edge edge : contractedArborescence) {
            int source = (edge.source == contractedRoot) ? root : cycleVertices.get(edge.source);
            int destination = (edge.destination == contractedRoot) ? root : cycleVertices.get(edge.destination);
            arborescence.add(new Edge(source, destination, graph.adjList.get(edge.destination).get(0).weight));
        }

        return arborescence;
    }

    public static void printArborescence(List<Edge> arborescence) {
        System.out.println("Minimum Spanning Arborescence Edges:");
        for (Edge edge : arborescence) {
            System.out.println(edge.source + " -> " + edge.destination + " (Weight: " + edge.weight + ")");
        }
    }

    public static void main(String[] args) {
        // Example usage
        int vertices = 4;
        Graph graph = new Graph(vertices);

        // Add directed edges with weights
        graph.addEdge(0, 1, 2.0);
        graph.addEdge(0, 2, 1.0);
        graph.addEdge(1, 2, 3.0);
        graph.addEdge(1, 3, 4.0);
        graph.addEdge(2, 3, 2.0);

        int root = 0;
        List<Edge> minimumSpanningArborescence = edmondsAlgorithm(graph, root);

        // Print the resulting minimum spanning arborescence
        printArborescence(minimumSpanningArborescence);
    }
}
