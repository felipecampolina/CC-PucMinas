#include <stdio.h>
#include <stdbool.h>
float leiaBase();
int leiaExpoente();
float exponenciacao(float base , int expoente);
int main()
{
    printf("Resultado =%f ",exponenciacao(leiaBase(),leiaExpoente()));

}
float leiaBase()
{
    float base;
    printf("Base: ");
    scanf("%f",&base);
    return base;
}
int leiaExpoente()
{
    int expoente = 0;
    printf("Expoente: ");
    scanf("%i",&expoente);
    return expoente;
}

float exponenciacao(float base, int expoente)
{
    
    float resultado = 0; // Quando usar a recursividade é importante de lembrar do TERMINO se não vira uma funcao sem fim.
    if (expoente == 0) resultado = 1;
    else if(expoente > 0) resultado = base * exponenciacao(base,expoente-1);
    
    return resultado;
}