// ---------------------------------------
// Exemplo_0505 - GATES
// Nome: Felipe Campolina
// Matricula:  - 762732
// ---------------------------------------

//Exemplo_0505 - Felipe Campolina - 762732
   //x    y    a    b
   //0    0    0    1
   //0    1    1    0
   //1    0    1    0
   //1    1    0    1

// --------------------------------------


module f5a (output s, input a, input b );

// definir dado local
 wire s1;
 
// descrever por portas
 xnor XNOR1 ( s1,a, b );
 not NOT1 ( s, s1 );
 
endmodule // f5a

// -------------------------
// -------------------------


module f5b ( output s, input a, input b );

// descrever por expressao
 assign s = ~(a ^ b);
endmodule // f5b


module test_f5;

// ------------------------- definir dados
 reg x;
 reg y;
 wire a, b;
 f5a moduloA ( a, x, y ); 
 f5b moduloB ( b, x, y ); 

// ------------------------- parte principal
 initial
 begin : main
 $display("Exemplo_0505 - Felipe Campolina - 762732");
 $display("   x    y    a    b");
 
 // projetar testes do modulo
 $monitor("%4b %4b %4b %4b", x, y, a, b);
 
 x = 1'b0; y = 1'b0;  // x = 1'b0 = 0 - y = 1'b0 = 0
 #1 x = 1'b0; y = 1'b1; // x = 0 - y = 1
 #1 x = 1'b1; y = 1'b0; // x = 1 - y = 0
 #1 x = 1'b1; y = 1'b1; // x = 1 - y = 1
 end
endmodule // test_f5