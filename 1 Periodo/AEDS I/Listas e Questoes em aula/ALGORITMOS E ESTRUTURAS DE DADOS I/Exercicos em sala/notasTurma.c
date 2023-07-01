#include <stdio.h>
#include <stdbool.h>
#include <stddef.h>
FILE* criaArquivo(char* nome);
FILE* abreArquivo(char* nome);
void leiaNotas(FILE *arq);
int verificaNotas(FILE *arq);
int main()
{
    FILE *arq = criaArquivo("notas.dat");
    leiaNotas(arq);
    if(arq != NULL) arq = abreArquivo("notas.dat");
    printf("\nO numero de alunos que farao prova complementar = %i",verificaNotas(arq));
}
FILE* criaArquivo(char* nome)
{
    FILE *arquivo;
    arquivo = fopen(nome,"w");
    return arquivo;
}
void leiaNotas(FILE *arq)
{
    float notas = 0;
    const FLAG = 0;
    bool ERRO;
    do{
    printf("\nDigite uma nota [ FLAG = 0]");
    scanf("%f",&notas);
    ERRO = (notas < 0) || (notas > 100);
    if(ERRO)printf("\nOpcao Invalida ]0,100]");
    }while(ERRO);
    while(notas != FLAG)
    {
        do{
        fprintf(arq,"%.2f\n",notas);
        printf("\nDigite uma nota [ FLAG = 0]");
        scanf("%f",&notas);
        if(ERRO)printf("\nOpcao Invalida ]0,100]");
        }while(ERRO);
    }
    fclose(arq);

}
int verificaNotas(FILE *arq)
{
    float notas;
    int provaComplementar = 0, bombaDireta = 0 , Aprovados = 0, count =  0;
    fscanf(arq,"%f",&notas);
    while(!feof(arq))
    {
        count++;
        if((notas > 20) && (notas < 60)) provaComplementar++;
        else if (notas < 20) bombaDireta++;
        else Aprovados++;
        fscanf(arq,"%f",&notas);
    }
    return provaComplementar;
}
FILE * abreArquivo(char* nome)
{
    return fopen(nome,"r");
}