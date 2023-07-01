#include<stdio.h>
#include<stdlib.h>

int main()
{
    float altura;
    printf("Digite sua altura em metros: ");
    scanf("%f",&altura);
    float peso = (62.1 *altura) - 44.7;
    printf("Peso ideal:%f",peso);
}
