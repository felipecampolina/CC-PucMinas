#include <iostream>
#include <vector>
#include <map>
#include <set>
#include <limits>
#include <algorithm>
#include <unordered_map>
#include <unordered_set>
#include <stack>

using namespace std;

class Graph {
public:
    map<int, vector<pair<int, int>>> adjList;

    void addEdge(int u, int v, int weight) {
        adjList[u].push_back(std::pair<int, int>(v, weight));
    }

    vector<int> getVertices() const {
        // Implemente a obtenção de vértices
        return vector<int>();
    }
   bool hasEdge(int u, int v) const {
        auto it = adjList.find(u);
        if (it != adjList.end()) {
            const vector<pair<int, int>>& edges = it->second;
            for (const auto& edge : edges) {
                if (edge.first == v) {
                    return true;
                }
            }
        }
        return false;
    }

    vector<pair<pair<int, int>, int>> getEdges() const {
        // Implemente a obtenção de arestas
        return vector<pair<pair<int, int>, int>>();
    }

    friend ostream& operator<<(ostream& os, const Graph& graph) {
        for (const auto& pair : graph.adjList) {
            os << pair.first << ": [";
            for (size_t i = 0; i < pair.second.size(); ++i) {
                os << "(" << pair.second[i].first << ", " << pair.second[i].second << ")";
                if (i < pair.second.size() - 1) os << ", ";
            }
            os << "]";
            if (pair != *graph.adjList.rbegin()) os << ", ";
        }
        return os;
    }

    
   Graph findMST(int root) {
    Graph mst;
    Graph g = *this;
    map<int, Graph> components;

    auto minEdges = findMinEdges(g);
    detectAndContractCycles(g, minEdges, components);

    for (const pair<const int, pair<int, int>>& p : minEdges) {
        if (p.first != root) {
            mst.addEdge(p.second.first, p.first, p.second.second);
        }
    }

    cout << "Components: ";
    for (const pair<const int, Graph>& p : components) {
        cout << p.first << ": " << p.second << endl;
    }

    cout << "MST: " << mst << endl;

    // Combina a MST e os componentes em um único grafo para cada ciclo
    for (auto& component : components) {

        Graph& componentGraph = component.second;

        // Adiciona as arestas do componente ao MST
        for (const auto& componentEdgePair : componentGraph.adjList) {
            int u = componentEdgePair.first;

            for (const auto& componentEdge : componentEdgePair.second) {
                int v = componentEdge.first;
                mst.addEdge(u, v, componentEdge.second);
            }
        }
    }

    // Imprime o MST combinado com os componentes
    for(auto& [u, _] : components) {
        for(auto& [v, E] : mst.adjList) {
            if(v >= 1000000 && components.find(v) != components.end()) {
                Graph& component = components[v];

                for(auto& e : E) {
                    for(auto& [node, edges] : component.adjList) {
                        if(std::find(edges.begin(), edges.end(), e) != edges.end()) {
                            E.erase(std::find(E.begin(), E.end(), e));
                            if(std::find(mst.adjList[node].begin(), mst.adjList[node].end(), e) == mst.adjList[node].end()) {
                                mst.adjList[node].push_back(e);
                            }
                        }
                    }
                }
            } 

            for(auto& e : E) {
                if(e.first >= 1000000 && components.find(e.first) != components.end()) {
                    Graph& component = components[e.first];
                    
                    bool found = false;
                    do {
                        int n;
                        found = false;
                        for(auto& [node, edges] : component.adjList) {
                            if(node >= 1000000) {
                                found = true;
                                n = node;
                                break;
                            }

                            for(auto& edge : edges) {
                                if(edge.first >= 1000000) {
                                    found = true;
                                    n = edge.first;
                                    break;
                                }
                            }
                        }

                        for(auto& edge : this->adjList[v]) {
                            if(component.adjList.find(edge.first) != component.adjList.end() && edge.second == e.second) {
                                E.erase(std::find(E.begin(), E.end(), e));
                                if(std::find(E.begin(), E.end(), edge) == E.end()) E.push_back(edge);
                            }   
                        }

                        if(found) component = components[n];
                    } while(found);

                }
            }
        }

        mst.adjList.erase(u);
    }

    removeReturnEdges(mst, root);
    cout << "Combined MST: " << mst << endl;
    return mst;
}
    Graph combineMSTAndComponents(const Graph& mst, const map<int, Graph>& components) {
    Graph combinedGraph = mst;

    for (const auto& component : components) {

        const Graph& componentGraph = component.second;

        // Adiciona os vértices do componente ao grafo combinado
        for (int vertex : componentGraph.getVertices()) {
            combinedGraph.addVertex(vertex);
        }

        // Adiciona as arestas do componente ao grafo combinado, evitando duplicatas
        for (const auto& componentEdgePair : componentGraph.adjList) {
            int u = componentEdgePair.first;

            for (const auto& componentEdge : componentEdgePair.second) {
                int v = componentEdge.first;
                int weight = componentEdge.second;

                // Verifica se a aresta já existe antes de adicioná-la
                if (!combinedGraph.hasEdge(u, v) && !combinedGraph.hasEdge(v, u)) {
                    combinedGraph.addEdge(u, v, weight);
                }
            }
        }
    }

    return combinedGraph;
}

