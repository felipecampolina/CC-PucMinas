

// ---------------------
// TABELA VERDADE
// Nome: Felipe Campolina 
// Matricula: 762732
// ---------------------
// ---------------------
// -- expressão
// ---------------------
module A0113 ( output s, input a, input b, input c); 
  assign s = ~((~a|~b) & ~(a&c)); 
endmodule 

module test_module;
reg x, y, z;
wire s1;
A0113 teste (s1, x, y, z);

initial begin: main
 // identificacao
 $display("Recuperação - Felipe campolina - 762732");
 $display("Teste função lógica -> SoP");

 // monitoramento
 $display(" a  b  c   = SoP");
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
Recuperação - Felipe campolina - 762732
Teste função lógica -> SoP
 a  b  c   = SoP
 x  x  x  =  x 
 0  0  0  =  0 
 0  0  1  =  0 
 0  1  0  =  0 
 0  1  1  =  0 
 1  0  0  =  0 
 1  0  1  =  1 
 1  1  0  =  1 
 1  1  1  =  1 
*/

