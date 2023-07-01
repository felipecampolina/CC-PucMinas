#include <stdio.h>
#include <stdbool.h>
#include <string.h>

FILE* abreArquivo(char *nome){
    return fopen(nome,"r");
}
int leQuantidade(FILE* arq){
    int x =0;
    fscanf(arq,"%i",&x);
    return x;


}

void leEscreveArquivo(int x,FILE* arq){
    float arquivo[x] ;
    int i = 0;
   while(!feof(arq)){
    fscanf(arq,"%f",&arquivo[i]);
    i++;
   }
   for(int i = 79 ; i>=0;i--){
    printf("%.3f\n",arquivo[i]);
   }
   fclose(arq);

}


int main(){
    FILE * arq = abreArquivo("pub.in");
    leEscreveArquivo(leQuantidade(arq),arq);
}