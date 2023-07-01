


import java.io.*;

class TP01Q09ArquivoemJava2 {//Inicio classe

 public static void abreLeArquivo (int n)
  {
    //definir dados
     RandomAccessFile file;
     double dados;
    //testar quantidade para eviar looliping infinito
     if (n >= 0);
      {
	try {
    	      //criar arquivo 
	    file = new RandomAccessFile ("teste.txt","rw");
	  //ler os dados do teclado
	    for (int i = 0; i < n; i = i+1)
	      {
	         //ler um dado do teclado 
		  dados = MyIO.readDouble ();
		 //guardar dado em arquivo
		  file.writeDouble (dados);
	      }//fim repeticao
	    for (int i = 0; i < n; i = i+1)
	      { 
		long posicao = file.getFilePointer ( );
	   	file.seek (posicao-8);//ir para posicao
		double x = file.readDouble (); file.seek (posicao-8);
	        int y = (int) x;
		if (x == y)
		{
		 MyIO.println (y);
		}
		else
		{
		 MyIO.println (x);
		}
	      }
	    file.close ( ); // fechar file
	} catch (IOException Teste) 
	{MyIO.println ("Erro");}
      }//fim if
  }//fim outFile

 
//main
 public static void main (String [] args)
 {
 //definir dados
  int x = MyIO.readInt ();
 //ler dados e gravar no arquivo
  abreLeArquivo (x);
 }//fim main
}//fim classe
