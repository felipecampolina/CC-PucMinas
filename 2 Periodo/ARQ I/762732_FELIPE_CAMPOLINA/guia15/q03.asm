// q03 - Felipe Campolina
// 762732
# ORG 2000H
# BEGIN 2000H
	   LDA 2501
	   MOV H,A
	   LDA 2502
	   ADD H
	   MOV H,A
	   LDA 2503
	   ADD H
	   DAA
	   STA 2504
	   HLT
# ORG 2501H
# DB 17H, 13H, 11H
