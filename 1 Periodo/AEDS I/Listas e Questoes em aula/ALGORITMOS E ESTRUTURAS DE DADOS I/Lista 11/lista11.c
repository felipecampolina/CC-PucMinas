#include <stdio.h>
#include <stdbool.h>
float pedeArray(float x[],int y);
void escreveArray(float x[],int y);
void trocaPrimeiroUltimo(float x[],int y);
void trocaDuasposicoes(float x[],int y , int z , int w);
float somaArray(float x[],int y);
float mediaArray(float x , int y);
bool notasValidas(float x[],int y);
int descobreAlunosAprovados(float x[],int y);
int arrayFibonaccci(int x[],int y);
void escreveArrayFibonacci(int x[],int y);
int contaElementosNegativos(int x[],int y);
void leiaNotas(int x [],int y,int z);
void contaElementosNegativosRecursivo(float x[],int y, int * z);
int main()
{
    bool ERRO;
    int tamanho = 0;
    do{
    printf("\nDigite o tamanho do Array: ");
    scanf("%i",&tamanho);
    ERRO = tamanho < 0;
    if(ERRO)printf("\nTamanho Invalido");
    }while(ERRO);

    float array[tamanho];
    //int arrayFibonacci[tamanho];
    pedeArray(array,tamanho);
    //trocaPrimeiroUltimo(array,tamanho);
    //trocaDuasposicoes(array,tamanho,pedePosicoes(),pedePosicoes());
    //somaArray(array,tamanho);
    //printf("A media e %f\n", mediaArray(somaArray(array,tamanho),tamanho));
    //if(notasValidas(array,tamanho))printf("\nTodas as notas sao validas\n");
    //else printf("\nExite uma nota Invalida\n");
    //if(notasValidas(array,tamanho))printf("\nNumero de alunos aprovados : %i\n",descobreAlunosAprovados(array,tamanho));
    //printf("\nQuantidade de numeros negativos: %i \n",contaElementosNegativos(array,tamanho));
    //escreveArray(array,tamanho);
   //arrayFibonaccci(arrayFibonacci,tamanho);
   //escreveArrayFibonacci(arrayFibonacci,tamanho);
   
   /*int idade, count = 0;
   const FLAG = 0;
   printf("\nDigite uma idade: \n");
   scanf("%i",&idade);
   count ++;
   int arrayNotas[count];
   leiaNotas(arrayNotas,count,idade);
   while (idade != FLAG){
    printf("\nDigite uma idade: \n");
    scanf("%i",&idade);
    count++;
    arrayNotas[count];
    leiaNotas(arrayNotas,count,idade);
    mediaNotas(arrayNotas,count,idade);*/
    int a = 0;
    contaElementosNegativosRecursivo(array,tamanho,&a);
    printf("\nQuantidade de numeros negativos: %i\n",a);
    
}
float pedeArray(float x[],int y)
{
    int i = 0;
    for(i = 0; i < y; i++)
    {
        printf("\nDigite um numero real: ");
        scanf("%f",&x[i]);

    }
    return x[y];
}
void escreveArray(float x[],int y)
{
    int i = 0;
    for ( i = 0; i < y ; i++ ){
        printf("a[%i] = %.2f\n", i , x[i]);
    }
    
}
void trocaPrimeiroUltimo(float x[],int y)
{
    float aux;
    aux = x[0];
    x[0] = x[y-1];
    x[y-1] = aux;

}
int pedePosicoes()
{
    int posicao;
    bool ERRO;
    do{
    printf("\nDigite a posicao : ");
    scanf("%i",&posicao);
    ERRO = posicao < 0;
    if(ERRO)printf("\nTamanho Invalido");
    }while(ERRO);
    return posicao;
}
void trocaDuasposicoes(float x[],int y , int z , int w){
    float aux;
    aux = x[z];
    x[z] = x[w];
    x[w] = aux;
}
float somaArray(float x[],int y)
{
    float soma = 0;
    int i = 0;
    for(i = 0; i < y ; i++)
    {
        soma += x[i];
    }
    return soma;
}
float mediaArray(float x , int y)
{
    float media = 0;
    media = x / y;
    return media;
}
bool notasValidas(float x[],int y)
{
    bool validas = true;
    int i = 0;

    while((i < y)&&(validas == true)) {
    if((x[i] < 0) || (x[i] > 100) ){
        validas = false;
    }
    i++;
    }
    return validas;
}
int descobreAlunosAprovados(float x[],int y)
{
    int i,alunos_aprovados = 0;
    for(i = 0 ; i < y ; i++){
        if(x[i] >= 60){
            alunos_aprovados++;
    }
    }
    return alunos_aprovados;
}
int arrayFibonaccci(int x[],int y)
{
    int i = 0,aux,a=0,b=1;
    for(i = 1 ; i < y ; i++){
        aux = a + b;
        a = b;
        b = aux;
        x[0] = 1;
        x[i] = aux;
        x[i+1] = a;
        x[i+2] = b;
    }
}
void escreveArrayFibonacci(int x[],int y)
{
    int i = 0;
    for ( i = 0; i < y ; i++ ){
        printf("a[%i] = %i\n", i , x[i]);
    }
    
}
/*int contaElementosNegativos(int x[],int y)
{
    int i = 0,numeros_negativos = 0;;
    for(i = 0 ; i < y ; i++)
    {
        if(x[i] < 0)numeros_negativos++;

    }
   return numeros_negativos;
} */
void leiaNotas(int x [],int y,int z){
    int i = 0;
    for(i = 0 ; i < y ; i++){
        x[i] = z;
    }
}
void contaElementosNegativosRecursivo(float x[],int y, int * z){
    if(x[y] < 0) (*z)++;
    if(y > 0) contaElementosNegativosRecursivo(x,--y,z);
} 