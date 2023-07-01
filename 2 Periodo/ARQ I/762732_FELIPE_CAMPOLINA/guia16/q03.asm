	   LXI H,000C
	   MVI B,04
	   MVI C,08
	   MOV A,H
	   SUB B
	   MOV H,A
	   INR L
	   SHLD 8020
	   HLT
