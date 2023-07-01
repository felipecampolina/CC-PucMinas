#include <stdio.h>
#include <stdbool.h>

int main()
{

    float numero_alunos;
    float idade_alunos;
    float menores_de_idade = 0;
    char genero_aluno;
    bool ERRO;
    float maiores_de_idade = 0;
    float maior_idade = 0;
    float menor_idade = 100;
    int total_alunos;
    float homens = 0;
    float mulheres = 0;
    float porcentagem_homens;
    float porcentagem_mulheres;
    float porcentagem_maior;
    float porcentagem_menor;



    printf("\nPERFIL DA TURMA");
    do{
    printf("\nDigite o numero de alunos da turma: ");
    scanf("%f",&numero_alunos);
    total_alunos = numero_alunos;
    ERRO = numero_alunos <= 0;
    }while(ERRO);

    while(numero_alunos > 0)
    {
        do{
        printf("\nDigite a idade do aluno: ");
        scanf("%f",&idade_alunos);
        ERRO = (idade_alunos <= 0) || (idade_alunos >= 91);

        if ((idade_alunos >= 18) && (idade_alunos <= 90))
        {
            maiores_de_idade = maiores_de_idade + 1;
            numero_alunos = numero_alunos - 1;
            if (idade_alunos < menor_idade)
            {
                menor_idade = idade_alunos;
            }
            if ( idade_alunos > maior_idade)
            {
                maior_idade = idade_alunos;
            }
        }
        if ((idade_alunos > 0) && ( idade_alunos < 18))
        {
            menores_de_idade = menores_de_idade + 1;
            numero_alunos = numero_alunos - 1;
            if (idade_alunos < menor_idade)
            {
               menor_idade= idade_alunos;
            }
            if ( idade_alunos > maior_idade)
            {
                maior_idade = idade_alunos;
            }
        }
        if (ERRO)
        {
            printf("Opcao Invalida");
            maiores_de_idade = maiores_de_idade;
            menores_de_idade = menores_de_idade;
            numero_alunos = numero_alunos;
        }
        }while(ERRO);


        do{ 
        printf("\nDigite o genero do aluno: [F/M]");
        scanf(" %c",&genero_aluno);
        genero_aluno = toupper(genero_aluno);
        ERRO = ((genero_aluno != 'F') && (genero_aluno != 'M'));
        if(ERRO)
        {
            printf("\nOpcao Invalida");
            homens = homens;
            mulheres = mulheres;
        }
        if(genero_aluno == 'M')
        {
            homens = homens + 1;
        }
        if (genero_aluno == 'F')
        {
            mulheres = mulheres + 1;
        }
        }while(ERRO);

    }

    porcentagem_homens = (homens/total_alunos) * 100;
    porcentagem_mulheres = (mulheres/total_alunos) * 100;
    porcentagem_maior = (maiores_de_idade/total_alunos) * 100;
    porcentagem_menor = (menores_de_idade/total_alunos) * 100;

    printf("\nNumero de menores de idade: %f", menores_de_idade);
    printf("\nNumero de maiores de idade: %f", maiores_de_idade);
    printf("\nA maior idade: %.0f", maior_idade);
    printf("\nA menor idade: %.0f" ,menor_idade);
    printf("\nPorcentagem de homens: %.2f", porcentagem_homens);
    printf("\nPorcentagem de mulheres %.2f", porcentagem_mulheres);
    printf("\nPorcentagem de menores de idade: %.2f", porcentagem_menor);
    printf("\nPorcentagem de  maiores de idade %.2f", porcentagem_maior);

    
}