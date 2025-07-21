#include <stdio.h>
#include <stdlib.h>
#include "smpl.h"

/*---- aqui definimos os eventos ----*/
#define test   1
#define fault  2
#define recovery 3

typedef struct {
    int id;       /* identificador de facility SMPL */
} TipoProcesso;

TipoProcesso *proc;

int main(int argc, char *argv[]) {
    int i, n, token, evento, teste, encontrou_corretos;
    
    if (argc != 2) {
        puts("Uso correto: tempo <num-processos>");
        exit(1);
    }

    n = atoi(argv[1]);   /* numero de processos */
    smpl(0, "Uma simulacao simples"); /* Inicializa a simulação */
    proc = (TipoProcesso *) malloc(n * sizeof(TipoProcesso));
    
    /* Definir as facilities de todos os processos */
    for (i = 0; i < n; i++) {
        char fa_name[5];
        sprintf(fa_name, "%d", i);
        proc[i].id = facility(fa_name, 1);
    }

    /* Agora, agendar o evento de teste para cada processo */
    for (i = 0; i < n; i++) {
        schedule(test, 30.0, i); /* Agenda o evento de teste inicial para o processo i */
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
                            printf("O processo %d testou o processo %d correto no tempo %.1f\n", teste, i, time());
                            encontrou_corretos = 1; /* Encontrou um processo correto, então pode parar os testes */
                            break;
                        } else {
                            printf("O processo %d testou o processo %d falho no tempo %.1f\n", teste, i, time());
                        }
                    }
                }

                if (!encontrou_corretos) {
                    printf("O processo %d não encontrou nenhum processo correto no tempo %.1f\n", teste, time());
                }

                /* Reagenda o próximo teste */
                schedule(test, 30.0, teste);
                break;
        }
    }
    
    return 0;
}
