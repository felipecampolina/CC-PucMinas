#include <stdio.h>
#include <stdlib.h>
#include <string.h>
typedef struct {
    int dia;
    int mes;
    int ano;
}data;//Criando tipo e usando typedef
//typedef struct data data;//Usando typedef para nao usar struct
struct pessoa{
    char nome[50];
    data data;
};
typedef struct pessoa pessoa;
int alunos;

int menu(){
    int op;
    printf("\nDigite 0 para sair\nDigite 1 para cadastrar\nDigite 2 para pesquisar");
    scanf("%i",&op);
    return op;
}
void cadastra(pessoa A[]){
    
    for(int i = 0; i < alunos ; i++){
        printf("\n\tAluno %i",i+1);
        fflush(stdin);//limpar buffer
        puts("\nNome:  ");
        gets(A[i].nome);
        puts("\nNascimento [dd/m/aa]: ");
        scanf("%i/%i/%i",&A[i].data.dia,&A[i].data.mes,&A[i].data.ano);
    }

}
void listaAniversariantes(pessoa A[],int mes){
    for(int i =0; i < alunos ; i++){
        if(A[i].data.mes == mes){
            printf("\n%s -- %i/%i/%i",A[i].nome,A[i].data.dia,A[i].data.mes,A[i].data.ano);
        }
    }
}
void pesquisa(pessoa A[]){
    puts("\nDigite mes chave da pesquisa:");
    int mes;
    scanf("%i",&mes);
    listaAniversariantes(A,mes);
}
int numeroAlunos(){
    int x;
    puts("\nDigite o numero de alunos: ");
    scanf("%i",&x);
    return x;
}
int main()
{
    alunos = numeroAlunos();
    pessoa A[alunos];
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
