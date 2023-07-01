#include<stdio.h>
#include<stdlib.h>

int main()
{
    int numero;
    printf("Digite um numero inteiro");
    scanf("%i",&numero);

    if(numero % 2 == 0)
    {
        printf("O numero %i",numero);
        printf("e par");
    }
    else
    {
        printf("O numero %i ",numero);
        printf("e impar");
    }
}