#include <stdio.h>

int main()
{
    float x; // Se x for importante para o codigo é necessário declarar ele antes de for.
    // Caso não declarado antes, ele irá existir só durante o for.

    for(x = 1;x > 0.00000000000000000000000000000000000000000000000000000000000000000000000000000000001; x = x/10 )
    {
        printf("\n%.1000f",x);
    }

}