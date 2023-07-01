#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

float perimetroQuadrado(float lado); // Protótipo da função --> parecido com declaracão de variável.
float leiaLado();
void escrevaResultado(float lado);

int main() // Função principal --> main
{
    float lado;
    lado = leiaLado();
    escrevaResultado(lado);
    return 0;
}

float perimetroQuadrado(float lado)
{
    float perimetro;
    perimetro = lado * 4;
    return perimetro;
}

float leiaLado()
{
    float lado;
    bool ERRO;
    do{
    printf("Lado: ");
    scanf("%f",&lado);
    ERRO = lado <= 0;
    if(ERRO)
    {
        printf("Opcao Invalida");
    }
    }while(ERRO);
    return lado;
}
void escrevaResultado(float lado)
{
    printf("Perimetreo do quadrado de lado %f = %f",lado, perimetroQuadrado(lado));
}