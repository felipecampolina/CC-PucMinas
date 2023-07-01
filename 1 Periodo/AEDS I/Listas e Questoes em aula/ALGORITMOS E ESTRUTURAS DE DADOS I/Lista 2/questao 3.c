#include<stdio.h>
#include<stdlib.h>

int main()
{
    float altura;
    printf("Digite sua altura em metros: ");
    scanf("%f",&altura);
    float peso = (72.7 *altura) - 58;
    printf("Peso ideal:%f",peso);
}
