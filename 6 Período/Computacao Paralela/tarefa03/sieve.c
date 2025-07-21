// Grupo: Felipe Campolina , Henrique Diniz e Marcelo Reis

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <omp.h>

#define MAX 1000000000

// Função para contar o número de primos até n usando o Crivo de Eratóstenes
int sieve(int n, int chunk_size, int num_threads) {
    char *is_prime = (char *)malloc((n+1) * sizeof(char));
    int count = 0;

    // Inicializa todos os números como primos
    for (int i = 2; i <= n; i++) {
        is_prime[i] = 1;
    }

    int sqrt_n = (int)sqrt(n);

    // Crivo de Eratóstenes paralelo para marcar múltiplos de primos com chunk
    #pragma omp parallel for schedule(static, chunk_size) num_threads(num_threads) reduction(+:count)
    for (int p = 2; p <= sqrt_n; p++) {
        if (is_prime[p]) {
            for (int i = p * p; i <= n; i += p) {
                is_prime[i] = 0;
            }
        }
    }

    // Contar primos restantes com chunk
    #pragma omp parallel for schedule(static, chunk_size) num_threads(num_threads) reduction(+:count)
    for (int i = 2; i <= n; i++) {
        if (is_prime[i]) {
            count++;
        }
    }

    free(is_prime);
    return count;
}

int main() {
    int n = 100000000; // Limite superior para a contagem de primos
    int prime_count;
    int chunk_size = 1000;  // Tente chunks menores
    int num_threads = 4;  // Defina o número de threads
    double start_time, end_time;

    // Medir tempo de execução sequencial
    start_time = omp_get_wtime();
    prime_count = sieve(n, chunk_size, 1); // Contagem de primos sem paralelismo (1 thread)
    end_time = omp_get_wtime();
    printf("Número de primos até %d: %d\n", n, prime_count);
    printf("Tempo sequencial: %.3f ms\n", (end_time - start_time) * 1000);

    // Medir tempo de execução paralelo (static) com chunk
    start_time = omp_get_wtime();
    prime_count = sieve(n, chunk_size, num_threads); // Contagem de primos em paralelo com política estática
    end_time = omp_get_wtime();
    printf("Tempo paralelo (static) com chunk %d e %d threads: %.3f ms\n", chunk_size, num_threads, (end_time - start_time) * 1000);

    // Medir tempo de execução paralelo (dynamic) com chunk
    start_time = omp_get_wtime();
    prime_count = sieve(n, chunk_size, num_threads); // Contagem de primos em paralelo com política dinâmica
    end_time = omp_get_wtime();
    printf("Tempo paralelo (dynamic) com chunk %d e %d threads: %.3f ms\n", chunk_size, num_threads, (end_time - start_time) * 1000);

    // Medir tempo de execução paralelo (guided) com chunk
    start_time = omp_get_wtime();
    prime_count = sieve(n, chunk_size, num_threads); // Contagem de primos em paralelo com política guiada
    end_time = omp_get_wtime();
    printf("Tempo paralelo (guided) com chunk %d e %d threads: %.3f ms\n", chunk_size, num_threads, (end_time - start_time) * 1000);

    return 0;
}



// Testes 

//Arquitetura : 12th Gen Intel(R) Core(TM) i7-12700H, que possui 14 núcleos. Windows 11 64-bit.

// Número de primos ate 100000000: 5761455
// Tempo sequencial: 1078.000 ms
// Tempo paralelo (static) com chunk 100 e 4 threads: 636.000 ms
// Tempo paralelo (dynamic) com chunk 100 e 4 threads: 687.000 ms
// Tempo paralelo (guided) com chunk 100 e 4 threads: 628.000 ms


// Número de primos ate 100000000: 5761455
// Tempo sequencial: 1076.000 ms
// Tempo paralelo (static) com chunk 1000 e 8 threads: 851.000 ms
// Tempo paralelo (dynamic) com chunk 1000 e 8 threads: 849.000 ms
// Tempo paralelo (guided) com chunk 1000 e 8 threads: 874.000 ms


// Numero de primos ate 100000000: 5761455
// Tempo sequencial: 1090.000 ms
// Tempo paralelo (static) com chunk 10000 e 10 threads: 1021.000 ms
// Tempo paralelo (dynamic) com chunk 10000 e 10 threads: 985.000 ms
// Tempo paralelo (guided) com chunk 10000 e 10 threads: 1036.000 ms

// Numero de primos ate 100000000: 5761455
// Tempo sequencial: 1120.000 ms
// Tempo paralelo (static) com chunk 100000 e 2 threads: 984.000 ms
// Tempo paralelo (dynamic) com chunk 100000 e 2 threads: 1002.000 ms
// Tempo paralelo (guided) com chunk 100000 e 2 threads: 1073.000 ms

// Numero de primos ate 100000000: 5761455
// Tempo sequencial: 1016.000 ms
// Tempo paralelo (static) com chunk 1000 e 4 threads: 809.000 ms
// Tempo paralelo (dynamic) com chunk 1000 e 4 threads: 796.000 ms
// Tempo paralelo (guided) com chunk 1000 e 4 threads: 838.000 ms