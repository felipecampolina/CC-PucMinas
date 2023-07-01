.text
.globl main

main:
lui $t0,0x1001
#addi $t1,$zero,10
#addi $t2,$zero,5
lw $s0,0($t0) # pega 1 numero memoria
lw $s1,4($t0) # pega 2 numero memoria
mult $s0,$s1 # estara armazenado em $lo
mflo $s2 # tirou do lo e colocou em s2

.data
x:.word 15
y: .word -1

#Para mumtiplicarmos temos que levar em consideração que 3 bits x 3 bits --> 6 bits
#Processador armazena em $hi e $lo , temos que mover para registrador

#para sabermos se precisara do high , contaremos o numero de bits significantes se soma maior que 8
#provavelmente vai precisar
#Como descobrir números de bits significantes?
#shift pra direita até ser 0 -->numeros positivos
#shift que coloca 1 pra direita até ser -1(ou seja tudo 1) -->  numeros negativos

