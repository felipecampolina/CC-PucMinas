#include<stdio.h>
#include<stdbool.h>

int main()
{
    printf("\nIdade Media da turma");
    int n;
    bool ERRO;
do{
    printf("\nTamanho da turma: ");
    scanf("%i",&n);
    ERRO = n <= 0;
    if(ERRO) printf("\nOpcao Invalida");
}while(ERRO);
    

    int idade_turma;
    int soma_idade = 0;
    int contador = 0;
do{
    do{
    printf("\nDigite a idade do aluno: ");
    scanf("%i",&idade_turma);
    ERRO = (idade_turma <= 0) || (idade_turma > 150);
    if(ERRO) printf("\nOpcao Invalida");
    }while(ERRO);


    soma_idade = soma_idade + idade_turma;
    contador = contador + 1;
}while(contador < n );

float media_turma = (float) soma_idade / n ;
printf("A media das idades e %.2f",media_turma);

   
}