
// --------------------------- 
//-- test clock generator (5) 
// Matricula: 762732 
// Nome: Felipe Campolina Soares de Paula
// -------------------------
 
 `include "clock.v" 

module pulse1 ( signal, clock );
   input   clock;
   output signal;
   reg    signal;
  
    always @ ( clock )  
    begin        
     
        #3 signal = 1'b1;  
        #3 signal = 1'b0;           
      
    end
endmodule // pulse


module Exemplo_0905; 
 
   wire  clock;  
   clock clk ( clock ); 
 
   wire  p1; 
 
   pulse1   pls1   ( p1, clock );  

   initial 
   begin   
       $display("Exemplo0905 - Felipe Campolina Soares de Paula- 762732");
      $dumpfile ( "Exemplo0905.vcd" );   
      $dumpvars ( 1, clock, p1 ); 
 
      #480 $finish;  
   end 
   
 endmodule // Exemplo_0905
