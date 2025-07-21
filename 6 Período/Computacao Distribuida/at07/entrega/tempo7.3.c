#include <stdio.h>
#include <stdlib.h>
#include "smpl.h"

/*---- aqui definimos os eventos ----*/
#define test   1
#define fault  2
#define recovery 3

typedef struct {
    int id;       /* identificador de facility SMPL */
    int *State;   /* Vetor de estados dos processos */
} TipoProcesso;

TipoProcesso *proc;

int main(int argc, char *argv[]) {
    int i, j, n, token, evento, teste, encontrou_corretos;
    
    if (argc != 2) {
        puts("Uso correto: tempo <num-processos>");
        exit(1);
    }

    n = atoi(argv[1]);   /* numero de processos */
    smpl(0, "Uma simulacao simples"); /* Inicializa a simulação */
    proc = (TipoProcesso *) malloc(n * sizeof(TipoProcesso));
    
    /* Definir as facilities e inicializar o vetor State[N] para cada processo */
    for (i = 0; i < n; i++) {
        char fa_name[5];
        sprintf(fa_name, "%d", i);
        proc[i].id = facility(fa_name, 1);
        
        /* Inicializar o vetor State[N] */
        proc[i].State = (int *) malloc(n * sizeof(int));
        for (j = 0; j < n; j++) {
            if (i == j) {
                proc[i].State[j] = 0; /* O processo sabe que ele mesmo está correto */
            } else {
                proc[i].State[j] = -1; /* Estado desconhecido para os demais */
            }
        }
    }

    /* Agora, agendar o evento de teste e de falha para cada processo */
    for (i = 0; i < n; i++) {
        schedule(test, 30.0, i); /* Agenda o evento de teste inicial para o processo i */
        schedule(fault, 40.0 + i * 10, i); /* Falhas acontecem em tempos diferentes para cada processo */
        schedule(recovery, 100.0 + i * 10, i); /* Recuperações acontecem em tempos diferentes para cada processo */
    }
    
    /* Loop de execução da simulação */
    while (time() < 150.0) { /* Executa até o tempo 150 */
        cause(&evento, &teste);
        
        switch (evento) {
            case test:
                encontrou_corretos = 0; /* Flag para indicar se encontrou processos corretos */

                /* Processo 'teste' realiza o teste em todos os outros processos */
                for (i = 0; i < n; i++) {
                    if (i != teste) { /* O processo não testa ele mesmo */
                        int status_proc = status(proc[i].id);
                        if (status_proc == 0) {
                            proc[teste].State[i] = 0; /* Atualiza o vetor State indicando que o processo está correto */
                            printf("O processo %d testou o processo %d correto no tempo %.1f\n", teste, i, time());
                            encontrou_corretos = 1;
                        } else {
                            proc[teste].State[i] = 1; /* Atualiza o vetor State indicando que o processo está falho */
                            printf("O processo %d testou o processo %d falho no tempo %.1f\n", teste, i, time());
                        }
                    }
                }

                /* Imprimir o vetor State após os testes */
                printf("Estado do processo %d no tempo %.1f: [", teste, time());
                for (i = 0; i < n; i++) {
                    printf("%d", proc[teste].State[i]);
                    if (i < n - 1) printf(", ");
                }
                printf("]\n");

                /* Reagenda o próximo teste */
                schedule(test, 30.0, teste);
                break;

           case fault:
    /* Processa a falha */
    if (request(proc[teste].id, teste, 0.0) != 0) {  // Adicionando o terceiro parâmetro (tempo de espera)
        printf("Erro ao falhar o processo %d no tempo %.1f\n", teste, time());
    } else {
        printf("O processo %d falhou no tempo %.1f\n", teste, time());
    }
    break;

case recovery:
    /* Processa a recuperação */
    release(proc[teste].id, teste);  // Adicionando o terceiro parâmetro (tempo de espera)
    printf("O processo %d recuperou no tempo %.1f\n", teste, time());
    break;
        }
    }
    
    return 0;
}
