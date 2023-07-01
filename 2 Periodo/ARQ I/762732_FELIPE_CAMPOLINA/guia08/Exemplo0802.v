// ------------------------- 
// Exemplo_0802 - FULL SUBTRACTOR
// Nome: Felipe Campolina Soares de Paula
// Matricula: 762732 
// -------------------------

// ------------------------- 
//  subtractor 
// ------------------------- 
module halfsubtractor (output s1, s0, input a, b); 
 
    wire nota;
    // descrever por portas 
    
    xor  XOR1 ( s0, a, b ); 
    not NOT1  (nota, a);
    and AND1  ( s1, nota, b ); 
 
endmodule // subtractor

// ------------------------- 
//  subtractor 
// ------------------------- 
module fullsubtractor ( output s1, s0, input a, b, carryIn ); 
    
    wire s, c0, c1;
    
    halfsubtractor HS1(c0, s, a, b);
    halfsubtractor HS2(c1, s0, s, carryIn);
    or OR1 (s1, c0, c1);
 
endmodule // subtractor 

module subtractor (output s0, s1, s2, s3, s4,s5, input a0, a1, a2, a3,a4, b0, b1, b2, b3,b4);
    
    wire c0, c1, c2,c3;
    
    halfsubtractor HS1(c0, s0, a0, b0);
    fullsubtractor FS1(c1, s1, a1, b1, c0 ); 
    fullsubtractor FS2(c2, s2, a2, b2, c1 ); 
    fullsubtractor FS3(c3, s3, a3, b3, c2 ); 
    fullsubtractor FS4(s5, s4, a4, b4, c3 ); 

endmodule


module test_fullsubtractor; 
// ------------------------- definir dados       
    reg  [4:0] a;       
    reg  [4:0] b;              
    wire [5:0] s;
    integer i, j;
 
   subtractor S1(s[0], s[1], s[2], s[3], s[4],s[5], a[0], a[1], a[2], a[3],a[4], b[0], b[1], b[2], b[3],b[4]); 
 
// ------------------------- parte principal  
    initial begin        
        
      #1 b = 4'b0100; a = 4'b0001;
      
      $display("Exemplo0802 - Felipe Campolina Soares de Paula - 762732");     
      $display("Test ALU’s full subtractor"); 
      
      $display("a    - b    = s6   subtracao");
      
      $monitor("%b - %b = %b    %b%b%b%b%b",a, b, s[5],s[4], s[3], s[2], s[1], s[0]);
      for (i = 0; i < 32; i = i + 1) begin
      for (j = 0; j < 32; j = j + 1) begin
        #1 a=i; b=j;
      end
      end
 // projetar testes do subtrator completo 
      end 
 
endmodule // test_fullsubtractor