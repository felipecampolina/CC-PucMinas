// ------------------------- 
// Exemplo_0804 - IGUALDADE 
// Nome: Felipe Campolina Soares de Paula
// Matricula: 762732 
// -------------------------

// -------------------------
//  comparador para igualdade
// ------------------------- 

module igualdade (output s, input a0, a1, a2, a3,a4, b0, b1, b2, b3,b4);
    
    wire s0, s1, s2, s3,s4;
    
    xnor XNOR0(s0, a0, b0);
    xnor XNOR1(s1, a1, b1);
    xnor XNOR2(s2, a2, b2);
    xnor XNOR3(s3, a3, b3);
    xnor XNOR4(s4, a4, b4);
    
    assign s = s0 | s1 | s2 | s3 | s4;
 
endmodule


module test_igualdade; 
// ------------------------- definir dados       
    reg  [4:0] a;       
    reg  [4:0] b;              
    wire resp; 
    integer i, j;
 
   igualdade I1(resp, a[0], a[1], a[2], a[3],a[4], b[0], b[1], b[2], b[3],b[4]); 
 
// ------------------------- parte principal  
    initial begin        
        
      
      $display("Exemplo0804 - Felipe Campolina Soares de Paula 762732");     
      $display("Test LU’s igualdade"); 
      
      $display("a    = b    resp");
      
      $monitor("%b  %b  %b",a, b, resp);
      
    

    for ( i = 0; i < 32; i = i + 1) begin
    for ( j = 0; j < 32; j = j + 1) begin
        #1 a=i; b=j;
    end
    end
   
    end 
 
endmodule // test_igualdade 