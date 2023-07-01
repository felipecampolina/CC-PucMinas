/*
EXERCICIO 2 DO TP 2
FELIPE CAMPOLINA SOARES DE PAULA 
762732
*/


//--- Bibliotecas ---//
#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <stdlib.h>
//---Fim bibliotecas---//

//---Criação do tipo Game---//
typedef struct {
    int app_id;
    char nome[200];
    char release_date[20];
    char owners[30];
    int age;
    float price;
    int dlcs;
    char **languages;
    char website[200];
    bool windows;
    bool mac;
    bool linuxx;
    float upvotes;
    int abg_pt;
    char developers;
    char **genre;


}Game; 
//---Criação do tipo Game---//



//---Métodos --- //


void inserir(char x, int pos,char *frase,int n) {//Inicio Inserir- função que insere um caracter em um array de caracteres
   int i;

   //validar insercao
   if( pos < 0 || pos > n){
      printf("Erro ao inserir!");
      exit(1);
   }

   //levar elementos para o fim do array
   for(i = n; i > pos; i--){
      frase[i] = frase[i-1];
   }

   frase[pos] = x;
   n++;

   for(int i = 0 ; i <n;i++){
        printf(" %c %i\n",frase[i],i);
        
    }
}//Fim inserir

char *arrumaDados(char *dado)// Início arrumaDados -- função que trata dados e suas execoes para ser processados mais tarde
{
    char *dado_tratado = (char *)calloc(1000, sizeof(char));
    int j = 0;

    for (int i = 0; i < strlen(dado); i++)
    {
        if (dado[i] == '\"')
        {
            i++;

            if (dado[i] == '[')
            {
                while (dado[i] != '\"')
                {
                    if (dado[i] != '\'')
                    {
                        dado_tratado[j] = dado[i];
                        j++;
                    }

                    i++;
                }
            }
            else
            {
                while (dado[i] != '\"')
                {
                    dado_tratado[j] = dado[i];
                    j++;
                    i++;
                }
            }
        }
        else if (dado[i] == '[')
        {
            while (dado[i] != ']')
            {
                if (dado[i] != '\'')
                {
                    dado_tratado[j] = dado[i];
                    j++;
                }

                i++;
            }

            dado_tratado[j] = dado[i];
            j++;
        }
        else if (dado[i] == ',')
        {
            dado_tratado[j] = ';';
            j++;
        }
        else
        {
            dado_tratado[j] = dado[i];
            j++;
        }
    }

    return dado_tratado;
}//Fim arrumaDados



void replace(char *texto, int index, const char *tag){//Inicio replace -- funcao que substitui uma posicao por uma substring
    char cptexto[500];
    int lentag;
    
    lentag = strlen(tag);
    
    strcpy(cptexto, texto+index+1); //copia tudo que vem depois do caractere
    strcpy(texto+index, tag); //sobrescreve a tag no texto a partir da posição do caractere
    strcpy(texto+index+lentag, cptexto); //copia devolta para o texto depois da tag adicionada
}//Fim replace


int main (){//Inicio main

    char *frase ="";
    char *atributos;
    char atributosAntigo[1000];
    char idVerde[1000];
    char fraseNova[1000];
    char *teste;
    int idVERDE = 0;
    int idCSV = 0;
    int test = 0;

//---Ler arquivo csv e verde ---//
    fgets(idVerde,1000,stdin);
    while(idVerde[0]!='F'){
    FILE * arquivo = fopen("/tmp/games.csv", "r"); // Ler arquivo 
    idVERDE = atoi(idVerde);
    do{
    fgets(fraseNova,1000,arquivo);
    frase = fraseNova;
    for(int i = 0 ; i<100 ; i++){
        atributosAntigo[i]=frase[i];
        if(frase[i]==',')break;
        else{
        atributosAntigo[i]=frase[i];
        }
    }
    idCSV = atoi(atributosAntigo);
    test++;
    }while(idCSV!=idVERDE);
//---Ler arquivo csv e verde ---//


//Identifica se existe ,, e caso positivo substitui por ,null,//
for(int i = 0 ; i<strlen(frase);i++){
if(frase[i]==','&&frase[i+1]==',')replace(frase,i,",null");
}







//Joga a frase para funcao de arrumar//
    frase = arrumaDados(frase);
    int i = 0;
    float porcentagem = 0;
    int antigo = 0;
    atributos = strtok(frase,";");

//Imprime com exeções do enunciado
    while(atributos){
        if(i!=16 && i!=14 && i!=13 && i!=2&&i!=12)printf("%s ",atributos);
        if(i==16){//Trata generos
            printf("[%s",atributos);

        }
        if(i==2){//Trata data
            int tamanho = strlen(atributos);
            for(int i = 0 ; i<strlen(atributos);i++){
                if(atributos[i]==',')atributos[i]='/';
            }
            if(tamanho==12){
            for(int i = 0 ; i<tamanho ; i++){
                if(i!=3 && i!=4 && i!=5  && i!=7) printf("%c",atributos[i]);
            }
            printf(" ");
            }
            if(tamanho==11){
                for(int i = 0 ; i<tamanho ; i++){
                if(i!=3 && i!=4 && i!=6) printf("%c",atributos[i]);
            }
            printf(" ");
            }
            

        }
        if(i==14){//Trata tempo
            if(atoi(atributos)/60!=0 && atoi(atributos)%60!=0 ){
                printf("%ih %im ",atoi(atributos)/60,atoi(atributos)%60);
            }
            else if(atoi(atributos)/60!=0 && atoi(atributos)%60==0){
                 printf("%ih %im ",atoi(atributos)/60);

            }
            else{
                 printf("null ");
            }
        }
        if(i==13){//Trata porcentagem
            char x = '%';
            porcentagem += atoi(atributos);
            porcentagem = (antigo/porcentagem)*100;
            printf("%.0f",porcentagem);
            printf("%c ",x);

        }
        if(i==12){//Trata porcentagem
            porcentagem += atoi(atributos);
           antigo = atoi(atributos);
            
        }
        atributos = strtok(NULL,";");
        i++;
    }
    fclose(arquivo);//Fecha arquivo
    fgets(idVerde,1000,stdin);//ler arquivo
    }
    
}//Fim main