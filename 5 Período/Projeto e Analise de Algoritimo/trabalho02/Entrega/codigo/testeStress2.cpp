#include <iostream>
#include <vector>
#include <algorithm>
#include <limits.h>
#include <chrono>
#include <cstdlib>
using namespace std;
using namespace std::chrono;

struct TreeNode {
    int val;
    vector<TreeNode*> children;
    TreeNode(int x) : val(x) {}
};

// Função para calcular a distância de edição recursiva ingênua
int editDistRec(TreeNode* t1, TreeNode* t2) {
    if (!t1) return t2 ? 1 : 0;
    if (!t2) return t1 ? 1 : 0;
    int cost = (t1->val == t2->val) ? 0 : 1;
    int m = t1->children.size();
    int n = t2->children.size();
    vector<vector<int>> dp(m + 1, vector<int>(n + 1));
    for (int i = 0; i <= m; ++i) dp[i][0] = i;
    for (int j = 0; j <= n; ++j) dp[0][j] = j;
    for (int i = 1; i <= m; ++i) {
        for (int j = 1; j <= n; ++j) {
            dp[i][j] = min({dp[i-1][j] + 1, dp[i][j-1] + 1, dp[i-1][j-1] + (t1->children[i-1]->val == t2->children[j-1]->val ? 0 : 1)});
        }
    }
    return dp[m][n] + cost;
}

// Função para calcular a distância de edição usando o algoritmo de Tai
int taiTreeEditDistance(TreeNode* t1, TreeNode* t2) {
    if (!t1) return t2 ? 1 : 0;
    if (!t2) return t1 ? 1 : 0;
    int cost = (t1->val == t2->val) ? 0 : 1;
    int m = t1->children.size();
    int n = t2->children.size();
    vector<vector<int>> dp(m + 1, vector<int>(n + 1));
    for (int i = 0; i <= m; ++i) dp[i][0] = i;
    for (int j = 0; j <= n; ++j) dp[0][j] = j;
    for (int i = 1; i <= m; ++i) {
        for (int j = 1; j <= n; ++j) {  // Corrigido aqui
            dp[i][j] = min({dp[i-1][j] + 1, dp[i][j-1] + 1, dp[i-1][j-1] + (t1->children[i-1]->val == t2->children[j-1]->val ? 0 : 1)});
        }
    }
    return dp[m][n] + cost;
}

// Função para calcular a distância de edição usando o algoritmo de Zhang-Shasha
int zhangShasha(TreeNode* t1, TreeNode* t2) {
    if (!t1) return t2 ? 1 : 0;
    if (!t2) return t1 ? 1 : 0;
    int cost = (t1->val == t2->val) ? 0 : 1;
    int m = t1->children.size();
    int n = t2->children.size();
    vector<vector<int>> dp(m + 1, vector<int>(n + 1));
    for (int i = 0; i <= m; ++i) dp[i][0] = i;
    for (int j = 0; j <= n; ++j) dp[0][j] = j;
    for (int i = 1; i <= m; ++i) {
        for (int j = 1; j <= n; ++j) {
            dp[i][j] = min({dp[i-1][j] + 1, dp[i][j-1] + 1, dp[i-1][j-1] + (t1->children[i-1]->val == t2->children[j-1]->val ? 0 : 1)});
        }
    }
    return dp[m][n] + cost;
}

// Função para gerar strings aleatórias
string generateRandomString(int length) {
    string characters = "abcdefghijklmnopqrstuvwxyz";
    string result = "";
    for (int i = 0; i < length; ++i) {
        result += characters[rand() % characters.size()];
    }
    return result;
}

// Função para converter string em árvore de forma mais complexa
TreeNode* stringToTree(const string& str) {
    if (str.empty()) return nullptr;
    TreeNode* root = new TreeNode(str[0]);
    for (size_t i = 1; i < str.size(); ++i) {
        TreeNode* current = root;
        for (int j = 0; j < rand() % 3; ++j) {
            if (current->children.empty()) {
                current->children.push_back(new TreeNode(str[i]));
            }
            current = current->children[0];
        }
        current->children.push_back(new TreeNode(str[i]));
    }
    return root;
}

// Modifica ligeiramente a árvore base para criar a árvore adversária
TreeNode* modifyTree(const string& str) {
    string adversaryString = str;

    int modifyIndex = rand() % adversaryString.size();

    while(adversaryString[modifyIndex] == str[modifyIndex]) {
        adversaryString[modifyIndex] = 'A' + rand() % 26;
    }
    
    TreeNode* tree2 = stringToTree(adversaryString);

    return tree2;
}

int main() {
    srand(time(0));

    for (int length = 10000; length <= 100000; length += 10000) {
        string str1 = generateRandomString(length);

        TreeNode* t1 = stringToTree(str1);
        TreeNode* t2 = modifyTree(str1);


        auto start = high_resolution_clock::now();
        int distRec = editDistRec(t1, t2);
        auto stop = high_resolution_clock::now();
        auto duration = duration_cast<milliseconds>(stop - start);
        cout << "Recursive Edit Distance for length " << length << ": " << distRec << " (" << duration.count() << " ms)" << endl;

        start = high_resolution_clock::now();
        int distZhangShasha = zhangShasha(t1, t2);
        stop = high_resolution_clock::now();
        duration = duration_cast<milliseconds>(stop - start);
        cout << "Zhang-Shasha Edit Distance for length " << length << ": " << distZhangShasha << " (" << duration.count() << " ms)" << endl;

        start = high_resolution_clock::now();
        int distTai = taiTreeEditDistance(t1, t2);
        stop = high_resolution_clock::now();
        duration = duration_cast<milliseconds>(stop - start);
        cout << "Tai Edit Distance for length " << length << ": " << distTai << " (" << duration.count() << " ms)" << endl;
        cout << endl;
    }

    return 0;
}
