
import java.util.Date;
import java.util.Scanner;

import javax.print.event.PrintEvent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
class Lista {
    private int[] array;
    private int n;
 
 
    /**
     * Construtor da classe.
     */
    public Lista () {
       this(6);
    }
 
 
    /**
     * Construtor da classe.
     * @param tamanho Tamanho da lista.
     */
    public Lista (int tamanho){
       array = new int[tamanho];
       n = 0;
    }
 
 
    /**
     * Insere um elemento na primeira posicao da lista e move os demais
     * elementos para o fim da lista.
     * @param x int elemento a ser inserido.
     * @throws Exception Se a lista estiver cheia.
     */
    public void inserirInicio(int x) throws Exception {
 
       //validar insercao
       if(n >= array.length){
          throw new Exception("Erro ao inserir!");
       } 
 
       //levar elementos para o fim do array
       for(int i = n; i > 0; i--){
          array[i] = array[i-1];
       }
 
       array[0] = x;
       n++;
    }
 
 
    /**
     * Insere um elemento na ultima posicao da lista.
     * @param x int elemento a ser inserido.
     * @throws Exception Se a lista estiver cheia.
     */
    public void inserirFim(int x) throws Exception {
 
       //validar insercao
       if(n >= array.length){
          throw new Exception("Erro ao inserir!");
       }
 
       array[n] = x;
       n++;
    }
 
 
    /**
     * Insere um elemento em uma posicao especifica e move os demais
     * elementos para o fim da lista.
     * @param x int elemento a ser inserido.
     * @param pos Posicao de insercao.
     * @throws Exception Se a lista estiver cheia ou a posicao invalida.
     */
    public void inserir(int x, int pos) throws Exception {
 
       //validar insercao
       if(n >= array.length || pos < 0 || pos > n){
          throw new Exception("Erro ao inserir!");
       }
 
       //levar elementos para o fim do array
       for(int i = n; i > pos; i--){
          array[i] = array[i-1];
       }
 
       array[pos] = x;
       n++;
    }
 
 
    /**
     * Remove um elemento da primeira posicao da lista e movimenta 
     * os demais elementos para o inicio da mesma.
     * @return resp int elemento a ser removido.
     * @throws Exception Se a lista estiver vazia.
     */
    public int removerInicio() throws Exception {
 
       //validar remocao
       if (n == 0) {
          throw new Exception("Erro ao remover!");
       }
 
       int resp = array[0];
       n--;
 
       for(int i = 0; i < n; i++){
          array[i] = array[i+1];
       }
 
       return resp;
    }
 
 
    /**
     * Remove um elemento da ultima posicao da lista.
     * @return resp int elemento a ser removido.
     * @throws Exception Se a lista estiver vazia.
     */
    public int removerFim() throws Exception {
 
       //validar remocao
       if (n == 0) {
          throw new Exception("Erro ao remover!");
       }
 
       return array[--n];
    }
 
 
    /**
     * Remove um elemento de uma posicao especifica da lista e 
     * movimenta os demais elementos para o inicio da mesma.
     * @param pos Posicao de remocao.
     * @return resp int elemento a ser removido.
     * @throws Exception Se a lista estiver vazia ou a posicao for invalida.
     */
    public int remover(int pos) throws Exception {
 
       //validar remocao
       if (n == 0 || pos < 0 || pos >= n) {
          throw new Exception("Erro ao remover!");
       }
 
       int resp = array[pos];
       n--;
 
       for(int i = pos; i < n; i++){
          array[i] = array[i+1];
       }
 
       return resp;
    }
 
 
    /**
     * Mostra os elementos da lista separados por espacos.
     */
    public void mostrar (){
       System.out.print("[ ");
       for(int i = 0; i < n; i++){
          System.out.print(array[i] + " ");
       }
       System.out.println("]");
    }
 
 
    /**
     * Procura um elemento e retorna se ele existe.
     * @param x int elemento a ser pesquisado.
     * @return <code>true</code> se o array existir,
     * <code>false</code> em caso contrario.
     */
    public boolean pesqBin(int x){
		//Ordenar array//
		for (int i = 0; i < (n - 1); i++) {
			int menor = i;
			int j = (i + 1);
			for (j = (i + 1); j < n; j++){
			   if (array[menor] > array[j]){
				  menor = j;
			   }
			   int temp = array[menor];
			   array[menor]=array[i];
			   array[i]=temp;
			}
			
		}
        boolean resp = false;
        int dir = n-1, esq = 0, meio;
        while (esq <= dir){
           meio = (esq + dir) / 2;
           if(x == array[meio]){
              resp = true;
              esq = n;
           } else if (x > array[meio]) {
              esq = meio + 1;
           } else {
              dir = meio - 1;
           }
        }
        return resp;
     }
 }
 
     
     
 
 
 

