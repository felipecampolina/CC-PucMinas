#include<stdio.h>
#include<stdbool.h>

int main()
{
    printf("VOTACAO CHAPA DACC");
    int voto = 0;
    bool ERRO;
    float chapa_1 = 0;
    float chapa_2 = 0;
    float nulo = 0;
    float total_votos = 0;
    float porcentagem_chapa1 = 0;
    float porcentagem_chapa2 = 0;
    float porcentagem_nulo = 0;
    
    
    do{
    printf("\n 0 - SAIR DO PROGRAMA");
    printf("\n 1 - CHAPA 1");
    printf("\n 2 - CHAPA 2");
    printf("\n 3 - VOTO NULO/BRANCO\n");
    scanf("%i",&voto);
    ERRO = (voto != 0) && (voto != 1) && (voto != 2) && (voto != 3);
    if (ERRO)5
    {
        printf("\n\aOpcao Invalida");
    }
    else
    {
        switch(voto)
        {
            case 0:
                break;
            case 1:
                chapa_1 = chapa_1 + 1;
                break;
            case 2:
                chapa_2 = chapa_2 + 1;
                break;
            case 3:
                nulo = nulo + 1;
                break;


        }

    }

    }while(ERRO || (voto != 0));
    total_votos = chapa_1 + chapa_2 + nulo;
    porcentagem_chapa1 = (chapa_1/total_votos) * 100;
    porcentagem_chapa2 = (chapa_2/total_votos) * 100;
    porcentagem_nulo = (nulo/total_votos) * 100;

    printf("\nVotos chapa 1: %.0f",chapa_1);
    printf("\nVotos chapa 2: %.0f",chapa_2);
    printf("\nVotos nulos/brancos: %.0f",nulo);
    printf("\nVotos totais: %.0f",total_votos);
    if ( chapa_1 > chapa_2)
    {
        printf("\nA CHAPA 1 foi a vencedora com %.2f porcento dos votos.",porcentagem_chapa1); 
    }
    if ( chapa_2 > chapa_1)
    {
        printf("\nA CHAPA 2 foi a vencedora com %.2f porcento dos votos.",porcentagem_chapa2); 
    }
    if(chapa_1 == chapa_2)
    {
        printf("\nTeve um empate entre as chapas com %.2f porcento dos votos.",porcentagem_chapa2);
    }
}