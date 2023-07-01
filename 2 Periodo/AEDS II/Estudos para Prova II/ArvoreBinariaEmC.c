#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

typedef struct No{
    int elemento;
    struct No *esq , *dir;

}No;

No* novoNo(int elemento){
    No* novo = (No*)malloc(sizeof(No));
    novo->elemento = elemento;
    novo->dir = NULL;
    novo->esq = NULL;
    return novo;
}

No* raiz;

void start(){
    raiz = NULL;
}

void inserir(int elemento ){
    inserirRec(elemento,&raiz);
}
void inserirRec(int x , No** i){
    if(*i == NULL) *i = novoNo(x);
    else if(x > (*i)->elemento)inserirRec(x, &(*i)->dir);
    else if (x < (*i)->elemento)inserirRec(x, &(*i)->esq);
    else printf("ERRO ELEMENTO REPETIDO");
}


void caminhamentoCentral(){
    printf("[");
    caminhamentoCentralRec(raiz);
    printf("]");
}
 void caminhamentoCentralRec(No *i){
    if(i!=NULL){
        caminhamentoCentralRec(i->esq);
        printf("%i ",i->elemento);
        caminhamentoCentralRec(i->dir);
    }
}
int getAlturaRec(No ** i, int altura){
    if(*i==NULL)altura--;
    else{
        int alturaEsq = getAlturaRec(&(*i)->esq,altura+1);
        int alturaDir = getAlturaRec(&(*i)->dir,altura+1);
        if(alturaDir>=alturaEsq) altura = alturaDir;
        else altura = alturaEsq;
    }
    return altura;
}

int getAltura(){
    int x = getAlturaRec(&raiz,0);
    return x;
}

int getSomaRec(No*i,int soma){
    if(i!=NULL){
        soma += i->elemento + getSomaRec(i->esq,soma) + getSomaRec(i->dir,soma);
    }
    return soma;
}

int getSoma(){
    int soma = getSomaRec(raiz,0);
    return soma;
}

bool pesquisarRec(int x , No* i){
    bool resp = false;
    if(i ==NULL) resp = false;
    else if(x>i->elemento)resp = pesquisarRec(x,i->dir);
    else if(x<i->elemento)resp = pesquisarRec(x,i->esq);
    else{
        resp = true;
     }
    return resp;
}

bool pesquisar(int x ){
    return pesquisarRec(x,raiz);

}




int main(){
    start();
    inserir(10);
    inserir(45);
    inserir(21);
    inserir(3);

    caminhamentoCentral();
    printf("\n%i",getAltura());
    printf("\n%i",getSoma());
    if(pesquisar(10))printf("ACHEI");
    else printf("NAO ACHEI");
    if(pesquisar(-5))printf("ACHEI");
    else printf("NAO ACHEI");
}