public class Game {
	
	
//--- Contrutores ---- //
	/**
	 * @param app_id
	 * @param name
	 * @param release_data
	 * @param owners
	 * @param age
	 * @param price
	 * @param dlcs
	 * @param languages
	 * @param website
	 * @param windowns
	 * @param mac
	 * @param linux
	 * @param upvotes
	 * @param avg_pt
	 * @param developers
	 * @param genres
	 */
	public Game(int app_id, String name, String release_data, String owners, int age, float price, int dlcs,
			String[] languages, String website, boolean windowns, boolean mac, boolean linux, float upvotes, int avg_pt,
			String developers, String[] genres) {
		this.app_id = app_id;
		this.name = name;
		this.release_data = release_data;
		this.owners = owners;
		this.age = age;
		this.price = price;
		this.dlcs = dlcs;
		this.languages = languages;
		this.website = website;
		this.windowns = windowns;
		this.mac = mac;
		this.linux = linux;
		this.upvotes = upvotes;
		this.avg_pt = avg_pt;
		this.developers = developers;
		this.genres = genres;
	}
	public Game() {
		this.app_id = 0;
		this.name = "";
		this.release_data = "";
		this.owners = "";
		this.age = 0;
		this.price = 0;
		this.dlcs = 0;
		this.languages = new String[0];
		this.website = "";
		this.windowns = true;
		this.mac = true;
		this.linux = true;
		this.upvotes = 0;
		this.avg_pt = 0;
		this.developers = "";
		this.genres = new String[0];
		
	}
	
	//---Fim Consrutores---//
	
	
	//--Atributos--//

	private int app_id;
	private String name;
	private String release_data;
	private String owners;
	private int age;
	private float price;
	private int dlcs;
	private String[] languages;
	private String website;
	private boolean windowns;
	private boolean mac;
	private boolean linux;
	private float upvotes;
	private int avg_pt;
	private String developers;
	private String[] genres;
	
	//---Fim atributos---//
	
	//------- Setters e Getters------//
	public String getName() {
		return name;
	}





	public void setName(String name) {
		this.name = name;
	}





	public String getRelease_data() {
		return release_data;
	}





	public void setRelease_data(String release_data) {
		this.release_data = release_data;
	}





	public String getOwners() {
		return owners;
	}





	public void setOwners(String owners) {
		this.owners = owners;
	}





	public int getAge() {
		return age;
	}





	public void setAge(int age) {
		this.age = age;
	}





	public float getPrice() {
		return price;
	}





	public void setPrice(float price) {
		this.price = price;
	}





	public int getDlcs() {
		return dlcs;
	}





	public void setDlcs(int dlcs) {
		this.dlcs = dlcs;
	}





	public String[] getLanguages() {
		return languages;
	}





	public void setLanguages(String[] languages) {
		this.languages = languages;
	}





	public String getWebsite() {
		return website;
	}





	public void setWebsite(String website) {
		this.website = website;
	}





	public boolean isWindowns() {
		return windowns;
	}





	public void setWindowns(boolean windowns) {
		this.windowns = windowns;
	}





	public boolean isMac() {
		return mac;
	}





	public void setMac(boolean mac) {
		this.mac = mac;
	}





	public boolean isLinux() {
		return linux;
	}





	public void setLinux(boolean linux) {
		this.linux = linux;
	}





	public float getUpvotes() {
		return upvotes;
	}





	public void setUpvotes(float upvotes) {
		this.upvotes = upvotes;
	}





	public int getAvg_pt() {
		return avg_pt;
	}





	public void setAvg_pt(int avg_pt) {
		this.avg_pt = avg_pt;
	}
	
	public String getDevelopers() {
		return developers;
	}

	public void setDevelopers(String developers) {
		this.developers = developers;
	}
	public String[] getGenres() {
		return genres;
	}
	public void setGenres(String[] genres) {
		this.genres = genres;
	}
	public void setApp_id(int app_id) {
		this.app_id = app_id;
	}
	public int getApp_id() {
		return app_id;
	}
	//---- FIM Setters e Getters------///
	
	
	//---- Clone ----- //
	public Game clone() {
		Game novo = new Game();
		novo.app_id = app_id;
		novo.name = name;
		novo.release_data = release_data;
		novo.owners = owners;
		novo.age = age;
		novo.price = price;
		novo.dlcs = dlcs;
		novo.languages = languages;
		novo.website = website;
		novo.windowns = windowns;
		novo.mac=mac;
		novo.linux = linux;
		novo.upvotes = upvotes;
		novo.avg_pt = avg_pt;
		novo.developers = developers;
		novo.genres = genres;
		
		
		return novo;
	}
	
