// --------------------
// -- Finite Machine---
// --------------------
/*

*/

// constant definitions
`define found 1
`define notfound 0

// FSM 
module finite_machine ( y, x, clk, reset );
	output y;
	input x;
	input clk;
	input reset;
	reg y;
	
	parameter // state identifiers
	start = 2'b00,
	q1 = 2'b01,
	q3 = 2'b11,
	q2 = 2'b10;
	q4 = 2'b100; //não estou conseguindo representar os numeros 4 e 5 em binário
	final = 2'b101;
	
	reg [1:0] E1; // current state variables
	reg [1:0] E2; // next state logic output
	
	// next state logic
	always @( x or E1 )
	begin
	y = `notfound;
	case ( E1 )
	start:
		if ( x )
		E2 = q2;
		else
		E2 = q1;
	q1:
		if ( x )
		E2 = q3;
		else
		E2 = q1;
	q2:
		if ( x )
		E2 = q2;
		else
		E2 = q4;
	q3:
		if ( x )
		E2 = q2;
		else
		E2 = final;
	q4:
		if ( x )
		E2 = final;
		else
		E2 = q1;
	final:
		if ( x )
		begin
		E2 = start;
		y = `notfound;
		end
		else
		begin
		E2 = start;
		y = `found;
	end
	
	default: // undefined state
	E2 = 2'bxx;
	endcase
	end // always at signal or state changing
	
	// state variables
	always @( posedge clk or negedge reset )
	begin
	if ( reset )
	E1 = E2; // updates current state
	else
	E1 = 0; // reset
	end // always at signal changing
endmodule // mealy_1105


module Exemplo1106;
	reg clk, reset, x;
	wire m1;
	finite_machine fsm ( m1, x, clk, reset );
	
	initial
	begin
	$display ( "Time    X  FSM " );
	
	// initial values
	clk = 1;
	reset = 0;
	x = 0;
	
	// input signal changing
	#5 reset = 1;
	#10 x = 1;
	#10 x = 0;
	#10 x = 0;
	#20 x = 0;
	#10 x = 1;
	#10 x = 1;
	#10 x = 0;
	#10 x = 1;
	#30 $finish;
	end // initial
	
	always
	#5 clk = ~clk;
	always @( posedge clk )
	begin
	$display ( "%4d %4b %4b ", $time, x, m1 );
	
	end // always at positive edge clocking changing
endmodule // Exemplo_1105
