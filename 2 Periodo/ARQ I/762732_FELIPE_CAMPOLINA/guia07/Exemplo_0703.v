
// Exemplo_0703 - GATES
// Nome: Felipe Campolina
// Matricula: 762732
// -------------------------
// -------------------------
// f7_gate
// -------------------------
module f7 ( output s, input a, input b, input c, input d);
// descrever por portas
endmodule // f7

// -------------------------
// multiplexer
// -------------------------
module mux ( output s, input a, input b, input c, input d, input select, input select_group ); 
// definir dados locais

//GROUP AND - NAND
wire sA;
wire not_select;
wire sA1;
wire sA2;

not NOT1 (not_select, select);
and AND1 (sA1, a, not_select);
and AND2 (sA2, b, select);
or OR1 (sA, sA1, sA2);

//GROUP OR - NOR
wire sC;
wire sC1;
wire sC2;
wire not_sC1;
wire not_sC2;
wire not_c;

not NOT2 (not_c, c);
not NOT3 (not_d, d);
or OR2 (sC1, not_c, select);
or OR3 (sC2, not_d, not_select);
not NOT4 (not_sc1, sC1);
not NOT5 (not_sC2, sC2);
or OR4 (sC, not_sC1, not_sC2);

//group final
wire not_select_group;
wire s1;
wire s2;

and AND3 (s1, not_select_group, sA);
and AND4 (s2, select_group, sC);
or OR5 (s, s1, s2);

endmodule // mux

module test_f7;
// ------------------------- definir dados

 reg a; 
 reg b; 
 reg c; //entrada
 reg d;
 reg s;
 reg s1;
 //reg z;

 //wire s;
// wire s1; //seletores
 
 wire z; //saida
 
 f7 modulo ( z, a, b, c, d );
 mux MUX1 ( z, a, b, c, d, s, s1 ); // 
 
// ------------------------- parte principal
 initial
 begin : main
 $display("Exemplo_0702 - Felipe Campolina - 762732");
 $display("z = saida");
 $display(",a, b, c, d = entradas");
 $display("s = variável de seleção - portas");
 $display("s1 = variável de seleção - grupos");
 $display("   a    b    c    d    s    s1   z");
 
 a = 1'b0; b = 1'b0; c = 1'b0; d=1'b0; s=1'b0; s1=1'b0; // E0=0, E1=0, chave=0, z=0
 

 #1 $monitor("%4b %4b %4b %4b %4b %4b %4b", a, b, c, d, s, s1, z);
 
  a = 1'b1; b = 1'b0; c = 1'b0; d=1'b0; s=1'b0; s1=1'b0;
  a = 1'b1; b = 1'b1; c = 1'b1; d=1'b1; s=1'b1; s1=1'b0;
 end
endmodule // test_f7

