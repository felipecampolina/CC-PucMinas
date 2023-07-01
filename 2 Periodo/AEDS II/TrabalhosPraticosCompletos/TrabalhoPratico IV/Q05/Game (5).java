

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.IntSummaryStatistics;
import java.util.Locale;
import javax.management.openmbean.ArrayType;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.IntBuffer;
import java.security.Principal;

class CelulaDupla {
	public int elemento;
	public CelulaDupla ant;
	public CelulaDupla prox;

	/**
	 * Construtor da classe.
	 */
	public CelulaDupla() {
		this(0);
	}


	/**
	 * Construtor da classe.
	 * @param elemento int inserido na celula.
	 */
	public CelulaDupla(int elemento) {
		this.elemento = elemento;
		this.ant = this.prox = null;
	}
}
class Lista extends CelulaDupla {
	private CelulaDupla primeiro;
	private CelulaDupla ultimo;


	/**
	 * Construtor da classe que cria uma lista dupla sem elementos (somente no cabeca).
	 */
	public Lista() {
		primeiro = new CelulaDupla();
		ultimo = primeiro;
	}


	/**
	 * Insere um elemento na primeira posicao da lista.
    * @param x int elemento a ser inserido.
	 */
	public void inserirInicio(int x) {
		CelulaDupla tmp = new CelulaDupla(x);

      tmp.ant = primeiro;
      tmp.prox = primeiro.prox;
      primeiro.prox = tmp;
      if(primeiro == ultimo){
         ultimo = tmp;
      }else{
         tmp.prox.ant = tmp;
      }
      tmp = null;
	}


	/**
	 * Insere um elemento na ultima posicao da lista.
    * @param x int elemento a ser inserido.
	 */
	public void inserirFim(int x) {
		ultimo.prox = new CelulaDupla(x);
      ultimo.prox.ant = ultimo;
		ultimo = ultimo.prox;
	}


	/**
	 * Remove um elemento da primeira posicao da lista.
    * @return resp int elemento a ser removido.
	 * @throws Exception Se a lista nao contiver elementos.
	 */
	public int removerInicio() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		}

      CelulaDupla tmp = primeiro;
		primeiro = primeiro.prox;
		int resp = primeiro.elemento;
      tmp.prox = primeiro.ant = null;
      tmp = null;
		return resp;
	}


	/**
	 * Remove um elemento da ultima posicao da lista.
    * @return resp int elemento a ser removido.
	 * @throws Exception Se a lista nao contiver elementos.
	 */
	public int removerFim() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		} 
      int resp = ultimo.elemento;
      ultimo = ultimo.ant;
      ultimo.prox.ant = null;
      ultimo.prox = null;
		return resp;
	}


	/**
    * Insere um elemento em uma posicao especifica considerando que o 
    * primeiro elemento valido esta na posicao 0.
    * @param x int elemento a ser inserido.
	 * @param pos int posicao da insercao.
	 * @throws Exception Se <code>posicao</code> invalida.
	 */
   public void inserir(int x, int pos) throws Exception {

      int tamanho = tamanho();

      if(pos < 0 || pos > tamanho){
			throw new Exception("Erro ao inserir posicao (" + pos + " / tamanho = " + tamanho + ") invalida!");
      } else if (pos == 0){
         inserirInicio(x);
      } else if (pos == tamanho){
         inserirFim(x);
      } else {
		   // Caminhar ate a posicao anterior a insercao
         CelulaDupla i = primeiro;
         for(int j = 0; j < pos; j++, i = i.prox);
		
         CelulaDupla tmp = new CelulaDupla(x);
         tmp.ant = i;
         tmp.prox = i.prox;
         tmp.ant.prox = tmp.prox.ant = tmp;
         tmp = i = null;
      }
   }


	/**
    * Remove um elemento de uma posicao especifica da lista
    * considerando que o primeiro elemento valido esta na posicao 0.
	 * @param posicao Meio da remocao.
    * @return resp int elemento a ser removido.
	 * @throws Exception Se <code>posicao</code> invalida.
	 */
	public int remover(int pos) throws Exception {
      int resp;
      int tamanho = tamanho();

		if (primeiro == ultimo){
			throw new Exception("Erro ao remover (vazia)!");

      } else if(pos < 0 || pos >= tamanho){
			throw new Exception("Erro ao remover (posicao " + pos + " / " + tamanho + " invalida!");
      } else if (pos == 0){
         resp = removerInicio();
      } else if (pos == tamanho - 1){
         resp = removerFim();
      } else {
		   // Caminhar ate a posicao anterior a insercao
         CelulaDupla i = primeiro.prox;
         for(int j = 0; j < pos; j++, i = i.prox);
		
         i.ant.prox = i.prox;
         i.prox.ant = i.ant;
         resp = i.elemento;
         i.prox = i.ant = null;
         i = null;
      }

		return resp;
	}


	/**
	 * Mostra os elementos da lista separados por espacos.
	 */
	public void mostrar() {
		System.out.print("[ "); // Comeca a mostrar.
		for (CelulaDupla i = primeiro.prox; i != null; i = i.prox) {
			System.out.print(i.elemento + " ");
		}
		System.out.println("] "); // Termina de mostrar.
	}


	/**
	 * Mostra os elementos da lista de forma invertida 
    * e separados por espacos.
	 */
	public void mostrarInverso() {
		System.out.print("[ ");
		for (CelulaDupla i = ultimo; i != primeiro; i = i.ant){
			System.out.print(i.elemento + " ");
      }
		System.out.println("] "); // Termina de mostrar.
	}


	/**
	 * Procura um elemento e retorna se ele existe.
	 * @param x Elemento a pesquisar.
	 * @return <code>true</code> se o elemento existir,
	 * <code>false</code> em caso contrario.
	 */
	public boolean pesquisar(int x) {
		boolean resp = false;
		for (CelulaDupla i = primeiro.prox; i != null; i = i.prox) {
         if(i.elemento == x){
            resp = true;
            i = ultimo;
         }
		}
		return resp;
	}

	/**
	 * Calcula e retorna o tamanho, em numero de elementos, da lista.
	 * @return resp int tamanho
	 */
   public int tamanho() {
      int tamanho = 0; 
      for(CelulaDupla i = primeiro; i != ultimo; i = i.prox, tamanho++);
      return tamanho;
   }
}
public class Game {//Inicio classe Game
    //---atributos---//
    static SimpleDateFormat default_dateFormat = new SimpleDateFormat("MMM/yyyy", Locale.ENGLISH);
    private String name, owners, website, developers;
    private String languages="[", genres="[";
    private Date release_date;
    private int app_id, age, dlcs, avg_playtime;
    private float price, upvotes;
    private boolean windows, mac, linux;
 //---atributos---//