	//---- FIM clone ----- ///
	
	
	
	public  void remover(int pos,String array[])  { // Inicio remover -- função usada para remover um objeto do array de uma posicao específica
		int n = array.length;
		
	      //validar remocao
	      
	      n--;

	      for(int i = pos; i < n; i++){
	         array[i] = array[i+1]; // Mudança de posição de TODO array, pois a remoção é lógica
	      }

	   }//Fim remover
	
	
	public  int juntaArrayLinguas(String frase,String atributos[]) {//Início juntaArrayLinguas -- funcao usada para identificar o inicio e  o fim do atributo e separa-lo em apenas uma String
		String teste;
		int posinicialColchete = 0;
		int posFinalColchete =0;
		for(int i = 0 ; i<frase.length();i++) {
			if(frase.charAt(i)=='[')posinicialColchete = i+1;
			if(frase.charAt(i)==']')posFinalColchete = i;
			//i=frase.length();
		}
		atributos[8] = frase.substring(posinicialColchete,posFinalColchete);// Atribui o uma sustring(linguas) para array[8] 
		return 0;
	}//Fim JuntaArrayLinguas
	
	public  void removeArrayLinguas(String atributos[]) {//Inicio removeArrayLinguas -- funcao usada para remover arrays extras depois do split
		int fim = 8;//Começa em 8 pois será a posicao em que as linguas em todos os jogos estão
		for(int j = 0 ; j< atributos.length; j++) {
			if(atributos[j].charAt(0)==32 && atributos[j].charAt(1)==39 ) {
				remover(j,atributos);//função que remove
				j=0;
			}
		}
	}//Fim removeArrayLinguas
	
	
	public  void juntaArrayGeneros(String frase,String atributos[]) {//Início JuntaArrayGeneros -- função usada para juntar arrays que sao de genero em um so e remo
		String teste;
		int virgulas = 0;
		int posinicialAspas = 0;
		int posFinalAspas =frase.length();
		for(int i = 0 ; i<frase.length()-1;i++) {
			if(frase.charAt(i)==32)virgulas++;
			if(frase.charAt(i)==34)posinicialAspas = i;
			//i=frase.length();
		}
		teste = frase.substring(posinicialAspas,posFinalAspas);//Separa função 
		atributos[17] = frase.substring(posinicialAspas,posFinalAspas);
		for(int j = posinicialAspas ; j< atributos.length; j++) {
			if(atributos[j].charAt(0)==32) {
				remover(j,atributos); // Mudança de posição de TODO array, pois a remoção é lógica
				j=0;
			}
		}
		
		
		
	}//Fim JuntaArrayGeneros
	public  void juntaArrayData(String frase, String atributos[]) {
		for(int i = 0 ; i<7; i++) {
			if(atributos[i].charAt(0)==34) {
				atributos[i] = atributos[i]+""+atributos[i+1];
				remover(i+1,atributos);
				
			}
			
		}
		
	}
	public  int contaNumeroAspas(String frase) {
		int resp = 0 ;
		for(int i = 0 ; i<frase.length()-1;i++) {
			if(frase.charAt(i)==34)resp++;
		}
		return resp;
	}
	public  String verificaDuasVirgulas(String frase) {
		for(int i = 0 ; i<frase.length();i++) {
			if(frase.charAt(i)==44 && frase.charAt(i+1)==44) {
				frase = frase.replaceAll(",,",",null,");
			}
		}
		return frase;
	}
	public void juntaArrayDevelopers(String frase,String atributos[]) {
		String teste;
		int posinicialAspas = 0;
		int posFinalAspas =frase.length();
		for(int i = 0 ; i<frase.length()-1;i++) {
			if(frase.charAt(i)==44 && frase.charAt(i-1)>=48 && frase.charAt(i-1)<=57)posinicialAspas = i+1;
			//i=frase.length();
			if(frase.charAt(i)==44 && frase.charAt(i-1)==34) {
				posFinalAspas = i;
			}
		}
		teste = frase.substring(posinicialAspas,posFinalAspas);
		atributos[15] = frase.substring(posinicialAspas,posFinalAspas);
		for(int j = posinicialAspas ; j< atributos.length; j++) {
			if(atributos[j].charAt(0)==32) {
				remover(j,atributos);
				j=0;
			}
		}
	}
	
