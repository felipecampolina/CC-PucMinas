


public class TP01Q15IsemJavaRecusrivo {//Inicio classe
	
	public static boolean eFim(String palavra){//Início eFim
		boolean resp = false;
		if((palavra.charAt(0) == 'F') && (palavra.charAt(1)== 'I') && (palavra.charAt(2)=='M')&& (palavra.length()==3) ) resp = true;//Confere se palavra == FIM
		return resp;
	}//Fim eFim
	 public static boolean vogais (String s, int i)
	 {
	  //definir dados
	    boolean resp;
	  //Conferir se valido
	   if (s == null || i < 0 || s.length () < 0)
		   resp = false;
	   else
	   {
	    //conferir se a String tem letras fora vogais
	     if (s.charAt (i) == 'a' ||
		 s.charAt (i) == 'e' ||
		 s.charAt (i) == 'i' ||
		 s.charAt (i) == 'o' ||
		 s.charAt (i) == 'u' ||
		 s.charAt (i) == 'A' ||
		 s.charAt (i) == 'E' ||
		 s.charAt (i) == 'I' ||
		 s.charAt (i) == 'O' ||
		 s.charAt (i) == 'U' )
		     resp = true;
	     else
		     resp = false;
	     if (resp && i +1 < s.length())
		     resp = (resp && vogais (s, i +1));
	   }//fim else
	   return resp;
	 }//fim vogais
	public static boolean apenasConsoanteRecusrivo(String palavra,int i) {//Início apenasConsoantes
		boolean resp = true;
		if(i<palavra.length()-1 && resp == true) {
			if(palavra.charAt(i)== 97||palavra.charAt(i)== 101||palavra.charAt(i)== 105||palavra.charAt(i)== 111||palavra.charAt(i)== 117||palavra.charAt(i)== 65||palavra.charAt(i)== 69||palavra.charAt(i)== 73||palavra.charAt(i)== 79||palavra.charAt(i)== 85)resp = false;//Confere se todos os caracteres são iguais a vogais
			if((palavra.charAt(i)>=48) && (palavra.charAt(i)<=57))resp=false;// Confere se não é um número
			apenasConsoanteRecusrivo(palavra,++i);
		}
		return resp;
	}//Fim apenasConsoante
	public static boolean eNumeroInteiroRecursivo(String palavra,int i ) {//Início eNumeroInteiro
		boolean resp = true;

		if(i<palavra.length()-1 && resp == true) {
			if((palavra.charAt(i)<48) || (palavra.charAt(i)>57))resp=false;// Confere se  é um número
			eNumeroInteiroRecursivo(palavra,++i);
		}
		return resp;
	}//Fim eNumeroInteiro
	public static boolean eNumeroRealRecursivo(String palavra , int i) {//Início eNumeroReal
		boolean resp = false;
		if(i<palavra.length()-1 && resp == false) {
			if((palavra.charAt(i)<48) || (palavra.charAt(i)>57))resp=false;// Confere se é um número
			if((palavra.charAt(i)==44) || (palavra.charAt(i)==46))resp=true;// Confere se tem ponto ou virgula
			eNumeroRealRecursivo(palavra,++i);
		}
		return resp;
	}//Fim eNumeroReal
	public static void main(String args[]) {//Início da main
		String palavra;
		palavra = MyIO.readLine();
		while(!eFim(palavra)){
			if(vogais(palavra,0))MyIO.print("SIM ");   // Confere se é vogal , consante , inteiro ou real e imprime SIM ou NAO.
			else MyIO.print("NAO ");
			if(apenasConsoanteRecusrivo(palavra,0))MyIO.print("SIM ");
			else MyIO.print("NAO ");
			if(eNumeroInteiroRecursivo(palavra,0))MyIO.print("SIM ");
			else MyIO.print("NAO ");
			if(eNumeroRealRecursivo(palavra,0))MyIO.println("SIM ");
			else MyIO.println("NAO ");
			palavra = MyIO.readLine();
		}
	}//Fim da main

}//Fim classe
