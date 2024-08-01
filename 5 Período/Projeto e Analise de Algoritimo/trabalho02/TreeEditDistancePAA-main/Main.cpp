#include <iostream>
#include "TreeDistance.hpp"
#include "LabelTree.hpp"

using namespace std;

int main()
{
    // Create trees for comparison
    LabelTree tree1("Tree1");
    tree1.addNode("", "D");
    tree1.addNode("D", "B");
    tree1.addNode("B", "A");
    tree1.addNode("B", "C");
    tree1.addNode("D", "F");
    tree1.addNode("F", "E");
    tree1.addNode("F", "G");

    LabelTree tree2("Tree2");
    tree2.addNode("", "F");
    tree2.addNode("F", "E");
    tree2.addNode("E", "C");
    tree2.addNode("F", "G");

    // Create TreeEditDistance object
    TreeEditDistance ted;
    int distance = ted.calculateTreeDistance(tree1, tree2);
    cout << "Tree Edit Distance: " << distance << endl;
    return 0;
}