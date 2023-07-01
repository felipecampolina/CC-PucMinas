#include <stdio.h>
#include <stdbool.h>
int const tamanho = 9;

bool pesquisaSequencial(int array[],int x){
    bool resp = false;
    for(int i = 0 ; i<tamanho;i++){
        if(array[i] == x)resp = true;
    }
    return resp;

}
bool pesquisaBinaria(int array[],int x){
    bool resp = false;
    int direita = tamanho-1,esquerda = 0,meio;
    while(direita>=esquerda){
        meio = (direita+esquerda)/2;
        if(x == array[meio]){
            resp = true;
            esquerda = tamanho;
        }if(x>array[meio]){
            esquerda = meio + 1;
        }else{
            direita = meio -1;
        }
    }
    return resp;

}
void maxEmin(int array[]){
    int max = 0, min;
    for(int i = 0; i<tamanho;i++){
        if(array[i]>max)max=array[i];
        if(array[i]<min)min=array[i];

    }
    printf("\nNumero maximo:  %i",max);
    printf("\nNumero minimo:  %i",min);
}


int main(){
    int numero_pesquisado = 15;
    
    int array[9] = {10,25,85,95,65,20,25,32,35};
    int arrayOrdenado[9] = {0,1,2,3,4,5,6,7,8};
    if(pesquisaSequencial(array,numero_pesquisado))printf("\nNumero achado!"); //ex1
    if(pesquisaBinaria(arrayOrdenado,numero_pesquisado))printf("\nNumero achado!");//ex2
    maxEmin(array);
    maxEmin(arrayOrdenado);


}