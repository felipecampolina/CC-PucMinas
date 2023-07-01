#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#define colunas 4
#define linhas 4 

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
int funcao(int a,int *b){
    int x;
    x = a * (*b);
    *b = *b * 2;
    return x;
}
void leiaValores(FILE *antigo){
    antigo = fopen("dados.dat","w");
    float numeros;
    int tamanho = 5;
    for(int i = 0; i < tamanho ; i++){
        printf("\nNumero : ");
        scanf("%f",&numeros);
        fprintf(antigo,"%.1f\n",numeros);
    }
    fclose(antigo);
}
void apagaInvalidos(FILE * antigo , FILE * novo){
    antigo = fopen("dados.dat","r");
    novo = fopen("novos.dat","w");
    float numero;
    fscanf(antigo,"%f",&numero);
    while(!feof(antigo)){
        if(numero >= 0)fprintf(novo,"%.1f\n",numero);
        fscanf(antigo,"%f",&numero);
    }
    fclose(novo);
    fclose(antigo);
}
void inverteArranjo(int M1[linhas],int tamanho){
    int aux,i,j;
    for(i = 0, j = tamanho - 1; i <(tamanho/2); i++ , j--){
        aux = M1[i];
        M1[i] = M1[j];
        M1[j] = aux;
    }
    
}
void escreveArranjo(int M1[linhas]){
    for(int i = 0; i < linhas ; i++){
        printf("\na[%i] = %i",i,M1[i]);
    }
}
void inverteArranjoRecursivo(int M1[linhas], int i , int j){
    
    int aux;
    if(i > j){
    aux = M1[i];
    M1[i] = M1[j];
    M1[j] = aux;
    inverteArranjoRecursivo(M1,i+1,j-1);
    }
}
int somaArranjo(int M1[linhas]){
    int soma = 0;
    for(int i = 0; i<linhas; i++){
        soma += M1[i];

    }
    return soma;

}
int somaArranjoRecursivo(int M1[linhas],int i , int j){
    int soma = 0;
    if(i < j) soma = M1[i] + somaArranjoRecursivo(M1,i+1,j);
    return soma;
}
int numeroVogais(char palavra[]){
    int tamanho = strlen(palavra);
    int numero_vogais = 0;
    char vogais[4] = {'A','E','I','O','U'};
    for(int i = 0 ; i < tamanho ; i++){
        for(int j = 0; j < 4 ; j++){
            palavra[i] = toupper(palavra[i]);
            if(palavra[i] == vogais[j])numero_vogais++;
        }
    }
    return numero_vogais;

}
bool verdadeiro(int M1[][colunas],int M2[][colunas]){
    int i = 0,num1,num2;
    bool iguais = true;
    while(iguais && (i<colunas)){
        num1 = M1[0][i];
        num2 = M2[i][0];
        if(num1 != num2) iguais = false;
        i++;
    }
    return iguais;
}
int main(){
    //float M[linhas][colunas];
    leiaMatriz(M);
    //printf("\n O maior valor acima da diagonal e %f",maiorAcimaDiagonal(M));  //EXERCICIO 1
    //int a = 7; 
    //int b= 2; 
    //int x = funcao(a, &b); //EXERCICIO 2
    //printf("\n%i", x); 
    //printf("\n%i", b); 
    //FILE * antigo;
    //FILE *novo;
    //leiaValores(antigo);
    //apagaInvalidos(antigo,novo); //EXERCICIO 3
    int M1[linhas] = {10,20,30,40}; 
    int soma = 0;
    //inverteArranjo(M1,linhas);// EXERCICIO 4  A
    //inverteArranjoRecursivo(M1,0,linhas-1);//EXERCICIO 4 B
    //escreveArranjo(M1);
    //printf("\nA soma e %i",somaArranjo(M1));//EXERCICIO 5 A
    //soma = somaArranjoRecursivo(M1,0,linhas);// EXERCICIO  B
    //printf("\nA soma e %i",soma);
    //char palavra[30];
    //printf("\nPalavra: ");
    //gets(palavra);
    //printf("\nO numero de vogais e %i",numeroVogais(palavra));//EXERCICIO 6
    //return 0;
    int matriz1[4][4] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
    int matriz2[4][4] = {1,5,6,7,2,8,9,10,3,11,12,13,4,14,15,16};
    if(verdadeiro(matriz1,matriz2))printf("VERDADEIRO");//EXERCICIO 7
    else printf("FALSO");
}