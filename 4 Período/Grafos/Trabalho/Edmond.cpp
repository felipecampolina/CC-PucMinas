#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

// Estrutura para representar uma aresta
struct Edge {
    int from, to, weight;

    Edge(int f, int t, int w) : from(f), to(t), weight(w) {}
};

// Função para contrair um ciclo e atualizar os pesos
void contractCycle(vector<Edge>& edges, vector<int>& cycleVertices) {
    // Encontrar a aresta de peso mínimo que chega ao ciclo
    int minWeight = INT_MAX;
    Edge minEdge(-1, -1, -1);

    for (const Edge& edge : edges) {
        if (find(cycleVertices.begin(), cycleVertices.end(), edge.to) != cycleVertices.end() &&
            edge.weight < minWeight) {
            minWeight = edge.weight;
            minEdge = edge;
        }
    }

    // Atualizar os pesos das arestas que chegam ao ciclo
    for (Edge& e : edges) {
        if (find(cycleVertices.begin(), cycleVertices.end(), e.to) == cycleVertices.end()) {
            e.weight -= minWeight;
        }
    }

    // Contrair o ciclo (remover vértices do ciclo e adicionar uma nova aresta)
    int contractedVertex = cycleVertices[0];
    for (size_t i = 1; i < cycleVertices.size(); ++i) {
        edges.erase(remove_if(edges.begin(), edges.end(),
            [&](const Edge& e) { return e.from == cycleVertices[i] || e.to == cycleVertices[i]; }),
            edges.end());

        // Adicionar uma nova aresta que representa o ciclo contraído
        edges.push_back(Edge(minEdge.from, contractedVertex, minEdge.weight));
    }
}

int main() {
    // Exemplo de uso
    vector<Edge> edges = { {1, 2, 5}, {2, 3, 8}, {3, 4, 6}, {4, 1, 9}, {2, 4, 2} };
    vector<int> cycleVertices = { 2, 3, 4 };

    cout << "Antes da contração:" << endl;
    for (const Edge& edge : edges) {
        cout << edge.from << " -> " << edge.to << " : " << edge.weight << endl;
    }

    // Contrair o ciclo e atualizar os pesos
    contractCycle(edges, cycleVertices);

    cout << "\nDepois da contração:" << endl;
    for (const Edge& edge : edges) {
        cout << edge.from << " -> " << edge.to << " : " << edge.weight << endl;
    }

    return 0;
}



