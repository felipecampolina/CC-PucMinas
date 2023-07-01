#include<stdio.h>

int main()
{
    printf("Desafio para aula do dia 18/03");
    char estado_civil;
do
{
        
        printf("\nDigite seu estado civil.");
        printf("\nDigite c se for casado.");
        printf("\nDigite s se for solteiro.");
        printf("\nDigite d se for divorciado.");
        printf("\nDigite v se fot viuvo.");


        scanf(" %c",&estado_civil);
        
        if ((estado_civil != 's') && (estado_civil != 'c') && (estado_civil != 'd') && (estado_civil != 'v'))
        {
            printf("\nOpcao invalida");
        }
}
while((estado_civil != 's') && (estado_civil != 'c') && (estado_civil != 'd') && (estado_civil != 'v'));
    printf("Ok! Entendido.");
    return 0;
    
}