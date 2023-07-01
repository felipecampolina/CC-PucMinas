#include <stdio.h>
#include <stdbool.h>
int fatorialNumeroInteiro(numero);
int exponenciacao(float base, int expoente);
int leiaNatural();
float leiaReal();
int sequenciaFibonacci(k);
void troca(float *x , float *y);
void zerar(float *x, float *y);
void leiaNaturalPonteiro(int *numeroPonteiro);
float soma(int x,float y);
void dobrar(float *z);
int main ()

{
    int fibonacci, numeroPonteiro = 0;
    float x = 10;
    float y = 20;
    float z = leiaNatural();
    
    //float numero1 = leiaNatural();
    //printf("\nO fatorial e: %i ",fatorialNumeroInteiro(leiaNatural()));
    //printf("\nO Resultado e: %i ",exponenciacao(leiaReal(), leiaNatural()));
    //fibonacci = leiaNatural();
    //printf("O %i termo da sequencia de Fibonacci e : %i",fibonacci,sequenciaFibonacci(fibonacci));
    //printf("\n Antes da funcao -- x = %f e y = %f -- lugar memoria x =%p e y = %p",x,y,&x,&y);
    //troca(&x,&y);
    //zerar(&x,&y);
    //printf("\n Depois da funcao -- x = %f e y = %f -- lugar memoria x =%p e y = %p",x,y,&x,&y);
    //leiaNaturalPonteiro(&numeroPonteiro);
    //printf("\nA soma de %f e %i = %f",numero1,numeroPonteiro,soma(numeroPonteiro,numero1));
    //dobrar(&z);
    //printf("\nO numero dobrado %f",z);

}

int leiaNatural()
{
    int numeroInteiroPositivo;
    bool ERRO;
    do{
    printf("\nDigite um numero inteiro: ");
    scanf("%i",&numeroInteiroPositivo);
    ERRO = numeroInteiroPositivo < 0;
    if (ERRO) printf("\nNumero Invalido");
    }while(ERRO);
    return numeroInteiroPositivo;
}
int fatorialNumeroInteiro(numero)
{
    int fatorial = 1;
    if (numero > 0) fatorial = numero * fatorialNumeroInteiro(numero-1);
    return fatorial;
}

float leiaReal()
{
    float numeroReal;
    printf("\nDigite um numero real: ");
    scanf("%f",&numeroReal);

    return numeroReal;
    
}
exponenciacao(float base, int expoente){
    int resultado = 1;
    if(expoente > 0)resultado = base * exponenciacao(base,expoente-1);

    return resultado;
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

void troca(float *x,float *y)
{
    float aux;
    aux = *x;
    *x = *y;
    *y = aux;

}
void zerar(float *x,float *y)
{
    *x = 0;
    *y = 0;
}
void leiaNaturalPonteiro(int *numeroPonteiro)
{
    int numeroInteiroPositivoPonteiro;
    bool ERRO;
    do{
    printf("\nDigite um numero inteiro: ");
    scanf("%i",&numeroInteiroPositivoPonteiro);
    ERRO = numeroInteiroPositivoPonteiro < 0;
    if (ERRO) printf("\nNumero Invalido");
    }while(ERRO);
    *numeroPonteiro = numeroInteiroPositivoPonteiro;
}
float soma(int x,float y)
{
    float soma;
    soma = x + y;
    return soma;
}
void dobrar(float *z)
{
    *z =*z * 2;
}

