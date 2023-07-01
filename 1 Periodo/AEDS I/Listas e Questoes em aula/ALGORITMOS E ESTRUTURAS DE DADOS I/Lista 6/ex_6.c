#include <stdio.h>
#include <stdbool.h>

int main ()
{
    int numero;
    bool ERRO;
   long int resultado = 1;
    int aux;


    printf("\nFatorial - exercicio 6 da lista 6");
    do{
        printf("\nDigite um numero: ");
        scanf("%i",&numero);
        ERRO = numero < 0;
    if (ERRO)
    {
        printf("\nOpcao Invalida");
    }
    }while(ERRO);
    aux = numero;

    while(numero != 0)
    {
        resultado *= numero;
        numero--;
    }
    printf("%i! = %d",aux , resultado);
}
// Caso o numero seja muito grande o programa retornará o valor < 0, pois o int tem um limite máximo, que quando atingido vira negativo.

