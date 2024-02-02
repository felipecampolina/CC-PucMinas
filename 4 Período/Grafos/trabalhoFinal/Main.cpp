#include <iostream>
#include "Graph.hpp"




int main() {
    Graph g;
    g.addEdge(0, 1, 8);
    g.addEdge(0, 2, 5);
    g.addEdge(0, 4, 2);
    g.addEdge(1, 2, 1);
    g.addEdge(2, 1, 2);
    g.addEdge(2, 3, 3);
    g.addEdge(4, 1, 3);
    g.addEdge(1, 4, 1);
    g.addEdge(4, 5, 5);
    g.addEdge(5, 4, 1);
    


    Graph mst = g.findMST(0);


   

    return 0;
}



