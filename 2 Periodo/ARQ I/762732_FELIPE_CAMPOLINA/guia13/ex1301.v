// -------------------- 
// ---Asynchronous counter(down)--------- 
// Matricula: 762732 
// Nome: Felipe Campolina
// -------------------- 
// 

module jkff ( output q, output qnot, input j, input k, input clk, input preset, input clear); 
  reg    q, qnot; 

   always @( posedge clk  or preset or clear ) 
   begin   
      if ( clear )      
      begin 
         q <= 0; 
         qnot <= 1; 
      end   
      else     
         if ( preset )  
         begin 
            q <= 1; qnot <= 0;
         end     
         else       
            if ( j & ~k ) 
            begin 
               q <= 1; 
               qnot <= 0; 
            end       
            else        
               if ( ~j & k )
               begin 
                  q <= 0; 
                  qnot <= 1; 
               end        
               else         
                  if ( j & k )                 
                  begin 
                     q <= ~q; 
                     qnot <= ~qnot; 
                   end 
end 
 
endmodule // jkff 

module contador(output [5:0] s, input pulse, reset);
   wire[5:0]q;
   
   jkff JK1( q[0], s[0], 1'b1, 1'b1, pulse, 1'b0, reset);
   jkff JK2( q[1], s[1], 1'b1, 1'b1, s[0], 1'b0, reset);
   jkff JK3( q[2], s[2], 1'b1, 1'b1, s[1], 1'b0, reset);
   jkff JK4( q[3], s[3], 1'b1, 1'b1, s[2], 1'b0, reset);
   jkff JK5( q[4], s[4], 1'b1, 1'b1, s[3], 1'b0, reset);
   jkff JK6( q[5], s[5], 1'b1, 1'b1, s[4], 1'b0, reset);
endmodule

module exemplo1301;  
   reg  pulse, reset;
   reg   x;  
   wire[5:0]  s;
   
   contador C1( s, pulse, reset);
 
   initial   
      begin    
      $display ( "Asynchronous counters - Down " ); 
      $display ( "Exemplo1301 - Felipe Campolina - 762732" );
      $display ( "time     out" );
 
   // initial values  
      pulse = 0;              
      reset = 1;       
      #5 reset = 0; 
      #340 $finish;  
      end // initial 
 
   always   
      #5 pulse = ~pulse; 
 
   always @( negedge pulse )   
      begin    
         $display ( "%5d    %5b ", $time, s);   
      end // always at positive edge clocking changing 
 
endmodule