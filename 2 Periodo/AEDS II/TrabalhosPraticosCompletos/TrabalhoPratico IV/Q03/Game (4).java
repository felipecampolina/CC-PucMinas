


/**
EX 03 tp 04
FELIPE CAMPOLINA - 762732
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.net.ssl.TrustManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


class Celula {
	public int elemento; // Elemento inserido na celula.
	public Celula prox; // Aponta a celula prox.


	/**
	 * Construtor da classe.
	 */
	public Celula() {
		this(0);
	}

	/**
	 * Construtor da classe.
	 * @param elemento int inserido na celula.
	 */
	public Celula(int elemento) {
      this.elemento = elemento;
      this.prox = null;
	}
}
 class Fila {
	private Celula primeiro;
	private Celula ultimo;
    public int tamanho = 0;


	/**
	 * Construtor da classe que cria uma fila sem elementos (somente no cabeca).
	 */
	public Fila() {
		primeiro = new Celula();
		ultimo = primeiro;

	}


	/**
	 * Insere elemento na fila (politica FIFO).
	 * @param x int elemento a inserir.
	 * @throws Exception
	 */
	public void inserir(int x) throws Exception {
        if(getTamanho()==5)remover();
		ultimo.prox = new Celula(x);
		ultimo = ultimo.prox;
        this.tamanho++;
	}


	/**
	 * Remove elemento da fila (politica FIFO).
	 * @return Elemento removido.
	 * @trhows Exception Se a fila nao tiver elementos.
	 */
	public int remover() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover!");
		}

      Celula tmp = primeiro;
		primeiro = primeiro.prox;
		int resp = primeiro.elemento;
      tmp.prox = null;
      tmp = null;
      this.tamanho--;
		return resp;
	}


	/**
	 * Mostra os elementos separados por espacos.
	 */
	public String mostrar() {
		String resp = "";
		
		for(Celula i = primeiro.prox; i != null; i = i.prox) {
			resp += i.elemento + " ";
		}
		
		return resp;
	}
    public int getTamanho(){
        return this.tamanho;
    }
 }
public class Game {//Inicio classe Game

    static SimpleDateFormat default_dateFormat = new SimpleDateFormat("MMM/yyyy", Locale.ENGLISH);

    private String name, owners, website, developers;
    private String languages="[", genres="[";
    private Date release_date;
    private int app_id, age, dlcs, avg_playtime;
    private float price, upvotes;
    private boolean windows, mac, linux;
//Construtor
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
//Fim -- Construtor//


//---Setters e Gettres--//
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
//---Setters e Gettres--//

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
    //---Clone---//

    

    public void ler(String line) {//Inicio função ler - resposnavel por pegar uma linha do csv e atribuir 
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
    }



    public void imprimir() {//Inicio imprimir - imprime no formato do enunciado 
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
    }//Fim imprimir

    public String lerLinhas(String path, String id) throws IOException {//Inicio Lerlinha- compara id csv e verde, retornando a linha completa do Game
        BufferedReader leitura = new BufferedReader(new FileReader(path));
        String frase = leitura.readLine();
        String teste[] = new String[100];
        teste = frase.split(",");

        while(!(id.contentEquals(teste[0]) && !(isFim(id)))){ //teste[0] = id
            frase = leitura.readLine();
            teste = frase.split(",");
        }
        return frase;
    }//FIM Lerlinha-

    public String lerLinhasLista(String path, String id) throws IOException {//Inicio LerlinhaLista- compara id csv e verde, retornando id do Game 
        BufferedReader leitura = new BufferedReader(new FileReader(path));
        String frase = leitura.readLine();
        String teste[] = new String[100];
        teste = frase.split(",");

        while(!(id.contentEquals(teste[1]) && !(isFim(id)))){ //teste[1] = nome
            frase = leitura.readLine();
            teste = frase.split(",");
        }
        return teste[0]; //retornando o id
    }//Fim lerLinhasListaS
    
    public boolean isFim(String s){//Inicio is Fim - funcao que identifica se palavra = FIM
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
     }//FIm isFim

    public String[] separaIdString (String idString){//Inicio separaIdString - funcao que pega o id da linha completa do csv
        String [] stringSeparada = new String[500];
        stringSeparada = idString.split(" ");
        return stringSeparada;
        
    }//Fim separaIdString

    public void imprimeRemovidos(int id,String path) throws IOException{//Inicio imprimeRemovidos - imprime nome de games removidos da lista
        BufferedReader leitura = new BufferedReader(new FileReader(path));
        String frase = leitura.readLine();
        String teste[] = new String[100];
        teste = frase.split(",");

        while(!(id==Integer.parseInt(teste[0])) && !(isFim(frase))){ //teste[0] = id
            frase = leitura.readLine();
            teste = frase.split(",");
        }
        MyIO.println("R");
        MyIO.println("(R) "+teste[1]);

    }//Fim imprimeRemovidos

