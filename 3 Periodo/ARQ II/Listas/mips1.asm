.data
    fibonacci:  .space 400     # Espaço para armazenar os 100 termos da série Fibonacci

.text
    main:
        # Inicialização
        la $t0, fibonacci     # Carrega o endereço base do vetor Fibonacci em $t0
        li $t1, 100           # Define o número de termos a serem calculados como 100

        # Inicializa os dois primeiros termos como 1
        li $t2, 1             # Primeiro termo
        li $t3, 1             # Segundo termo

        # Armazena os dois primeiros termos na memória
        sw $t2, ($t0)         # Armazena o primeiro termo na primeira posição livre da memória
        sw $t3, 4($t0)        # Armazena o segundo termo na próxima posição livre da memória

        addi $t0, $t0, 8     # Incrementa o ponteiro do vetor em 8 bytes (2 termos de 4 bytes cada)

        # Loop para calcular e armazenar os próximos termos
        li $t4, 2            # Contador para controlar o número de termos já calculados (começa em 2)

        loop:
            add $t4, $t4, 1    # Incrementa o contador de termos já calculados

            add $t5, $t2, $t3  # Calcula o próximo termo somando os dois termos anteriores

            sw $t5, ($t0)      # Armazena o próximo termo na próxima posição livre da memória

            addi $t0, $t0, 4   # Incrementa o ponteiro do vetor em 4 bytes (1 termo de 4 bytes)

            move $t2, $t3      # Atualiza o primeiro termo com o valor do segundo termo
            move $t3, $t5      # Atualiza o segundo termo com o valor do próximo termo

            blt $t4, $t1, loop  # Se o número de termos já calculados for menor que 100, continua o loop

        # Seu código continua aqui