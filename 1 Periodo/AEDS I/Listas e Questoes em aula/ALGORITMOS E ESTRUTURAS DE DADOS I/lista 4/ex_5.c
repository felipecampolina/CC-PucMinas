// Escrever de A até B, onde A e B são valores lidos
#include <stdio.h>

int main()
{
    int numero_menor;
    int numero_maior;
do{
    printf("Digite o menor numero:");
    scanf("%i",&numero_menor);
    printf("Digite o maior numero:");
    scanf("%i",&numero_maior);
    }while(numero_maior < numero_menor);

    do{
        printf("%i\n",numero_maior);
        numero_maior = numero_maior - 1 ;
    }while(numero_menor <= numero_maior);
}