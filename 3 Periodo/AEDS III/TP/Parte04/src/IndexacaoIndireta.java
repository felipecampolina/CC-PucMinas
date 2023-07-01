import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

class Celula {
    public String elemento; // Elemento inserido na celula.
    public long posArqOriginal;
    public Celula prox; // Aponta a celula prox.

    /**
     * Construtor da classe.
     */
    public Celula() {
        this("VAZIO", -1);
    }

    /**
     * Construtor da classe.
     * 
     * @param elemento int inserido na celula.
     */
    public Celula(String elemento, long posArqOriginal) {
        this.elemento = elemento;
        this.posArqOriginal = posArqOriginal;
        this.prox = null;
    }
}

class Lista {
    public Celula primeiro;
    public Celula ultimo;

    /**
     * Construtor da classe que cria uma lista sem elementos (somente no cabeca).
     */
    public Lista() {
        primeiro = new Celula();
        ultimo = primeiro;
    }

    /**
     * Insere um elemento na primeira posicao da lista.
     * 
     * @param x int elemento a ser inserido.
     */
    public void inserirInicio(String x, long posArqOriginal) {
        Celula tmp = new Celula(x, posArqOriginal);
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if (primeiro == ultimo) {
            ultimo = tmp;
        }
        tmp = null;
    }

    /**
     * Insere um elemento na ultima posicao da lista.
     * 
     * @param x int elemento a ser inserido.
     */
    public void inserirFim(String x, long posArqOriginal) {
        ultimo.prox = new Celula(x, posArqOriginal);
        ultimo = ultimo.prox;
    }

    /**
     * Remove um elemento da primeira posicao da lista.
     * 
     * @return resp int elemento a ser removido.
     * @throws Exception Se a lista nao contiver elementos.
     */
    public String removerInicio() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover (vazia)!");
        }

        Celula tmp = primeiro;
        primeiro = primeiro.prox;
        String resp = primeiro.elemento;
        tmp.prox = null;
        tmp = null;
        return resp;
    }

    /**
     * Remove um elemento da ultima posicao da lista.
     * 
     * @return resp int elemento a ser removido.
     * @throws Exception Se a lista nao contiver elementos.
     */
    public String removerFim() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover (vazia)!");
        }

        // Caminhar ate a penultima celula:
        Celula i;
        for (i = primeiro; i.prox != ultimo; i = i.prox)
            ;

        String resp = ultimo.elemento;
        ultimo = i;
        i = ultimo.prox = null;

        return resp;
    }

    /**
     * Insere um elemento em uma posicao especifica considerando que o
     * primeiro elemento valido esta na posicao 0.
     * 
     * @param x   int elemento a ser inserido.
     * @param pos int posicao da insercao.
     * @throws Exception Se <code>posicao</code> invalida.
     */

    /**
     * Remove um elemento de uma posicao especifica da lista
     * considerando que o primeiro elemento valido esta na posicao 0.
     * 
     * @param posicao Meio da remocao.
     * @return resp int elemento a ser removido.
     * @throws Exception Se <code>posicao</code> invalida.
     */
    public String remover(int pos) throws Exception {
        String resp;
        int tamanho = tamanho();

        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover (vazia)!");

        } else if (pos < 0 || pos >= tamanho) {
            throw new Exception("Erro ao remover (posicao " + pos + " / " + tamanho + " invalida!");
        } else if (pos == 0) {
            resp = removerInicio();
        } else if (pos == tamanho - 1) {
            resp = removerFim();
        } else {
            // Caminhar ate a posicao anterior a insercao
            Celula i = primeiro;
            for (int j = 0; j < pos; j++, i = i.prox)
                ;

            Celula tmp = i.prox;
            resp = tmp.elemento;
            i.prox = tmp.prox;
            tmp.prox = null;
            i = tmp = null;
        }

        return resp;
    }

    /**
     * Mostra os elementos da lista separados por espacos.
     */
    public void mostrar() {
        System.out.print("[ ");
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            System.out.print(i.elemento + " " + i.posArqOriginal);
        }
        System.out.println("] ");
    }

    /**
     * Procura um elemento e retorna se ele existe.
     * 
     * @param x Elemento a pesquisar.
     * @return <code>true</code> se o elemento existir,
     *         <code>false</code> em caso contrario.
     */
    public boolean pesquisar(String x) {
        boolean resp = false;
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            if (i.elemento == x) {
                resp = true;
                i = ultimo;
            }
        }
        return resp;
    }

    /**
     * Calcula e retorna o tamanho, em numero de elementos, da lista.
     * 
     * @return resp int tamanho
     */
    public int tamanho() {
        int tamanho = 0;
        for (Celula i = primeiro; i != ultimo; i = i.prox, tamanho++)
            ;
        return tamanho;
    }
}

