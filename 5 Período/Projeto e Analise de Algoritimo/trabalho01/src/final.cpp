#include <iostream>
#include <vector>
#include <stack>
#include <ctime>
#include <functional>
#include <chrono>
#include <algorithm>
#include <math.h>

using namespace std;

class Graph {
public:
    long vertices;
    vector<vector<long>> adj_list;

    // Construtor da classe Graph
    Graph(long v,int arestas) : vertices(v), adj_list(v) {
        // Inicializa a semente para geração de números aleatórios
        srand(time(NULL));

        // Adiciona arestas aleatórias ao grafo
        for (long i = 0; i < arestas; ++i) {
            long u, v;
            // Escolhe dois vértices aleatórios diferentes
            do {
                u = rand() % vertices;
                v = rand() % vertices;
            } while (u == v || edgeExists(u, v)); // Garante que os vértices escolhidos são diferentes e a aresta não existe

            // Adiciona a aresta ao grafo
            addEdge(u, v);
        }
    }

    // Método para adicionar uma aresta ao grafo
    void addEdge(long u, long v) {
        adj_list[u].push_back(v); // Adiciona v a lista de adjacência de u
        adj_list[v].push_back(u); // Adiciona u a lista de adjacência de v
    }

    // Método para verificar se uma aresta já existe no grafo
    bool edgeExists(long u, long v) {
        return find(adj_list[u].begin(), adj_list[u].end(), v) != adj_list[u].end();
    }

    // Métodos para detecção de pontes
    vector<pair<long, long>> naiveBridgeDetection(); // Método ingênuo
    vector<pair<long, long>> tarjanBridgeDetection(); // Método Tarjan
    void fleuryNaive(); // Algoritmo de Fleury com detecção de pontes ingênua
    void fleuryTarjan(); // Algoritmo de Fleury com detecção de pontes usando Tarjan
    void fleuryUtil(long u, vector<pair<long, long>>& bridgeList,bool &pathFound); // Função auxiliar para o algoritmo de Fleury
    bool isBridgeNaive(long u, long v); // Verifica se uma aresta é uma ponte usando o método ingênuo
    int getNumEdges() const; // Obtém o número total de arestas no grafo
};

// Método para obter o número total de arestas no grafo
int Graph::getNumEdges() const {
    int numEdges = 0;
    // Percorre a lista de adjacência de cada vértice
    for (const auto& adj : adj_list) {
        numEdges += adj.size(); // Incrementa o número de arestas pelo tamanho da lista de adjacência do vértice
    }
    // Cada aresta é contada duas vezes, então divide por 2
    return numEdges / 2;
}

// Método para detecção de pontes ingênuo
vector<pair<long, long>> Graph::naiveBridgeDetection() {
    vector<pair<long, long>> bridges; // Vetor para armazenar as pontes encontradas
    // Percorre todos os vértices do grafo
    for (long u = 0; u < vertices; ++u) {
        for (long v : adj_list[u]) {
            // Cria uma cópia temporária do grafo
            Graph tempGraph = *this;

            // Remove temporariamente a aresta (u, v) do grafo
            tempGraph.adj_list[u].erase(remove(tempGraph.adj_list[u].begin(), tempGraph.adj_list[u].end(), v), tempGraph.adj_list[u].end());
            tempGraph.adj_list[v].erase(remove(tempGraph.adj_list[v].begin(), tempGraph.adj_list[v].end(), u), tempGraph.adj_list[v].end());

            // Vetor para marcar vértices visitados durante a busca em profundidade
            vector<bool> visited(vertices, false);
            stack<long> stack;

            stack.push(0);
            visited[0] = true;

            // Executa a busca em profundidade no grafo temporário
            while (!stack.empty()) {
                long current = stack.top();
                stack.pop();

                for (long neighbor : tempGraph.adj_list[current]) {
                    if (!visited[neighbor]) {
                        stack.push(neighbor);
                        visited[neighbor] = true;
                    }
                }
            }

            // Verifica se o grafo é conectado após remover a aresta
            if (count(visited.begin(), visited.end(), true) != vertices) {
                bridges.emplace_back(u, v); // Se o grafo não for conectado, a aresta é uma ponte
            }
        }
    }
    return bridges;
}

// Método para detecção de pontes usando o algoritmo de Tarjan
vector<pair<long, long>> Graph::tarjanBridgeDetection() {
    vector<pair<long, long>> bridges; // Vetor para armazenar as pontes encontradas
    vector<long> discovery_time(vertices, -1);
    vector<long> low(vertices, -1);
    vector<long> parent(vertices, -1);
    vector<bool> visited(vertices, false);
    long time = 0;

    // Função recursiva para realizar a busca em profundidade com o algoritmo de Tarjan
    function<void(long)> dfs = [&](long u) {
        visited[u] = true;
        time++;
        discovery_time[u] = low[u] = time;

        for (long v : adj_list[u]) {
            if (!visited[v]) {
                parent[v] = u;
                dfs(v);
                low[u] = min(low[u], low[v]);

                if (low[v] > discovery_time[u]) {
                    bridges.emplace_back(u, v); // Se o vértice v for inacessível a partir de u, a aresta (u, v) é uma ponte
                }
            } else if (v != parent[u]) {
                low[u] = min(low[u], discovery_time[v]);
            }
        }
    };

    // Percorre todos os vértices do grafo
    for (long node = 0; node < vertices; ++node) {
        if (!visited[node]) {
            dfs(node); // Inicia a busca em profundidade a partir do vértice não visitado
        }
    }

    return bridges;
}


