

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


class Lista extends Game{//Inicio classe Lista
    private int[] array;
    private int n;
 
 
    /**
     * Construtor da classe.
     */
    public Lista  () {
       this(6);
    }

    public int getElemento(int posicao){
        return array[posicao];
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
    public String mostrar (){
       String lista = "";
       for(int i = 0; i < n; i++){
          lista += Integer.toString(array[i])+" ";
       }
       return lista;
       
    }
 
 
    /**
     * Procura um elemento e retorna se ele existe.
     * @param x int elemento a ser pesquisado.
     * @return <code>true</code> se o array existir,
     * <code>false</code> em caso contrario.
     */
    public boolean pesquisar(int x) {
       boolean retorno = false;
       for (int i = 0; i < n && retorno == false; i++) {
          retorno = (array[i] == x);
       }
       return retorno;
    }
    public int getTamanho(){
        return n;
    }
 }//Fim classe  Lista

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

    public int juntarAtributos(int listaSeparada,String path) throws IOException{//Inico juntarAtributos -- funcao que junta os atribytos a partir do id da lista
        BufferedReader leitura = new BufferedReader(new FileReader(path));
        String frase = leitura.readLine();
        String teste[] = new String[500];
        teste = frase.split(",");

        while(!(listaSeparada==Integer.parseInt(teste[0])) && !(isFim(frase))){ //teste[0] = id
            frase = leitura.readLine();
            teste = frase.split(",");
        }
        return Integer.parseInt(teste[0]);

    }//Fim juntarAtributos
        
    
            public void insercao(int array[]) { // Inicio insecao -- funcao que ordena por INSERCAO
                int n = array.length;
                for (int i = 1; i < n; i++) {
                    int tmp = array[i];
                 int j = i - 1;
        
                 while ((j >= 0) && (array[j] > tmp)) {
                    array[j + 1] = array[j];
                    j--;
                 }
                 array[j + 1] = tmp;
              }
            }//Fim insercao

         public String juntarAtributos2(int listaSeparada,String path) throws IOException{//Inico juntarAtributos -- funcao que junta os atribytos a partir do id da lista
            BufferedReader leitura = new BufferedReader(new FileReader(path));
            String frase = leitura.readLine();
            String teste[] = new String[500];
            teste = frase.split(",");
    
            while(!(listaSeparada == Integer.parseInt(teste[0])) && !(isFim(frase))){ //teste[0] = id
                frase = leitura.readLine();
                teste = frase.split(",");
            }
            return frase;
    
        }//Fim juntarAtributos


        public static void main(String[] args)  throws Exception {//Inicio main
           //--Variaveis--//
            long start = System.currentTimeMillis();
            Game Game = new Game();
            Lista lista = new Lista(500);

            String id = MyIO.readLine();
            String path = "/tmp/games.csv";
            boolean resp = false;
            //--Variaveis--//

            while(!Game.isFim(id)){ //lendo e criando a lista
                Game.ler(Game.lerLinhas(path, id)); //pegando a id do verde e convertendo para a linha do csv
                lista.inserirFim(Game.getAppId());//insere id na lista
                if(id.contentEquals("1450100"))resp = true;
                id = MyIO.readLine();
            }

            int nomesJogosListaInteiros[] = new int[lista.getTamanho()];
        
            for(int i = 0 ; i<lista.getTamanho();i++){
                nomesJogosListaInteiros[i] = Game.juntarAtributos(lista.getElemento(i), path); // cria um array de inteiros com os ids
            }
               Game.insercao(nomesJogosListaInteiros);// ordena array de inteiros por id
            
            String nomesJogosLista[] = new String[lista.getTamanho()];

            for(int i = 0 ; i<lista.getTamanho();i++){
                Game Game3 = new Game();//cria novos Games para cada id
                nomesJogosLista[i]=Game.juntarAtributos2(nomesJogosListaInteiros[i], path);// cria um array de string com as frases completas do arquivo csv
                Game3.ler(nomesJogosLista[i]);//ler jogo
                Game3.imprimir();//imprime jogo
            }





                   // --- Criando arquivo log --- //
                   FileWriter arq = new FileWriter("matricula_insercao.txt");
                   PrintWriter gravarArq = new PrintWriter(arq);
                   gravarArq.printf("Matricula: 762732 - INSERCAO -  Q02\t");
                   gravarArq.printf("N de Comparações: (n-1) ou n^2  \t");
                   gravarArq.printf("N de Movimentos: 2(n-1) \t");
                   long elapsed = System.currentTimeMillis() - start;
                   gravarArq.printf("Tempo de Execução:" + elapsed);
                   arq.close();
                     // --- Criando arquivo log --- //
    
        }//Fim main
}//Fim classe Game
