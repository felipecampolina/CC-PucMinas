#include <stdio.h>
#include <omp.h>

int main()
{
    // Definindo o número de threads
    omp_set_num_threads(2);
    
    // Região paralela
    #pragma omp parallel
    {
        printf("Ola Mundo!\n");
    }

    // Criando uma nova região paralela com variável privada para as threads
    #pragma omp parallel private(id)
    {
        int id = omp_get_thread_num(); // Obtém o número da thread
        printf("Ola Mundo! Aqui é a thread %d\n", id);
    }

    return 0;
}
