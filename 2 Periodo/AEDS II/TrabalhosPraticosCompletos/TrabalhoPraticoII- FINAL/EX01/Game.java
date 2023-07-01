//--- Bibliotecas --- //
import java.util.Date;
import java.util.Scanner;
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
//--- Bibliotecas --- //

public class Game {//Início classe Game
	
	
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

	/// ---- Metódos --- ///
	public  void remover(int pos,String array[])  {//Início Remover - função usada para remover uma String de um Array de String de uma certa posição
		int n = array.length;
		
	      //validar remocao
	      

	      //String resp = array[pos];
	      n--;

	      for(int i = pos; i < n; i++){
	         array[i] = array[i+1];
	      }

	   }// Fim remover

	public  int juntaArrayLinguas(String frase,String atributos[]) {//Início JuntaArrayLinguas -- função usada para juntar arrays que armazenam o atributo linguas e unicafica-los em uma String
		String teste;
		int posinicialColchete = 0;
		int posFinalColchete =0;
		for(int i = 0 ; i<frase.length();i++) {
			if(frase.charAt(i)=='[')posinicialColchete = i+1;//Encontra chave e definir posicão inicial
			if(frase.charAt(i)==']')posFinalColchete = i;//Encontra chave e define posição final
			//i=frase.length();
		}
		teste = frase.substring(posinicialColchete,posFinalColchete);
		atributos[8] = frase.substring(posinicialColchete,posFinalColchete);//Atribui array[8] para divisao de frase em um subarray
		return 0;
	}//Fim JuntaArrayLinguas

	public  void removeArrayLinguas(String atributos[]) {//Início removeArrayLinguas -- função usada para remover array linguas repetidos 
		int fim = 8;
		for(int j = 0 ; j< atributos.length; j++) {
			if(atributos[j].charAt(0)==32 && atributos[j].charAt(1)==39 ) {
				remover(j,atributos);//chama funcao remover
				j=0;
			}
		}
	}//Fim removeArrayLinguas
	public  void juntaArrayGeneros(String frase,String atributos[]) {//Início juntaArrayGeneros -- função usada para unificar e remover duplicadosd e generos
		String teste;
		int virgulas = 0;
		int posinicialAspas = 0;
		int posFinalAspas =frase.length();
		for(int i = 0 ; i<frase.length()-1;i++) {
			if(frase.charAt(i)==32)virgulas++;//conta quntidade de virgulas
			if(frase.charAt(i)==34)posinicialAspas = i;
			//i=frase.length();
		}
		teste = frase.substring(posinicialAspas,posFinalAspas);
		atributos[17] = frase.substring(posinicialAspas,posFinalAspas);//Atribui array[17] para divisao de frase em um subarray
		for(int j = posinicialAspas ; j< atributos.length; j++) {
			if(atributos[j].charAt(0)==32) {
				remover(j,atributos);//Chama função remover
				j=0;
			}
		}

		
		
		
	}//Fim juntaArrayGeneros

	public  void juntaArrayData(String frase, String atributos[]) {//Inicio juntaArrayData -- função que unifica a data em apenas um array e remove os arrays duplicados
		for(int i = 0 ; i<7; i++) {
			if(atributos[i].charAt(0)==34) {
				atributos[i] = atributos[i]+""+atributos[i+1];//Unifica arrays
				remover(i+1,atributos);//chama funcao remover
				
			}
			
		}
		
	}//Fim juntaArrayData
	public  int contaNumeroAspas(String frase) {//Início contaNumeroAspas -- função que conta a quantidade de aspas da frase
		int resp = 0 ;
		for(int i = 0 ; i<frase.length()-1;i++) {
			if(frase.charAt(i)==34)resp++;// Contador de aspas
		}
		return resp;
	}//Fim contaNumeroAspas

	public  String verificaDuasVirgulas(String frase) {//Início verificaDuasvirguals -- função que verifica se existe duas virgulas na farse , caso sim, subtitui por null
		for(int i = 0 ; i<frase.length();i++) {
			if(frase.charAt(i)==44 && frase.charAt(i+1)==44) {
				frase = frase.replaceAll(",,",",null,");//Substituição de ,, por null
			}
		}
		return frase;
	}//Fim verificaDuasVirgulas

