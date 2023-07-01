// -------------------- 
// ---Decade counter(up - fft)--------- 
// Matricula: 762732 
// Nome: Felipe Campolina
// -------------------- 
// 

module tff ( output q, output qnot,   input    t, input    clk,   input    preset, input clear ); 
 
reg q, qnot; 
 
always @( posedge clk or preset or clear) 
begin  
    if ( clear )   
    begin    
        q <= 0;  
        qnot <= 1;   
    end  
    else   
        if ( preset )    
        begin   
            q <= 1;  
            qnot <= 0;   
        end   
        else    
        begin       
            if ( t ) 
            begin 
                q <= ~q; 
                qnot <= ~qnot; 
            end    
        end 
    end 
 
endmodule // tff 
 

module contador(output [4:0] s, input pulse, reset);
   wire[4:0]q;
   wire na = (~s[0]&s[1]&~s[2]&s[3]&~s[4]);
   wire r = reset | na ;
   
   tff T1( s[0], q[0], 1'b1, pulse, 1'b0, r);
   tff T2( s[1], q[1], 1'b1, q[0], 1'b0, r);
   tff T3( s[2], q[2], 1'b1, q[1], 1'b0, r);
   tff T4( s[3], q[3], 1'b1, q[2], 1'b0, r);
   tff T5( s[4], q[4], 1'b1, q[3], 1'b0, r);
endmodule

module exemplo1305;  
   reg  pulse, reset;
   reg   x;  
   wire[4:0]  s;
   
   contador C1( s, pulse, reset);
 
   initial   
      begin    
      $display ( "Asynchronous counters - Up FF T " ); 
      $display ( "Exemplo1305 - Felipe campolina - 762732" );
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