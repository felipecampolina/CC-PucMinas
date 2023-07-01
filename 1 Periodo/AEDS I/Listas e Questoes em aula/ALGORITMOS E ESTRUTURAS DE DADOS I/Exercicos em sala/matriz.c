#include <stdio.h>
#define LIN 3
#define COL 2
void escreveMatriz(float M1[][2])
{
    for(int i = 0; i < LIN; i++)//Andando pela  LINHAS matrix
    {
        for(int j = 0; j < COL; j++)//Andandando pelas COLUNAS matrix
        {
        printf("\t%.1f",M1[i][j]);
        }
        puts("\n"); // escrever uma string
    }
}
void leiaMatrix(float M[][COL])//Apenas colunas necessárias para declarar
{

    for(int i = 0; i<LIN ; i++)
    {
        for(int j = 0 ; j<COL; j++)\
        {
            printf("\nM[%i][%i]",i,j);
            scanf("%f",&M[i][j]);
        }
    }
}
void somaMatriz(float M1[][COL],float M2[][COL],float M3[][COL]){
    for(int i = 0; i<LIN; i++){
        for(int j = 0; j<COL;j++){
            M3[i][j] = M2[i][j] + M1[i][j];
        }
    }

}
int contaNegativos(float M1[][COL]){
    int negativos = 0;
    for(int i = 0; i<LIN;i++){
        for(int j = 0; j<COL; j++){
            if(M1[i][j] < 0)negativos++;
        }
    }
    printf("Negativos: %i",negativos);
    return negativos;
}
int main(){

    float M1[][2] = {{5,6},{7,8},{9,10}};
    //escreveMatriz(M1);
    float M2[LIN][COL];
    leiaMatrix(M2);
    escreveMatriz(M2);
    float M3[LIN][COL];
    somaMatriz(M1,M2,M3);
    escreveMatriz(M3);
    contaNegativos(M3);
}