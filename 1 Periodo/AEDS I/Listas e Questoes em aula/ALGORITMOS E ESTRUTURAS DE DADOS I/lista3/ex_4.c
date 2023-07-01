#include<stdio.h>
#include<stdlib.h>

int main()
{
    float num1,num2;
    int ope;
    printf("\nDigite um numero:");
    scanf("%f",&num1);
    printf("\nDigite um numero:");
    scanf("%f",&num2);
    printf("\nBem vindo a calculadora.");
    printf("\nDigite 1 para somar dois numeros.");
    printf("\nDigite 2 para subtrair dois numeros");
    printf("\nDigite 3 para multiplicar dois numeros.");
    printf("\nDigite 4 para dividir dois numeros.\n");
    scanf("%i",&ope);
    switch (ope)
    {
    case 1:
    printf("O resultado e %.2f",num1 + num2);
    break;
    case 2:
    printf("O resultado e %.2f",num1 - num2);
    break;
    case 3:
    printf("O resultado e %.2f",(num1 * num2));
    break;
    case 4:
    printf("O resultado e %.2f",num1 / num2);
    break;
    default:
    printf("Opcao Invalida");
    break;
    }
    return 0;
    

}
