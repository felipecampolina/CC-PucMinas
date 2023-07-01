package exercicios_introducaoaojava;

import java.util.Scanner;
import java.lang.Math;
public class Exercicios {
	
	public static void identificadorTriangulo() {
		Scanner scanner = new Scanner(System.in);
		float x , y , z;
		System.out.println("X: ");
		x = scanner.nextFloat();
		System.out.println("Y: ");
		y = scanner.nextFloat();
		System.out.println("Z: ");
		z = scanner.nextFloat();
		
		if(x == z) {
			if(x==y)System.out.println("Equilatero -- Todos lados iguais");
			else System.out.println("Isoceles -- Apenas dois lado igual");
			
		}else {
			if(x!=y)System.out.println("Escaleno -- Tudo diferente");
			else System.out.println("Isoceles -- Apenas dois lado igual");
		}
		
	}
	public static void identificadorMaiorMenor(){
		Scanner scanner = new Scanner(System.in);
		float x , y , z, maior = 0,menor = 0;
		System.out.println("X: ");
		x = scanner.nextFloat();
		System.out.println("Y: ");
		y = scanner.nextFloat();
		System.out.println("Z: ");
		z = scanner.nextFloat();
		if((x>=y) && (x>=z))maior = x;
		if((z>=y) && (z>=z))maior = z;
		if((y>=z) && (y>=x))maior = y;
		if((x<=y) && (x<=z))menor = x;
		if((z<=y) && (z<=x))menor = z;
		if((y<=z) && (y<=z))menor = y;
		System.out.println(maior);
		System.out.println(menor);
		
	
		
		
	}
	public static void identificadorMaiorMenorDezNumeros() {
		Scanner scanner = new Scanner(System.in);
		int x =0, maior =0;
		for(int i = 0; i<10 ; i++) {
			System.out.println("Digite um numero");
			x = scanner.nextInt();
			if(x > maior)maior = x;
			
		}
		System.out.println(maior);
	}
	public static void exercicio4() {
		Scanner scanner = new Scanner(System.in);
		int x , y, soma ;
		System.out.println("X: ");
		x = scanner.nextInt();
		System.out.println("Y: ");
		y = scanner.nextInt();
		if((x>45) || (y>45)) {
			soma = x + y;
		}else {
			if((x>20) || (y>20)){
				if(x>y)soma = x -y;
				else soma = y-x;
			}
			if((x<=10)&&(y!=0)||(y<=10)&&(x!=0))soma = x/y;
			else System.out.println("Felipe");
		}
		
	}
	public static void resultadoPartidaFutebol() {
		Scanner scanner = new Scanner(System.in);
		int x , y, soma ;
		System.out.println("GOLS da equipe X: ");
		x = scanner.nextInt();
		System.out.println("GOLS da equipe Y: ");
		y = scanner.nextInt();
		if(x > y)System.out.println("Equipe X GANHOU");
		else if(x==y)System.out.println("O jogo EMPATOU");
		else System.out.println("Equipe Y GANHOU");
	}
	public static boolean emprestimoBancoZe() {
		Scanner scanner = new Scanner(System.in);
		float x , y;
		boolean resultado = false;
		
		System.out.println("Salario: ");
		x = scanner.nextInt();
		System.out.println("Prestaçao: ");
		y = scanner.nextInt();
		if(y <= (x*0.4)) {
			resultado = true;
			System.out.println("Aprovado!");
		}
		return resultado;
		
	}
	public static void calculaRaizLog() {
		Scanner scanner = new Scanner(System.in);
		float x , y,max,min;
		
		System.out.println("X: ");
		x = scanner.nextFloat();
		System.out.println("Y: ");
		y = scanner.nextFloat();
		max = Math.max(y, x);
		min = Math.min(y, x);
		double resultado1 = Math.cbrt(min);
		double resultado2 = Math.log(min);
		System.out.println(resultado1);
		System.out.println(resultado2);
		
		
	}
	public static void dezPrimeirosInteirosPositivos() {
		int i = 0;
		while(i<10) {
			System.out.println(i);
			i++;
		}
	}
	public static void nPrimeirosImpares() {
		int i = 0;
		Scanner scanner = new Scanner(System.in);
		int x;
		System.out.println("N: ");
		x = scanner.nextInt();
		while(i<x) {
			if(i%2 == 1)System.out.println(i);
			i++;
		}
		
	}
	public static void nPrimeirosNumerosSequencia() {
		int i = 1;
		int res = 1;
		Scanner scanner = new Scanner(System.in);
		int x;
		System.out.println("N: ");
		x = scanner.nextInt();
		while(i<=x) {
			if(i%2 == 1) {
				System.out.println(res);
				i++;
				res = res +4;
			}
			else {
				System.out.println(res);
				i++;
				res = res +7;
			}
		}
		
	}
	public static void sistemaNotaProva() {
		int i = 1;
		float media = 0;
		int alunosAbaixoMedia = 0 , alunosA = 0;
		Scanner scanner = new Scanner(System.in);
		float notaMax;
		System.out.println("NOTA MAXIMA: ");
		notaMax = scanner.nextFloat();
		float nota;
		while(i<=20) {
			do {	
				System.out.println(i + "- NOTA ALUNO: ");
				nota = scanner.nextFloat();
			}while((nota>notaMax)||(nota<0));
			if(nota > (notaMax*0.9))alunosA++;
			if(nota < (notaMax*0.6))alunosAbaixoMedia++;
			media += nota;
			i++;
			
			
		}
		media = media/20;
		System.out.println("Media: "+ media);
		System.out.println("Alunos A: "+ alunosA);
		System.out.println("Alunos abaixo media: "+ alunosAbaixoMedia);
		
		
		
	}
	public static void sequenciaFibonacci() {
		int x, i = 0,res = 1,aux,res2 = 1;;
		Scanner scanner = new Scanner(System.in);
		System.out.println("N: ");
		x = scanner.nextInt();
		System.out.println(res);
		System.out.println(res);
		while(i<(x-2)) {
			aux = res;
			res = res + res2;
			res2 = aux;
			System.out.println(res);
			i++;
		}
	}
	public static void descobrirMediaArray() {
		int x,numero,soma=0;
		Scanner scanner = new Scanner(System.in);
		System.out.println("N: ");
		x = scanner.nextInt();
		int array[] = new int[x];
		for(int i = 1; i<=x; i++) {
			System.out.println("Numero"+i+": ");
			numero = scanner.nextInt();
			array[i-1] = numero;
			soma +=array[i-1];
		}
		soma = soma/x;
		System.out.println("Media: " + soma);
		
	}
	public static void posicaoMenorElemento() {
		int x,numero,soma=0;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Tamanho array: ");
		x = scanner.nextInt();
		int array[] = new int[x];
		for(int i = 1; i<=x; i++) {
			System.out.println("Numero"+i+": ");
			numero = scanner.nextInt();
			array[i-1] = numero;
		}
		int menor = array[0];
		for(int z = 0; z<x ; z++) {
			if(array[z]<menor)menor = z;
		}
		System.out.println("Posicao do menor ="+menor);
	}
	public static void ordenaArray() {
		int array[] = new int[5];
		array[0] = 100;
		array[1] = 250;
		array[2] = 20;
		array[3] = 14;
		array[4] = 5;
		
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				if (array[i] < array[j]) {
					int temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
		}
		for(int x = 0 ; x<array.length;x++) {
			System.out.print(array[x]+ " ");
		}
		
		
	}
	public static void uniaoIntercessaoArray() {
		int array[] = new int[5];
		array[0] = 100;
		array[1] = 250;
		array[2] = 20;
		array[3] = 14;
		array[4] = 5;
		
		int array2[] = new int[5];
		array2[0] = 6;
		array2[1] = 250;
		array2[2] = 10;
		array2[3] = 100;
		array2[4] = 3;
		int uniao[] = new int[5];
		int intercessao[] = new int[5];
		
		for(int j = 0 ; j<array.length;j++) {
			for(int i = 0 ; i<array.length;i++) {
				if(array[i]==array2[j])uniao[i] = array[i];
				else intercessao[i] = array[i];
				
			}

		}
		for(int x = 0 ; x<array.length;x++) {
			System.out.println("UNIAO - "+ uniao[x]+ " ");
		}
		for(int x = 0 ; x<array.length;x++) {
			System.out.println("INTERCESSAO - "+ intercessao[x]+ " ");
		}
	}
	public static void mostraElemntosArray() {
		int array[] = new int[5];
		array[0] = 10;
		array[1] = 5;
		array[2] = 8;
		array[3] = 2;
		array[4] = 8;
		for(int i = 0; i<array.length; i++) {
			System.out.println(array[i]);
		}
	}
	
