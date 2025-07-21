#include <stdio.h>
#include <stdlib.h>
#include <omp.h>

// Função de multiplicação de matrizes com OpenMP
void mm_omp(double* a, double* b, double* c, int width) 
{
  #pragma omp parallel for collapse(2)
  for (int i = 0; i < width; i++) {
    for (int j = 0; j < width; j++) {
      double sum = 0;
      for (int k = 0; k < width; k++) {
        double x = a[i * width + k];
        double y = b[k * width + j];
        sum += x * y;
      }
      c[i * width + j] = sum;
    }
  }
}

int main()
{
  int width = 2000;
  size_t size = width * width * sizeof(double);
  
  // Alocação de memória no host
  double *h_a = (double*) malloc(size);
  double *h_b = (double*) malloc(size);
  double *h_c = (double*) malloc(size);

  // Inicialização das matrizes
  for(int i = 0; i < width; i++) {
    for(int j = 0; j < width; j++) {
      h_a[i * width + j] = i;
      h_b[i * width + j] = j;
      h_c[i * width + j] = 0;
    }
  }

  // Medir o tempo de execução
  double start_time, end_time;

  start_time = omp_get_wtime();
  mm_omp(h_a, h_b, h_c, width);
  end_time = omp_get_wtime();
  printf("Tempo da versão OpenMP: %f segundos\n", end_time - start_time);

  // Liberação de memória
  free(h_a);
  free(h_b);
  free(h_c);

  return 0;
}