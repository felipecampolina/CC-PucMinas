#include <stdio.h>
#include <stdbool.h>


int main()
{
int a = 1;
int b = 100;
int numero_termos;
bool ERRO;
int count;
bool impar;

printf("\nExercicio 2 - Sequencia");
do{
printf("\nDigite o numero de termos da sequencia: ");
scanf("\n%i",&numero_termos);
ERRO = numero_termos <= 0;
if (ERRO)
{
    printf("\nOpcao invalida");
}

}while(ERRO);

for(count = 0; count != numero_termos ; count++)
{
impar = a % 2 == 1;
if(impar)
{
    printf("\n%i / %i",a , b);
}
else
{
    printf("\n%i / %i",b , a);
}
a = a + 1;
b = b - 3;

}
return 0;
}