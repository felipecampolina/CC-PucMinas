//EX 04 - DANDO ERRO
// ------------------ 762732 - Felipe Capolina --------------


module dff ( output q, output qnot, input d, clk );
    reg q, qnot;
    always @( posedge clk )
    begin
     q <= d; qnot <= ~d;
    end
endmodule // dff

module anel (output a, b, c, d, e, f, input clock, _input);
    wire a_output, b_output, c_output, d_output, e_output, f_output, neg, a_or, not_a;
    
    assign a_or = _input | ~a_output;
    
    dff DFF1 (a_output, neg, b_output, clock);
    dff DFF2 (b_output, neg, c_output, clock);
    dff DFF3 (c_output, neg, d_output, clock);
    dff DFF4 (d_output, neg, e_output, clock);
    dff DFF5 (e_output, neg, f_output, clock);
    dff DFF6 (f_output, neg, a_or, clock);
    
    assign a = a_output;
    assign b = b_output;
    assign c = c_output;
    assign d = d_output;
    assign e = e_output;
    assign f = f_output;
endmodule //deslocamento_esq

module main;
    reg clock, _input;
    wire a, b, c, d, e, f;
    
    anel ANEL01 (a, b, c, d, e, f, clock, _input);
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

