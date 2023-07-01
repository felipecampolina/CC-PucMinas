#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
float somaReais();
float perimetroQuadrado();
float areaCirculo();
float maiorValor();
float parImpar();
int anoBissexto();
float pesoIdeal();
int numeroFatorial();
float numeroPotencia();
float maiorValorConjunto();

int main()
{
    int x = 0;

    printf("\nPressione 1 para somar dois numeros.");
    printf("\nPressione 2 para calcular o perimetro de um quadrado.");
    printf("\nPressione 3 para calcular a area de um circulo");
    printf("\nPressione 4 para identificar o maior valor entre dois numeros");
    printf("\nPressione 5 para identificar se um numero inteiro e par ou impar");
    printf("\nPressione 6 para saber se um ano x e Bissexto");
    printf("\nPressione 7 para calcular peso ideal");
    printf("\nPressione 8 para calcular o fatorial de um numero natural");
    printf("\nPressione 9 para calcular uma potencia");
    printf("\nPressione 10 para calcular o maior numero de um conjunto real");

    scanf("\n%i",&x);
    
    switch(x)
    {
        case 1:
            somaReais();
            break;
        case 2:
            perimetroQuadrado();
            break;
        case 3:
            areaCirculo();
            break;
        case 4:
            maiorValor();
            break;
        case 5:
            parImpar();
            break;
        case 6:
            anoBissexto();
            break;
        case 7:
            pesoIdeal();
            break;
        case 8:
            numeroFatorial();
            break;
        case 9:
            numeroPotencia();
            break;
        case 10:
            maiorValorConjunto();
            break;

        default:
            printf("Opcao Invalida");
            break;
    }
}

float somaReais()
{
    float numero1,numero2,soma;
    printf("\nSoma de dois numeros reais");
    printf("\nDigite um numero:" );
    scanf("%f",&numero1);
    printf("\nDigite um numero:" );
    scanf("%f",&numero2);
    soma = numero1 + numero2;
    printf("A soma de %.2f e %.2f = %.2f",numero1,numero2,soma);
    return soma;
    
}
float perimetroQuadrado()
{
    float lado,perimetro;
    bool ERRO;
    do{
    printf("\nLado: ");
    scanf("%f",&lado);
    ERRO = lado <= 0;
    if(ERRO)
    {
        printf("\n\aOpcao Invalida [lado > 0] ");
    }
    }while(ERRO);
    perimetro = lado * 4;
    printf("O perimetro do quadrado = %.2f",perimetro);
    return perimetro;
}
float areaCirculo()
{
    float raio,area;
    bool ERRO;
    do{
        printf("Raio: ");
        scanf("%f",&raio);
        ERRO = raio <= 0;
        if(ERRO)
        {
        printf("\n\aOpcao Invalida [raio > 0] ");
        }
    }while(ERRO);
    area = (3.14 * (raio * raio));
    printf("A area do circulo e %.2f",area);
    return area;
}
float maiorValor()
{
    float numero1,numero2,maiorNumero;
    printf("\nDigite um numero:" );
    scanf("%f",&numero1);
    printf("\nDigite um numero:" );
    scanf("%f",&numero2);
    if(numero1 > numero2)
    {
        maiorNumero = numero1;
        printf("\nO maior numero entre %f e %f = %f",numero1,numero2,maiorNumero);
    }
    else if(numero1 == numero2)
    {
        printf("\nOs numeros sao iguais");
    }
    else
    {
        maiorNumero = numero2;
        printf("\nO maior numero entre %f e %f = %f",numero1,numero2,maiorNumero);
    }
    return maiorNumero;
    
}
float parImpar()
{
    int numero1;
    printf("\nDigite um numero inteiro:" );
    scanf("%i",&numero1);
    if(numero1 % 2 == 0)
    {
        printf("O numero %i e par",numero1);
    }
    else
    {
        printf("O numero %i e impar",numero1);
    }
}
int anoBissexto()
{
    int ano;
    bool ERRO;
    do{
    printf("\nDigite um ano: ");
    scanf("%i",&ano);
    ERRO = ano <= 0;
    if(ERRO)
        {
        printf("\n\aOpcao Invalida [ano > 0] ");
        }
    }while(ERRO);
    
    if (ano % 4 == 0)
    {
        if(ano % 100 == 0)
        {
            if(ano % 400 == 0)
            {
                printf("\n%i e Bissexto",ano);
            }
            else
            {
                printf("\n%i Nao e Bissexto",ano);  
            }
        }
        else
        {
        printf("\n%i e Bissexto",ano);
        }


    }
    else
    {
        printf("\n%i Nao e Bissexto",ano);
    }


}
float pesoIdeal()
{
    bool ERRO;
    float h;

    do{
        printf("\nAltura: ");
        scanf("%f", &h);
        ERRO = h<0;
    }while(ERRO);

    char genero;
    do{
        printf("\nGenero [M/F]: ");
        scanf(" %c", &genero);
        genero = toupper(genero);
        ERRO = (genero != 'M') && (genero != 'F');
    }while(ERRO);

    float pesoIdeal;
    if(genero == 'M') pesoIdeal = 72.7 * h - 58;
    else pesoIdeal = 62.1 * h - 44.7;

    printf("\nPeso Ideal = %f", pesoIdeal);
    return pesoIdeal;
}
int numeroFatorial()
{
    int numero1 = 1,count,fatorial=1;
    bool ERRO;
    do{
    printf("\nNumero: ");
    scanf("%i",&numero1);
    ERRO = numero1 < 0;
    if (ERRO)
    {
        printf("\nOpcao Invalida");
    }
    }while(ERRO);
    for(count = numero1; count > 0; count-- )
    {
        fatorial *= count;
    }
    printf("%i! = %i",numero1,fatorial);
    return fatorial;
}
float numeroPotencia()
{
    float numero1=1,resultado=1;
    int expoente,count;
    
    printf("Digite um numero: ");
    scanf("%f",&numero1);
    printf("Digite o expoente: ");
    scanf("%i",&expoente);
    if(expoente > 0)
    {
        for(count = expoente; count >= 0; count--)
        {
            resultado*=numero1;
        }
    }
    else if (expoente == 0)
    {
        resultado == 1;
    }
    else
    {
        for(count = expoente; count <= 0; count++)
        {
            resultado*=numero1;
        }
        resultado = (1/resultado);
    }
    printf("O resultado = %f",resultado);
    return resultado;

}
float  maiorValorConjunto()
{
    float numero1 = 1,maiorNumero ;
    const int FLAG = 0;
    printf("Digite um numero: [FLAG = 0]");
    scanf("%f",&numero1);
    while(numero1 != FLAG)
    {
        printf("Digite um numero: [FLAG = 0]");
        if ((numero1 >= maiorNumero) && (numero1 != FLAG))
        {
            maiorNumero = numero1;
        }
    scanf("%f",&numero1);
    }
    printf("O maior numero desse conjunto e %f",maiorNumero);

}

