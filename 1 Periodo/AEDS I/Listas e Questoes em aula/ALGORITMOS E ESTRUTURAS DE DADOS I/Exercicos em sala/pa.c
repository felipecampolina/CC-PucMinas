#include<stdio.h>
#include<stdbool.h>

int main()
{
    float primeiro_termo;
    float razao;
    int numero_de_termo;
    bool ERRO;
    float termos;
    int soma = 0;
    int media;



    printf("\nBem-vindo a calculadora de Progressao Aritimetica");
    printf("\nDigite o primeiro termo:");
    scanf("%f",&primeiro_termo);
    printf("\nDigite a razao");
    scanf("%f",&razao);
    
    do{
    printf("\nQuantos numeros voce quer presente em sua PA: [Sem contar com o 1]");
    scanf("%i",&numero_de_termo);
    ERRO = numero_de_termo <= 0;
    if (ERRO){
        printf("\nOpcao Invalida");
    }
    }while(ERRO);

    printf("%f",primeiro_termo);
    termos = primeiro_termo + razao;
    soma = primeiro_termo + termos;

    while(numero_de_termo > 0)
    {
    printf("\n%2.f",termos);
    termos = termos + razao;
    numero_de_termo = numero_de_termo - 1;
    soma = soma + termos;
    }
    media = soma / numero_de_termo;
    printf("\nA media dessa PA e:%i",media);
}