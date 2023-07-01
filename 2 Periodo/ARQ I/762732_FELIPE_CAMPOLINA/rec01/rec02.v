

// ---------------------
// TABELA VERDADE
// Nome: Felipe Campolina
// Matricula: 762732
// ---------------------
// ---------------------
// -- expressão
// ---------------------

module f ( output s, input a, input b, input c ); 
  wire w1, w2; 
  or  OR__1  (s,a,w1); 
  not NOT_1 (w2,c); 
  and AND_1 (w1,w2,b); 
endmodule // s = f (a,b,c) 

module test_module;
reg x, y, z;
wire s1;
f teste (s1, x, y, z);

initial begin: main
 // identificacao
 $display("Recuperação - Felipe Campolina - 762732");
 $display("Teste função lógica -> tabela verdade");

 // monitoramento
 $display(" a  b  c   = S");
 $monitor("%2b %2b %2b  = %2b ", x, y, z, s1);
 
 // sinalizacao
 #1 x=0; y=0; z=0;
 #1 x=0; y=0; z=1;
 #1 x=0; y=1; z=0;
 #1 x=0; y=1; z=1;
 #1 x=1; y=0; z=0;
 #1 x=1; y=0; z=1;
 #1 x=1; y=1; z=0;
 #1 x=1; y=1; z=1;
end
endmodule

/*
Recuperação - Felipe Campolina - 762732
Teste função lógica -> tabela verdade
 a  b  c   = S
 x  x  x  =  x 
 0  0  0  =  0 
 0  0  1  =  0 
 0  1  0  =  1 
 0  1  1  =  0 
 1  0  0  =  1 
 1  0  1  =  1 
 1  1  0  =  1 
 1  1  1  =  1 
*/

