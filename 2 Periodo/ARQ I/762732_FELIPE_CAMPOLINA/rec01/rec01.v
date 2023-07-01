

// ---------------------
// TABELA VERDADE
// Nome: Felipe Campolina
// Matricula: 762732
// ---------------------
// ---------------------
// -- expressão
// ---------------------
module fabcd (output s, input a, b, c, d);
assign s = ~b & ~c | ~a & ~c & d | b & c & ~d | a & ~c & ~d;
endmodule 

// ---------------------
// -- SoP
// ---------------------
module SoP (output s, input a, b, c, d);
assign s = ~b & ~c | ~a & ~c & d | b & c & ~d | a & ~c & ~d;
endmodule 


module PoS (output p, input a, b, c, d);
assign p = ((b | ~c) & (~c | ~d) & (a | ~b | c | d) & (~a | ~b | ~d));
endmodule 


module test_module;
reg x, y, z, w;
wire s1, s2, s3;
fabcd FXY1 (s1,w, x, y, z);
SoP SOP1 (s2, w, x, y, z);
PoS POS1 (s3, w, x, y, z);

initial begin: main
 // identificacao
 $display("Recuperação - Felipe Campolina - 762732");
 $display("Mintermos e maxtermos");

 // monitoramento
 $display(" a  b  c  d  = SoP PoS");
 $monitor("%2b %2b %2b %2b = %2b %2b",w, x, y, z, s2, s3);
 
 // sinalizacao
 #1 w=0; x=0; y=0; z=0;
 #1 w=0; x=0; y=0; z=1;
 #1 w=0; x=0; y=1; z=0;
 #1 w=0; x=0; y=1; z=1;
 #1 w=0; x=1; y=0; z=0;
 #1 w=0; x=1; y=0; z=1;
 #1 w=0; x=1; y=1; z=0;
 #1 w=0; x=1; y=1; z=1;
 #1 w=1; x=0; y=0; z=0;
 #1 w=1; x=0; y=0; z=1;
 #1 w=1; x=0; y=1; z=0;
 #1 w=1; x=0; y=1; z=1;
 #1 w=1; x=1; y=0; z=0;
 #1 w=1; x=1; y=0; z=1;
 #1 w=1; x=1; y=1; z=0;
 #1 w=1; x=1; y=1; z=1;
end
endmodule

/*
Recuperação - Felipe Campolina - 762732
Mintermos e maxtermos
 a  b  c  d  = SoP PoS
 x  x  x  x =  x  x
 0  0  0  0 =  1  1
 0  0  0  1 =  1  1
 0  0  1  0 =  0  0
 0  0  1  1 =  0  0
 0  1  0  0 =  0  0
 0  1  0  1 =  1  1
 0  1  1  0 =  1  1
 0  1  1  1 =  0  0
 1  0  0  0 =  1  1
 1  0  0  1 =  1  1
 1  0  1  0 =  0  0
 1  0  1  1 =  0  0
 1  1  0  0 =  1  1
 1  1  0  1 =  0  0
 1  1  1  0 =  1  1
 1  1  1  1 =  0  0
*/

