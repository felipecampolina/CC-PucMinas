#include <stdio.h>
#include <stdlib.h>
 
int main(void)
{
	// Declaração das variáveis
	float raio, pi=3.14, area;
	
	// Entrada de dados
	printf("RAIO = "); 
	scanf("%f", &raio);
 
	// Cálculo da área da circunferência
	area = pi * raio * raio;
 
	// Apresentação do resultado
	printf("AREA = %.2f m2 \n", area);
 
	system("pause");
	return 0;
}