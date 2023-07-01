// q06 - Felipe Campolina
// 762732
# ORG 2000H
# BEGIN 2000H
	   LHLD 2501
	   MOV A,H
	   SUB L
	   ADD A
	   SUB L
	   STA 2503
	   HLT
# ORG 2501H
# DB 0AH, 05H
