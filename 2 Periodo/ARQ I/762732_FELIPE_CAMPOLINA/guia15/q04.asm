// q04 Felipe Campolina
// 762732
# ORG 2000H
# BEGIN 2000H
	   LHLD 2501
	   MOV A,H
	   ADD H
	   ADD L
	   STA 2503
	   ADD L
	   ADD L
	   ADD L
	   ADD L
	   HLT
# ORG 2501H
# DB 0AH, 05H
