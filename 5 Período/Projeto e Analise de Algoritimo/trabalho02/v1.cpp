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

// Método Recursivo Ingênuo para Distância de Edição entre Árvores
int editDistRec(TreeNode* t1, TreeNode* t2) {
    if (!t1) return t2 ? 1 + editDistRec(nullptr, t2) : 0;
    if (!t2) return t1 ? 1 + editDistRec(t1, nullptr) : 0;
    int dist = (t1->label == t2->label) ? 0 : 1;
    int m = t1->children.size();
    int n = t2->children.size();
    vector<vector<int>> dp(m + 1, vector<int>(n + 1, 0));
    for (int i = 0; i <= m; ++i) dp[i][0] = i;
    for (int j = 0; j <= n; ++j) dp[0][j] = j;
    for (int i = 1; i <= m; ++i) {
        for (int j = 1; j <= n; ++j) {
            dp[i][j] = min(dp[i - 1][j] + 1, // Remover
                           dp[i][j - 1] + 1, // Inserir
                           dp[i - 1][j - 1] + editDistRec(t1->children[i - 1], t2->children[j - 1])); // Substituir
        }
    }
    return dist + dp[m][n];
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
            dp[i][j] = min(dp[i - 1][j] + 1, // Remover
                           dp[i][j - 1] + 1, // Inserir
                           dp[i - 1][j - 1] + editDistDP(t1->children[i - 1], t2->children[j - 1], memo)); // Substituir
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

// Função para imprimir a árvore
void printTree(TreeNode* root) {
    if (!root) return;
    queue<TreeNode*> q;
    q.push(root);
    while (!q.empty()) {
        int levelSize = q.size();
        while (levelSize--) {
            TreeNode* node = q.front();
            q.pop();
            cout << node->label << " ";
            for (TreeNode* child : node->children) {
                q.push(child);
            }
        }
        cout << endl; // Nova linha para cada nível
    }
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

// Função principal para testar os três métodos com strings aleatórias
int main() {
    vector<int> sizes = {100, 1000, 2000, 3000, 4000, 5000, 6000};
    for (int size : sizes) {
        string str1 = generateRandomString(size);
        string str2 = generateRandomString(size);

        TreeNode* t1 = stringToTree(str1);
        TreeNode* t2 = stringToTree(str2);

        // Método Recursivo Ingênuo
        auto startRec = high_resolution_clock::now();
        int recResult = editDistRec(t1, t2);
        auto stopRec = high_resolution_clock::now();
        auto durationRec = duration_cast<nanoseconds>(stopRec - startRec);
        printResultAndSpace("Recursivo Ingênuo", recResult, size * size);  // Aproximação da complexidade de espaço

        // Programação Dinâmica com Memoization
        map<pair<TreeNode*, TreeNode*>, int> memo;
        auto startMemo = high_resolution_clock::now();
        int memoResult = editDistDP(t1, t2, memo);
        auto stopMemo = high_resolution_clock::now();
        auto durationMemo = duration_cast<nanoseconds>(stopMemo - startMemo);
        printResultAndSpace("DP com Memoization", memoResult, memo.size());

        // Método Zhang-Shasha
        auto startZS = high_resolution_clock::now();
        int zsResult = zhangShasha(t1, t2);
        auto stopZS = high_resolution_clock::now();
        auto durationZS = duration_cast<nanoseconds>(stopZS - startZS);
        printResultAndSpace("Zhang-Shasha", zsResult, size * size);  // Aproximação da complexidade de espaço

        cout << "Tamanho: " << size << " caracteres" << endl;
        printDuration("Recursivo Ingênuo", durationRec);
        printDuration("DP com Memoização", durationMemo);
        printDuration("Zhang-Shasha", durationZS);
    }

    return 0;
}
