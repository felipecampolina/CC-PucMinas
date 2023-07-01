#include <stdio.h>
#include <stdbool.h>
void leiaArranjo(float x[],int y);
void escrevaArranjo(float x[],int y);
int contaNegativos(float x[],int y);
bool valorIgualaZero(float x [],int y);
void copiaArranjo(float x[],float y [], int z);
int main()
{
    int tamanho;
    bool ERRO;
    do{
        printf("\n Digite o tamanho do Array: ");
        scanf("%i",&tamanho);
        ERRO = tamanho < 0;
        if(ERRO)printf("\nOpcao Invalida");
    }while(ERRO);

    float Array[tamanho];
    float ArrayCopiado[tamanho];
    leiaArranjo(Array,tamanho);
    //printf("A quantidade de numeros negativos: %i", contaNegativos(Array,tamanho));
    //if(valorIgualaZero(Array,tamanho))printf("\nExiste 0 neste arranjo");
    //else printf("\nNao existe 0 no arranjo");
    copiaArranjo(Array,ArrayCopiado,tamanho);
    escrevaArranjo(ArrayCopiado,tamanho);
}
void leiaArranjo(float x[],int y){
    int i = 0;
    for(i = 0 ; i < y; i++ ){
        printf("\nDigite um valor: ");
        scanf("%f", &x[i]);
    }
}
void escrevaArranjo(float x[],int y){
    int i = 0;
    for(i = 0; i < y ; i++){
        printf("\na[%i] = %.2f",i , x[i] );
    }
}
int contaNegativos(float x[],int y){
    int i = 0, numeroNegativo = 0;
    for(i = 0 ; i < y ;i++){
        if(x[i] < 0)numeroNegativo++;
    }
    return numeroNegativo;
}
bool valorIgualaZero(float x [],int y){
    int i = 0; 
    bool valido = false;
    while((i < y)&&(!valido)){
        if(x[i] == 0)
        { 
            valido = true;
        }
        i++;
    }
    return valido;
}
void copiaArranjo(float x[],float y [], int z){
    int i = 0;
    for(i = 0 ; i < z ; i++){
        y[i] = x[i]; 
    }
}

