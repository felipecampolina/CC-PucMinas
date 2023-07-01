#include <stdio.h>
#include <stdbool.h>

int main()
{
    float primeiro_termo;
    float razao;
    bool ERRO;
    int quantidade_termos;
    int count;


    printf("\nCalculadora de PG");
    printf("\nDigite o primeiro termo: ");
    scanf("%f",&primeiro_termo);
    printf("\nDigite a razao: ");
    scanf("%f",&razao);
    float termos = primeiro_termo ;
   
    do{
    printf("\nDigite a quantidade de termos da PG: ");
    scanf("%i",&quantidade_termos);
    ERRO = quantidade_termos <= 0;
    if (ERRO)
    {
        printf("Opcao Invalida");
    }
    }while(ERRO);
    printf("\n%.1f",primeiro_termo);
    for(count = 0; count != quantidade_termos - 1; count++)
    {
        termos = (termos * razao);
        printf("\n%.1f",termos);
    }
    
}