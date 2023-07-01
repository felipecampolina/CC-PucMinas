// -------------------- 
// ---Decade counter(down)--------- 
// Matricula: 762732 
// Nome: Felipe campolina
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

module contador(output [4:0] s, input pulse, reset);
   wire[4:0]q;
   wire na = (q[0]&~q[1]&q[2]&~q[3]&q[4]);
   wire r = reset | na ;
   
   jkff JK1( s[0], q[0], 1'b1, 1'b1, pulse, r, 1'b0);
   jkff JK2( s[1], q[1], 1'b1, 1'b1, s[0], r, 1'b0);
   jkff JK3( s[2], q[2], 1'b1, 1'b1, s[1], r, 1'b0);
   jkff JK4( s[3], q[3], 1'b1, 1'b1, s[2], r, 1'b0);
   jkff JK5( s[4], q[4], 1'b1, 1'b1, s[3], r, 1'b0);
endmodule

module exemplo1304;  
   reg  pulse, reset;
   reg   x;  
   wire[4:0]  s;
   
   contador C1( s, pulse, reset);
 
   initial   
      begin    
      $display ( "Asynchronous counters - Down " ); 
      $display ( "Exemplo1304 - Felipe Campolina 762732" );
      $display ( "time     out" );
 
   // initial values  
      pulse = 0;              
      reset = 1;       
      #5 reset = 0; 
      #360 $finish;  
      end // initial 
 
   always   
      #5 pulse = ~pulse; 
 
   always @( negedge pulse )   
      begin    
         $display ( "%4d    %4b ", $time, s);   
      end // always at positive edge clocking changing 
 
endmodule