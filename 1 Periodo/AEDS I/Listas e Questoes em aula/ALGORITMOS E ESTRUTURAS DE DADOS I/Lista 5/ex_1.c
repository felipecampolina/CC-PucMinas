#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

int main()
{
    bool ERRO;
    int numero_alunos;
    char time;
    int contagem_a = 0;
    int contagem_c = 0;
    int contagem_o = 0;
    float porcentagem_a;
    float porcentagem_o;
    float porcentagem_c;
    float total;

    printf("\nTime favorito da turma");

    do{
    printf("\nNumero de alunos da turma: ");
    scanf("%i",&numero_alunos);
    ERRO = (numero_alunos < 0) ||  (numero_alunos > 70);
    }while(ERRO);

do{
    do{
    printf("\nQual time voce torce: [Cruzeiro = C, Atletico = A, Outros  O]");
    scanf(" %c", &time);
    time = toupper(time);
    ERRO = (time != 'C') && (time != 'A') && (time != 'O');
    if(ERRO)
    {
        printf("Opcao invalida");
    }
    }while(ERRO);

    switch(time)
    {
    case 'C':
        contagem_c = contagem_c + 1;
        break;
    case 'A':
        contagem_a = contagem_a + 1;
        break;
    case 'O':
        contagem_o = contagem_o + 1;
        break;
    }
    numero_alunos = numero_alunos - 1;
}while(numero_alunos > 0);

    total = contagem_a + contagem_c + contagem_o;
    
    porcentagem_a = (contagem_a / total) * 100;
    porcentagem_c = (contagem_c / total) * 100;
    porcentagem_o = (contagem_o / total) * 100;

    printf("\nPorcentagem de cruzeirenses: %2.f",porcentagem_c);
    printf("\nPorcentagem de atleticanos: %2.f",porcentagem_a);
    printf("\nPorcentagem de torcedores de outros times: %2.f",porcentagem_o);

    }
