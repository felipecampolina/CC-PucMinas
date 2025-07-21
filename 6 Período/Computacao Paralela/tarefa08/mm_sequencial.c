#include <stdio.h>
#include <stdlib.h>
#include <time.h>

// Função de multiplicação de matrizes sequencial
void mm_seq(double* a, double* b, double* c, int width) {
    for (int i = 0; i < width; i++) {
        for (int j = 0; j < width; j++) {
            double sum = 0;
            for (int k = 0; k < width; k++) {
                sum += a[i * width + k] * b[k * width + j];
            }
            c[i * width + j] = sum;
        }
    }
}

int main() {
    int width = 2000;
    size_t size = width * width * sizeof(double);

    // Alocação de memória para as matrizes
    double* a = (double*) malloc(size);
    double* b = (double*) malloc(size);
    double* c = (double*) malloc(size);

    // Inicialização das matrizes
    for (int i = 0; i < width; i++) {
        for (int j = 0; j < width; j++) {
            a[i * width + j] = i;
            b[i * width + j] = j;
            c[i * width + j] = 0;
        }
    }

    // Medir o tempo de execução da multiplicação sequencial
    clock_t start_time = clock();
    mm_seq(a, b, c, width);
    clock_t end_time = clock();

    double time_spent = (double)(end_time - start_time) / CLOCKS_PER_SEC;
    printf("Tempo da versão sequencial: %f segundos\n", time_spent);

    // Liberação da memória
    free(a);
    free(b);
    free(c);

    return 0;
}
