//EX 05 - DANDO ERRO
// ------------------ 762732 - Felipe Capolina --------------


module dff(q,qnot,d,clk);
	output q,qnot;
	input d,clk;
	reg q,qnot;

	always @(posedge clk) begin
		q <= d; 
		qnot <= ~d;
	end

endmodule 

module serialParalelo(clk,data,a,b,c,d,e,f);
   input clk,data;
   output a,b,c,d,e,f;
   reg a,b,c,d,e,f,g;
   wire oanot,obnot,ocnot,odnot,oenot,ofnot;
   always @(posedge clk) begin
      dff FF1(a,oanot,data,clk);
      dff FF2(b,obnot,a,clk);
      dff FF3(c,ocnot,b,clk);
      dff FF4(d,odnot,c,clk);
      dff FF5(e,oenot,d,clk);
	  dff FF6(f,ofnot,e,clk);
   end
endmodule //serialParalelo

module main;
    reg clock, _input;
    wire a, b, c, d, e, f;
    
    serialParalelo SERIAL (a, b, c, d, e, f, clock, _input);
    initial
        begin
        $display("762732 - Felipe Campolina");
        $display("a b c d e f");
        clock = 1;
        _input = 0;
        
        #20 $finish;
        end
        
        always 
        #2 clock = ~clock;
        always @ (negedge clock)
        begin 
        $display ("%b %b %b %b %b %b", a, b, c, d, e, f);
        end
    endmodule

