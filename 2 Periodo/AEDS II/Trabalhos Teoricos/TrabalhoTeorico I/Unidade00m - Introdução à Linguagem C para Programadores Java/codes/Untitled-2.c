#include <stdio.h>
#include<stdlib.h>
FILE* criaArquivo(char* nome){
    FILE *arq;
    arq = fopen(nome,"w");
    return arq;
}
FILE * abreArquivo(char* nome)
{
    return fopen(nome,"r");
}
void leiaNumeros(FILE *arq,int flag)
{
    float numero;
    for(int i = 0 ; i<flag ; i++){
        scanf("%f",&numero);
        fprintf(arq,"%1.f\n",numero);
    }
    fclose(arq);

}
void mostraOrdemInversa(FILE* arq,int flag){
    float numero;
    fopen("teste.txt","r");
    fscanf(arq,"%f",&numero);
    printf("%f",feof(arq));
    while(!feof(arq))
    {
        printf("\n%f",numero);
        fscanf(arq,"%f",&numero);
    }
    

}


int main(){
    int numero;
    int flag = 0;

    printf("\nQuantos numero: ");
    scanf("%i",&flag);

    FILE* arq = criaArquivo("teste.txt");
    leiaNumeros(arq,flag);
    mostraOrdemInversa(arq,flag);
}