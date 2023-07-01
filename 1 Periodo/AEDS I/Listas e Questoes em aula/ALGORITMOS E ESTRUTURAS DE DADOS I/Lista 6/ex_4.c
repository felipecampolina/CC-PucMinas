#include <stdio.h>
#include <stdlib.h>
#include<stdbool.h>

int main()
{
int a = 1;
int b = 1;
int n = 0;
int aux;
int count;
bool ERRO;
printf("\nSEQUENCIA FIBONACCI");
do{
printf("\nNumero de termo: ");
scanf("%i",&n);
ERRO = n <= 0;
if (ERRO)
{
    printf("Opcao Invalida");
}
}while(ERRO);

printf("\n%i",a);

for(count = 0; count < n-1; count ++)
{
printf("\n%i",b);
aux = b;
b = b+a;
a = aux;
}



}
