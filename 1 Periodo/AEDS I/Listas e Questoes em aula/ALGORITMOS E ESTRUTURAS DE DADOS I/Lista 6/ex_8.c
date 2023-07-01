#include <stdio.h>
#include <stdbool.h>

int main()
{
    int numero;
    int maior_numero = 0;
    int menor_numero ;
    int amplitude;
    int count = 0;
    bool ERRO;
    char continuar;



    printf("\nCalculadora de amplitude de um conjunto - exercicio 8 da lista 6");
    do{
        do{
            do{
                printf("\nNumero: ");
                scanf("%i",&numero);
                ERRO = numero < 0;
            if(ERRO)
            {
                printf("Opcao Invalida");
            }
            }while(ERRO);
        ERRO = numero != 0;
        if (numero > maior_numero)
        {
            maior_numero = numero;
        }
        if ((numero <= menor_numero) && (numero != 0))
        {
            menor_numero = numero;
        }
        if (numero == 0)
        {
            count = count;
        }
        else
        {
            count++;
        }
        }while(ERRO);
    
    if((numero == 0) && (count == 0))
    {
        printf("Nenhum valor informado");
    }
    else
    {
        amplitude = maior_numero - menor_numero;
        printf("\nMaior numero : %i",maior_numero);
        printf("\nMenor numero : %i",menor_numero);
        printf("\nAmplitude: %i",amplitude);
        printf("\nNumeros contados: %i",count);
    }

        maior_numero = 0;
        menor_numero = 1000000 ;
        count = 0;
    
        do
        {
            printf("\nDeseja continuar? [S/N]");
            scanf(" %c",&continuar);
            continuar = toupper(continuar);
        }while((continuar != 'S') && (continuar != 'N'));

    }while(continuar == 'S');
}