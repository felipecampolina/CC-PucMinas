#include <stdio.h>
#include <stdbool.h>

int main()
{
    float base;
    int expoente;
    bool ERRO;
    int count;
    float resultado = 1;



    printf("\nPotenciacao - exercicio 5 da lista 6");
    printf("\nBase: ");
    scanf("%f",&base);
    do{
        printf("Expoente: ");
        scanf("%i",&expoente);
        ERRO = expoente < 0;
        if(ERRO)
        {
            printf("\nOpcao Invalida");
        }

    }while(ERRO);

    for(count = 0 ; count != expoente ; count++ )
    {
        resultado = resultado * base;
    }

printf("\nO resultado e: %.2f",resultado);

}

