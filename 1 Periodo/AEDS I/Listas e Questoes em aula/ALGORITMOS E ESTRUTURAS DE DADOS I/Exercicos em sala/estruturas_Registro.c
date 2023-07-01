#include <stdio.h>
#include <stdlib.h>
struct data{
    int dia;
    int mes;
    int ano;
};//Criando tipo
typedef struct data data;//Usando typedef para nao usar struct
struct pessoa{
    char nome[50];
    data data;
};
int MAX = 3;


int menu(){
    int op;
    printf("\nDigite 0 para sair\nDigite 1 para cadastrar\nDigite 2 para pesquisar");
    scanf("%i",&op);
    return op;
}
void cadastra(data A[MAX]){
    
    for(int i = 0; i < MAX ; i++){
        printf("\n\tAluno %i",i+1);
        puts("\nNascimento [dia/mes/ano]: ");
        scanf("%i/%i/%i",&A[i].dia,&A[i].mes,&A[i].ano);
    }

}
void listaAniversariantes(data A[MAX],int mes){
    for(int i =0; i < MAX ; i++){
        if(A[i].mes == mes){
            printf("\n%i/%i/%i",A[i].dia,A[i].mes,A[i].ano);
        }
    }
}
void pesquisa(data A[MAX]){
    puts("\nDigite mes chave da pesquisa:");
    int mes;
    scanf("%i",&mes);
    listaAniversariantes(A,mes);
}
int main()
{
    data A[MAX];
    int op;
    do{
    op = menu();
    switch(op)
    {
        case 0:
            puts("Obrigado por usar meu programa!");
            break;
        case 1:
            cadastra(A);
            break;
            
        case 2:
            pesquisa(A);
            break;
            
    }
    }while(op != 0);

    return 0;
}
