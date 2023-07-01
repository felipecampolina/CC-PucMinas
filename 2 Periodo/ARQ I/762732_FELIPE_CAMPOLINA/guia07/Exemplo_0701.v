// -------------------------
// Exemplo_0701 - GATES
// Nome: Felipe Campolina
// Matricula: 762732
// -------------------------
// -------------------------
// f7_gate
// -------------------------
module f7 ( output s, output s2, input e);
// descrever por portas
endmodule // f7

// -------------------------
// multiplexer
// -------------------------
module mux ( output s, output s1, input a, input select ); // mux de 2 canais
// definir dados locais

 wire not_select;
 wire sa;
 wire sb;
 
// descrever por portas
 not NOT1 ( not_select, select ); //select = chave seletora
 and AND1 ( s, a, not_select ); //sa = select' . a
 and AND2 ( s1, a, select ); //sa = select . a
 //and AND2 ( sb, b, select ); //sb = select . b
 //or OR1 ( s , sa, sb ); // s = select'.a + select.b
endmodule // mux

module test_f7;
// ------------------------- definir dados

 reg x;
 reg y;
 reg s;
 wire w;
 wire w1;
 wire z;
 wire z1;
 
 f7 modulo ( w, w1, x );
 mux MUX1 ( z, z1, x, s ); // z = saida, x=E0, y=E1, s=chave seletora
 
// ------------------------- parte principal
 initial
 begin : main
 $display("Exemplo_0701 - Felipe Campolina - 762732");
 $display("z, z1 = saidas");
 $display("x = entrada");
 $display("s = variável de seleção");
 $display("   x    s    z    z1");
 
 x = 1'b0; y = 1'b1; s = 1'b0;  // E0=0, E1=1, chave=0, z=0
 
 // projetar testes do modulo
 #1 $monitor("%4b %4b %4b %4b", x, s, z, z1);
 
 #1 x = 1'b1; // entrada = 1
 #1 s = 1'b1; //chave=1 
 end
endmodule // test_f7


//Exemplo_0701 - Felipe Campolina - 762732
//z, z1 = saidas
//x = entrada
//s = variável de seleção
//   x    s    z    z1
//   0    0    0    0
//   1    0    1    0
//   1    1    0    1