	public static void notaAlunos() {
		int x,numero,soma=0;
		Scanner scanner = new Scanner(System.in);
		x = 5;
		int menor = 100;
		int array[] = new int[x];
		for(int i = 1; i<=x; i++) {
			System.out.println("Aluno"+i+": ");
			numero = scanner.nextInt();
			array[i-1] = numero;
			soma +=array[i-1];
			if(array[i-1]<menor)menor=array[i-1];
			
		}
		System.out.println("Menor: " + menor);
		System.out.println("Soma: " + soma);
		soma = soma/x;
		System.out.println("Media: " + soma);
		
	}
	public static void somaCasal() {
		int x,numero,soma=0;
		Scanner scanner = new Scanner(System.in);
		System.out.println("N: ");
		x = scanner.nextInt();
		int array[] = new int[x];
		for(int i = 1; i<=x; i++) {
			System.out.println("Numero"+i+": ");
			numero = scanner.nextInt();
			array[i-1] = numero;
		}
		if(x %2 == 0) {
			for(int i = 0; i<x-1;i+=2) {
				soma = 0;
				soma = array[i] + array[i+1];
				System.out.println(soma);
			}
		}
		
	}
	public static void numeroParesDivisivelPorTres(){
		int x,numero,pares=0,div3 = 0;
		Scanner scanner = new Scanner(System.in);
		System.out.println("N: ");
		x = scanner.nextInt();
		int array[] = new int[x];
		for(int i = 1; i<=x; i++) {
			System.out.println("Numero"+i+": ");
			numero = scanner.nextInt();
			array[i-1] = numero;
		}
		for(int i = 0; i<x; i++) {
			if(array[i]%2==0)pares++;
			if(array[i]%3==0)div3++;
		}
		System.out.println("Pares: "+ pares);
		System.out.println("Div por 3: "+ div3);
		
	}
	public static void somaContrario() {
		int x,numero,soma=0;
		Scanner scanner = new Scanner(System.in);
		System.out.println("N: ");
		x = scanner.nextInt();
		int array[] = new int[x];
		for(int i = 1; i<=x; i++) {
			System.out.println("Numero"+i+": ");
			numero = scanner.nextInt();
			array[i-1] = numero;
		}
		if(x %2 == 0) {
			for(int i = 0; i<array.length/2;i++) {
				soma = 0;
				soma = array[i] + array[x-1];
				System.out.println(soma);
				x--;
			}
		}
		
	}
	public static void somaMultiplos3() {
		int x,numero,soma=0;
		Scanner scanner = new Scanner(System.in);
		System.out.println("N: ");
		x = scanner.nextInt();
		int array[] = new int[x];
		for(int i = 1; i<=x; i++) {
			System.out.println("Numero"+i+": ");
			numero = scanner.nextInt();
			array[i-1] = numero;
		}
			for(int i = 0; i<x;i++) {
				if(array[i]%3==0)soma+=array[i];
			}
			System.out.println(soma);
			
		
	}
	public static void mostraVetoresIntercalados() {
		int x,numero,soma=0;
		Scanner scanner = new Scanner(System.in);
		System.out.println("N: ");
		x = scanner.nextInt();
		int array[] = new int[x];
		int array2[] = new int[x];
		for(int i = 1; i<=x; i++) {
			System.out.println("Numero"+i+": ");
			numero = scanner.nextInt();
			array[i-1] = numero;
		}
		for(int i = 1; i<=x; i++) {
			System.out.println("Numero"+i+": ");
			numero = scanner.nextInt();
			array2[i-1] = numero;
		}
		for(int i = 0; i<x; i++) {
			System.out.println(array[i]);
			System.out.println(array2[i]);
			
		}
		
		
	}
	
