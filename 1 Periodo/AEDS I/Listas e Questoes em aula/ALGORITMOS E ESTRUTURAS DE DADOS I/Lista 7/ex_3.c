#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

int main()
{
    int numero_alunos;
    bool ERRO;
    float nota;
    float maior_nota;
    float menor_nota = 20;
    float aux;
    float media;
    float soma;
    int aprovados = 0;


    printf("\nNotas aluno - exercicio 3 lista 7");

    do{
        printf("\nDigite o numero de alunos: ");
        scanf("%i",&numero_alunos);
        ERRO = numero_alunos <= 0;
        if(ERRO)
        {
            printf("Opcao Invalida");
        }
    }while(ERRO);
    aux = numero_alunos;

    while(numero_alunos > 0)
    {


        do{
        printf("\nDigite a nota do aluno: ");
        scanf("%f",&nota);
        ERRO = ((nota < 0) || (nota > 20));
        if(ERRO)
        {
            printf("Opcao Invalida");
        }
        }while(ERRO);
        numero_alunos--;

        if (nota > maior_nota)
        {
            maior_nota = nota;
        }
        if (nota <= menor_nota)
        {
            menor_nota = nota;
        }
        soma += nota;

        if(nota >= 12)
        {
            aprovados++;
        }


   }
   media = soma / aux;

   printf("\nA maior nota e %.2f",maior_nota);
   printf("\nA menor nota e %.2f",menor_nota);
   printf("\nA soma das notas e: %.2f",soma);
   printf("\nA media das notas e: %.2f",media);
   printf("\nNumero de alunos aprovados %i",aprovados);

}
