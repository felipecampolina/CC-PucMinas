#include <stdio.h>
#include <stdbool.h>

int main ()
{
    int termo;
    float razao;
    int numero_de_termos;
    bool ERRO;
    int count ;
    int divisor;
    int divisivel = 0;
    int nao_divisivel = 0;
    char continuar;





    printf("\nProgressao Aritmetica - exercicio 7 da lista 6");

    do{

    
        printf("\nPrimeiro termo: ");
        scanf("%i",&termo);
        printf("\nRazao: ");
        scanf("%f",&razao);
    do{
        printf("\nDivisor: ");
        scanf("%i",&divisor);
        ERRO = divisor <= 0;
    }while(ERRO);
    do{
        printf("\nNumero de termos: ");
        scanf("%i",&numero_de_termos);
        ERRO = numero_de_termos <= 0;
    if (ERRO)
    {
        printf("Opcao Invalida");
    }
    }while(ERRO);

    for(count = 0 ; count != numero_de_termos ; count++)
    {
        if (termo % divisor == 0)
        {
            divisivel++;
        }
        if (termo % divisor != 0 )
        {
            nao_divisivel++;
        }
        printf(" %i",termo);
        termo = termo + razao;
    }

    printf("\n%i Numeros desta sequencia sao divisiveis por %i.",divisivel , divisor);
    printf("\n%i Numeros desta sequencia NAO sao divisiveis por %i.",nao_divisivel , divisor);

    do
    {
        printf("\nDeseja continuar? [S/N]");
        scanf(" %c",&continuar);
        continuar = toupper(continuar);
    }while((continuar != 'S') && (continuar != 'N'));

    }while(continuar == 'S');
}