  //---Construtores---//
    public Game() {
        this.name = this.owners = this.website = this.developers = null;
        this.languages = languages;
        this.genres = genres;
        this.release_date = null;
        this.app_id = this.age = this.dlcs = this.avg_playtime = -1;
        this.price = this.upvotes = -1;
        this.windows = this.mac = this.linux = false;
    }

    public Game(String name, String owners, String website, String developers, String languages,
            String genres, Date release_date, int app_id, int age, int dlcs, int upvotes, int avg_playtime,
            float price, boolean windows, boolean mac, boolean linux) {
        this.name = name;
        this.owners = owners;
        this.website = website;
        this.developers = developers;
        this.languages = languages;
        this.genres = genres;
        this.release_date = release_date;
        this.app_id = app_id;
        this.age = age;
        this.dlcs = dlcs;
        this.upvotes = upvotes;
        this.avg_playtime = avg_playtime;
        this.price = price;
        this.windows = windows;
        this.mac = mac;
        this.linux = linux;
    }
 //---Fim atributos---//


  //---Setters e Getters---//
    public void setName(String name) {
        this.name = name;
    }

    public void setOwners(String owners) {
        this.owners = owners;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setDevelopers(String developers) {
        this.developers = developers;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public void setReleaseDate(Date release_date) {
        this.release_date = release_date;
    }

    public void setAppId(int app_id) {
        this.app_id = app_id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDlcs(int dlcs) {
        this.dlcs = dlcs;
    }

    public void setAvgPlaytime(int avg_playtime) {
        this.avg_playtime = avg_playtime;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setUpvotes(float upvotes) {
        this.upvotes = upvotes;
    }

    public void setWindows(boolean windows) {
        this.windows = windows;
    }

    public void setMac(boolean mac) {
        this.mac = mac;
    }

    public void setLinux(boolean linux) {
        this.linux = linux;
    }

    public String getName() {
        return this.name;
    }

    public String getOwners() {
        return this.owners;
    }

    public String getWebsite() {
        return this.website;
    }

    public String getDevelopers() {
        return this.developers;
    }

    public String getLanguages() {
        return this.languages;
    }

    public String getGenres() {
        return this.genres;
    }

    public Date getReleaseDate() {
        return this.release_date;
    }

    public int getAppId() {
        return this.app_id;
    }

    public int getAge() {
        return this.age;
    }

    public int getDlcs() {
        return this.dlcs;
    }

    public int getAvgPlaytime() {
        return this.avg_playtime;
    }

    public float getPrice() {
        return this.price;
    }

    public float getUpvotes() {
        return this.upvotes;
    }

    public boolean getWindows() {
        return this.windows;
    }

    public boolean getMac() {
        return this.mac;
    }

    public boolean getLinux() {
        return this.linux;
    }
  //---Fim Setters e Getters---//


    //---Clone---//
    public Game clone() {
        Game cloned = new Game();
        cloned.name = this.name;
        cloned.owners = this.owners;
        cloned.website = this.website;
        cloned.developers = this.developers;
        cloned.languages = this.languages;
        cloned.genres = this.genres;
        cloned.release_date = this.release_date;
        cloned.app_id = this.app_id;
        cloned.age = this.age;
        cloned.dlcs = this.dlcs;
        cloned.avg_playtime = this.avg_playtime;
        cloned.price = this.price;
        cloned.upvotes = this.upvotes;
        cloned.windows = this.windows;
        cloned.mac = this.mac;
        cloned.linux = this.linux;
        return cloned;
    }

    //---Fim Clone---//

    public void ler(String line) {//Inicio ler, funcao que ler linha inteira e atribue variaveis a suas respectivos lugares
        char c_search;
        int index = 0, atr_index = 0;

        // ---------------------------------- //
        // Find "AppID"
        while (true) {
            index++;
            if (line.charAt(index) == ',') {
                this.app_id = Integer.parseInt(line.substring(atr_index, index));
                atr_index = ++index;
                break;
            }
        }

        // ---------------------------------- //
        // Find "Name"
        if (line.charAt(atr_index) != ',') {
            if (line.charAt(atr_index) == '\"') {
                atr_index++;
                c_search = '\"';
            } else
                c_search = ',';
            while (true) {
                index++;
                if (line.charAt(index) == c_search) {
                    this.name = line.substring(atr_index, index);
                    if (c_search == ',')
                        index++;
                    else if (c_search == '\"')
                        index += 2;
                    atr_index = index;
                    break;
                }
            }
        } else
            atr_index = ++index;

        // ---------------------------------- //
        // Find release date
        if (line.charAt(atr_index) != ',') {
            SimpleDateFormat df;
            if (line.charAt(atr_index) == '\"') {
                df = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
                atr_index++;
                c_search = '\"';
            } else {
                df = new SimpleDateFormat("MMM yyyy", Locale.ENGLISH);
                c_search = ',';
            }
            while (true) {
                index++;
                if (line.charAt(index) == c_search) {
                    try {
                        this.release_date = df.parse(line.substring(atr_index, index));
                    } catch (java.text.ParseException e) {
                        e.printStackTrace();
                    }
                    if (c_search == ',')
                        index++;
                    else if (c_search == '\"')
                        index += 2;
                    atr_index = index;
                    break;
                }
            }
        } else
            atr_index = ++index;

        // ---------------------------------- //
        // Find "Owners"
        while (true) {
            index++;
            if (line.charAt(index) == ',') {
                this.owners = line.substring(atr_index, index);
                atr_index = ++index;
                break;
            }
        }

        // ---------------------------------- //
        // Find "Age"
        while (true) {
            index++;
            if (line.charAt(index) == ',') {
                this.age = Integer.parseInt(line.substring(atr_index, index));
                atr_index = ++index;
                break;
            }
        }

        // ---------------------------------- //
        // Find "Price"
        while (true) {
            index++;
            if (line.charAt(index) == ',') {
                this.price = Float.parseFloat(line.substring(atr_index, index));
                atr_index = ++index;
                break;
            }
        }

        // ---------------------------------- //
        // Find "DLCs"
        while (true) {
            index++;
            if (line.charAt(index) == ',') {
                this.dlcs = Integer.parseInt(line.substring(atr_index, index));
                atr_index = ++index;
                break;
            }
        }

        // ---------------------------------- //
        // Find "Languages"
        while (true) {
            index++;
            if (line.charAt(index) == ']') {
                index++;
                if (line.charAt(index) == ',')
                    index++;
                else if (line.charAt(index) == '\"')
                    index += 2;
                atr_index = index;
                break;
            } else if (line.charAt(index) == '\'') {
                int wordStart = index + 1;
                while (true) {
                    index++;
                    if (line.charAt(index) == '\'') {
                        if((line.charAt(index+1) == ']'))this.languages +=line.substring(wordStart, index)+"";
                        else this.languages +=line.substring(wordStart, index)+", ";
                        break;
                    }
                }
            }
        }

        // ---------------------------------- //
        // Find "Website"
        if (line.charAt(atr_index) != ',') {
            if (line.charAt(atr_index) == '\"') {
                atr_index++;
                c_search = '\"';
            } else
                c_search = ',';

            while (true) {
                index++;
                if (line.charAt(index) == c_search) {
                    this.website = line.substring(atr_index, index);
                    atr_index = ++index;
                    break;
                }
            }
        } else
            atr_index = ++index;

        // ---------------------------------- //

        // Find "Windows"
        while (true) {
            index++;
            if (line.charAt(index) == ',') {
                this.windows = Boolean.parseBoolean(line.substring(atr_index, index));
                atr_index = ++index;
                break;
            }
        }

        // Find "Mac"
        while (true) {
            index++;
            if (line.charAt(index) == ',') {
                this.mac = Boolean.parseBoolean(line.substring(atr_index, index));
                atr_index = ++index;
                break;
            }
        }

        // Find "Linux"
        while (true) {
            index++;
            if (line.charAt(index) == ',') {
                this.linux = Boolean.parseBoolean(line.substring(atr_index, index));
                atr_index = ++index;
                break;
            }
        }

        // ---------------------------------- //
        // Find "Upvotes"
        int positives, negatives;
        while (true) {
            index++;
            if (line.charAt(index) == ',') {
                positives = Integer.parseInt(line.substring(atr_index, index));
                atr_index = ++index;
                break;
            }
        }

        while (true) {
            index++;
            if (line.charAt(index) == ',') {
                negatives = Integer.parseInt(line.substring(atr_index, index));
                atr_index = ++index;
                break;
            }
        }
        this.upvotes = (float) (positives * 100) / (float) (positives + negatives);

        // ---------------------------------- //
        // Find "AVG Playtime"
        while (true) {
            index++;
            if (line.charAt(index) == ',') {
                this.avg_playtime = Integer.parseInt(line.substring(atr_index, index));
                atr_index = ++index;
                break;
            }
        }

        // ---------------------------------- //
        // Find "Developers"
        if (line.charAt(atr_index) != ',') {
            if (line.charAt(atr_index) == '\"') {
                atr_index++;
                c_search = '\"';
            } else
                c_search = ',';
            while (true) {
                index++;
                if (line.charAt(index) == c_search) {
                    this.developers = line.substring(atr_index, index);
                    atr_index = ++index;
                    break;
                }
            }
        } else
            atr_index = ++index;
        // ---------------------------------- //

        // Find "Genres"
        if (index < line.length() - 1) {
            if (line.charAt(index) == ',')
                atr_index = ++index;
            if (line.charAt(atr_index) == '\"') {
                atr_index++;
                while (true) {
                    index++;
                    if (line.charAt(index) == ',') {
                        this.genres +=(line.substring(atr_index, index)+", ");
                        atr_index = ++index;
                    } else if (line.charAt(index) == '\"') {
                        this.genres +=(line.substring(atr_index, line.length() - 1)+"");
                        break;
                    }
                }
            } else{
                this.genres+=line.substring(atr_index, line.length());
            }
        }
    }//Fim ler

    public void imprimir() {//Inicio imprimir -- funcao que imprime em padrao pedido no enunciado
        String avg_pt = null;
        if (this.avg_playtime == 0)
            avg_pt = "null ";
        else if (this.avg_playtime < 60)
            avg_pt = this.avg_playtime + "m ";
        else {
            if (this.avg_playtime % 60 == 0)
                avg_pt = this.avg_playtime / 60 + "h ";
            else
                avg_pt = (this.avg_playtime / 60) + "h " + (this.avg_playtime % 60) + "m ";
        }

        DecimalFormat df = new DecimalFormat("##");
        MyIO.println(this.app_id + " " + this.name + " " + default_dateFormat.format(this.release_date) + " "
                + this.owners + " " + this.age + " " + String.format("%.2f", this.price) + " " + this.dlcs +" "
                 + this.languages+']' + " " + this.website + " " + this.windows + " " + this.mac + " " + this.linux + " "
                + (Float.isNaN(this.upvotes) ? "0.0% " : df.format(this.upvotes) + "% ") + avg_pt + this.developers
                + " "+ this.genres+']');
    }//Fim imprime

    public String lerLinhas(String path, String id) throws IOException {//Inicio lerLinhas -- função que ler linahs do verde e compara com csv
        BufferedReader leitura = new BufferedReader(new FileReader(path));
        String frase = leitura.readLine();
        String teste[] = new String[100];
        teste = frase.split(",");

        while(!(id.contentEquals(teste[0]) && !(isFim(id)))){ //teste[0] = id
            frase = leitura.readLine();
            teste = frase.split(",");
        }
        return frase;
    }//Fim lerLinahs

    public String lerLinhasLista(String path, String id) throws IOException {//Inicio lerLinhasLista -- função que ler linahs do verde e compara com csv, retornando o id
        BufferedReader leitura = new BufferedReader(new FileReader(path));
        String frase = leitura.readLine();
        String teste[] = new String[100];
        teste = frase.split(",");

        while(!(id.contentEquals(teste[1]) && !(isFim(id)))){ //teste[1] = nome
            frase = leitura.readLine();
            teste = frase.split(",");
        }
        return teste[1]; //retornando o nome
    }//Fim lerLinhasLista
    
    public boolean isFim(String s){//Inicio isFim -- funcao que verifica se a frase = FIM
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
     }//Fim is Fim

    public String[] separaIdString (String idString){//Iniicio seperaIdString -- funcao que retorna um array de string da frase inteira
        String [] stringSeparada = new String[500];
        stringSeparada = idString.split(" ");
        return stringSeparada;
        
    }//Fim separaIdString

    public void imprimeRemovidos(int id,String path) throws IOException{//Inicio imprimeRemovidos -- funcao que imprime o NOME dos jogos removidos da lista
        BufferedReader leitura = new BufferedReader(new FileReader(path));
        String frase = leitura.readLine();
        String teste[] = new String[100];
        teste = frase.split(",");

        while(!(id==Integer.parseInt(teste[0])) && !(isFim(frase))){ //teste[0] = id
            frase = leitura.readLine();
            teste = frase.split(",");
        }
        MyIO.println("(R) "+teste[1]);

    }//Fim imprimeRemovidos 

    public void fazTudo(String comando, String id, Lista Lista,String path) throws Exception{//Inicio fazTudo -- funcao que compara os comandos recebidos e assim chama as funcoes para fazer tal ato
        int idInt = Integer.parseInt(id);
        

        switch(comando){
            case "II":
                Lista.inserirInicio(idInt);
            break;
            
            case "IF":
                Lista.inserirFim(idInt);
            break;

            case "RF":
                imprimeRemovidos(Lista.removerFim(), path);
            break;
            case "RF*":
                imprimeRemovidos(Lista.removerFim(), path);
            break;

            case "RI":
            
               imprimeRemovidos(Lista.removerInicio(), path);
            break;
        }
    }//Fim fazTudo

    public void fazTudoException(String comando, String pos, String id, Lista Lista,String path) throws Exception{//Inicio fazTudoException -- funcao que compara os comandos recebidos e assim chama as funcoes para fazer tal ato, considerando todos os casos , como *
        int idInt = Integer.parseInt(id);
        int posInt = Integer.parseInt(pos);

        switch (comando){
            case "I*": 
                Lista.inserir(idInt, posInt);
            break;

            case "R*":
                imprimeRemovidos(Lista.remover(posInt), path);
            break;
        }
    }//Fim fazTudoException

    public String juntarAtributos(int listaSeparada,String path) throws IOException{//Inico juntarAtributos -- funcao que junta reotrna frase completa do arquivo csv a partir de um id
        BufferedReader leitura = new BufferedReader(new FileReader(path));
        String frase = leitura.readLine();
        String teste[] = new String[500];
        teste = frase.split(",");
 
        while(!(listaSeparada==Integer.parseInt(teste[0])) && !(isFim(frase))){ //teste[0] = id
            frase = leitura.readLine();
            teste = frase.split(",");
        }
    
        return frase;


    }//Fim juntarAtributos
    public void swap(int i, int j,Date array[]) {
        Date temp = array[i];
        array[i] = array[j];
        array[j] = temp;
     }
        
        
     public void sort(Date array[]) {
        int n = array.length;
        quicksort(0, n-1,array);
     }
  
      /**
       * Algoritmo de ordenacao Quicksort.
      * @param int esq inicio do array a ser ordenado
      * @param int dir fim do array a ser ordenado
       */
      private void quicksort(int esq, int dir,Date array[]) {
          int i = esq, j = dir;
          Date pivo = array[(dir+esq)/2];
          while (i <= j) {
              while (array[i].compareTo(pivo)<0) i++;
              while (array[j].compareTo(pivo)>0) j--;
              if (i <= j) {
                  swap(i, j,array);
                  i++;
                  j--;
              }
          }
          if (esq < j)  quicksort(esq, j,array);
          if (i < dir)  quicksort(i, dir,array);
      }

            public boolean pesquisarEmArray(int array[],int x){
                boolean retorno = false;
                for (int i = 0; i < array.length; i++) {
                   if(array[i]==x)retorno=true;
                }
                return retorno;
    
            }

         public String juntarAtributos2(Date listaSeparada,String path,Lista lista,Game Game5) throws IOException{//Inico juntarAtributos -- funcao que junta os atribytos a partir do id da lista
            BufferedReader leitura = new BufferedReader(new FileReader(path));
            String frase = leitura.readLine();
            boolean continuar = true;


    
            while(!(listaSeparada.equals(Game5.getReleaseDate())) && !(isFim(frase)) || continuar){ //teste[0] = id
                continuar = false;
                frase = leitura.readLine();
                Game5.ler(frase);
                if(!lista.pesquisar(Game5.getAppId())){
                    continuar = true;
                } 
            }
            
            return frase;
    
        }//Fim juntarAtributos

        public String achaFraseCerta (String frases[],Date nomesJogosListaInteiros,Game Game){//Inicio achaFraseCerta -- compara um array de string e setorna uma frase caso seja a DATA certa
            String frase;
            int i = 0;
            Game = new Game();
            Game.ler(frases[i]);
            while(!(nomesJogosListaInteiros.equals(Game.getReleaseDate()))){
                Game = new Game();
                i++;
                Game.ler(frases[i]);
                

            }
            frase = frases[i];
            return frase;
        }


        public static void main(String[] args)  throws Exception {//Inicio main
           //--variaveis--//
            long start = System.currentTimeMillis();
            Game Game = new Game();
            Lista lista = new Lista();
            String id = MyIO.readLine();
            int array[] = new int[100];
            String path = "/tmp/games.csv";
            int k = 0;
            //--variaveis--//

            while(!Game.isFim(id)){ //lendo e criando a lista
                
                Game.ler(Game.lerLinhas(path, id)); //pegando a id do verde e convertendo para a linha do csv
                lista.inserirFim(Game.getAppId());//insere id no fim da lista
                array[k] = Game.getAppId();
                id = MyIO.readLine();
                k++;
            }
            Date nomesJogosListaInteiros[] = new Date[lista.tamanho()];
            String frases[] = new String[lista.tamanho()];

            for(int i = 0 ; i<lista.tamanho(); i++){
                    frases[i]=Game.juntarAtributos(array[i], path);//cria array de string com todas as frases do arquivo csv que possuem mesmo id escrito no pubin
                    Game.ler(frases[i]);//Le game para posteriormente pegar a data
                    nomesJogosListaInteiros[i] = Game.getReleaseDate();//atribue datas para array de date
            }

            Game.sort(nomesJogosListaInteiros);//ordena array de date usando quicksort

            for(int i = 0 ; i<lista.tamanho(); i++){
                Game = new Game();
                String fraseCerta ="";
                fraseCerta = Game.achaFraseCerta(frases,nomesJogosListaInteiros[i],Game);//Atribue uma string com a farse certa e ordenada
                Game.ler(fraseCerta);//Le game
                Game.imprimir();//escreve Game
             
               
            }
                       // --- Criando arquivo log --- //
                       FileWriter arq = new FileWriter("matricula_quicksort.txt");
                       PrintWriter gravarArq = new PrintWriter(arq);
                       gravarArq.printf("Matricula: 762732 - QUICKSORT -  Q06\t");
                       gravarArq.printf("N de Comparações: n^2 ou lg(n) ou 1,386 * n * lg(n) - 0,846 * n  \t");
                       gravarArq.printf("N de Movimentos: n/2) \t");
                       long elapsed = System.currentTimeMillis() - start;
                       gravarArq.printf("Tempo de Execução:" + elapsed);
                       arq.close();
                         // --- Criando arquivo log --- //
    
            
    
    
            
    
        }//Fim main
}//Fim classe Game