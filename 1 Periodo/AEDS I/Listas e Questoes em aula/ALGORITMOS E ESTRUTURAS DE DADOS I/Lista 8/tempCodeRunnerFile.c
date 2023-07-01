#include <stdio.h>
#include <stdbool.h>
float maiorValor(float a , float b);
float interacaoUsuario(float x);
int main()
{
    float a,b,x;
    a= interacaoUsuario(x);
    b= interacaoUsuario(x);
    printf("O maior valor e %f",maiorValor(a,b));
}

float maiorValor(float a , float b)
{
    if (a > b)
    {
        return a;
    }
    else
    {
        return b;
    }
}
float interacaoUsuario(float x)
{
    printf("Digite um numero: ");
    scanf("%f",&x);
    return x;
}