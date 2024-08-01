#include <bits/stdc++.h>
using namespace std;
#include <chrono>
using namespace std;
using namespace std::chrono;
#include <omp.h>

// Definição da estrutura de nó da árvore
struct TreeNode {
    char label;
    TreeNode* left;
    TreeNode* right;
    TreeNode(char lbl) : label(lbl), left(nullptr), right(nullptr) {}
};




// Função para criar uma árvore binária balanceada a partir de uma string
TreeNode* createBalancedTree(const string& str, int start, int end) {
    if (start > end) return nullptr;

    int mid = start + (end - start) / 2;
    TreeNode* node = new TreeNode(str[mid]);
    node->left = createBalancedTree(str, start, mid - 1); // Left child
    node->right = createBalancedTree(str, mid + 1, end);  // Right child

    return node;
}

TreeNode* stringToBalancedTree(const string& str) {
    if (str.empty()) return nullptr;
    return createBalancedTree(str, 0, str.length() - 1);
}

// Função utilitária para encontrar o mínimo de três números
int min(int x, int y, int z) { return std::min({x, y, z}); }

// Função recursiva para calcular a distância de edição entre duas árvores
int editDistRec(TreeNode* t1, TreeNode* t2) {
    if (!t1) return t2 ? 1 + editDistRec(nullptr, t2->left) + editDistRec(nullptr, t2->right) : 0;
    if (!t2) return t1 ? 1 + editDistRec(t1->left, nullptr) + editDistRec(t1->right, nullptr) : 0;

    int cost = (t1->label == t2->label) ? 0 : 1;
    return cost + min(
        editDistRec(t1->left, t2->left) + editDistRec(t1->right, t2->right),
        editDistRec(t1->left, t2) + editDistRec(t1->right, t2),
        editDistRec(t1, t2->left) + editDistRec(t1, t2->right)
    );
}

// Programação Dinâmica com Memoization para Distância de Edição entre Árvores
int editDistDP(TreeNode* t1, TreeNode* t2, map<pair<TreeNode*, TreeNode*>, int>& memo) {
    if (!t1) return t2 ? 1 + editDistDP(nullptr, t2->left, memo) + editDistDP(nullptr, t2->right, memo) : 0;
    if (!t2) return t1 ? 1 + editDistDP(t1->left, nullptr, memo) + editDistDP(t1->right, nullptr, memo) : 0;
    if (memo.find({t1, t2}) != memo.end()) return memo[{t1, t2}];

    int dist = (t1->label == t2->label) ? 0 : 1;
    dist += min(
        editDistDP(t1->left, t2->left, memo) + editDistDP(t1->right, t2->right, memo),
        editDistDP(t1->left, t2, memo) + editDistDP(t1->right, t2, memo),
        editDistDP(t1, t2->left, memo) + editDistDP(t1, t2->right, memo)
    );

    memo[{t1, t2}] = dist;
    return dist;
}


// Função para calcular o tamanho da subárvore
int subtreeSize(TreeNode* node) {
    if (!node) return 0;
    return 1 + subtreeSize(node->left) + subtreeSize(node->right);
}
void decomposeTreePostorder(TreeNode* node, std::vector<TreeNode*>& decomposition, std::unordered_map<TreeNode*, int>& nodeIndexMap) {
    if (!node) return;
    decomposeTreePostorder(node->left, decomposition, nodeIndexMap);
    decomposeTreePostorder(node->right, decomposition, nodeIndexMap);
    nodeIndexMap[node] = decomposition.size();
    decomposition.push_back(node);
}

void postOrder(TreeNode* node, unordered_map<TreeNode*, int>& postOrderMap, vector<TreeNode*>& postOrderList, int& index) {
    if (!node) return;
    postOrder(node->left, postOrderMap, postOrderList, index);
    postOrder(node->right, postOrderMap, postOrderList, index);
    postOrderMap[node] = index++;
    postOrderList.push_back(node);
}

int zhangShasha(TreeNode* t1, TreeNode* t2) {
    if (!t1) return t2 ? subtreeSize(t2) : 0;
    if (!t2) return t1 ? subtreeSize(t1) : 0;

    unordered_map<TreeNode*, int> postOrderMap1, postOrderMap2;
    vector<TreeNode*> postOrderList1, postOrderList2;
    int index1 = 0, index2 = 0;

    postOrder(t1, postOrderMap1, postOrderList1, index1);
    postOrder(t2, postOrderMap2, postOrderList2, index2);

    int m = postOrderList1.size();
    int n = postOrderList2.size();
    vector<vector<int>> dp(m + 1, vector<int>(n + 1, 0));

    for (int i = 1; i <= m; ++i) dp[i][0] = dp[i - 1][0] + 1;
    for (int j = 1; j <= n; ++j) dp[0][j] = dp[0][j - 1] + 1;

    for (int i = 1; i <= m; ++i) {
        for (int j = 1; j <= n; ++j) {
            TreeNode* node1 = postOrderList1[i - 1];
            TreeNode* node2 = postOrderList2[j - 1];
            int costReplace = dp[i - 1][j - 1] + (node1->label == node2->label ? 0 : 1);
            int costDelete = dp[i - 1][j] + 1;
            int costInsert = dp[i][j - 1] + 1;
            dp[i][j] = min(costReplace, costDelete, costInsert);
        }
    }

    return dp[m][n];
}
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

// Função para converter string em árvore binária
TreeNode* stringToBinaryTree(const string& str) {
    if (str.empty()) return nullptr;
    TreeNode* root = new TreeNode(str[0]);
    queue<TreeNode*> nodesQueue;
    nodesQueue.push(root);

    for (size_t i = 1; i < str.length(); i += 2) {
        TreeNode* current = nodesQueue.front();
        nodesQueue.pop();

        if (str[i] != '#') {
            current->left = new TreeNode(str[i]);
            nodesQueue.push(current->left);
        }

        if (i + 1 < str.length() && str[i + 1] != '#') {
            current->right = new TreeNode(str[i + 1]);
            nodesQueue.push(current->right);
        }
    }

    return root;
}

// Função para imprimir a árvore binária em ordem
void inorderTraversal(TreeNode* root) {
    if (root) {
        inorderTraversal(root->left);
        cout << root->label << " ";
        inorderTraversal(root->right);
    }
}


// Função principal para testar os três métodos com strings aleatórias
int main() {
    vector<int> sizes = {100, 200, 300, 400, 500};
    for (int size : sizes) {
        string str1 = generateRandomString(size);
        string str2 = generateRandomString(size);

        TreeNode* t1 = stringToBalancedTree(str1);
        TreeNode* t2 = stringToBalancedTree(str2);

        // Método Recursivo Ingênuo
        auto startRec = high_resolution_clock::now();
        //int recResult = editDistRec(t1, t2);
        auto stopRec = high_resolution_clock::now();
        auto durationRec = duration_cast<nanoseconds>(stopRec - startRec);
       // printResultAndSpace("Recursivo Ingênuo", recResult, size * size);

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
        printDuration("Recursivo Ingênuo", durationRec);
        printDuration("DP com Memoização", durationMemo);
        printDuration("Zhang-Shasha", durationZS);
    }
    
    return 0;
}
