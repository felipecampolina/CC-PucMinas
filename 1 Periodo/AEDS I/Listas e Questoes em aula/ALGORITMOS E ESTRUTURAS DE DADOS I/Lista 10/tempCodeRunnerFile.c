#include <stdio.h>
#include <stdlib.h>

void numerosNegativos(int a[], int tam, int* g) {
    if(a[tam] < 0) (*g)++;
    if(tam > 0) numerosNegativos(a, --tam, g);
}

int main () {
    int M1[] = {-10,20,-30,-50,20};
    int negativos = 0;
    int tamanho = sizeof(M1);
    numerosNegativos(M1,tamanho,&negativos);
    printf("%i",negativos);


    
}