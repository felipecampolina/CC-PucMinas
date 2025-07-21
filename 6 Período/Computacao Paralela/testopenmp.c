#include <stdio.h>
#include <omp.h>

int main() {
    #pragma omp parallel
    {
        int id = omp_get_thread_num();
        printf("Thread %d: Hello, world!\n", id);
    }
    return 0;
}
