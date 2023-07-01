#include <stdio.h>
#include <stddef.h>
#include <stdbool.h>
int lerTimes(FILE *arq);
void escrevaTimes(FILE *arq);
float criaArranjoCalculaPercentual(FILE *arq,int x);
int menuOpcoes();
int main(){
    FILE *arq;
    char nome;
    int op,total_times = 0;
    do{
    op = menuOpcoes();
    switch(op)
    {
            case 0:
            printf("Obrigado! Volte Sempre!");
            break;
            case 1:
            reiniciaAmbiente(arq);
            break;
            case 2:
            total_times = lerTimes(arq);
            break;
            case 3:
            escreveTimes(arq);
            break;
            case 4:
            criaArranjoCalculaPercentual(arq,total_times);
            break;
            }
    }while(op != 0);
}
int menuOpcoes(){
    int x;
    bool ERRO;
    printf("\n\t--MENU--\n0- Sair  do programa\n1- Inicializar o ambiente\n2- Ler time do coracao\n3- Escrever os times\n4- Pesquisar pelo percentual de cada time (A, C, O)");
    do{
        printf("\n\nDigite uma opcao: ");
        scanf("%i",&x);
        ERRO = ((x > 4) || (x < 0));
        if(ERRO)printf("\n\aOpcao Invalida");
    }while(ERRO);
    return x;
}
void reiniciaAmbiente(FILE * arq){
    arq = fopen("times.dat","w");
    fclose(arq);
}
int lerTimes(FILE *arq){
    char time;
    int total = 0;
    arq = fopen("times.dat","a");
    printf("\nDigite seu time: [c = Cruzeiro , a = Atletico , o = Outros e S para sair");
    scanf(" %c",&time);
    time = toupper(time);
    total++;
    while(time != 'S'){
        fprintf(arq,"%c\n",time);
        printf("\nDigite seu time: [c = Cruzeiro , a = Atletico , o = Outros e S para sair");
        scanf(" %c",&time);
        time = toupper(time);
        total++;
    }
    fclose(arq);
    return total;
}
void escreveTimes(FILE *arq){
   if(arq = fopen("times.dat","a")!= NULL);
    {
    arq = fopen("times.dat","r");
    rewind(arq);
    int x ;
    x = fscanf(arq,"%c",&x);
    while(!feof(arq)){
        if(x == 'C')printf("\nCruzeiro");
        else if(x == 'A')printf("\nAtletico-MG");
        if(x == 'O')printf("\nOutros");
        fscanf(arq," %c",&x);
        }
    }
    fclose(arq);
}
float criaArranjoCalculaPercentual(FILE *arq,int j){
    char respostas[j];
     arq = fopen("times.dat","r");
     rewind(arq);
     for(int i = 0; i < j-1; i++){
         fscanf(arq," %c",&respostas[i]);
     }
     fclose(arq);
    int cru = 0, cam = 0, outros = 0;
    for(int i = 0; i < j-1; i++){
        if(respostas[i] == 'C')cru++;
        else if(respostas[i] == 'A')cam++;
        if(respostas[i] == 'O')outros++;
     }
     float percentual_cruzeiro = 0, percentual_galo = 0, percentual_outros = 0;
     float total = j;
     percentual_cruzeiro = (cru/total) *100;
     percentual_galo = (cam/total) *100;
     percentual_outros = (outros/total) *100;
     printf("\nO percentoal de torcedores do CRUZEIRO = %.2f",percentual_cruzeiro);
     printf("\nO percentoal de torcedores do ATLETICO = %.2f",percentual_galo);
     printf("\nO percentoal de torcedores de OUTROS times = %.2f",percentual_outros);
}

    