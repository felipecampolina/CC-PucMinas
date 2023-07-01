#include <stdio.h>
#include <stdbool.h>

int main()
{
    int numero = 0, divisor = 1, soma_divisor = 0;
    bool ERRO;


    printf("\nVerificador de numero perfeito - ex 2 da lista 7 ");

    do{
        printf("\nDigite um numero: [x > 0] ");
        scanf("%i",&numero);
        ERRO = numero <= 0;
        if(ERRO)
        {
            printf("\nOpcao Invalida");
        }

    }while(ERRO);
    
    while(divisor < numero)
    {
        if(numero % divisor == 0)
        {
            soma_divisor += divisor;
        }
        divisor++;
    }

    if (soma_divisor == numero)
    {
        printf("\nO numero %i e um divisor perfeito",numero);
    }
    else
    {
        printf("\nO numero %i NAO um divisor perfeito",numero);
    }
    printf("\n%i",soma_divisor);
    return 0;
    }