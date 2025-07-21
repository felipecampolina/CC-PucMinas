// Código para a multiplicação de matrizes usando OpenMP para multicore e GPU]
// Felipe Campolina , Henrique Diniz e Marcelo Reis 
// Tempo de execução (em segundos):
// - Sequencial: [ 27.129000 segundos]
// - Paralela Multicore: [25.625000]
// - Paralela GPU - distribute: [3.818000]
// - Paralela GPU - distribute parallel for: [ 3.638000]
// - Paralela GPU - distribute parallel for simd: [3.534000]

#include <stdio.h>
#include <stdlib.h>
#include <omp.h>

// Função sequencial
void mm_sequencial(double* a, double* b, double* c, int width) 
{
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

// Função paralela para multicore
void mm_multicore(double* a, double* b, double* c, int width) 
{
  #pragma omp parallel for
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

// Função paralela para GPU - distribute
void mm_gpu_distribute(double* a, double* b, double* c, int width) 
{
  #pragma omp target teams distribute
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

// Função paralela para GPU - distribute parallel for
void mm_gpu_distribute_parallel_for(double* a, double* b, double* c, int width) 
{
  #pragma omp target teams distribute parallel for
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

// Função paralela para GPU - distribute parallel for simd
void mm_gpu_distribute_parallel_for_simd(double* a, double* b, double* c, int width) 
{
  #pragma omp target teams distribute parallel for simd
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
  double *a = (double*) malloc(width * width * sizeof(double));
  double *b = (double*) malloc(width * width * sizeof(double));
  double *c = (double*) malloc(width * width * sizeof(double));

  for (int i = 0; i < width; i++) {
    for (int j = 0; j < width; j++) {
      a[i * width + j] = i;
      b[i * width + j] = j;
      c[i * width + j] = 0;
    }
  }

  double start, end;

  // Sequencial
  start = omp_get_wtime();
  mm_sequencial(a, b, c, width);
  end = omp_get_wtime();
  printf("Tempo de execução - Sequencial: %f segundos\n", end - start);

  // Multicore
  start = omp_get_wtime();
  mm_multicore(a, b, c, width);
  end = omp_get_wtime();
  printf("Tempo de execução - Paralela Multicore: %f segundos\n", end - start);

  // GPU - distribute
  start = omp_get_wtime();
  mm_gpu_distribute(a, b, c, width);
  end = omp_get_wtime();
  printf("Tempo de execução - Paralela GPU - distribute: %f segundos\n", end - start);

  // GPU - distribute parallel for
  start = omp_get_wtime();
  mm_gpu_distribute_parallel_for(a, b, c, width);
  end = omp_get_wtime();
  printf("Tempo de execução - Paralela GPU - distribute parallel for: %f segundos\n", end - start);

  // GPU - distribute parallel for simd
  start = omp_get_wtime();
  mm_gpu_distribute_parallel_for_simd(a, b, c, width);
  end = omp_get_wtime();
  printf("Tempo de execução - Paralela GPU - distribute parallel for simd: %f segundos\n", end - start);

  free(a);
  free(b);
  free(c);

  return 0;
}
