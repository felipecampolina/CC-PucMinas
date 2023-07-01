
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Game {// Inicio classe Game
    // ---atributos---//
    static SimpleDateFormat default_dateFormat = new SimpleDateFormat("MMM/yyyy", Locale.ENGLISH);
    private String name, owners, website, developers;
    private String languages = "[", genres = "[";
    private Date release_date;
    private int app_id, age, dlcs, avg_playtime;
    private float price, upvotes;
    private boolean windows, mac, linux;
    // ---atributos---//

    // ---Construtores---//
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
    // ---Fim atributos---//

    // ---Setters e Getters---//
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
    // ---Fim Setters e Getters---//

    // ---Clone---//
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

    // ---Fim Clone---//

    public void ler(String line) {// Inicio ler, funcao que ler linha inteira e atribue variaveis a suas
                                  // respectivos lugares
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
                        if ((line.charAt(index + 1) == ']'))
                            this.languages += line.substring(wordStart, index) + "";
                        else
                            this.languages += line.substring(wordStart, index) + ", ";
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
                        this.genres += (line.substring(atr_index, index) + ", ");
                        atr_index = ++index;
                    } else if (line.charAt(index) == '\"') {
                        this.genres += (line.substring(atr_index, line.length() - 1) + "");
                        break;
                    }
                }
            } else {
                this.genres += line.substring(atr_index, line.length());
            }
        }
    }// Fim ler

    public void imprimir() {// Inicio imprimir -- funcao que imprime em padrao pedido no enunciado
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
                + this.owners + " " + this.age + " " + String.format("%.2f", this.price) + " " + this.dlcs + " "
                + this.languages + ']' + " " + this.website + " " + this.windows + " " + this.mac + " " + this.linux
                + " "
                + (Float.isNaN(this.upvotes) ? "0.0% " : df.format(this.upvotes) + "% ") + avg_pt + this.developers
                + " " + this.genres + ']');
    }// Fim imprime

    public String lerLinhas(String path, String id) throws IOException {// Inicio lerLinhas -- função que ler linahs do
                                                                        // verde e compara com csv
        BufferedReader leitura = new BufferedReader(new FileReader(path));
        String frase = leitura.readLine();
        String teste[] = new String[100];
        teste = frase.split(",");

        while (!(id.contentEquals(teste[0]) && !(isFim(id)))) { // teste[0] = id
            frase = leitura.readLine();
            teste = frase.split(",");
        }
        return frase;
    }// Fim lerLinahs

    public String lerLinhasLista(String path, String id) throws IOException {// Inicio lerLinhasLista -- função que ler
                                                                             // linahs do verde e compara com csv,
                                                                             // retornando o id
        BufferedReader leitura = new BufferedReader(new FileReader(path));
        String frase = leitura.readLine();
        String teste[] = new String[100];
        teste = frase.split(",");

        while (!(id.contentEquals(teste[1]) && !(isFim(id)))) { // teste[1] = nome
            frase = leitura.readLine();
            teste = frase.split(",");
        }
        return teste[0]; // retornando o id
    }// Fim lerLinhasLista

    public boolean isFim(String s) {// Inicio isFim -- funcao que verifica se a frase = FIM
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }// Fim is Fim

    public String[] separaIdString(String idString) {// Iniicio seperaIdString -- funcao que retorna um array de string
                                                     // da frase inteira
        String[] stringSeparada = new String[500];
        stringSeparada = idString.split(" ");
        return stringSeparada;

    }// Fim separaIdString

    public void imprimeRemovidos(int id, String path) throws IOException {// Inicio imprimeRemovidos -- funcao que
                                                                          // imprime o NOME dos jogos removidos da lista
        BufferedReader leitura = new BufferedReader(new FileReader(path));
        String frase = leitura.readLine();
        String teste[] = new String[100];
        teste = frase.split(",");

        while (!(id == Integer.parseInt(teste[0])) && !(isFim(frase))) { // teste[0] = id
            frase = leitura.readLine();
            teste = frase.split(",");
        }
        MyIO.println("(R) " + teste[1]);

    }// Fim imprimeRemovidos

    public String juntarAtributos(int listaSeparada, String path) throws IOException {// Inico juntarAtributos -- funcao
                                                                                      // que junta os atribytos a partir
                                                                                      // do id da lista
        BufferedReader leitura = new BufferedReader(new FileReader(path));
        String frase = leitura.readLine();
        String teste[] = new String[500];
        teste = frase.split(",");

        while (!(listaSeparada == Integer.parseInt(teste[0])) && !(isFim(frase))) { // teste[0] = id
            frase = leitura.readLine();
            teste = frase.split(",");
        }
        return frase;

    }// Fim juntarAtributos
}

class No1 {
    public char elemento; // Conteudo do no.
    public No1 esq; // No da esquerda.
    public No1 dir; // No da direita.
    public No2 outro;

    /**
     * Construtor da classe.
     * 
     * @param elemento Conteudo do no.
     */
    No1(char elemento) {
        this.elemento = elemento;
        this.esq = this.dir = null;
        this.outro = null;
    }

