#include<stdio.h>
#include<stdlib.h>

int main()
{
    float base;
    printf("Digite o lado do quadrado: ");
    scanf("%f",&base);
    float perimetro = base *4;
    printf("O perimetro de um quadrado de base %f",base);
    printf(" e igual a %f",perimetro);
}
