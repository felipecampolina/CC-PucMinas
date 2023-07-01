package TrabalhoPraticoI;

class TP01Q11PalíndromoemJavaRecursiv  {
	
	public static boolean eFim(String palavra){ 
		boolean resp = false;
		if((palavra.charAt(0) == 'F') && (palavra.charAt(1)== 'I') && (palavra.charAt(2)=='M')&& (palavra.length()==3) ) resp = true;//Confere se é FIM
		return resp;
	}//fim eFim
	
	public static boolean ePalindromo(String palavra,int i) {//Inicio ePalindromo
		boolean valido;
        if(i >= (palavra.length()/2)){
            valido = true;
        }
        else if (palavra.charAt(i) != palavra.charAt(palavra.length() - 1 - i)){ 
            valido = false;
        }
        else {
            valido = ePalindromo(palavra, i+1);
        }
        return valido;
		
		
	}//fim ePalindromo
	
	
	
	public static void main (String args[]) {
		String palavra[] = new String[10000];
		int entrada = 0;
		palavra[entrada] = MyIO.readLine();
		while(!eFim(palavra[entrada])) {
				if(ePalindromo(palavra[entrada],0))MyIO.println("SIM");
				else MyIO.println("NAO");
				entrada++;
				palavra[entrada] = MyIO.readLine();
			}
	}//fim main

}