    /**
     * Construtor da classe.
     * 
     * @param elemento Conteudo do no.
     * @param esq      No da esquerda.
     * @param dir      No da direita.
     */
    No1(char elemento, No1 esq, No1 dir) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
        this.outro = null;
    }
}

class No2 {
    public Game game; // Conteudo do no.
    public No2 esq; // No da esquerda.
    public No2 dir; // No da direita.

    /**
     * Construtor da classe.
     * 
     * @param elemento Conteudo do no.
     */
    No2(Game game) {
        this.game = game;
        this.esq = this.dir = null;
    }

    /**
     * Construtor da classe.
     * 
     * @param elemento Conteudo do no.
     * @param esq      No2 da esquerda.
     * @param dir      No2 da direita.
     */
    No2(Game game, No2 esq, No2 dir) {
        this.game = game;
        this.esq = esq;
        this.dir = dir;
    }
}

class ArvoreArvore {
    private No1 raiz; // Raiz da arvore.

    /**
     * Construtor da classe.
     * 
     * @throws Exception
     */
    public ArvoreArvore() throws Exception {
        // D, R, Z, X, V, B, F, P, U, I, G, E, J, L, H, T, A, W, S, O, M, N, K, C, Y, Q.
        // A
        raiz = null;
        inserir('D');
        inserir('R');
        inserir('Z');
        inserir('X');
        inserir('V');
        inserir('B');
        inserir('F');
        inserir('P');
        inserir('U');
        inserir('I');
        inserir('G');
        inserir('E');
        inserir('J');
        inserir('L');
        inserir('H');
        inserir('T');
        inserir('A');
        inserir('W');
        inserir('S');
        inserir('O');
        inserir('M');
        inserir('N');
        inserir('K');
        inserir('C');
        inserir('Y');
        inserir('Q');
        inserir('A');
        // os outros 23 caracteres.
    }

    public void inserir(char x) throws Exception {
        raiz = inserir(x, raiz);
    }

    /**
     * Metodo privado recursivo para inserir elemento.
     * 
     * @param x Elemento a ser inserido.
     * @param i No em analise.
     * @return No em analise, alterado ou nao.
     * @throws Exception Se o elemento existir.
     */
    private No1 inserir(char x, No1 i) throws Exception {
        if (i == null) {
            i = new No1(x);

        } else if (x< i.elemento) {
            i.esq = inserir(x, i.esq);

        } else if (x > i.elemento) {
            i.dir = inserir(x, i.dir);

        }

        return i;
    }

    public void inserir(Game s) throws Exception {
        inserir(s, raiz);
    }

    public void inserir(Game s, No1 i) throws Exception {
        if (i == null) {
            throw new Exception("Erro ao inserir: caractere invalido!");

        } else if (s.getName().charAt(0) < i.elemento) {
            inserir(s, i.esq);

        } else if (s.getName().charAt(0) > i.elemento) {
            inserir(s, i.dir);

        } else {
            i.outro = inserir(s, i.outro);
        }
    }

    private No2 inserir(Game s, No2 i) throws Exception {
        if (i == null) {
            i = new No2(s);

        } else if (s.getName().compareTo(i.game.getName()) < 0) {
            i.esq = inserir(s, i.esq);

        } else if (s.getName().compareTo(i.game.getName()) > 0) {
            i.dir = inserir(s, i.dir);

        }

        return i;
    }

    /**
     * Metodo publico iterativo para pesquisar elemento.
     * 
     * @param elemento Elemento que sera procurado.
     * @return <code>true</code> se o elemento existir,
     *         <code>false</code> em caso contrario.
     */
    public boolean pesquisar(String elemento) {
        System.out.println(elemento);
        System.out.print("raiz ");
        return pesquisar(raiz, elemento);
    }

    private boolean pesquisar(No1 no, String x) {
        boolean resp = false;
        if(no !=null){
            if(!resp)resp = pesquisarSegundaArvore(no.outro,x);
            if(resp){
                return resp;
            }
            else{
            if(!resp) System.out.print("ESQ ");
            if(!resp) resp = pesquisar(no.esq, x);
            if(!resp)System.out.print("DIR ");
            if(!resp)resp = pesquisar(no.dir, x);
            }
        }
        return resp;
    }
        
    private boolean pesquisarSegundaArvore(No2 no, String x) {
        boolean resp;
        if (no == null) {
            resp = false;

        } else if (x.compareTo(no.game.getName()) < 0) {
            System.out.print("esq ");
            resp = pesquisarSegundaArvore(no.esq, x);

        } else if (x.compareTo(no.game.getName()) > 0) {
            System.out.print("dir ");
            resp = pesquisarSegundaArvore(no.dir, x);

        } else {
            resp = true;
        }
        return resp;
    }

    public String converteNomeDoJogoParaId(String nomeDoJogo) {
        return converteNomeDoJogoParaId(nomeDoJogo, raiz);
    }

