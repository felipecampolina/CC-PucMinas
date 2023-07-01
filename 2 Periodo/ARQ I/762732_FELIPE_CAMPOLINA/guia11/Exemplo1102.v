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
      id11    = 3'b011,  
      id0     = 3'b110,    
      id00    = 3'b100, 
      id10    = 3'b101, 
      idfinal = 2'bxx;
 
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
                     E2 = id0;      
               id1:         
                  if ( x )          
                     E2 = id11;         
                  else          
                     E2 = id0;          
               id11: 
                  if ( x )          
                     begin            
                        E2 =  idfinal;          
                        y  = `found;
                     end         
                     else          
                        begin            
                           E2 =  id0;            
                           y  = `notfound;          
                        end  
                id0:         
                  if ( x )          
                     E2 = id1;         
                  else          
                     E2 = id00;          
                id00: 
                  if ( x )          
                     begin            
                        E2 =  id1;            
                        y  = `notfound; 
                     end         
                     else          
                        begin            
                           E2 =  id10;            
                           y  = `found;          
                        end  
                id10:
                if ( x )          
                     begin            
                        E2 =  id1;            
                        y  = `notfound; 
                     end         
                     else          
                        begin            
                           E2 =  idfinal;            
                           y  = `found;          
                        end 
                
                idfinal:
                     y = 1'bx;
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
 
module exemplo1106;  
   reg   clk, reset, x;  
   wire  m1;
   
   fsm  FMS1  ( m1, x, clk, reset );  
 
   initial   
      begin    
      $display ( "Time     X   FMS" ); 
      $display ( "Exemplo1102 - Felipe Campolina Soares de Paula - 762732" );
      $dumpfile ( "Exemplo_1102.vcd" );   
      $dumpvars ( 1, clk, x, m1 );
 
   // initial values        
      clk   = 1;        
      reset = 0;        
      x     = 0; 
   // input signal changing   
      #5  reset = 1;   
      #10 x     = 0;   
      #10 x     = 1;   
      #10 x     = 0;   
      #10 x     = 1;   
      #10 x     = 1;   
      #10 x     = 1;   
      #10 x     = 0;   
      #10 x     = 1; 
      #10 x     = 1; 
 
      #30 $finish;  
      end // initial 
 
   always   
      #5 clk = ~clk; 
 
   always @( posedge clk )   
      begin    
         $display ( "%4d  %4b  %4b", $time, x, m1 );   
      end // always at positive edge clocking changing 
 
endmodule // Exemplo_1103