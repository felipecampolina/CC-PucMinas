#include<bits/stdc++.h> 
using namespace std; 

int N; 

// function for printing the solution 
void printSol(vector<vector<int>>& board) 
{ 
    for(int i = 0; i < N; i++){ 
        for(int j = 0; j < N; j++){ 
            cout << board[i][j] << " "; 
        } 
        cout << "\n"; 
    } 
    cout << "\n"; 
} 

// Recursive function to solve N-queen Problem 
bool solve(vector<vector<int>>& board, int col, vector<bool>& rows, vector<bool>& left_digonals, vector<bool>& right_digonals) 
{        
    // base Case : If all Queens are placed  
    if(col >= N){ 
        printSol(board); 
        return true; 
    } 

    /* Consider this Column and move in all rows one by one */
    for(int i = 0; i < N; i++) 
    { 
        if(rows[i] == false && left_digonals[i + col] == false && right_digonals[col - i + N - 1] == false) 
        { 
            cout << "Placing queen at row " << i << " and col " << col << endl; 
            rows[i] = true; 
            left_digonals[i + col] = true; 
            right_digonals[col - i + N - 1] = true; 
            board[i][col] = 1; // placing the Queen in board[i][col] 
            printSol(board);

            /* recur to place rest of the queens */
            if(solve(board, col + 1, rows, left_digonals, right_digonals) == true){ 
                // No need to return true here, just continue exploring other possibilities
            } 

            // Backtracking  
            cout << "Removing queen from row " << i << " and col " << col << endl; 
            rows[i] = false; 
            left_digonals[i + col] = false; 
            right_digonals[col - i + N - 1] = false; 
            board[i][col] = 0; // removing the Queen from board[i][col] 
            printSol(board);
        } 
    } 

    return false; // return false when no solution is found in this path
} 

int main() 
{ 
    // Taking input from the user 
    cout << "Enter the no of rows for the square Board : "; 
    N = 4; 

    // board of size N*N 
    vector<vector<int>> board(N, vector<int>(N, 0)); 

    // array to tell which rows are occupied 
    vector<bool> rows(N, false); 

    // arrays to tell which diagonals are occupied                        
    vector<bool> left_digonals(2*N-1, false); 
    vector<bool> right_digonals(2*N-1, false); 

    solve(board, 0, rows, left_digonals, right_digonals); 

    return 0;
}
