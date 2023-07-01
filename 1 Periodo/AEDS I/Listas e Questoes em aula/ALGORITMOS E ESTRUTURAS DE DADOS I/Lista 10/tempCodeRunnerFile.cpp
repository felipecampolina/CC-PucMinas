#include <stdio.h>
#include <stdlib.h>
void contaNegativos(int M1[4],int tam, int* i){
    if(M1[tam] < 0) (*i)++;
    if(M1[tam]> 0) contaNegativos(M1,--tam,i);
  
}
int main () {
    int M1[4] = {1,-2,-3,-4};
    int negativos = 0;
    int tamanho = 4;
    contaNegativos(M1,tamanho,&negativos);
    printf("%i",negativos);


    
}