#include <stdio.h>
#include <stdbool.h>
#define LIN 3
#define COL 3
void leiaMatriz(float M1[][COL]){
    for(int i = 0 ; i<LIN; i++){
        for(int j=0;j<COL;j++){
            printf("M[%i][%i]: ",i,j);
            scanf("%f",&M1[i][j]);
        }
    }
}
void copiaMatriz(float M1[][COL],float M2[][COL]){
    for(int i = 0 ; i<LIN; i++){
        for(int j=0;j<COL;j++){
            M2[i][j] = M1[i][j];
        }
    }
}
void escreveMatriz(float M1[][COL]){
    for(int i = 0 ; i<LIN; i++){
        for(int j=0;j<COL;j++){
            printf("\t%.1f",M1[i][j]);
        }
        printf("\n");
    }
}
bool saoMatrizesIguais(float M1[][COL],float M2[][COL]){
    int i= 0, j = 0;
    bool iguais = false;

    while(!iguais && i<LIN ){
        if(M1[i][j] == M2[i][j])iguais = true;
        j++;
        if(j == COL){
            j = 0;
            i++;
        }
    }
    return iguais;
}
int quantidadeNumeroMatriz(float M1[][COL]){
    float numero;
    int numeros_iguais = 0;

    puts("\nDigite o numero a ser contado: ");
    scanf("%f",&numero);
    
    for(int i = 0 ; i<LIN; i++){
        for(int j=0;j<COL;j++){
            if(M1[i][j] == numero) numeros_iguais++;
        }
    }
    return numeros_iguais;

}
bool haNumeroEspecifico(float M1[][COL],int x){
    int i = 0, j = 0;
    bool haNumero = false;


    while(!haNumero && i < LIN){
        if(M1[i][j] == x ) haNumero = true;
        j++;
        if(j == COL){
            j = 0;
            i++;
        }
    }
    return haNumero;
}
float maiorNumeroDaPrimeiraLinha(float M1[][COL]){
    float maior_numero;
    for(int i = 0; i< LIN ; i++){
        if(maior_numero < M1[0][i])maior_numero = M1[0][i];
    }
    return maior_numero;
}
float mediamatriz(float M1[][COL]){
    float soma = 0;
    int qntNumeros = 0;
    float media;
    for(int i = 0 ; i<LIN; i++){
        for(int j=0;j<COL;j++){
            soma += M1[i][j];
            qntNumeros ++;
        }
    }
    media = soma / qntNumeros;

    return media;
}
int numerosMaiorMedia(float M1[][COL],float media){
    int numeros_maiores = 0;
    for(int i = 0 ; i<LIN; i++){
        for(int j=0;j<COL;j++){
            if(M1[i][j] > media) numeros_maiores++;
        }
    }
    return numeros_maiores;
}
float somaDiagonalPrincipal(float M1[][COL]){
   float soma = 0;
    for(int i = 0 ; i<LIN; i++){
        for(int j=0;j<COL;j++){
            if(i == j)soma += M1[i][j];
        }
    }
    return soma;
}
bool diagonalPrimeiraLinhaIguais(float M1[][COL]){
    bool saoiguais = false;
    int j = 0 , i = 0;
    while((LIN == COL) && (i < LIN) ){
        if(M1[0][j] == M1[i][j])saoiguais = true;
        else saoiguais = false;
        j++;
        if(j == COL){
            j = 0;
            i++;
        }
    }
    return saoiguais;
}
float maiorNumeroForaDiagonal(float M1[][COL]){
    float maior_numero_diagonal = 0;
    for(int i = 0 ; i<LIN; i++){
        for(int j=0;j<COL;j++){
            if((i != j) && (M1[i][j] > maior_numero_diagonal))maior_numero_diagonal = M1[i][j];
        }
    }
    return maior_numero_diagonal;
}
int main (){
    int numero_definido = 5;

    float M1[LIN][COL];
    float M2[LIN][COL];
    leiaMatriz(M1);
    copiaMatriz(M1,M2);// EXERCICIO 1
    escreveMatriz(M2);
    //if(saoMatrizesIguais(M1,M2)) printf("\nAs matrizes sao iguais");// EXERCÍCIO 2
    //else printf("\nAs matrizes sao diferentes");
    //printf("\nExistem %i desse numero",quantidadeNumeroMatriz(M1));//EXERCICIO 3
    //if(haNumeroEspecifico(M1,numero_definido))printf("\nExiste pelo menos um %i nesta matriz ",numero_definido);//EXERCICIO 4
    //printf("\nO maior numero da primeira linha e: %.1f",maiorNumeroDaPrimeiraLinha(M1));//EXERCICIO 5
    //printf("\nExistem %i numeros maiores que a media %.2f",numerosMaiorMedia(M1,mediamatriz(M1)),mediamatriz(M1));//EXERCICIO 6
    //printf("\nSoma da diagonal principal: %.2f",somaDiagonalPrincipal(M1));//EXERCICIO 7
    //if(diagonalPrimeiraLinhaIguais(M1))printf("\nA primeira linha e a diagonal sao iguais"); // EXERCICIO 8
    //printf("\nO maior numero fora da diagonal principal e %.1f",maiorNumeroForaDiagonal(M1)); // EXERCICIO 9





    
}