	public  void leituraDados(String path,String id) throws ParseException, IOException {
		BufferedReader ler = new BufferedReader(new FileReader(path));
		String frase = ler.readLine();
		String teste[] = new String[100];
		teste = frase.split(",");
		 
		while(!(id.contentEquals(teste[0])&&!(eFIM(id)))) {
			frase = ler.readLine();
			teste = frase.split(",");
			 
		}
		
		
		
		
		
		
		int contadorAspas = 0;
		int contadorChaves = 0;
		int numeroAtributos = 0;
		int k = 0;
		String atributos[] = new String[10000]; // Cria array atributos
		contadorAspas = contaNumeroAspas(frase);//Conta numero de aspas
		try {
		frase = verificaDuasVirgulas(frase); // Verifica se existe duas virgulas, caso sim, substitui por NULL
		atributos = frase.split(",");//Separa os atributos por virgulas
		juntaArrayLinguas(frase,atributos);// Junta array de linguas
		removeArrayLinguas(atributos);//Remove array duplicados de linguas
		if(atributos[17].charAt(0)=='\"' )juntaArrayGeneros(frase,atributos);//junta array de generos
		juntaArrayData(frase,atributos);//junta array de generos
		defineAtributos(atributos);
		for(int z = 0 ; z<frase.length() ; z++) {
			if((frase.charAt(z)==44) && (frase.charAt(z+1)==34) && (frase.charAt(z-1)>=48)&&(frase.charAt(z-1)<=57)&&(frase.charAt(z+2)!='[')&&(frase.charAt(z-2)>=48)&&(frase.charAt(z-2)<=57)&&frase.charAt(z-3)>=48&&frase.charAt(z-3)<=57) {
				juntaArrayDevelopers(frase,atributos);
				
			}
		}
		}catch(ArrayIndexOutOfBoundsException e){
			
		}catch(java.lang.NumberFormatException e) {
			
		}catch(java.lang.StringIndexOutOfBoundsException e) {
			
		}
		finally {
			
		}
		
			
		
		
	}
	
	public  void defineAtributos(String atributos[]) throws ParseException {
		app_id=Integer.parseInt(atributos[0]);
		name=atributos[1];
		atributos[2]=atributos[2].replaceAll("\"", "");
		release_data = atributos[2];
		owners=atributos[3];
		age = Integer.parseInt(atributos[4]);
		price = Float.parseFloat(atributos[5]);
		dlcs = Integer.parseInt(atributos[6]);
		languages = atributos[7].split(",");
		website = atributos[8];
		windowns = Boolean.parseBoolean(atributos[9]);
		mac = Boolean.parseBoolean(atributos[10]);
		linux = Boolean.parseBoolean(atributos[11]);
		upvotes = Math.round((Float.parseFloat(atributos[12])/(Float.parseFloat(atributos[12])+Float.parseFloat(atributos[13])))*100);
		avg_pt = Integer.parseInt(atributos[14]);
		developers = atributos[15];
		genres = atributos[16].split(",");
		
		
	}
	public static boolean eFIM(String x) {
		boolean resp = false;
		if((x.charAt(0) == 'F') && (x.charAt(1) == 'I' )&& (x.charAt(2) == 'M' )&& (x.length() == 3)) resp = true;
		return resp;
	}
    public  int leituraDados2(String path,String id) throws ParseException, IOException {
        BufferedReader ler = new BufferedReader(new FileReader(path));
		String frase = ler.readLine();
		String teste[] = new String[100];
		teste = frase.split(",");
		 
		while(!(id.contentEquals(teste[1])&&!(eFIM(id)))) {
			frase = ler.readLine();
			teste = frase.split(",");
           
            
		}
       return Integer.parseInt(teste[0]);
			

    }
    public static void main(String args[]) throws Exception {
		Game Game = new Game();
        Lista Lista = new Lista(500);
		int idLista = 0;
		String path = "/tmp/games.csv";
		String id =MyIO.readLine();
        int numeroIDInteiro = 0;
		while(!Game.eFIM(id)) {
			Game.leituraDados(path,id);
            Lista.inserirInicio(Game.getApp_id());
			id = MyIO.readLine();
		}


        id =MyIO.readLine();
        while(!Game.eFIM(id)) {
            try{
			numeroIDInteiro = Game.leituraDados2(path,id);
            if(Lista.pesqBin(numeroIDInteiro))MyIO.println("SIM");
            else MyIO.println("NAO");
            }catch(java.lang.NullPointerException e ){MyIO.println("NAO");}
            finally{
                id = MyIO.readLine();
            }
		}
}
}