// Função utilitária para o algoritmo de Fleury
void Graph::fleuryUtil(long u, vector<pair<long, long>>& bridgeList, bool& pathFound) {
    // Implementação ajustada para evitar modificações na lista durante a iteração
    size_t i = 0;
    while (i < adj_list[u].size()) {
        long v = adj_list[u][i];
        bool isBridge = find(bridgeList.begin(), bridgeList.end(), make_pair(u, v)) != bridgeList.end() ||
                       find(bridgeList.begin(), bridgeList.end(), make_pair(v, u)) != bridgeList.end();
        if (!isBridge) {
            adj_list[u].erase(adj_list[u].begin() + i);
            adj_list[v].erase(remove(adj_list[v].begin(), adj_list[v].end(), u), adj_list[v].end());
            fleuryUtil(v, bridgeList, pathFound);
            
            // // Se o caminho ainda não foi encontrado e esta é a primeira vez que ele é encontrado nesta chamada da função, imprime a mensagem
            // if (!pathFound) {
            //     cout << "Caminho encontrado!" << endl;
            //     pathFound = true; // Marca que o caminho foi encontrado para não imprimir novamente
            // }
            return; // Sai após encontrar um caminho válido
        } else {
            ++i;
        }
    }
}

// Algoritmo de Fleury com detecção de pontes ingênua
void Graph::fleuryNaive() {
    vector<pair<long, long>> bridgeList = naiveBridgeDetection();
    long start_vertex = 0; // Pode ser adaptado para escolher um vértice válido como início
    bool pathFound = false; // Variável para controlar se o caminho foi encontrado ou não
    fleuryUtil(start_vertex, bridgeList, pathFound);
}

// Algoritmo de Fleury com detecção de pontes usando Tarjan
void Graph::fleuryTarjan() {
    vector<pair<long, long>> bridgeList = tarjanBridgeDetection();
    long start_vertex = 0; // Pode ser adaptado para escolher um vértice válido como início
    bool pathFound = false; // Variável para controlar se o caminho foi encontrado ou não
    fleuryUtil(start_vertex, bridgeList, pathFound);
}


// Função para imprimir o tempo de execução de um método
void printExecutionTime(const string& method, const chrono::duration<double>& totalTime) {
    auto nanoseconds = chrono::duration_cast<chrono::nanoseconds>(totalTime).count();
    auto microseconds = chrono::duration_cast<chrono::microseconds>(totalTime).count();
    auto milliseconds = chrono::duration_cast<chrono::milliseconds>(totalTime).count();
    auto seconds = chrono::duration_cast<chrono::seconds>(totalTime).count();

    cout << method << " - Average Time: "
        << nanoseconds << " nanoseconds, "
        << microseconds << " microseconds, "
        << milliseconds << " milliseconds, "
        << seconds << " seconds" << endl;
}

// Função principal
int main() {
    std::srand(std::time(nullptr));
    vector<long> vertices = {100, 1000, 10000, 100000};
    vector<long> arestas = {100, 200, 300, 400};


    for (int x = 0; x < arestas.size(); x++)
    {
     for (int i = 0; i < vertices.size(); ++i){


        Graph graph(vertices[i],arestas[x]); // Cria um grafo com o número de vértices especificado

        // Imprime o número de vértices e arestas do grafo
        cout << "Graph with " << vertices[i] << " vertices and " << graph.getNumEdges() << " edges:" << endl;


        // Mede o tempo para detecção de pontes ingênuas
        auto naiveTotalTime = chrono::duration<double>::zero();
        int NumRuns = 10; // Número de execuções para calcular a média
        for (int run = 0; run < NumRuns; ++run) {
            auto naiveStart = chrono::high_resolution_clock::now();
            graph.naiveBridgeDetection();
            auto naiveEnd = chrono::high_resolution_clock::now();
            naiveTotalTime += naiveEnd - naiveStart;
        }
        naiveTotalTime /= NumRuns;
        printExecutionTime("Naive method", naiveTotalTime);

        // Mede o tempo para detecção de pontes usando Tarjan
        auto tarjanTotalTime = chrono::duration<double>::zero();
        for (int run = 0; run < NumRuns; ++run) {
            auto tarjanStart = chrono::high_resolution_clock::now();
            graph.tarjanBridgeDetection();
            auto tarjanEnd = chrono::high_resolution_clock::now();
            tarjanTotalTime += tarjanEnd - tarjanStart;
        }
        tarjanTotalTime /= NumRuns;
         printExecutionTime("Tarjan method", tarjanTotalTime);

        // Exemplo de uso do algoritmo de Fleury com detecção de pontes ingênuas e Tarjan
        auto fleuryNaiveTotalTime = chrono::duration<double>::zero();
        for (int run = 0; run < NumRuns; ++run) {
            auto fleuryNaiveStart = chrono::high_resolution_clock::now();
            graph.fleuryNaive();
            auto fleuryNaiveEnd = chrono::high_resolution_clock::now();
            fleuryNaiveTotalTime += fleuryNaiveEnd - fleuryNaiveStart;
        }
        fleuryNaiveTotalTime /= NumRuns;
        printExecutionTime("Fleury Naive", fleuryNaiveTotalTime);

        auto fleuryTarjanTotalTime = chrono::duration<double>::zero();
        for (int run = 0; run < NumRuns; ++run) {
            auto fleuryTarjanStart = chrono::high_resolution_clock::now();
            graph.fleuryTarjan();
            auto fleuryTarjanEnd = chrono::high_resolution_clock::now();
            fleuryTarjanTotalTime += fleuryTarjanEnd - fleuryTarjanStart;
        }
        fleuryTarjanTotalTime /= NumRuns;
        printExecutionTime("Fleury Tarjan", fleuryTarjanTotalTime);

        cout << "--------------------------------------" << endl;
    }
    }

    return 0;
    
}
