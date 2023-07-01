
public class TP01Q06IsemJava {//Inicio classe
	
	public static boolean eFim(String palavra){//Início eFim
		boolean resp = false;
		if((palavra.charAt(0) == 'F') && (palavra.charAt(1)== 'I') && (palavra.charAt(2)=='M')&& (palavra.length()==3) ) resp = true;//Confere se palavra == FIM
		return resp;
	}//Fim eFim
	public static boolean apenasVogal(String palavra) {//Início apenasVogal
		boolean resp = true;
		int i =0;
		while(i<palavra.length()-1 && resp == true) {
			if(palavra.charAt(i)!= 'a'&&palavra.charAt(i)!= 'e'&&palavra.charAt(i)!= 'i'&&palavra.charAt(i)!= 'o'&&palavra.charAt(i)!= 'u'&&palavra.charAt(i)!= 'A'&&palavra.charAt(i)!= 'E'&&palavra.charAt(i)!= 'I'&&palavra.charAt(i)!= 'O'&&palavra.charAt(i)!= 'U'&&palavra.charAt(i)!= ' ')resp = false;//Confere se todos os caracteres são diferentes de vogais
			if((palavra.charAt(i)>=48) && (palavra.charAt(i)<=57))resp=false; // Confere se não é um número
			i++;
		}
		return resp;
	}//Fim apenasVogal
	public static boolean apenasConsoante(String palavra) {//Início apenasConsoantes
		boolean resp = true;
		int i =0;
		while(i<palavra.length()-1 && resp == true) {
			if(palavra.charAt(i)== 97||palavra.charAt(i)== 101||palavra.charAt(i)== 105||palavra.charAt(i)== 111||palavra.charAt(i)== 117||palavra.charAt(i)== 65||palavra.charAt(i)== 69||palavra.charAt(i)== 73||palavra.charAt(i)== 79||palavra.charAt(i)== 85)resp = false;//Confere se todos os caracteres são iguais a vogais
			if((palavra.charAt(i)>=48) && (palavra.charAt(i)<=57))resp=false;// Confere se não é um número
			i++;
		}
		return resp;
	}//Fim apenasConsoante
	public static boolean eNumeroInteiro(String palavra) {//Início eNumeroInteiro
		boolean resp = true;
		int i =0;
		while(i<palavra.length()-1 && resp == true) {
			if((palavra.charAt(i)<48) || (palavra.charAt(i)>57))resp=false;// Confere se  é um número
			i++;
		}
		return resp;
	}//Fim eNumeroInteiro
	public static boolean eNumeroReal(String palavra) {//Início eNumeroReal
		boolean resp = false;
		int i =0;
		while(i<palavra.length() && resp == false) {
			if((palavra.charAt(i)<48) || (palavra.charAt(i)>57))resp=false;// Confere se é um número
			if((palavra.charAt(i)==44) || (palavra.charAt(i)==46))resp=true;// Confere se tem ponto ou virgula
			i++;
		}
		return resp;
	}//Fim eNumeroReal
	public static void main(String args[]) {//Início da main
		String palavra;
		palavra = MyIO.readLine();
		while(!eFim(palavra)){
			if(apenasVogal(palavra))MyIO.print("SIM ");   // Confere se é vogal , consante , inteiro ou real e imprime SIM ou NAO.
			else MyIO.print("NAO ");
			if(apenasConsoante(palavra))MyIO.print("SIM ");
			else MyIO.print("NAO ");
			if(eNumeroInteiro(palavra))MyIO.print("SIM ");
			else MyIO.print("NAO ");
			if(eNumeroReal(palavra))MyIO.println("SIM ");
			else MyIO.println("NAO ");
			palavra = MyIO.readLine();
		}
	}//Fim da main

}//Fim classe
