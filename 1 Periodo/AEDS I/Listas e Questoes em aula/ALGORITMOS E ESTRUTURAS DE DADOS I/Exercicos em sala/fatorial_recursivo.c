#include <stdio.h>
#include <stdbool.h>
int escreveAte5(x);
int main()
{
    int n;
    printf("O fatorial e: %i",fatorialDeN(leiaReal(n)));
}
int fatorialDeN (int x)
{

    int fatorial = 1;
    if(x > 0)
    {
        fatorial = x * fatorialDeN(x-1);
    }
    return fatorial;
}
int leiaReal()
{
    int n;
    printf("\nEscreva um numero: ");
    scanf("%i",&n);
    return n;
}