package ex1a4;
import ex1a4.Arq;
import java.util.Scanner;
import java.lang.StringBuilder;

public class ex1a4 {
	
	public static void arquivoFrase() {
		String x, y;
		System.out.println("Nome do arquivo: ");
		Scanner scanner = new Scanner(System.in);
		x = scanner.nextLine()+".txt";
		Arq.openWrite(x);
		System.out.println("Escrava uma frase: ");
		y = scanner.nextLine();
		Arq.println(y);
		Arq.close();
		
	}
	public static void arquivoConteudo() {
		String x,y;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Nome do arquivo para ser lido: ");
		x = scanner.nextLine();
		Arq.openRead(x);
		y = Arq.readAll();
		System.out.println(y);
		
		
	}
	public static void arquivoConteudoMaiscula() {
		String x,y;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Nome do arquivo para ser lido: ");
		x = scanner.nextLine();
		Arq.openRead(x);
		y = Arq.readAll();
		System.out.println(y.toUpperCase());
		
		
	}
	public static void copiaPrimeiroUltimo() {
		String primeiro,ultimo,conteudo;
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Nome do arquivo 1: ");
		primeiro = scanner.nextLine();
		System.out.println("Nome do arquivo 2: ");
		ultimo = scanner.nextLine();
		Arq.openRead(primeiro);
		conteudo = Arq.readAll();
		Arq.close();
		Arq.openWrite(ultimo);
		Arq.println(conteudo);
		Arq.close();
		
	}
	public static void copiaPrimeiroUltimoMaiscula() {
		String primeiro,ultimo,conteudo;
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Nome do arquivo 1: ");
		primeiro = scanner.nextLine();
		System.out.println("Nome do arquivo 2: ");
		ultimo = scanner.nextLine();
		Arq.openRead(primeiro);
		conteudo = Arq.readAll();
		Arq.close();
		Arq.openWrite(ultimo);
		Arq.println(conteudo.toUpperCase());
		Arq.close();
		
	}
	public static void copiaPrimeiroUltimoInvertido() {
		String primeiro,ultimo,conteudo,invertido = "";
		Scanner scanner = new Scanner(System.in);
		System.out.println("Nome do arquivo 1: ");
		primeiro = scanner.nextLine();
		System.out.println("Nome do arquivo 2: ");
		ultimo = scanner.nextLine();
		Arq.openRead(primeiro);
		conteudo = Arq.readAll();
		Arq.close();
		invertido = "";
		for(int i = conteudo.length(); i>0;i--) {
			invertido += conteudo.charAt(i-1);
			
		}
		Arq.openWrite(ultimo);
		Arq.println(invertido);
		Arq.close();
		
	}
	public static void copiaCiframentoCesar() {
		String primeiro, conteudo;
		int ascii, k = 3;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Nome do arquivo que voce deseja criptografar: ");
		primeiro = scanner.nextLine();
		Arq.openRead(primeiro);
		conteudo = Arq.readAll();
		for(int i = 0; i< conteudo.length(); i++) {
			ascii = (int)conteudo.charAt(i) + k;
			System.out.println((char)ascii);
			
		}
		
		
	}
	public static void descobrirCiframentoCesar() {
		String primeiro, conteudo;
		int ascii, k = 3;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Nome do arquivo que voce deseja descriptografar: ");
		primeiro = scanner.nextLine();
		Arq.openRead(primeiro);
		conteudo = Arq.readAll();
		for(int i = 0; i< conteudo.length(); i++) {
			ascii = (int)conteudo.charAt(i) - k;
			System.out.println((char)ascii);
			
		}
		
		
	}
	
	
	public static void main(String args[]) {
		Arq Arq = new Arq();
		//arquivoFrase(); // ex1
		//arquivoConteudo(); // ex2
		//arquivoConteudoMaiscula();  //ex3
		//copiaPrimeiroUltimo(); //ex4
		//copiaPrimeiroUltimoMaiscula(); //ex5
		//copiaPrimeiroUltimoInvertido();
		//copiaCiframentoCesar();
		descobrirCiframentoCesar();
	}

}
