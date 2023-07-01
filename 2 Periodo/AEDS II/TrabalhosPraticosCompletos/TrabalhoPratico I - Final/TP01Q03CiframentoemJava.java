package TrabalhoPraticoI;
class TP01Q03CiframentoemJava {

	public static boolean eFim(String palavra){//Inicio eFim
		boolean resp = false;
		if((palavra.charAt(0) == 'F') && (palavra.charAt(1)== 'I') && (palavra.charAt(2)=='M')&& (palavra.length()==3) ) resp = true;//Confere se a palavra = FIM
		return resp;
	}//Final eFim
	public static void ciframentoCesar(String palavra) {//Início ciframentoCesar
		String palavraNova = "";
		int letraNovaNumero;
		char letraNovaLetra;
		for(int i = 0 ; i< palavra.length(); i++) {
			letraNovaNumero =  (int)palavra.charAt(i) + 3; // Adiciona 3(key) ao valor ASCII da palvra - Codificação César
			letraNovaLetra = (char) letraNovaNumero;// Transforma ASCII em letra
			palavraNova += letraNovaLetra;
			
		}
		MyIO.println(palavraNova);//Imprime palavra nova
	}//Final ciframentoCesar
	public static void main(String args[]) {// Início da main
		
		String palavra = MyIO.readLine();
		while(!eFim(palavra)) {
			ciframentoCesar(palavra);
			palavra = MyIO.readLine();
		}//Realiza looping até palavra = FIM
	}//Final da main
}
