package TrabalhoPraticoI;

class TP01Q01PalíndromoemJava  {
	
	public static boolean eFim(String palavra){ 
		boolean resp = false;
		if((palavra.charAt(0) == 'F') && (palavra.charAt(1)== 'I') && (palavra.charAt(2)=='M')&& (palavra.length()==3) ) resp = true;//Confere se é FIM
		return resp;
	}//fim eFim
	public static String invertePalavra(String palavra) {
		String x = "";
		for(int i = palavra.length()-1; i>=0;i--) { // Rodar a palavra de trás para frente
			x= x + palavra.charAt(i); //Adicionando as ultimas letras nas primeiras posições
		}
		return x;
	}//fim invertePalavra
	
	public static void ePalindromo(String palavra) {
		String palavraInvertida;
		palavraInvertida = invertePalavra(palavra);
		boolean resp = true;
		int i= 0;
		while(resp == true && i<palavra.length()) {
			if(palavra.charAt(i) != palavraInvertida.charAt(i))resp = false; // Conferir se a palavra invertida é igual a palavra normal
			i++;
		}
		if(resp)System.out.println("SIM");
		else System.out.println("NAO");
	}//fim ePalindromo
	
	
	
	public static void main (String args[]) {
		String palavra[] = new String[1000]; // Criando um vetor de strings
		int entrada = 0;
		do {
			palavra[entrada] = MyIO.readLine(); // Lendo a String
			if(!eFim(palavra[entrada])) {
				ePalindromo(palavra[entrada]);
			}
			entrada++;
			
			
		}while(!eFim(palavra[entrada-1])); // Fazer o looping até que a palavra final seja FIM
	}//fim main

}
