#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

typedef struct No2{
    char *elemento;
    struct No2 *dir , *esq;

}No2;

typedef struct No1{
    char elemento;
    struct No1 *dir , *esq;
    struct No2 *arvore;

}No1;

No1* novoNo1(char elemento){
    No1* novo = (No1*)malloc(sizeof(No1));
    novo->elemento = elemento;
    novo->dir = NULL;
    novo->esq = NULL;
    novo->arvore = NULL;

}
No2* novoNo2(char * elemento){
    No2* novo = (No2*)malloc(sizeof(No2));
    novo->elemento = elemento;
    novo->dir = NULL;
    novo->esq = NULL;

}


No1* raiz;


void start(){
    raiz = NULL;
}

void inserirNo2(char x){
    inserirRec(x,&raiz);
}
void inserirRec(char x , No1**i){
    if(i==NULL)inserirRecNo2(x,&(*i)->arvore);
    else if(x > (*i)->elemento)inserirRec(x, &(*i)->dir);
    else if (x < (*i)->elemento)inserirRec(x, &(*i)->esq);
    else{ 
        printf("ERRO ELEMENTO REPETIDO");}
}
void inserirRecNo2(char *x , No2**i ){
    if(*i==NULL) *i = novoNo2(x);
    else if(x[0] > (*i)->elemento)inserirRecNo2(x, &(*i)->dir);
    else if (x[0] < (*i)->elemento)inserirRecNo2(x, &(*i)->esq);
    else{ 
        printf("ERRO ELEMENTO REPETIDO");
    }
    
}

void inserirNo1(char x){
    inserirRecNo1(x,&raiz);
}
void inserirRecNo1(char x,No1**i){
    if(*i == NULL) *i = novoNo1(x);
    else if(x > (*i)->elemento)inserirRecNo1(x, &(*i)->dir);
    else if (x < (*i)->elemento)inserirRecNo1(x, &(*i)->esq);
    else printf("ERRO ELEMENTO REPETIDO");
}

void caminhamentoCentral(){
    caminhamentoCentralRec(raiz);
}
void caminhamentoCentralRec(No1*i){
   if(i!=NULL){
    caminhamentoCentralRec(i->esq);
     printf("%c ",i->elemento);
     caminhamentoCentralNo2(i->arvore);
     caminhamentoCentralRec(i->dir);
   }
}
void caminhamentoCentralNo2(No2*i){
    if(i!=NULL){
     caminhamentoCentralNo2(i->esq);
     printf(" %s ",i->elemento);
    caminhamentoCentralNo2(i->dir);
    }
}
bool hasStringTam10RecNo2(No2 *i){
    bool resp = false;
    if(i != NULL){
        resp = strlen(i->elemento==10) || hasStringTam10RecNo2(i->dir) || hasStringTam10RecNo2(i->esq);
    }
    return resp;
}
bool hasStringTam10Rec(No1 *i){
    bool resp;
    if(i != NULL){
     resp = hasStringTam10RecNo2(i->arvore) || hasStringTam10Rec(i->esq) || hasStringTam10Rec(i->dir);
    }
    return resp;
}


bool hasStringTam10(){
    bool resp ;
    resp = hasStringTam10Rec(raiz);
}





int main (){
    char * palavra = "abacate";

    start();
    inserirNo1('a');
    inserirNo1('b');
    inserirNo1('c');
    inserirNo1('d');
    inserirNo2(palavra);
    caminhamentoCentral();


}
