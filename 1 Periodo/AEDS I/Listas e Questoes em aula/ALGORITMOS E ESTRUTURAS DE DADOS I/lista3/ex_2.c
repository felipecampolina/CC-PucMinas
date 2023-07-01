#include<stdio.h>
#include<stdlib.h>

int main()
{
    printf("\nPeso Ideal");
    printf("\nAltura: ");
    float h;
    scanf("%f",&h);
    printf("\nGenero: [M/F]");
    char genero;
    scanf (" %c",&genero);
    float peso_ideal;

    if((genero == 'M') || (genero == 'm'))
    {
        peso_ideal = (72.7 * h) - 58;
    }
    else
    {
        if((genero == 'F') || (genero == 'f'))
        {
            peso_ideal = (62.1 * h) - 44.7;
        }
        else
        {
            printf("Opcao invalida");
        }
    }
    
        printf("\n Peso ideal: %f",peso_ideal);

}

    