class IndexacaoIndireta extends Lista {
    public Lista lista[] = new Lista[100];

    IndexacaoIndireta(String fileInput, String fileOutput) throws ParseException, IOException {
        for (int i = 0; i < lista.length; i++) {
            lista[i] = new Lista();
        }
    }

    public Lista[] lerArquivoIndexadoIndireto(RandomAccessFile arq) throws IOException {
        String nome = "";
        int tamanhoLista = 0;
        int quantidadeLista = 0;

        Lista lista[] = new Lista[100];
        arq.seek(0);

        while (true) {
            try {
                nome = arq.readUTF();
                
                tamanhoLista = arq.readInt();
                lista[quantidadeLista] = new Lista();

                for (int i = 0; i < tamanhoLista; i++) {
                    lista[quantidadeLista].inserirFim(nome, arq.readLong());
                }
                arq.readUTF();
                quantidadeLista++;
            } catch (java.io.EOFException e) {
                return lista;
            }
        }

    }

    public void lerArquivoDados(String fileInput, String fileOutput, Lista[] lista)
            throws ParseException, IOException {

        RandomAccessFile arq = new RandomAccessFile(fileInput, "rw");

        int len = 0;
        String idProcurado = "";
        long posIni = 0;
        boolean valido;
        byte ba[];
        Filme filmeTemp = new Filme();
        int i = 0;
        // Variáveis e Instâncias//

        while (true) {

            try {
                arq.seek(posIni); // posiciona ponteiro no inicio do arquivo
                valido = arq.readBoolean();// ler lapide -- se TRUE filme existe , caso FALSE filme apagado
                len = arq.readInt(); // ler tamanho do registro
                ba = new byte[len]; // cria um vetor de bytes com o tamanho do registro
                arq.read(ba); // Ler registro
                filmeTemp.fromByteArray(ba); // Transforma vetor de bytes lido por instancia de FIlme

                for (int j = 0; j < lista.length; j++) {
                    if (lista[j].primeiro.prox != null
                            && lista[j].primeiro.prox.elemento.contentEquals(filmeTemp.lingua)) {
                        lista[j].inserirFim(filmeTemp.lingua, posIni);
                        break;
                    } else if (lista[j].primeiro.prox == null) {
                        lista[j].inserirFim(filmeTemp.lingua, posIni);
                        break;
                    }
                }
                posIni = arq.getFilePointer();// Marca posição que acabou o registro e será iniciado outro
            } catch (java.io.IOException e) {
                break;
            }
            i++;
        }
        criaArquivoIndexado(fileOutput, lista);

    }
    public void lerArquivoDadosGeneros(String fileInput, String fileOutput, Lista[] lista)
            throws ParseException, IOException {

        RandomAccessFile arq = new RandomAccessFile(fileInput, "rw");

        int len = 0;
        String idProcurado = "";
        long posIni = 0;
        boolean valido;
        byte ba[];
        Filme filmeTemp = new Filme();
        int i = 0;
        // Variáveis e Instâncias//

        while (true) {

            try {
                arq.seek(posIni); // posiciona ponteiro no inicio do arquivo
                valido = arq.readBoolean();// ler lapide -- se TRUE filme existe , caso FALSE filme apagado
                len = arq.readInt(); // ler tamanho do registro
                ba = new byte[len]; // cria um vetor de bytes com o tamanho do registro
                arq.read(ba); // Ler registro
                filmeTemp.fromByteArray(ba); // Transforma vetor de bytes lido por instancia de FIlme
                

                for (int j2 = 0; j2 < filmeTemp.generos.length; j2++) {
                    
                

                for (int j = 0; j < lista.length; j++) {
                    if (lista[j].primeiro.prox != null
                            && lista[j].primeiro.prox.elemento.contentEquals(filmeTemp.generos[j2])) {
                        lista[j].inserirFim(filmeTemp.generos[j2], posIni);
                        break;
                    } else if (lista[j].primeiro.prox == null) {
                        lista[j].inserirFim(filmeTemp.generos[j2], posIni);
                        break;
                    }
                }
            }
                posIni = arq.getFilePointer();// Marca posição que acabou o registro e será iniciado outro
            } catch (java.io.IOException e) {
                break;
            }
            i++;
        }
        criaArquivoIndexado(fileOutput, lista);

    }

