
import java.util.Random;

public class TP01Q04AlteracaoAleatoriaemJava {//Início da classe
	public static boolean eFim(String palavra){//Início eFim
		boolean resp = false;
		if((palavra.charAt(0) == 'F') && (palavra.charAt(1)== 'I') && (palavra.charAt(2)=='M')&& (palavra.length()==3) ) resp = true;//Confere se palavra == FIM
		return resp;
	}//Fim eFIm
	public static void alteracaoAleatoria(String palavra){//Início alteracaoAleatoria
		Random gerador = new Random();
		gerador.setSeed(4);//Semente
		char c1, c2;
		c1 = (char)('a'+ (Math.abs(gerador.nextInt())%26));//Gera palavra aleatória
		c2 = (char)('a'+ (Math.abs(gerador.nextInt())%26));//Gera palavra aleatória
		String palavraNova = "";
		for(int i = 0 ; i<palavra.length(); i++) {
			if(palavra.charAt(i)==c1)palavraNova +=c2; //Substitue caracter aleatorio da palavra
			else palavraNova+=palavra.charAt(i); // Adiciona caracter normal na palavra
		}
		MyIO.println(palavraNova);//Imprime palavra


	}//Final alteracaoAleatoria
	public static void main(String args[]) {//Início main
		
		String palavra = MyIO.readLine();
		while(!eFim(palavra)) {
			alteracaoAleatoria(palavra);
			palavra = MyIO.readLine();
		} 
	}//Final main
}//Fim classe
