package copiaArquivo;
import copiaArquivo.Arq;
import java.util.Scanner;


public class CopiaArquivo {
	
	public static void escreveArquivo() {
		Arq.openWrite("arquivo1.txt");
		Arq.println(1);
		Arq.println(2);
		Arq.println(3);
		Arq.println(4);
		Arq.close();
	}
	public static void leCopiaArquivo() {
		int x;
		
		Arq.openRead("arquivo1.txt");
		while(Arq.hasNext()) {
		x = Arq.readInt();
		Arq.openWrite("arquivo2.txt");
		Arq.println(x);
		Arq.println(4);
		System.out.println("teste");
		
		}
	}
	
	public static void main(String args[]) {
		Arq Arq = new Arq();
		escreveArquivo();
		leCopiaArquivo();
		
	}
}
