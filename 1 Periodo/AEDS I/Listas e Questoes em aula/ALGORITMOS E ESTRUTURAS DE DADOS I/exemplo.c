#include <stdio.h>

int main()
{
    printf("\nDias Vividos\n\n");
    int idade;
    int erro;
    
do
{
    printf("\nIdade: ");
    scanf("%i",&idade);
    erro = (idade < 0) || (idade > 150);

    if(erro)
    {
        printf("\n\aVALOR INVALIDO");
    }
}
while(erro);

    int dias = idade * 365;
    printf("Dias vividos: %i",dias);
    return 0;
}