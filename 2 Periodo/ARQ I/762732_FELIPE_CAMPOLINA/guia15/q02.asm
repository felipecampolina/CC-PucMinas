// Q02 - Felipe Campolina
// 762732
# ORG 2000H
# BEGIN 2000H
	   LHLD 2505
	   MOV A,L
	   CMA
	   MOV L,A
	   MOV A,H
	   CMA
	   MOV H,A
	   INX H
	   XCHG
	   LHLD 2501
	   DAD D
	   XCHG
	   LHLD 2503
	   DAD D
	   SHLD 2507
	   HLT
# ORG 2501H
# DB 0AH, 00H, 05H, 00H, 02H, 00H
