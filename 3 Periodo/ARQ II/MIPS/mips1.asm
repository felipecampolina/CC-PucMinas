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

#operaçoes bitwise --> bit a bit
or e ori 
and e andi
#Para fazer uma cópia de um pedaço usaremos um add com uma mascara(0xFFF)
#Para fazer tudo virar 1 de um pedaço usaremos um or com uma mascara(0xFFF)


#operaçoes de shift 
#shift right e shift left LOGICA
sll $s1,$s2,8 #shift left logical - sem overflow -- multiplicacao
srl $s1,$s2,8 #shift right logical - sem overflow -- 
#a a cada shift pra esquerda multiplica por 2
#tomar cuidado com o overflow e o numero do sinal , pois se multiplicar e virar negativo é overflow

#shift right e shift left ARITIMED
#corrige o problema do 0 
sra $s1,$s2,8 # usar para divisão


#Para isolar o byte 0 de uma palvra usa-se
andi $t0,$t0,0xFF


#x=3;y=x*1025

addi $s0,$zero,3;
sll $t0,$s0,10;
add $s1,$t0,$s0;

