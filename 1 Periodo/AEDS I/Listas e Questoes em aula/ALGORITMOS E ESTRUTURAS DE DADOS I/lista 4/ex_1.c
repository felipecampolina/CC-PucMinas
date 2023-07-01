#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <ctype.h>

int main()
{
    printf("\n\tCalculo do Peso Ideal\n\n");

    bool ERRO;
    float h;
    char resp;
do{
    do{
        printf("\nAltura: ");
        scanf("%f", &h);
        ERRO = h<0;
    }while(ERRO);

    char genero;
    do{
        printf("\nGenero [M/F]: ");
        scanf(" %c", &genero);
        genero = toupper(genero);
        ERRO = (genero != 'M') && (genero != 'F');
    }while(ERRO);

    float pesoIdeal;
    if(genero == 'M') pesoIdeal = 72.7 * h - 58;
    else pesoIdeal = 62.1 * h - 44.7;

    printf("\nPeso Ideal = %f", pesoIdeal);
    do{
        printf("\nDeseja continuar?: [S/N]");
        scanf(" %c",&resp);
        resp = toupper(resp);
        ERRO = (resp != 'S')&& (resp !='N');
    }while(ERRO);

}while(resp == 'S');
    return 0;
}
