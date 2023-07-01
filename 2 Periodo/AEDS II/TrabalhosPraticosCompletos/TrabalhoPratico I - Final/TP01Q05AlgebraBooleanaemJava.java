package TrabalhoPraticoI;
import java.lang.String;

public class TP01Q05AlgebraBooleanaemJava {
	public static void leEntrada(String frase) {
		String entrada;
		int valor = 0,valor2 = 0 , valor3 = 0;
		int numeroEntrada = frase.charAt(0)-48;
		if (numeroEntrada ==2 ){
			valor = frase.charAt(2)-48;
			valor2 = frase.charAt(4)-48;
			tranformaSentenca(frase,valor,valor2);
			
		}
		else if (numeroEntrada ==3 ){
			valor = frase.charAt(2)-48;
			valor2 = frase.charAt(4)-48;
			valor3 = frase.charAt(6)-48;
			System.out.println(valor);
			System.out.println(valor2);
			System.out.println(valor3);
		}
	}
	public static void tranformaSentenca(String frase,int valor , int valor2) {
		String fraseNova;
		
		for(int i = frase.length()-1 ; i>=0 ;i--) {
			if(frase.charAt(i)=='(') { // Achar o parentese fechando 
				System.out.println("i="+  i);
				if(frase.charAt(i-1)=='d') { // AND
					if((valor==1) && (valor2==1)) { // Único caso que resultará em TRUE
						int j = i-3;
						fraseNova = "1";
						System.out.println("J="+  j);
						System.out.println("i teste="+  i);
						frase = frase.substring(6, j) + fraseNova +  ")";
						System.out.println(frase);
						for(i = frase.length()-1; i>=0 ; i--) {
							if(frase.charAt(i)=='(')break;
						}
						//USAR SPLIT
						
					}
					else { // Todos casos que resultará em FALSE //2 1 1 not(and(A , B))
						fraseNova = "0";
						int j = i-3;
						frase = frase.substring(6, j) + fraseNova +  ")";
						System.out.println(frase);
						for(i = frase.length()-1; i>=0 ; i--) {
							if(frase.charAt(i)=='(')break;
						}
					}
				}
				System.out.println("iTESTE2.0="+  i);
				if(frase.charAt(i-1)=='t') {//NOT
					int j = i-3;
					fraseNova = "";
					System.out.println("ENTREOU NO NOT " + i);
					System.out.println("Iteste 2 = " + i);
					if(frase.charAt(i+1) =='1')fraseNova = "0";
					if(frase.charAt(i+1) =='0')fraseNova = "1";
					frase = frase.substring(j,j+6) + fraseNova;
					System.out.println(frase);
					for(i = frase.length()-1; i>=0 ; i--) {
						if(frase.charAt(i)=='(')break;
						else {
							i= frase.length();
							break;
						}
					}
						
				}
				if(frase.charAt(i-1)== 'r') {//or
					int j = i-3;
					if((valor==0) && (valor2==0)) { // Único caso que resultará em FALSE
						System.out.println("I = " + i);
						fraseNova = "0";
						frase = frase.substring(j,j+10) + fraseNova;
						System.out.println(frase);
						for(i = frase.length()-1; i>=0 ; i--) {
							if(frase.charAt(i)=='(')break;
							else {
								i= frase.length();
								break;
							}
						}
						
						
					}
					else { // Todos casos que resultará em TRUE
						fraseNova = "1";
						j = i-3;
						frase = frase.substring(0,0) + fraseNova;
						System.out.println(frase);
						for(i = frase.length()-1; i>=0 ; i--) {
							if(frase.charAt(i)=='(')break;
							else {
								i= frase.length();
								break;
							}
						}
						
					}
					
				}
			}
		}
	}
	public static void main(String args[] ) {
		String frase = MyIO.readLine();
		leEntrada(frase);
	}
}
