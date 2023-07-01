#include <stdio.h>
#include <stdbool.h>

int main ()
{
float notas_alunos;
float numero_alunos = 0;
bool ERRO;
float soma = 0;
float media = 0;
float maior_nota = 0;
float menor_nota = 100;
printf("\nPESQUISA PERFIL DA TURMA");
printf("\n[Digite -1 para encerrar a repeticao e notas negativas serao desconsideradas]");



do
{
printf("\nDigite a nota do aluno[0..100]:");
scanf("%f",&notas_alunos);
ERRO = (numero_alunos < 100) && (notas_alunos != -1);
if ((notas_alunos == -1) || (notas_alunos < 0) || (notas_alunos > 100))
{
    soma = soma;
    numero_alunos = numero_alunos;
}
else
{
    soma = soma + notas_alunos;
    numero_alunos = numero_alunos + 1;
    if(notas_alunos > maior_nota)
    {
        maior_nota = notas_alunos;
    }
    if(notas_alunos < menor_nota)
    {
        menor_nota = notas_alunos;
    }
}
}while(ERRO);

media = soma / numero_alunos;

if((soma == -1) || (numero_alunos == 0))
{
    media = 0;
    menor_nota = 0;
    
}
printf("\nA soma das notas:%.2f",soma);
printf("\nA media das notas: %.2f",media);
printf("\nO numero de notas consideradas: %.0f",numero_alunos);
printf("\nA maior nota: %.2f",maior_nota);
printf("\nA menor nota: %.2f",menor_nota);
}
