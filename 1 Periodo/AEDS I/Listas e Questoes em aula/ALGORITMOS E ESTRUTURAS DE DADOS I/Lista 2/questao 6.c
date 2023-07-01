#include<stdio.h>
#include<stdlib.h>

int main()
{
    float raio ;
    printf("Raio:");
    scanf("%f",&raio);
    float area = 3.141592653589793 * (raio * raio);
    printf("A area desse circulo = %f",area);
}
