#include <stdio.h>
#include <stdbool.h>

int main ()
{
    int tamanho = 0,count;
    bool ERRO;
    int razao , termo , pares = 0;

    printf("\nNumeros pares em uma PG");
    do{
        printf("\nDigite o tamanho: ");
        scanf("%i",&tamanho);
        ERRO = tamanho <= 0;
        if(ERRO)
        {
            printf("\n\aOpcao Invalida");
        }
    }while(ERRO);
    printf("\nDigite a razao: ");
    scanf("%i",&razao);
    printf("\nDigite o primeiro termo: ");
    scanf("%i",&termo);
    for(count = 0; count < tamanho ; count++)
    {
        if(termo % 2 == 0)
        {
            pares++;
        }
        printf("%i ",termo);
        termo *= razao;
        
    }
    printf("\nNumeros de pares nesta PG : %i",pares);

}