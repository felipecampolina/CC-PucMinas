#include <stdio.h>
#include <stdbool.h>
#include <stddef.h>
FILE* criaArquivo(char* nome);
FILE* abreArquivoLeitura(char* nome);
void leIdades(FILE* arq);
float mediaIdades(FILE* arquivo);
float percentualAcimaMedia(FILE *arq, float media);
int main()
{
    FILE *arq = criaArquivo("idades.dat");
    leIdades(arq);
    if(arq != NULL)arq = abreArquivoLeitura("idades.dat");
    float media = mediaIdades(arq);
    printf("%.2f porcento",percentualAcimaMedia(arq,media));
}
FILE* criaArquivo(char* nome)//char* --> aponta para um conjunto de chars, ou seja uma string.
{
    FILE *arquivo;//Varaiavel para receber endereço do arquivo
    arquivo = fopen(nome,"w");
    return arquivo;
    //Pode ser feito em uma linha:
    //return fopen(nome,"W");
}
void leIdades(FILE* arq)
{
    int idade;
    const FLAG = 0;
    printf("\nDigite uma idade [FLAG = 0]: ");
    scanf("%i",&idade);
    while(idade != FLAG)
    {
        fprintf(arq,"%i\n",idade);//Escrever no arquivo.
        printf("\nDigite uma idade [FLAG = 0]: ");
        scanf("%i",&idade);

    }
    fclose(arq);
}
FILE* abreArquivoLeitura(char *nome)
{
    return  fopen(nome,"r");
}
float mediaIdades(FILE* arquivo)
{
    int soma = 0, count = 0,idade;
    fscanf(arquivo,"%i",&idade);
    while(!feof(arquivo)){
        soma += idade;
        count ++;
        fscanf(arquivo,"%i",&idade);
    }
    float media = 0.0;
    if(count > 0)media = (float)soma / count;
    return media;

}
float percentualAcimaMedia(FILE *arq, float media) // passagem por referencia 
{
    rewind(arq); // voltar para o inicio do arquivo
    int idade,maiorQueMedia= 0,total=0;
    float percentual = 0;
    fscanf(arq,"%i",&idade);
    while(!feof(arq))
    {
        if(idade >= media ) maiorQueMedia++;
        total++;
        fscanf(arq,"%i",&idade);
    }
    if(total>0)percentual = ((float)maiorQueMedia / (float)total)*100;
    return percentual;
}
