#a --> $s0
#b --> $s1
#c --> $s2

#a=10;b=-1;a=4*a+1;c=a+b; programa  em c 

addi $s0,$zero,10 #a=10
addi $s1,$zero,-1 #b=-1
add $t0,$s0,$s0 # t0 = 2a
add $t0,$t0,$t0 #t0 = 4a
addi $s0,$t0,1 #s0 = 4a+1
add $s2,$s0,$s1 #s2 = a+b


#a=0x10;b=0x1abc;c=a+b

addi $s0,$zero,0x10
addi $s1,$zero,0x1ABC
add $s2,$s0,$s1

#opera�oes bitwise --> bit a bit
or e ori 
and e andi
#Para fazer uma c�pia de um peda�o usaremos um add com uma mascara(0xFFF)
#Para fazer tudo virar 1 de um peda�o usaremos um or com uma mascara(0xFFF)


#opera�oes de shift 
#shift right e shift left LOGICA
sll $s1,$s2,8 #shift left logical - sem overflow -- multiplicacao
srl $s1,$s2,8 #shift right logical - sem overflow -- 
#a a cada shift pra esquerda multiplica por 2
#tomar cuidado com o overflow e o numero do sinal , pois se multiplicar e virar negativo � overflow

#shift right e shift left ARITIMED
#corrige o problema do 0 
sra $s1,$s2,8 # usar para divis�o



#aula 2 do dia 16/05/2023

#Para isolar o byte 0 de uma palvra usa-se
andi $t0,$t0,0xFF
#x = 554287 -- x = 0x8752F Como armazenar n�meros maiores que addi aguenta
addi $s0,$zero,0x0008
sll $t0,$t0,16
ori $s0,$t0,0x752F

#Instru�oes para transferencias de dados
#memoria --> registrador - LOAD
#registrador --> memoria - SOTORE
lw $t0,12($s0)   # t0 = MEM[12+s0)
sw $t0,12($s0) # mem[12+s0] = t0

