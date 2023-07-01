#include <stdio.h>
#include<stdbool.h>

int main()
{
    int x = 2; // Se x for importante para o codigo é necessário declarar ele antes de for.
    // Caso não declarado antes, ele irá existir só durante o for.
    int soma = 0;
    int y;
    bool ERRO;
    do{
    printf("\nQuantos termos tera na sequencia: ");
    scanf("%i",&y);
    ERRO = y <= 0;
    
    if(ERRO)
    {
        printf("Opcao Invalida");
    }
    }while(ERRO);

    for(int count = 0; count < y ; count++)
    {
        printf("\nTermo %i = %i",count +1 ,x);
        soma = soma + x;
        x = x + 3;
    }
    printf("\nA soma de todos termos e:%i",soma);

}