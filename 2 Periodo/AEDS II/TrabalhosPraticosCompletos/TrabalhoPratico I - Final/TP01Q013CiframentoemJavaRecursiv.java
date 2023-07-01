package TrabalhoPraticoI;


class TP01Q013CiframentoemJavaRecursiv {

	public static boolean eFim(String palavra){//Inicio eFim
		boolean resp = false;
		if((palavra.charAt(0) == 'F') && (palavra.charAt(1)== 'I') && (palavra.charAt(2)=='M')&& (palavra.length()==3) ) resp = true;//Confere se a palavra = FIM
		return resp;
	}//Final eFim
	public static void ciframentoCesarRecusrivo(String palavra,int i) {//In�cio ciframentoCesar
		String palavraNova = "";
		int letraNovaNumero;
		char letraNovaLetra;
		if(i>=0) {
			letraNovaNumero =  (int)palavra.charAt(i) + 3; // Adiciona 3(key) ao valor ASCII da palvra - Codifica��o C�sar
			letraNovaLetra = (char) letraNovaNumero;// Transforma ASCII em letra
			palavraNova += letraNovaLetra;
			ciframentoCesarRecusrivo(palavra,i-1);
		}
		MyIO.print(palavraNova);//Imprime palavra nova
	}//Final ciframentoCesar
	public static void main(String args[]) {// In�cio da main
		
		String palavra = MyIO.readLine();
		while(!eFim(palavra)) {
			ciframentoCesarRecusrivo(palavra,palavra.length()-1);
			MyIO.println("");
			palavra = MyIO.readLine();
		}//Realiza looping at� palavra = FIM
	}//Final da main
}