	public static void escreveMatrizFormatoGrid() {
		int l , c,numero;
		Scanner scanner = new Scanner(System.in);
		System.out.println("L: ");
		l = scanner.nextInt();
		System.out.println("C: ");
		c = scanner.nextInt();
		int array[][] = new int[c][l];
		for(int i = 0 ; i<c ; i++) {
			for(int j = 0 ; j<l; j++) {
				System.out.println("A[ " + i + "]["+j+"]");
				numero = scanner.nextInt();
				array[i][j] = numero;
			}
		}
		for(int i = 0 ; i<c ; i++) {
			for(int j = 0 ; j<l; j++) {
				System.out.print(array[i][j]+ " ");
			}
			System.out.println();
		}
	}
	public static void escreveMatrizFormatoTransporta() {
		int l , c,numero;
		Scanner scanner = new Scanner(System.in);
		System.out.println("L: ");
		l = scanner.nextInt();
		System.out.println("C: ");
		c = scanner.nextInt();
		int array[][] = new int[c][l];
		for(int i = 0 ; i<c ; i++) {
			for(int j = 0 ; j<l; j++) {
				System.out.println("A[ " + i + "]["+j+"]");
				numero = scanner.nextInt();
				array[i][j] = numero;
			}
		}
		for(int i = 0 ; i<c ; i++) {
			for(int j = 0 ; j<l; j++) {
				System.out.print(array[j][i]+ " ");
			}
			System.out.println();
			
			
		}
	}
	public static void somaMatrz() {
		int l , c,numero;
		Scanner scanner = new Scanner(System.in);
		System.out.println("L: ");
		l = scanner.nextInt();
		System.out.println("C: ");
		c = scanner.nextInt();
		int array[][] = new int[c][l];
		int array2[][] = new int[c][l];
		int array3[][] = new int[c][l];
		for(int i = 0 ; i<c ; i++) {
			for(int j = 0 ; j<l; j++) {
				System.out.println("A[ " + i + "]["+j+"]");
				numero = scanner.nextInt();
				array[i][j] = numero;
			}
			
			
		}
		for(int i = 0 ; i<c ; i++) {
			for(int j = 0 ; j<l; j++) {
				System.out.println("A2[ " + i + "]["+j+"]");
				numero = scanner.nextInt();
				array2[i][j] = numero;
			}
			
			
		}
		for(int i = 0 ; i<c ; i++) {
			for(int j = 0 ; j<l; j++) {
				array3[i][j] = array[i][j] + array2[i][j];
				
			}
			
			
		}
		for(int i = 0 ; i<c ; i++) {
			for(int j = 0 ; j<l; j++) {
				System.out.print(array3[i][j]+ " ");
			}
			System.out.println();
			
			
		}
	}
	public static void diagonalPrimariaSecundaria() { 
	int l ,numero;
	Scanner scanner = new Scanner(System.in);
	System.out.println("L e C: ");
	l = scanner.nextInt();
	int array[][] = new int[l][l];
	for(int i = 0 ; i<l ; i++) {
		for(int j = 0 ; j<l; j++) {
			System.out.println("A[ " + i + "]["+j+"]");
			numero = scanner.nextInt();
			array[i][j] = numero;
		}
	}
	
	System.out.println("DIAGONAL PRINCIPAL: ");
	for(int i = 0 ; i<l ; i++) {
		for(int j = 0 ; j<l; j++) {
			if(j==i)System.out.print(array[j][i]+ " ");
		}
		System.out.println();
		
		
	}
	System.out.println("DIAGONAL SECUNDARIA: ");
	for(int i = array.length-1 ; i>=0 ; i--) {
		for(int j = 0 ; j<l; j++) {
			if(i+j==array.length-1)System.out.print(array[j][i]+ " ");
		}
		System.out.println();
		
		
	}
}
	public static void mediaMatrixz() {
		int l , c,numero,soma =0;
		Scanner scanner = new Scanner(System.in);
		System.out.println("L: ");
		l = scanner.nextInt();
		System.out.println("C: ");
		c = scanner.nextInt();
		int array[][] = new int[c][l];
		for(int i = 0 ; i<c ; i++) {
			for(int j = 0 ; j<l; j++) {
				System.out.println("A[ " + i + "]["+j+"]");
				numero = scanner.nextInt();
				array[i][j] = numero;
			}
		}
		for(int i = 0 ; i<c ; i++) {
			for(int j = 0 ; j<l; j++) {
				soma += array[i][j];
			}
			
			
		}
		System.out.println("Media: "+soma/(l*c));
		
	}
	public static void mediaMatrixzColunas() {
		int l , c,numero,soma =0;
		Scanner scanner = new Scanner(System.in);
		System.out.println("L: ");
		l = scanner.nextInt();
		System.out.println("C: ");
		c = scanner.nextInt();
		int array[][] = new int[c][l];
		for(int i = 0 ; i<c ; i++) {
			for(int j = 0 ; j<l; j++) {
				System.out.println("A[ " + i + "]["+j+"]");
				numero = scanner.nextInt();
				array[i][j] = numero;
			}
		}
		for(int i = 0 ; i<c ; i++) {
			for(int j = 0 ; j<l; j++) {
				soma += array[i][j];
			}
			System.out.println("Media: "+soma/(l));
			soma = 0;
			
			
		}
		
	}
	public static void caracteresEmString() {
		Scanner scanner = new Scanner(System.in);
		String palavra;
		String x;
		int contador = 0;
		
		System.out.println("Digite uma frase: ");
		palavra = scanner.nextLine();
		System.out.println("Digite uma letra: ");
		x = scanner.nextLine();
		for(int i = 0 ; i<palavra.length();i++) {
			if(palavra.charAt(i)==x.charAt(0))contador++;
		}
		System.out.println("Existem " + contador + " letras "+ x);
		
	}
	public static void stringApenasPorDigitos() {
		Scanner scanner = new Scanner(System.in);
		String str = scanner.nextLine();
		boolean resp = true;
		for (int i = 0; i < str.length(); i++){
		if ((str.charAt(i) >= '0' && str.charAt(i) <= '9') == false){
		resp = false;
		i = str.length();
		 }
		 }
		if (resp == true) {
		System.out.println("parabens");
		} else {
		System.out.println("Não e numero");
		}

	}
	public static void stringApenasPorInteiros() {
		Scanner scanner = new Scanner(System.in);
		String str = scanner.nextLine();
		int num = 0, tmp;
		for (int i = 0; i < str.length(); i++){
		tmp = (int)(str.charAt(i) - 48);
		tmp *= ((int)Math.pow(10, str.length() - i - 1));
		num += tmp;
		 }
		System.out.println(num);

	}
	public static void ePalindromo() {
		Scanner scanner = new Scanner(System.in);
		String str = scanner.nextLine();
		boolean resp = true;
		for (int i = 0; i < str.length()/2; i++){
		if (str.charAt(i) != str.charAt(str.length() - i - 1)){
		resp = false;
		i = str.length();
		}
		}
		System.out.println(resp);
	
	}
	public static void numeroCaracteresMaiscula() {
		Scanner scanner = new Scanner(System.in);
		String palavra;
		String x;
		int contador = 0, maiscula = 0;
		
		System.out.println("Digite uma frase: ");
		palavra = scanner.nextLine();
		for(int i = 0; i<palavra.length();i++) {
			if((palavra.charAt(i)>=65)&&(palavra.charAt(i)<=90))maiscula++;
		}
		System.out.println("Letras: "+palavra.length());
		System.out.println("Letras maiscula: "+maiscula);
		
		}
	public static void primeiraOcorrenciaDoA() {
		Scanner scanner = new Scanner(System.in);
		String palavra;
		String x;
		int contador = 0, maiscula = 0;
		int i = 0;
		
		System.out.println("Digite uma frase: ");
		palavra = scanner.nextLine();
		while(!(palavra.charAt(i)==65)||!(palavra.charAt(i)==97)) {
			i++;
		}
		System.out.println("Letra a achacado na posicao " + i);
		}
	