	public void juntaArrayDevelopers(String frase,String atributos[]) {//Início juntaArrayDevelopers -- função que unifica e remove array duplicados do atributo desenvolvedores
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
		atributos[15] = frase.substring(posinicialAspas,posFinalAspas);//Atribui array[15] para substring criada na função
		for(int j = posinicialAspas ; j< atributos.length; j++) {
			if(atributos[j].charAt(0)==32) {
				remover(j,atributos);//Chama remover
				j=0;
			}
		}
	}//Fim JuntaArrayDevelopers

	public void imprimeDados(String atributos[]) {//Início imprimeDados -- função que imprime na tela 
		String temp[] = new String[100];
		try {
		//--- Padronização dos dados levando em considereção o enunciado ---- //	
		temp = atributos[2].split(" ");
		temp[0] = temp[0] + "/" + temp[2];
		atributos[2] = temp[0];
		atributos[7]=atributos[7].replaceAll("'", "");
		atributos[7] = "["+atributos[7]+"]";
		atributos[16]=atributos[16].replaceAll("\"", "");
		atributos[16] = "["+atributos[16]+"]";
		atributos[16] = atributos[16].replaceAll(",", ", ");
		if((Integer.parseInt(atributos[14])/60!=0) && (Integer.parseInt(atributos[14])%60!=0 ))atributos[14]=Integer.parseInt(atributos[14])/60+"h"+" "+Integer.parseInt(atributos[14])%60+"m";
		else if((Integer.parseInt(atributos[14])/60!=0) && (Integer.parseInt(atributos[14])%60==0 ))atributos[14]=Integer.parseInt(atributos[14])/60+"h";
		else atributos[14]=null;
		//--- Padronização dos dados levando em considereção o enunciado ---- //	
		MyIO.println(atributos[0]+" "+atributos[1]+" "+atributos[2]+ " "
		+atributos[3]+" "+atributos[4] + " "+atributos[5]+" "+atributos[6]+" "+atributos[7]+" "+
		atributos[8]+" "+atributos[9].toLowerCase()+" "+atributos[10].toLowerCase()+" "+atributos[11].toLowerCase()+" "
		+Math.round(upvotes)+"%"+" "+atributos[14] +" "+atributos[15]+" "+atributos[16]);
		}catch(java.lang.ArrayIndexOutOfBoundsException e) {
			
		}catch(java.lang.NullPointerException e){}finally {}
		
	}/// Fim ImprimeDados
	
	public  void leituraDados(String path,String id) throws ParseException, IOException {//Início leituraDados -- funçãp que ler os dados
		BufferedReader ler = new BufferedReader(new FileReader(path));//Atribuindo a leitura ao arq games.csv
		String frase = ler.readLine();
		String teste[] = new String[100];
		teste = frase.split(",");
		 
		while(!(id.contentEquals(teste[0])&&!(eFIM(id)))) { // Continua a leitura se diferente de FIM
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
		// --- Exeçoes e erros --- //
		}catch(ArrayIndexOutOfBoundsException e){
			imprimeDados(atributos);
		}catch(java.lang.NumberFormatException e) {
			imprimeDados(atributos);
		}catch(java.lang.StringIndexOutOfBoundsException e) {
			imprimeDados(atributos);
		}
		finally {
			imprimeDados(atributos);
		}
		
			
		// --- Exeçoes e erros --- //
		
	}//Fim leituraDados
	
	public  void defineAtributos(String atributos[]) throws ParseException {//Início defineAtribuots -- função que define atributos 
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
		
		
	}//Fim defineAtributos

	public static boolean eFIM(String x) {//Inicio eFIM -- confere se palavra = FIM
		boolean resp = false;
		if((x.charAt(0) == 'F') && (x.charAt(1) == 'I' )&& (x.charAt(2) == 'M' )&& (x.length() == 3)) resp = true;
		return resp;
	}//Fim eFIM
	
	public static void main(String args[]) throws ParseException, IOException {//Iníico main
		Game Game = new Game();
		
		String path = "/tmp/games.csv";
		String id =MyIO.readLine();
		while(!eFIM(id)) {
			Game.leituraDados(path,id);
			id = MyIO.readLine();
		}
		

		
	}//Fim main
	
	
	


}//Fim classe GAME