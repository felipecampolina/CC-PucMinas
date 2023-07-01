// -------------------------
// Exemplo_0701 - GATES
// Nome: Felipe Campolina
// Matricula: 762732

// -------------------------
module f7 ( output s, input a, input b);
// descrever por portas
endmodule // f7

module mux ( output s, input a, input b, input select ); // mux de 2 canais
// definir dados locais

 wire not_select;
 wire not_a;
 wire not_b;
 wire sa;
 wire sb;
 wire not_sa;
 wire not_sb;
 
// descrever por portas
 not NOT1 ( not_a, a ); 
 not NOT2 ( not_b, b ); 
 not NOT3 ( not_select, select ); 
 or OR1 (sa, not_a, select);
 or OR2 (sb, not_b, not_select);
 not NOT4 (not_sa, sa);
 not NOT5 (not_sb, sb);
 or OR3 (s, not_sa, not_sb); 

endmodule // mux

module test_f7;
// ------------------------- definir dados

 reg x; //a
 reg y; //b
 reg s; //s

 wire z;
 wire z1;
 
 f7 modulo ( w, x, y );
 mux MUX1 ( z, x, y, s ); // z = saida, x=E0, y=E1, s=chave seletora
 
// ------------------------- parte principal
 initial
 begin : main
 $display("Exemplo_0702 - Felipe Campolina- 762732");
 $display("z = saida");
 $display("x, b = entradas");
 $display("s = variável de seleção");
 $display("   x    y    s    z");
 
 x = 1'b0; y = 1'b0; s = 1'b0;  // E0=0, E1=0, chave=0, z=0
 
 // projetar testes do modulo
 #1 $monitor("%4b %4b %4b %4b", x, y, s, z);
 
 
 #1 s = 1'b1; 
 #1 y = 1'b1;
 #1 x = 1'b1;
 #1 s = 1'b0;
 #1 y = 1'b0;
 end
endmodule // test_f7

