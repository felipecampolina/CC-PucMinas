#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

int main()
{
   int numero;
   bool ERRO;
   int divisor = 1;
   int numero_divisor = 0;


   printf("\nIdentificador de numero primo - Exercicio 1 da lista");
   do{
    printf("\nDigite um numero: ");
    scanf("%i",&numero);
    ERRO = numero <= 0;
    if (ERRO)
    {
        printf("Opcao Invalida");
    }
    }while(ERRO);

    while(divisor != numero +1)
    {
        if (numero % divisor == 0 )
        {
            numero_divisor++;
        }
        divisor++;

    }

    if (numero_divisor == 2)
    {
        printf("\nO numero %i e primo",numero);
    }
    else
    {
        printf("\nO numero %i NAO e primo",numero);
    }
    printf("\nNumero de divisores: %i",numero_divisor);

    return 0;






}
