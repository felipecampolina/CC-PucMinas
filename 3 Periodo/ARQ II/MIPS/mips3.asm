#Ex 10
#x = h = A [ i ] ;
 #A[ i ] = A [ i + 1] ;
 #A [ i + 1] = h ;
#s0 = h
#s1 = k 
#s3 = i

lw $t0, i($a0)         # Carrega o valor de A[i] em $t0 ($a0 é o endereço base do array A)
lw $t1, 4($a0)     # Carrega o valor de A[i+1] em $t1

move $t2, $t0          # Move o valor de A[i] para $t2 (h)
sw $t2, 4($a0)     # Armazena o valor de h em A[i+1]

move $t2, $t1          # Move o valor de A[i+1] para $t2
sw $t2, i($a0)         # Armazena o valor de A[i+1] em A[i]

