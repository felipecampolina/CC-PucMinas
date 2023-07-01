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

void ePalindromo(char palavra[],int tam){//Início ePalindromo
    int j = tam-1;
    bool valido = true;
    for(int i = 0 ; i < tam-1 ; i++){
        if(palavra[i]!=palavra[j])valido = false; // Confere se palavra normal é igual a invertida
        j--;
    }
    if(valido)printf("SIM\n");
    else printf("NAO\n");
}//Fim ePalindromo
int main(){//Início main
    char palavra[1000];
    lePalavra(palavra);
    int tam = strlen(palavra)-1; // Define tamanho

    while(!eFim(palavra)){
        ePalindromo(palavra,tam);
        lePalavra(palavra);
        tam = strlen(palavra)-1;
    }//Repetição até achar FIM
}//Fim main