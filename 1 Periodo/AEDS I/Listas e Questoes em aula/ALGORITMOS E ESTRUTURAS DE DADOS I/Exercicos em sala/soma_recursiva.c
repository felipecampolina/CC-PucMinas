#include <stdio.h>
#include <stdbool.h>
int escreveAte5(x);
int main()
{
    int n;
    printf("A soma e: %i",escreveAte5(leiaReal(n)));
}
int escreveAte5 (int x)
{

    int soma = 0;//valor chutado para economizar no IF/ELSE
    if(x > 0)
    {
        soma = x + escreveAte5(x-1);      
   }
   return soma;// 1 retorno e no final
}
int leiaReal()
{
    int n;
    printf("\nEscreva um numero: ");
    scanf("%i",&n);
    return n;
}