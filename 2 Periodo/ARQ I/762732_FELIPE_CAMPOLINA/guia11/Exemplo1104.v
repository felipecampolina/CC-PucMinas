// ------- FSM -------
// Matricula: 762732 
// Nome: Felipe Campolina Soares de Paula
// -------------------- 
// 

// constant definitions 
`define found    1 
`define notfound 0

// FSM 
module fsm ( y, x, clk, reset );  
   output y;  
   input  x;  
   input  clk;  
   input  reset; 
   
   reg    y; 
 
   parameter      // state identifiers
      start   = 3'b000,    
      id1     = 3'b001,    
      id01    = 3'b011,   
      id11    = 3'b010,
      idfinal = 3'bxxx;
 
      reg [2:0] E1; // current state variables    
      reg [2:0] E2; // next state logic output 
      
      // next state logic    
      always @( x or E1 )     
         begin      
            y = `notfound;      
            case ( E1 )       
               start:         
                  if ( x )         
                     E2 = id1; 
                  else          
                     E2 = start;       
               id1:         
                  if ( x )          
                     E2 = start;         
                  else          
                     E2 = id01;          
               id01: 
                  if ( x )          
                     begin            
                        E2 =  id11;          
                     end         
                     else          
                        begin            
                           E2 =  start;            
                        end
                id11:
                if ( x )          
                     begin            
                        E2 =  idfinal;          
                        
                     end         
                     else          
                        begin            
                           E2 =  start;            
                           y  = `notfound;          
                        end
                idfinal:
                     if ( x )          
                     begin            
                        E2 =  id1;          
                        y  = `found;
                     end         
                     else          
                        begin            
                           E2 =  start;            
                           y  = `notfound;          
                        end
                default:   // undefined state   
                   E2 =  2'bxx;      
   endcase   
end // always at signal or state changing 

// state variables    
   always @( posedge clk or negedge reset )     
      begin      
         if ( reset )       
            E1 = E2;    // updates current state      
         else       
            E1 = 0;     // reset 
         end // always at signal changing 
 
endmodule // fsm
 
module exemplo1104;  
   reg   clk, reset, x;  
   wire  m1;
   
   fsm  FSM1  ( m1, x, clk, reset );  
 
   initial   
      begin    
      $display ( "Time     X   Moore" ); 
      $display ( "Exemplo1104 - Felipe Campolina Soares de Paula - 762732" );
      $dumpfile ( "Exemplo_1104.vcd" );   
      $dumpvars ( 1, clk, x, m1 );
 
   // initial values        
      clk   = 1;        
      reset = 0;        
      x     = 0; 
   // input signal changing   
      #10  reset = 1;   
      #10 x     = 1;   
      #10 x     = 0;   
      #10 x     = 1;   
      #10 x     = 1;   
      #10 x     = 1;   
      #10 x     = 0;   
      #10 x     = 1;
      #10 x     = 1; 
      #10 x     = 1;   
      #10 x     = 0;   
      #10 x     = 1;   //teste com 0111
      #10 x     = 1; 
 
      #30 $finish;  
      end // initial 
 
   always   
      #5 clk = ~clk; 
 
   always @( posedge clk )   
      begin    
         $display ( "%4d  %4b  %4b", $time, x, m1 );   
      end // always at positive edge clocking changing 
 
endmodule // Exemplo_1105 