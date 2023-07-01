#include <stdio.h>
#include<stdbool.h>


int main()
{
int n;
bool ERRO;
float H;
float termos;
int count;
float x = 100;

printf("\nExercicio 1 - Valor de H\n");

do{
printf("\nNumero de termos: ");
scanf("\n%i",&n);
ERRO = n <=0;
if(ERRO)
{
    printf("\nOpcao Invalida");
}
}while(ERRO);

for(count= 0 ; count != n ; count++)
{
termos = 1 /x;
printf("\ntermo 1/%.0f: %f ",x ,termos);
x = x - 3;
H = H +termos;
}

printf("\nH = %f ", H);




}




