#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

int sequenciaFibonacci(k);
int leiaInteiro();
float leiaFloat();
bool primoEntreSi(numero1,numero2);
bool ehPrimo(numero);
float novoSalario(float salario,float aumento);
bool aprovacaoAluno(float nota);

int main()

{
    float k,aumento,salario;
    //printf("O enesimo termo da sequencia de fibonacci: %i  ",sequenciaFibonacci(9));
    //maiorDivisor();
    //if(primoEntreSi(leiaInteiro(),leiaInteiro())) printf("True"); else printf("False");
    //if(ehPrimo(leiaInteiro())) printf("True"); else printf("False");
    //salario = leiaFloat();
    //aumento = leiaFloat();
    //printf( "\nO antigo salario era %f  , com um aumento de %f   vira %f",salario , aumento , novoSalario(salario,aumento));
    //if(aprovacaoAluno(leiaFloat())) printf("True"); else printf("False");
}

int sequenciaFibonacci(k)
{
    int a, b, auxiliar, i, n;

    a = 0;
    b = 1;
    for(i = 0; i < k-1; i++) {

        auxiliar = a + b;
        a = b;
        b = auxiliar;
    }
    return auxiliar;
}
int maiorDivisor()
{
    int numero,maior_divisor,count;
    numero = leiaInteiro();
    for(count = 1 ; count < numero; count++)
    {
        if(numero % count == 0)
        {
            if (numero >= maior_divisor)
            {
                maior_divisor = count;
            }
        }
    }
    return maior_divisor;
}
int leiaInteiro()
{
    int x ;
    printf("\nDigite um numero: ");
    scanf("%i",&x);
    return x;
}
bool primoEntreSi(numero1,numero2)
{
    int maior_numero,count,mdc = 1;

    maior_numero = numero1;
    if(numero2 > numero1) maior_numero = numero2;


    for(count = 1;count < maior_numero ; count++ )
    {
        if((numero1 % count == 0) && (numero2 % count == 0))
        {
            mdc = count;
        }
    }
    
    if(mdc == 1)
    {
        return true;
    }
    else return false;
}
bool ehPrimo(numero)
{
    int count,divisor = 0;

    for(count = 1; count < numero+1 ; count++)
    {
        if(numero % count == 0)
        {
        divisor++;
        }
    }
    
    if(divisor == 2) return true;
    else return false;

}
float novoSalario(float salario,float aumento)
{
    printf("\nSalario: %f",salario);
    printf("\nAumento: %f",aumento);
    aumento = salario * (aumento * 0.01);
    return salario + aumento;
    
}
float leiaFloat()
{
    float x ;
    printf("\nDigite um numero: ");
    scanf("%f",&x);
    return x;
}
bool aprovacaoAluno(float nota){
    bool aprovacao = false;
    if(nota >= 60) aprovacao = true;
    return aprovacao;
}