	public static void main(String args[]) {
		//identificadorTriangulo(); //ex1
		//identificadorMaiorMenor();//ex2
		//identificadorMaiorMenorDezNumeros();//ex3
		//exercicio4(); //ex4
		//resultadoPartidaFutebol();//ex5
		//emprestimoBancoZe();//ex6
		//calculaRaizLog();//ex7
		//dezPrimeirosInteirosPositivos();//ex8
		//nPrimeirosImpares();//ex9
		//nPrimeirosNumerosSequencia();//ex10
		//sistemaNotaProva();//ex10
		//sequenciaFibonacci();//ex11
		//descobrirMediaArray();//ex12
		//posicaoMenorElemento();//ex13
		//ordenaArray();//ex14
		//uniaoIntercessaoArray();//ex15
		//mostraElemntosArray();//ex16
		//notaAlunos();//ex17
		//somaCasal();//ex18
		//numeroParesDivisivelPorTres();//ex19
		//somaContrario();//ex20
		//somaMultiplos3();//ex21//
		//mostraVetoresIntercalados();//ex22
		//escreveMatrizFormatoGrid();//ex23
		//escreveMatrizFormatoTransporta();//ex24
		//somaMatrz();//ex25
		//diagonalPrimariaSecundaria();//ex26
		//mediaMatrixz();//ex27
		//mediaMatrixzColunas();//ex28
		//caracteresEmString();//ex29
		//stringApenasPorDigitos();//ex30
		//stringApenasPorInteiros();//ex31
		//ePalindromo();//ex32
		//numeroCaracteresMaiscula();//ex33
		//primeiraOcorrenciaDoA();//ex34
		
		
		
		
	}
}


