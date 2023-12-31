// --------------------------- 
//-- test clock generator (7) 
// Matricula: 762732 
// Nome: Felipe Campolina Soares de Paula
// -------------------------
 
 `include "clock.v" 
module pulse1 ( signal, clock );  
   input   clock;  
   output signal;  
   reg    signal; 
   
    always @ ( negedge clock )   
    begin         
         signal = 1'b1;
      #4 signal = 1'b0;
      #4 signal = 1'b1;
      #4 signal = 1'b0;  
      
      end 
endmodule // pulse 


module Exemplo_0907; 
 
   wire  clock;  
   clock clk ( clock ); 
 
   wire  p1; 
 
   pulse1   pls1   ( p1, clock );  
 
   initial 
   begin   
      $display("Exemplo0907 - Felipe Campolina Soares de Paula - 762732");
      $dumpfile ( "Exemplo0907.vcd" );   
      $dumpvars ( 1, clock, p1); 
 
      #480 $finish;  
   end 
   
 endmodule // Exemplo_0907