    public void insereArqIndexado(Filme filmeTemp, Lista[] lista, Long posicaoArqOriginal)
            throws ParseException, IOException {

        String fileOutput = "dados/indexIndiretoLingua.db";

        int len = 0;
        String idProcurado = "";
        long posIni = 0;
        boolean valido;
        byte ba[];
        int i = 0;
        // Variáveis e Instâncias//

        for (int j = 0; j < lista.length; j++) {

            if (lista[j].primeiro.prox != null
                    && lista[j].primeiro.prox.elemento.contentEquals(filmeTemp.lingua)) {
                lista[j].inserirFim(filmeTemp.lingua, posicaoArqOriginal);
                break;
            } else if (lista[j].primeiro.prox == null) {
                lista[j].inserirFim(filmeTemp.lingua, posicaoArqOriginal);
                break;
            }
        }
        criaArquivoIndexado(fileOutput, lista);

    }
    public void insereArqIndexadoGeneros(Filme filmeTemp, Lista[] lista, Long posicaoArqOriginal)
            throws ParseException, IOException {

        String fileOutput = "dados/indexIndiretoGeneros.db";

        int len = 0;
        String idProcurado = "";
        long posIni = 0;
        boolean valido;
        byte ba[];
        int i = 0;
        // Variáveis e Instâncias//

        for (int j2 = 0; j2 < filmeTemp.generos.length; j2++) {
            
        
        for (int j = 0; j < lista.length; j++) {

            if (lista[j].primeiro.prox != null
                    && lista[j].primeiro.prox.elemento.contentEquals(filmeTemp.generos[j2])) {
                lista[j].inserirFim(filmeTemp.generos[j2], posicaoArqOriginal);
                break;
            } else if (lista[j].primeiro.prox == null) {
                lista[j].inserirFim(filmeTemp.generos[j2], posicaoArqOriginal);
                break;
            }
        }
    }
        criaArquivoIndexado(fileOutput, lista);

    }

    public void criaArquivoIndexado(String fileOutput, Lista[] lista) throws IOException {

        File f = new File(fileOutput);
        if (f.delete())
            System.out.println("arqdeletado");

        RandomAccessFile saida = new RandomAccessFile(fileOutput, "rw");

        for (int i = 0; i < lista.length; i++) {
            if (lista[i].primeiro.prox != null) {
                saida.writeUTF(lista[i].primeiro.prox.elemento);
                saida.writeInt(lista[i].tamanho());
                //System.out.println(lista[i].tamanho());
                for (Celula j = lista[i].primeiro.prox; j != null; j = j.prox) {
                    // System.out.print(j.elemento + " " + j.posArqOriginal);
                    saida.writeLong(j.posArqOriginal);
                }
                saida.writeUTF("?");
            }

        }
        saida.close();
        System.out.println("Index Indireto criado");

    }

    public long[] read2(String idProcurado, Lista[] lista) {
        long resultados[];
        resultados = new long[0];
        int x = 0;

        for (int i = 0; i < lista.length; i++) {
            if (lista[i].primeiro.prox != null && idProcurado.contentEquals(lista[i].primeiro.prox.elemento)) {
                resultados = new long[lista[i].tamanho()];
                for (Celula g = lista[i].primeiro.prox; g != null; g = g.prox) {
                    resultados[x] = g.posArqOriginal;
                    x++;
                }
                break;

            }
        }
        return resultados;

    }

    public Lista[] removeArqIndexado(Filme filmeTemp, Lista[] lista, Long posicaoArqOriginal)
            throws Exception {

        String fileOutput = "dados/indexIndiretoLingua.db";

        int len = 0;
        String idProcurado = "";
        long posIni = 0;
        boolean valido;
        byte ba[];
        int i = 0;
        // Variáveis e Instâncias//

        for (int j = 0; j < lista.length; j++) {

            if (lista[j].primeiro.prox != null
                    && lista[j].primeiro.prox.elemento.contentEquals(filmeTemp.lingua)) {
                for (Celula c = lista[j].primeiro.prox; c != null; c = c.prox, i++) {
                    if (c.posArqOriginal == posicaoArqOriginal) {
                        System.out.println(lista[j].remover(i));
                    }
                }
            }
        }

        return lista;

        // criaArquivoIndexado(fileOutput, lista);
    }

}