    public void fazTudo(String comando, String id, Fila Fila,String path,String[] mediaString,Fila FilaMedia,int z,float soma,float media) throws Exception{//Inicio fazTudo - funcao que identifica os comandos e ids para isneçâo ou remoção
        int idInt = Integer.parseInt(id);
        

        switch(comando){
            case "I":
                Fila.inserir(idInt);
                MyIO.println(comando+" "+id);
            break;
            case "R":
            
               imprimeRemovidos(Fila.remover(), path);
            break;
            case "R 10":
               imprimeRemovidos(Fila.remover(), path);
            break;
        }
    }//Fim fazTudo

    public String juntarAtributos(int listaSeparada,String path) throws IOException{//Inicio juntaAtributos 
        BufferedReader leitura = new BufferedReader(new FileReader(path));
        String frase = leitura.readLine();
        String teste[] = new String[500];
        teste = frase.split(",");

        while(!(listaSeparada==Integer.parseInt(teste[0])) && !(isFim(frase))){ //teste[0] = id
            frase = leitura.readLine();
            teste = frase.split(",");
        }
        return frase;

    }//Fim juntaAtributos
    public void printaMedia(String[] mediaString,Fila FilaMedia,int z,float soma,float media) throws Exception{//Inicio printaMeida - funcao que calcula a meida da lista e imprime ela

            if(z==1){
                mediaString = FilaMedia.mostrar().split(" ");
                soma = Float.parseFloat(mediaString[0]);
                media = soma/z;
            }
            else if(z==2){
                mediaString = FilaMedia.mostrar().split(" ");
                soma = Float.parseFloat(mediaString[0])+Float.parseFloat(mediaString[1]);
                media = soma/z;
               
            }
            else if (z==3){
                mediaString = FilaMedia.mostrar().split(" ");
                soma = Float.parseFloat(mediaString[0])+Float.parseFloat(mediaString[1])+Float.parseFloat(mediaString[2]);
                media = soma/z;
                

            }
            else if(z==4){
                mediaString = FilaMedia.mostrar().split(" ");
                soma = Float.parseFloat(mediaString[0])+Float.parseFloat(mediaString[1])+Float.parseFloat(mediaString[2])+Float.parseFloat(mediaString[3]);
                media = soma/z;
               

            }
            else{
                mediaString = FilaMedia.mostrar().split(" ");
                soma = Float.parseFloat(mediaString[0])+Float.parseFloat(mediaString[1])+Float.parseFloat(mediaString[2])+Float.parseFloat(mediaString[3])+Float.parseFloat(mediaString[4]);
                media = soma/z;
                
            }
            MyIO.println(Math.round(media));
    }//Fim printaMedia


    public static void main(String[] args)  throws Exception {//Inicio main
        Game Game = new Game();
        Fila Fila = new Fila();
        Fila FilaMedia = new Fila();
        String [] stringSeparada = new String[500];
        String [] listaSeparada = new String[500];
        String id = MyIO.readLine();
        String path = "/tmp/games.csv";
        String idString;
        String nome;
        String listaCheia;
        int numeroLinhas;
        int primeiro = 0;
        int z =1;
        float soma = 0;
        float media = 0;
        String [] mediaString = new String[5];


        while(!Game.isFim(id)){ //lendo e criando a lista
            Game.ler(Game.lerLinhas(path, id)); //pegando a id do verde e convertendo para a linha do csv
            Fila.inserir(Game.getAppId());
            FilaMedia.inserir(Game.getAvgPlaytime());
            Game.printaMedia(mediaString,FilaMedia,z,soma,media);
            if(z!=5)z++;
            id = MyIO.readLine();
            
        }
        numeroLinhas = MyIO.readInt();
        for(int i = 0; i<numeroLinhas; i++){
            idString = MyIO.readLine();
            try{
                stringSeparada = Game.separaIdString(idString); //non static = colocar Game.funcao
                Game.fazTudo(stringSeparada[0], stringSeparada[1], Fila,path,mediaString,FilaMedia,z,soma,media);
            }catch(java.lang.ArrayIndexOutOfBoundsException e){
                Game.fazTudo(stringSeparada[0], "0", Fila,path,mediaString,FilaMedia,z,soma,media);
            }
            try{
            String fraseCompleta = Game.juntarAtributos(Integer.parseInt(stringSeparada[1]),path);
            Game Game3 = new Game();
            Game3.ler(fraseCompleta);
            FilaMedia.inserir(Game3.getAvgPlaytime());
            Game.printaMedia(mediaString,FilaMedia,z,soma,media);
            }catch(java.lang.ArrayIndexOutOfBoundsException e){
            }catch(java.lang.NullPointerException e){
            }
        }

        listaCheia = Fila.mostrar();
        String fraseInteira;
        listaSeparada = Game.separaIdString(listaCheia);

        
        for(int i = 0 ;i<listaSeparada.length;i++){
            Game Game2 = new Game();
            fraseInteira = Game2.juntarAtributos(Integer.parseInt(listaSeparada[i]),path);
            Game2.ler(fraseInteira);
            MyIO.print("["+i+"] ");
            Game2.imprimir();
        }


        

        

    }
}
