#include <bits/stdc++.h>
using namespace std;
#include <chrono>
using namespace std::chrono;

// Definição da estrutura de nó da árvore
struct TreeNode {
    char label;
    vector<TreeNode*> children;
    TreeNode(char lbl) : label(lbl) {}
};

// Função para converter string em árvore de forma não-linear (apenas para fins de exemplo simples)
TreeNode* stringToTree(const string& str) {
    if (str.empty()) return nullptr;
    TreeNode* root = new TreeNode(str[0]); 
    for (size_t i = 1; i < str.length(); ++i) {
        root->children.push_back(new TreeNode(str[i]));
    }
    return root;
}

// Função utilitária para encontrar o mínimo de três números
int min(int x, int y, int z) { return std::min({x, y, z}); }

// Função recursiva para calcular a distância de edição entre duas árvores
int editDistRec(TreeNode* t1, TreeNode* t2) {
    if (!t1) return t2 ? 1 + editDistRec(nullptr, t2->children.empty() ? nullptr : t2->children[0]) : 0;
    if (!t2) return t1 ? 1 + editDistRec(t1->children.empty() ? nullptr : t1->children[0], nullptr) : 0;

    int cost = (t1->label == t2->label) ? 0 : 1;
    int m = t1->children.size();
    int n = t2->children.size();

    if (m == 0 && n == 0) {
        return cost;
    }

    if (m == 0) {
        int insertCost = 0;
        for (TreeNode* child : t2->children) {
            insertCost += 1 + editDistRec(nullptr, child);
        }
        return cost + insertCost;
    }

    if (n == 0) {
        int removeCost = 0;
        for (TreeNode* child : t1->children) {
            removeCost += 1 + editDistRec(child, nullptr);
        }
        return cost + removeCost;
    }

    int minCost = INT_MAX;
    for (int i = 0; i < m; ++i) {
        for (int j = 0; j < n; ++j) {
            int currentCost = editDistRec(t1->children[i], t2->children[j]);
            for (int k = 0; k < m; ++k) {
                if (k != i) {
                    currentCost += 1 + editDistRec(t1->children[k], nullptr);
                }
            }
            for (int l = 0; l < n; ++l) {
                if (l != j) {
                    currentCost += 1 + editDistRec(nullptr, t2->children[l]);
                }
            }
            minCost = min(minCost, currentCost);
        }
    }
    return cost + minCost;
}
// Função para calcular a profundidade da árvore
int treeDepth(TreeNode* root) {
    if (!root) return 0;
    int maxDepth = 0;
    for (TreeNode* child : root->children) {
        maxDepth = max(maxDepth, treeDepth(child));
    }
    return 1 + maxDepth;
}

// Implementação do algoritmo Zhang-Shasha para Distância de Edição entre Árvores
int zhangShasha(TreeNode* t1, TreeNode* t2) {
    if (!t1) return t2 ? 1 + zhangShasha(nullptr, t2) : 0;
    if (!t2) return t1 ? 1 + zhangShasha(t1, nullptr) : 0;

    int m = t1->children.size();
    int n = t2->children.size();
    vector<vector<int>> dp(m + 1, vector<int>(n + 1, 0));

    for (int i = 0; i <= m; ++i) dp[i][0] = i;
    for (int j = 0; j <= n; ++j) dp[0][j] = j;

    for (int i = 1; i <= m; ++i) {
        for (int j = 1; j <= n; ++j) {
            dp[i][j] = min({dp[i - 1][j] + 1, dp[i][j - 1] + 1,
               dp[i - 1][j - 1] + (t1->children[i - 1] && t2->children[j - 1] && t1->children[i - 1]->label == t2->children[j - 1]->label ? 0 : 1)});
        }
    }

    return dp[m][n] + (t1->label == t2->label ? 0 : 1);
}

// Função auxiliar para calcular o mínimo entre três valores
int min3(int a, int b, int c) {
    return min(a, min(b, c));
}

