#include <time.h>
#include <stdio.h>
#include <mpi.h>

#define N 10
#define MAX 4
#define NUMBER 3

int main(int argc, char* argv[]) {
  int rank, maior_parcial, maior_final, numProcs;
  int buffer[N];
  MPI_Status status;

  MPI_Init(&argc, &argv) ;
  MPI_Comm_rank(MPI_COMM_WORLD, &rank);
  MPI_Comm_size(MPI_COMM_WORLD, &numProcs);

  if (rank == 0) {
    // preencher o buffer com N valores inteiros aleatórios
    srand(time(NULL));
    for (int i = 0; i < N; i++) {
      buffer[i] = rand() % MAX;
    }
  }

  // distribuir o vetor para todos os outros processos
  int counts[numProcs], displs[numProcs];
  for (int i = 0; i < numProcs; i++) {
    counts[i] = N / numProcs;
  }
  int remainder = N % numProcs;
  for (int i = 0; i < remainder; i++) {
    counts[i]++;
  }
  displs[0] = 0;
  for (int i = 1; i < numProcs; i++) {
    displs[i] = displs[i-1] + counts[i-1];
  }

  int recv_count = counts[rank];
  int recv_buffer[recv_count];

  MPI_Scatterv(buffer, counts, displs, MPI_INT,
               recv_buffer, recv_count, MPI_INT,
               0, MPI_COMM_WORLD);

  // processar o maior dos valores dentro do seu intervalo
  maior_parcial = recv_buffer[0];
  for (int i = 1; i < recv_count; i++) {
    if (recv_buffer[i] > maior_parcial) {
      maior_parcial = recv_buffer[i];
    }
  }

  // reduzir os maiores no maior, enviando o resultado para o processo com rank = 0
  MPI_Reduce(&maior_parcial, &maior_final, 1, MPI_INT, MPI_MAX, 0, MPI_COMM_WORLD);

  if (rank == 0) {
    // imprimir maior
    printf("O maior valor é %d\n", maior_final);
  }

  MPI_Finalize();
  return 0;
}