    map<int, pair<int, int>> findMinEdges(const Graph& g) {
        map<int, pair<int, int>> minEdges;
        for (const pair<const int, vector<pair<int, int>>>& p : g.adjList) {
            for (const pair<int, int>& edge : p.second) {
                int u = p.first, v = edge.first, w = edge.second;
                if (minEdges.find(v) == minEdges.end() || minEdges[v].second > w) {
                    minEdges[v] = {u, w};
                }
            }
        }

        for(const pair<const int, pair<int, int>>& p : minEdges) {
            cout << p.first << ": " << p.second.first << ", " << p.second.second << endl;
        }

        return minEdges;
    }

    bool findCycle(int node, Graph& g, const map<int, pair<int, int>>& minEdges, unordered_map<int, bool>& visited, unordered_set<int>& cycle, Graph& component) {
        if (visited[node]) {
            if (cycle.find(node) != cycle.end()) 
                return true; 

            return false;
        }

        visited[node] = true;
        cycle.insert(node);
        if (g.adjList.find(node) != g.adjList.end()) 
            component.adjList[node] = g.adjList.at(node);

        auto it = minEdges.find(node);
        if (it != minEdges.end()) 
            if (findCycle(it->second.first, g, minEdges, visited, cycle, component)) 
                return true;

        cycle.erase(node);
        if (component.adjList.find(node) != component.adjList.end()) 
            component.adjList.erase(node);
        return false;
    }

    void contractCycle(Graph& g, const unordered_set<int>& cycle, int superNode, map<int, pair<int, int>>& minEdges) {
        Graph newGraph;

        for (const int& node : cycle) 
            for (const pair<int, int>& edge : g.adjList[node]) 
                if (cycle.find(edge.first) == cycle.end()) 
                    newGraph.addEdge(superNode, edge.first, edge.second);

        for(const pair<const int, pair<int, int>>& p : minEdges) 
            if(cycle.find(p.first) == cycle.end() && cycle.find(p.second.first) == cycle.end()) 
                newGraph.addEdge(p.second.first, p.first, p.second.second);

        for (const pair<const int, vector<pair<int, int>>>& p : g.adjList) {
            if (cycle.find(p.first) != cycle.end()) continue;

            for (const pair<int, int>& edge : p.second) {
                if (cycle.find(edge.first) != cycle.end()) 
                    newGraph.addEdge(p.first, superNode, edge.second);
                else
                    newGraph.addEdge(p.first, edge.first, edge.second);
            }
        }   

        g.adjList = move(newGraph.adjList);
        minEdges = findMinEdges(g);

        cout << "Contracted cycle: " << g << endl;
    }
    void addVertex(int vertex) {
        // If the vertex is not already in the graph, add it
        if (adjList.find(vertex) == adjList.end()) {
            adjList[vertex] = std::vector<std::pair<int, int>>();
        }
    }

    void detectAndContractCycles(Graph& g, map<int, pair<int, int>>& minEdges, map<int, Graph>& components) {
        unordered_map<int, bool> visited;

        auto it = minEdges.begin();
        while (it != minEdges.end()) {
            if (!visited[it->first]) {
                unordered_set<int> cycle;
                Graph component;

                if (findCycle(it->first, g, minEdges, visited, cycle, component)) {
                    static int superNode = 1000000; 
                    components[superNode] = component;
                    contractCycle(g, cycle, superNode, minEdges);
                    superNode++;

                    it = minEdges.begin(); 
                } else ++it;
            } else ++it;
        }
    }

    void removeReturnEdges(Graph& mst, int root) {
        unordered_set<int> visited;
        stack<int> stack;
        stack.push(root);

        // Realiza uma DFS a partir da raiz
        while (!stack.empty()) {
            int u = stack.top();
            stack.pop();

            if (visited.find(u) != visited.end()) {
                continue;
            }

            visited.insert(u);

            // Itera sobre todas as arestas saindo de u
            auto it = mst.adjList[u].begin();
            while (it != mst.adjList[u].end()) {
                int v = it->first;
                if (visited.find(v) == visited.end()) {
                    stack.push(v);
                    ++it;
                } else {
                    // Remove arestas de retorno
                    it = mst.adjList[u].erase(it);
                }
            }
        }
    }



};
