	   LXI H,2050
	   MOV B,M
	   MVI C,00
	   INX H
	   MOV A,M
	   CMP B
	   JC 2011
	   SUB B
	   INR C
	   JMP 2008
	   STA 3050
	   MOV A,C
	   STA 3051
	   HLT
