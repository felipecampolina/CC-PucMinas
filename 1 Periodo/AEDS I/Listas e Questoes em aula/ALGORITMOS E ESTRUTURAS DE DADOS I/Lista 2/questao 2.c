#include<stdio.h>
#include<stdlib.h>

int main()
{
    int idade;
    printf("Digite sua idade em anos: ");
    scanf("%i",&idade);
    int dias = idade * 365;
    printf("Voce ja viveu %i dias",dias);
}