// Função de custo para transformar um nó em outro
int transformCost(TreeNode* a, TreeNode* b) {
    if (!a && !b) return 0;  // Nenhum custo se ambos são nulos
    if (!a) return 1;  // Custo de inserção
    if (!b) return 1;  // Custo de remoção
    return (a->label == b->label) ? 0 : 1;  // Custo de substituição se os rótulos são diferentes
}

// Função recursiva para calcular a distância de edição entre duas subárvores
int tai(TreeNode* t1, TreeNode* t2) {
    if (!t1 || !t2) return 0; // Se uma das árvores é nula, a distância é zero

    int m = t1->children.size();
    int n = t2->children.size();

    vector<vector<int>> dp(m+1, vector<int>(n+1, 0));

    // Inicializa as bordas do DP para as operações de inserção e remoção
    for (int i = 1; i <= m; ++i) dp[i][0] = dp[i-1][0] + transformCost(t1->children[i-1], nullptr);
    for (int j = 1; j <= n; ++j) dp[0][j] = dp[0][j-1] + transformCost(nullptr, t2->children[j-1]);

    // Preenche a matriz DP
    for (int i = 1; i <= m; ++i) {
        for (int j = 1; j <= n; ++j) {
            int costSub = transformCost(t1->children[i-1], t2->children[j-1]);
            dp[i][j] = min3(dp[i-1][j] + transformCost(t1->children[i-1], nullptr),  // Remoção
                            dp[i][j-1] + transformCost(nullptr, t2->children[j-1]),  // Inserção
                            dp[i-1][j-1] + tai(t1->children[i-1], t2->children[j-1]) + costSub);  // Substituição
        }
    }

    return dp[m][n];
}

// Função para imprimir a duração do método
void printDuration(const string& method, const nanoseconds& duration) {
    cout << method << ": " << duration.count() << " ns / "
         << duration_cast<milliseconds>(duration).count() << " ms / "
         << duration_cast<seconds>(duration).count() << " s" << endl;
}



// Função para gerar strings aleatórias
string generateRandomString(int length) {
    string chars = "abcdefghijklmnopqrstuvwxyz";
    random_device rd;
    mt19937 generator(rd());
    uniform_int_distribution<> distribution(0, chars.size() - 1);
    string result;
    for (int i = 0; i < length; ++i) {
        result += chars[distribution(generator)];
    }
    return result;
}
// Função principal para testar os três métodos com strings aleatórias
int main() {
    vector<int> sizes = {200, 400, 600, 800, 1000, 1200 , 1400 , 1600 , 1800 ,2000};
    for (int size : sizes) {
        nanoseconds totalDurationRec(0);
        nanoseconds totalDurationZS(0);
        nanoseconds totalDurationTAI(0);

        for (int i = 0; i < 10; ++i) {
            string str1 = generateRandomString(size);
            string str2 = generateRandomString(size);

            TreeNode* t1 = stringToTree(str1);
            TreeNode* t2 = stringToTree(str2);

            // Método Recursivo Ingênuo
            auto startRec = high_resolution_clock::now();
            int recResult = editDistRec(t1, t2);
            auto stopRec = high_resolution_clock::now();
            totalDurationRec += duration_cast<nanoseconds>(stopRec - startRec);

            // Método Tai
            auto startTAI = high_resolution_clock::now();
            int taiResult = tai(t1, t2);
            auto stopTAI = high_resolution_clock::now();
            totalDurationTAI += duration_cast<nanoseconds>(stopTAI - startTAI);

            // Método Zhang-Shasha
            auto startZS = high_resolution_clock::now();
            int zsResult = zhangShasha(t1, t2);
            auto stopZS = high_resolution_clock::now();
            totalDurationZS += duration_cast<nanoseconds>(stopZS - startZS);
        }

        auto avgDurationRec = totalDurationRec / 10;
        auto avgDurationZS = totalDurationZS / 10;
        auto avgDurationTAI = totalDurationTAI / 10;

        cout << "Tamanho: " << size << " caracteres" << endl;
        printDuration("Recursivo Ingênuo (Média)", avgDurationRec);
        printDuration("Zhang-Shasha (Média)", avgDurationZS);
        printDuration("Tai (Média)", avgDurationTAI);
    }

    return 0;
}