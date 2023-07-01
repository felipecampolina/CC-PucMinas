#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <stddef.h>
void reinicializaAmbiente(FILE* arq);
int leiaValores(FILE* arq);
void escrevaValores(FILE* arq);
void ordemCrescente(int a[],int y);
void criaArray(FILE * arq,int y,int a[]);
int menuOpcoes();
int main()
{
    FILE* arq;
    char* nome;
    int op,tamanho = 0;
    int array[tamanho];
    do{
    op = menuOpcoes();
    switch(op)
    {
            case 0:
            printf("\nObrigado!");
            break;
            case 1:
            reinicializaAmbiente(arq);
            break;
            case 2:
            tamanho = leiaValores(arq);
            criaArray(arq,tamanho,array);
            break;
            case 3:
            escrevaValores(arq);
            break;
            case 4:
            ordemCrescente(array,tamanho);
            break;
            }
    }while(op != 0);
    

}
int menuOpcoes(){
    int x;
    bool ERRO;
    printf("\n\t--MENU--\n0- Sair  do programa\n1- Reiniciar o arquivo\n2- Ler valores\n3- Escrever valores\n4- Escrever valores em ordem crescente");
    do{
        printf("\n\nDigite uma opcao: ");
        scanf("%i",&x);
        ERRO = ((x > 4) || (x < 0));
        if(ERRO)printf("\n\aOpcao Invalida");
    }while(ERRO);
    return x;
}
void reinicializaAmbiente(FILE* arq){
    arq = fopen("valores.dat","w");
    fclose(arq);
}
int leiaValores(FILE* arq)
{
    arq = fopen("valores.dat","a");
    const FLAG = 0;
    int x, tamanho = 0;
    printf("\nDigite valor: ");
    scanf("%i",&x);
    while(x != FLAG){
        tamanho++;
        fprintf(arq,"%i\n",x);
        printf("\nDigite valor: ");
        scanf("%i",&x);
    }
fclose(arq);
return tamanho;
}
void escrevaValores(FILE* arq)
{
    
    if(arq = fopen("valores.dat","a")!= NULL);
    {
    arq = fopen("valores.dat","r");
    rewind(arq);
    int x ;
    fscanf(arq,"%i",&x);
    while(!feof(arq)){
        printf("\n%i",x);
        fscanf(arq,"%i",&x);
        }
    }
    fclose(arq);

}
void ordemCrescente(int a[],int y){
    float aux;
    int i, j ,k,x;
    for(j=0; j < y-1-j; j++){
        for(i = 0 ; i < y-1; i++){
            if(a[i] > a[i+1]){
                aux = a[i];
                a[i] = a[i+1];
                a[i+1] = aux;
            }
        }
    }
    for(x = 0;  x < y ; x++){
    printf("\na[%i] = %.2i",x , a[x]);
    }
}
void criaArray(FILE *arq,int y,int a[]){
    arq = fopen("valores.dat","r");
    rewind(arq);
    int x;
    for(x = 0; x < y ; x++){
    fscanf(arq,"%i",&a[x]);
    printf("\na[%i] = %.2i",x , a[x]);
    }
    fclose(arq);
}
