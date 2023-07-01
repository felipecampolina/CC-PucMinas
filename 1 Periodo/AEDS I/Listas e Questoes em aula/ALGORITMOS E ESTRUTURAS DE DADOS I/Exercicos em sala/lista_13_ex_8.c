#include <stdio.h>
#include <stdlib.h>
int linhas;
int colunas;

void leiaDimensao()
{
    puts("\nLinhas: ");
    scanf("%i",&linhas);
    puts("\nColunas: ");
    scanf("%i",&colunas);
}
float maiorAcimaDiagonal(float M[][colunas]){
    float maior;

    if(linhas==colunas){
        maior = M[0][1];
        for(int i = 0 ; i < linhas-1 ; i++){
            printf("\n");
            for(int j = i +1; j < colunas ; j++){
                printf("%f",M[i][j]);
                 if(M[i][j]>maior)maior = M[i][j];
            }
        }
    }
    else maior = 0;
    return maior;
}
void leiaMatriz(float M[][colunas])
{
    for(int i = 0; i < linhas ; i++){
        for(int j = 0; j < colunas ; j++){
            printf("a[%i][%i]: ",i,j);
            scanf("%f",&M[i][j]);
        }
    }
}
void leiaArranjo(float M[])
{
    for(int i = 0; i < linhas ; i++){
            printf("a[%i]: ",i);
            scanf("%f",&M[i]);
    }
}
void escreveArranjo(float M[])
{
    for(int i = 0; i < linhas ; i++){
            printf("\na[%i]: %f ",i,M[i]);
    }
}
void inverteArranjo(float M[],int tamanho)
{
    int aux,i,j;
    for(i = 0, j = tamanho -1 ; i < (tamanho/2) ; i++,j--){
        aux = M[i];
        M[i] = M[j];
        M[j] = aux;
    }
}
void inverteArranjoRecursivo(float M[],int i , int j)
{
    int aux;
    if(i<j){
    aux = M[i];
    M[i] = M[j];
    M[j] = aux;
    inverteArranjoRecursivo(M,i+1,j-1);
    }
}
int main(){
    leiaDimensao();
    float M[linhas][colunas];
    float M1[linhas];
    leiaArranjo(M1);
    //inverteArranjo(M1,linhas);
    inverteArranjoRecursivo(M1,0,linhas-1);
    escreveArranjo(M1);
    //leiaMatriz(M);
    //printf("\n O maior valor acima da diagonal e %f",maiorAcimaDiagonal(M));
    return 0;
}

