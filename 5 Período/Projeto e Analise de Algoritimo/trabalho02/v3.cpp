#include <bits/stdc++.h>
using namespace std;
#include <chrono>
using namespace std;
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

// Programação Dinâmica com Memoization para Distância de Edição entre Árvores
int editDistDP(TreeNode* t1, TreeNode* t2, map<pair<TreeNode*, TreeNode*>, int>& memo) {
    if (!t1) return t2 ? 1 + editDistDP(nullptr, t2, memo) : 0;
    if (!t2) return t1 ? 1 + editDistDP(t1, nullptr, memo) : 0;
    if (memo.find({t1, t2}) != memo.end()) return memo[{t1, t2}];
    int dist = (t1->label == t2->label) ? 0 : 1;
    int m = t1->children.size();
    int n = t2->children.size();
    vector<vector<int>> dp(m + 1, vector<int>(n + 1, 0));
    for (int i = 0; i <= m; ++i) dp[i][0] = i;
    for (int j = 0; j <= n; ++j) dp[0][j] = j;
    for (int i = 1; i <= m; ++i) {
        for (int j = 1; j <= n; ++j) {
            dp[i][j] = min(dp[i - 1][j] + 1,
                           dp[i][j - 1] + 1,
                           dp[i - 1][j - 1] + editDistDP(t1->children[i - 1], t2->children[j - 1], memo));
        }
    }
    memo[{t1, t2}] = dist + dp[m][n];
    return memo[{t1, t2}];
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

// Função para imprimir a duração do método
void printDuration(const string& method, const nanoseconds& duration) {
    cout << method << ": " << duration.count() << " ns / "
         << duration_cast<milliseconds>(duration).count() << " ms / "
         << duration_cast<seconds>(duration).count() << " s" << endl;
}

// Função para imprimir o resultado e a complexidade de espaço
void printResultAndSpace(const string& method, int result, size_t space) {
    cout << method << " - Resultado: " << result << ", Complexidade de Espaço: " << space << " unidades" << endl;
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

/ Função para calcular a profundidade de um nó
int depth(TreeNode* node, TreeNode* root, int currentDepth = 0) {
    if (node == root) {
        return currentDepth;
    }
    for (TreeNode* child : root->children) {
        int d = depth(node, child, currentDepth + 1);
        if (d != -1) {
            return d;
        }
    }
    return -1; // Nó não encontrado
}


// Função principal para testar os três métodos com strings aleatórias
int main() {
    vector<int> sizes = {100, 200, 300, 400, 500};
    for (int size : sizes) {
        string str1 = generateRandomString(size);
        string str2 = generateRandomString(size);

        TreeNode* t1 = stringToTree(str1);
        TreeNode* t2 = stringToTree(str2);

        // Método Recursivo Ingênuo
        auto startRec = high_resolution_clock::now();
        //int recResult = editDistRec(t1, t2);
        auto stopRec = high_resolution_clock::now();
        auto durationRec = duration_cast<nanoseconds>(stopRec - startRec);
        //printResultAndSpace("Recursivo Ingênuo", recResult, size * size);

        // Programação Dinâmica com Memoization
        map<pair<TreeNode*, TreeNode*>, int> memo;
        auto startMemo = high_resolution_clock::now();
        int memoResult = editDistDP(t1, t2, memo);
        auto stopMemo = high_resolution_clock::now();
        auto durationMemo = duration_cast<nanoseconds>(stopMemo - startMemo);
        printResultAndSpace("DP com Memoization", memoResult, size * size);

        // Método Zhang-Shasha
        auto startZS = high_resolution_clock::now();
        int zsResult = zhangShasha(t1, t2);
        auto stopZS = high_resolution_clock::now();
        auto durationZS = duration_cast<nanoseconds>(stopZS - startZS);
        printResultAndSpace("Zhang-Shasha", zsResult, size * size);

        cout << "Tamanho: " << size << " caracteres" << endl;
        //printDuration("Recursivo Ingênuo", durationRec);
        printDuration("DP com Memoização", durationMemo);
        printDuration("Zhang-Shasha", durationZS);
    }

    return 0;
}