    /**
     * Metodo privado recursivo para pesquisar elemento.
     * 
     * @param x Elemento que sera procurado.
     * @param i No em analise.
     * @return <code>true</code> se o elemento existir,
     *         <code>false</code> em caso contrario.
     */
    private String converteNomeDoJogoParaId(String x, No1 i) {
        String resp = "";
        if (i == null) {
            resp = "DEU RUIM";

        } else if (x.charAt(0) < i.elemento) {
            resp = converteNomeDoJogoParaId(x, i.esq);

        } else if (x.charAt(0) > i.elemento) {
            resp = converteNomeDoJogoParaId(x, i.dir);

        } else {
            resp = converteNomeDoJogoParaId2(x,i.outro);
        }
        return resp;
    }
    private String converteNomeDoJogoParaId2(String x, No2 i) {
        String resp = "";
          if (i == null) {
           resp = "DEU RUIM";
  
        } else if (x.compareTo(i.game.getName()) == 0) {
           resp = Integer.toString(i.game.getAppId());
  
        } else if (x.compareTo(i.game.getName()) < 0) {
           resp = converteNomeDoJogoParaId2(x, i.esq);
  
        } else {
           resp = converteNomeDoJogoParaId2(x, i.dir);
        }
        return resp;
      }

    public void remover(Game x) throws Exception {
        remover(x, raiz);
    }

    /**
     * Metodo privado recursivo para remover elemento.
     * 
     * @param x Elemento a ser removido.
     * @param i No em analise.
     * @return No em analise, alterado ou nao.
     * @throws Exception Se nao encontrar elemento.
     */
    private void remover(Game x, No1 i) throws Exception {

        if (i == null) {

        } else if (x.getName().charAt(0) < i.elemento) {
            remover(x, i.esq);

        } else if (x.getName().charAt(0) > i.elemento) {
            remover(x, i.dir);

        } else {
            i.outro = remover(x, i.outro);
        }
    }
    private No2 remover(Game x, No2 i) throws Exception {
            if (i == null) {
             throw new Exception("Erro ao remover!");
    
          } else if (x.getName().compareTo(i.game.getName())<0) {
             i.esq = remover(x, i.esq);
    
          } else if (x.getName().compareTo(i.game.getName())>0) {
             i.dir = remover(x, i.dir);
    
          // Sem no a direita.
          } else if (i.dir == null) {
             i = i.esq;
    
          // Sem no a esquerda.
          } else if (i.esq == null) {
             i = i.dir;
    
          // No a esquerda e no a direita.
          } else {
             i.esq = maiorEsq(i, i.esq);
            }
    
            return i;
        }
    
    private No2 maiorEsq(No2 i, No2 j) {

        // Encontrou o maximo da subarvore esquerda.
          if (j.dir == null) {
              i.game = j.game; // Substitui i por j.
              j = j.esq; // Substitui j por j.ESQ.
  
        // Existe no a direita.
          } else {
           // Caminha para direita.
              j.dir = maiorEsq(i, j.dir);
          }
          return j;
      }
    }

class tp05q02 {
    public static void main(String[] args) throws Exception {// Inicio main
        Game Game = new Game();
        String id = MyIO.readLine();
        String path = "/tmp/games.csv";
        ArvoreArvore arvoreArvore = new ArvoreArvore();
        int numeroLinhas = 0;
        String[] stringSeparada = new String[500];
        String palavraPesquisada;
        String idString;
        String nomeDoJogo;
        String idJogoArvore;

        while (!Game.isFim(id)) { // lendo e criando a lista
            Game = new Game();
            Game.ler(Game.lerLinhas(path, id)); // pegando a id do verde e convertendo para a linha do csv
            arvoreArvore.inserir(Game);
            id = MyIO.readLine();
        }
        numeroLinhas = MyIO.readInt();// ler quantidade de comandos

        for (int i = 0; i < numeroLinhas; i++) {
            String linhaCSV;
            idString = MyIO.readLine();
            stringSeparada = Game.separaIdString(idString); // non static = colocar Game.funcao
            if (stringSeparada[0].contentEquals("I")) {
                Game = new Game();
                Game.ler(Game.lerLinhas(path, stringSeparada[1])); // pegando a id do verde e convertendo para a linha
                                                                   // do csv
                arvoreArvore.inserir(Game);
            } else {
                nomeDoJogo = "";
                for (int j = 1; j < stringSeparada.length; j++) {
                    if (j != stringSeparada.length - 1)
                        nomeDoJogo += stringSeparada[j] + " ";
                    else
                        nomeDoJogo += stringSeparada[j];
                }
                idJogoArvore = arvoreArvore.converteNomeDoJogoParaId(nomeDoJogo);
                Game = new Game();
                Game.ler(Game.lerLinhas(path, idJogoArvore)); // pegando a id do verde e convertendo para a linha do csv
                arvoreArvore.remover(Game);

            }
        }
        palavraPesquisada = MyIO.readLine();
        while (!palavraPesquisada.contentEquals("FIM")) {

            if (arvoreArvore.pesquisar(palavraPesquisada))
                MyIO.println("SIM");
            else
                MyIO.println("NAO");
            palavraPesquisada = MyIO.readLine();

        }

    }// Fim main
}
