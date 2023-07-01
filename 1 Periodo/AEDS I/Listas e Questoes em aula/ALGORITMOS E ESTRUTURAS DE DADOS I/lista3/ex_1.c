#include <stdio.h>
#include <stdlib.h>

int main()
{
    float lado1,lado2,lado3;
    printf("Digite lados de uma triangulo.");
    printf("\nLado 1 : ");
    scanf("%f",&lado1);
    printf("\nLado 2 : ");
    scanf("%f",&lado2);
    printf("\nLado 3 : ");
    scanf("%f",&lado3);

    if( (lado1==lado2) || (lado1 == lado3) || ( lado3==lado2 ))
    {
        if((lado1==lado2) && (lado2 == lado3))
        {
            printf("O trinagulo e equilatero");
        }
        else
        {
           printf("O trinagulo e isoceles"); 
        }
    }
    else
    {
        printf("O triangulo e escaleno");
    
    }   
}