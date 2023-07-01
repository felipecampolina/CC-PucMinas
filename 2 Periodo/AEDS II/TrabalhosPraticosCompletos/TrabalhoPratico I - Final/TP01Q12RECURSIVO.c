#include <stdio.h>
#include <string.h>
#include <stdbool.h>

bool eFim(char x []){//Início eFim
    bool resp = false;
    if((x[0]=='F') && (x[1] == 'I') && (x[2] =='M')) resp = true; // Confere se palavra == FIM
    return resp;
}//Fim eFim

void lePalavra(char palavra []){//Início lePalavra
    fgets(palavra,1000,stdin);// Comando para ler a palavra
}//Fim lePalavra

void ePalindromoRecursivo(char palavra[],int tam,int i , int j){//Início ePalindromo
    bool valido = true;
    if(i<strlen(palavra)){
        if(palavra[i]!=palavra[j])valido = false;
        ePalindromoRecursivo(palavra,tam,++i,--j);
    }
    if(i==strlen(palavra)-1){
        if(valido)printf("SIM\n");
        else printf("NAO\n");
}
}//Fim ePalindromo
int main(){//Início main
    char palavra[1000];
    lePalavra(palavra);
    int tam = strlen(palavra)-1; // Define tamanho

    while(!eFim(palavra)){
        int i = 0;
        int j = tam -1;
        
        ePalindromoRecursivo(palavra,tam,i,j);
        lePalavra(palavra);
        tam = strlen(palavra)-1;
    }//Repetição até achar FIM
}//Fim main