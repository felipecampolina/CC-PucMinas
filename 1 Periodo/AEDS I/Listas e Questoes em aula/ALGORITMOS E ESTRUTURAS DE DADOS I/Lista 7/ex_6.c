#include <stdio.h>

int main ()
{
    int count;
    float h , soma = 0 , a=11 , b=101;

    printf("\nValor de H - ex 6 da lista 7");
    for(count = 1; count < 11; count++)
    {
        if (count % 2 == 1)
        {
            h = a / b;
            printf("\n%i- h = %.0f / %.0f = %.3f",count,a,b,h);
        }
        else
        {
            h = b/a;
            printf("\n%i- h = %.0f / %.0f = %.3f",count,b,a,h);
        }
        soma += h;
        a++;
        b++;
    }
    printf("\n\nH = %.2f",soma);
    return 0;
}