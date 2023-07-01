// Q01 - Felipe Campolina
// 762732
# ORG 2000H
# BEGIN 2000H
	   LXI H,2501
	   MOV A,M
	   INX H
	   SUB M
	   INX H
	   SUB M
	   INX H
	   MOV M,A
	   HLT
# ORG 2501H
# DB 0AH, 05H, 02H
