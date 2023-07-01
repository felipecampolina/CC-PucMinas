#include <stdio.h>

int main()
{
    int x = 0;
    int count = 0;

    for (x = 1 ; count < 10 ;)
    {
        printf("\n1/%i",x);
        count ++;
        x = x * 10;
    }
}