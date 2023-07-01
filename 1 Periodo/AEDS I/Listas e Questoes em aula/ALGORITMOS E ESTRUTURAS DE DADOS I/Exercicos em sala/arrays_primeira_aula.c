#include <stdio.h>
#include <stdlib.h>
int escreveArranjo(int x[],int tamanho);
void leiaArranjo(int x[],int tamanho);
int main()
{
    int tamanho = 0,i;
    printf("\nDigite o tamanho: ");
    scanf("%i",&tamanho);
    int a [ tamanho];
    leiaArranjo(a,tamanho);
    escreveArranjo(a,tamanho);
 
}
void leiaArranjo(int x[],int tamanho){
    int i = 0;
    for(i = 0; i<tamanho;i++){
        printf("Digite um inteiro:");
        scanf("%i",&x[i]);
    }
}
int escreveArranjo(int x[],int tamanho){
    int i = 0;
    for(i = 0; i < tamanho;i++){
        printf("\nA[%i] = %i",i , x[i]);
    }
 
}
