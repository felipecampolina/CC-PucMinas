#include <string.h>
#include <stdbool.h>
#include <stdio.h>
bool senhaCorreta(char senha[]);

int main(){
    char senha[30];
    printf("\nDigite senha: ");
    gets(senha);
    printf("\nSua senha tem %i caracteres",strlen(senha));
    if(senhaCorreta(senha))printf("\nSenha Correta!");
}
bool senhaCorreta(char senha[]){
    char correta[] = "faculdade";
    bool valida = false;
    if(!strcmp(senha, correta)) valida = true;
    return valida;
}