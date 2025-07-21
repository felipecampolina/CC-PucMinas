// Grupo: Felipe Campolina , Henrique Diniz e Marcelo Reis

#include <stdio.h>
#include <omp.h>

int main()
{
    int i;

    #pragma omp parallel num_threads(2) // Define duas threads
    {
        int tid = omp_get_thread_num(); // Identificador da thread

        // O uso de schedule(static, 1) assegura que cada iteração seja atribuída
        // sequencialmente a uma thread e o 'ordered' força a ordem correta.
        #pragma omp for schedule(static, 1) ordered
        for(i = 1; i <= 3; i++) 
        {
            // A diretiva ordered garante que as impressões sejam feitas na ordem correta,
            // mesmo que múltiplas threads estejam envolvidas.
            #pragma omp ordered
            {
                printf("[PRINT1] T%d = %d \n",tid,i);
                printf("[PRINT2] T%d = %d \n",tid,i);
            }
        }
    }
}
