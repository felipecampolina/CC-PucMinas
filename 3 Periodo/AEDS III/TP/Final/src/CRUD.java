/* Terceira Parte - Trabalho Prático AEDS 3
 * Alunos: Felipe Campolina e Gabriela Colen
 * 
 * ATENÇÃO!
 * Você deve alterar o caminho para a leitura dos dados de acordo com seu computador.
 * Isso estará sinalizado na main.
 * Caso você não faça, aparecerá uma mensagem de erro e o programa não ira rodar!
 */

//Bibliotecas
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class CRUD {
    static SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

    // A função lê uma linha do CSV e retorna uma instância de Filme
    public static Filme leDadosCsv(BufferedReader leitura) throws IOException, ParseException {
        String frase = leitura.readLine();
        frase = frase.replace("\"", ""); // remover " das linhas
        while (frase.contains(";;")) {
            frase = frase.replace(";;", ";NULL;"); // remover " das linhas
        }
        return trataDados(frase);

    }

    /* A função trata os dados e retorna uma instância de Filme */
    public static Filme trataDados(String frase) throws ParseException {
        String[] fraseTratada = new String[6];
        fraseTratada = frase.split(";"); // divide linha csv a cada ponto e vírgula (;)

        // Tratar gêneros
        String[] generos = new String[20];// cria um array de String para representar os generos
        fraseTratada[5] = fraseTratada[5].replace(" ", "");// remove espaço entre cada genero
        generos = fraseTratada[5].split(",");// divide os generos a cada ,

        // Tratar data
        Date dataCerta = new Date(); // cria um tipo DATE

        if (!fraseTratada[4].contentEquals("NULL"))
            dataCerta = dataFormatada.parse(fraseTratada[4]); // atribue a data, a patir de uma função para formatar ela
        else {
            dataCerta = dataFormatada.parse("01/01/2023");
        }
        Filme filme = new Filme(Integer.parseInt(fraseTratada[0]), fraseTratada[2], fraseTratada[3], dataCerta,
                generos); // chama construtor passando os parametros certos
        return filme; // retorna filme
    }

    /* Função CREATE do CRUD */
    public static int criar(RandomAccessFile arq, int ultimoID, IndexacaoArvoreB index, IndexacaoTabelaHash index2,
            IndexacaoIndireta index3, IndexacaoIndireta index4)
            throws Exception {
        // Variáveis e Instâncias
        int tamGeneros;
        String tituloTemp, dataTempString, linguaTemp;
        DateFormat dataFormatadaTemp = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date dataTemp;
        Scanner sc = new Scanner(System.in);
        byte ba[];
        long posIni = 0;

        ultimoID = ultimoID + 1;
        posIni = arq.length();
        arq.seek(posIni); // posiciona o ponteiro do arquivo no final , ou seja, todos filmes novos serão
                          // adicionados no final.

        // Pedir para os usuários informações do filme a ser adicionado
        System.out.println("Insira o título do Filme: ");
        tituloTemp = sc.nextLine(); // TITULO
        System.out.println("Insira a língua do Filme: (xx)");
        linguaTemp = sc.nextLine(); // IDIOMA
        System.out.println("Insira a data de lançamento do Filme: (dd/MM/yyyy)");
        dataTempString = sc.nextLine();// DATA
        dataTemp = dataFormatadaTemp.parse(dataTempString);
        System.out.println("Insira o número de gêneros: ");
        tamGeneros = Integer.parseInt(sc.nextLine());// GENEROS
        String generosTemp[] = new String[tamGeneros];

        for (int i = 0; i < tamGeneros; i++) {
            generosTemp[i] = sc.nextLine();
        }

        // Cria uma instância de filme com as informações digitadas pelo usuário
        Filme filmeTemp = new Filme(ultimoID, tituloTemp, linguaTemp, dataTemp, generosTemp);

        ba = filmeTemp.toByteArray(); // Transforma filme em vetor de bytes
        arq.writeBoolean(true); // Escreve lápide
        arq.writeInt(ba.length); // Escreve tamanho do vetor de bytes(Registro)
        arq.write(ba); // Escreve registro

        index.create(Integer.toString(filmeTemp.id), Long.valueOf(posIni).intValue());
        index2.create(filmeTemp.id, posIni);
        index3.insereArqIndexado(filmeTemp, index3.lista, posIni);
        index4.insereArqIndexadoGeneros(filmeTemp, index4.lista, posIni);

        System.out.println("Filme criado com sucesso. ID = " + ultimoID);

        return ultimoID;
    }

    public static int resetarBancoDeDados(RandomAccessFile arq, BufferedReader leitura)
            throws IOException, ParseException { // Função que cria/reseta arquivo .db , com as informações lidas pelo
                                                 // arquivo CSV

        int ultimoID = 0;
        arq.seek(0); // Posiciona ponteiro no início do arquivo
        byte ba[];
        System.out.println("Resetando Banco de Dados...");
        int i = 0;

        while (i < 1000) { // le TODOS filmes do arquivo CSV e salva em bytes em um arquivo .db
            Filme filme = leDadosCsv(leitura);// Cria uma instancia de filme a partir de cada linha csv
            // System.out.println(filme.titulo);
            ba = filme.toByteArray();// transforma filme em vetor de bytes
            arq.writeBoolean(true); // Escreve lápide
            arq.writeInt(ba.length); // Escreve tamanho do vetor de bytes(Registro)
            arq.write(ba); // Escreve registro
            ultimoID++;
            i++;

        }
        System.out.println("Reset concluído");
        return ultimoID;
    }

    /* Função READ do CRUD */
    public static void ler(RandomAccessFile arq, IndexacaoArvoreB index) throws IOException, ParseException {
        // Variáveis e Instâncias//
        int len = 0;
        String idProcurado = "";
        long posIni = 0;
        boolean valido;
        byte ba[];
        Scanner sc = new Scanner(System.in);
        Filme filmeTemp = new Filme();
        // Variáveis e Instâncias//

        System.out.println("Digite o id: "); // Pede id para usuário
        idProcurado = sc.nextLine();
        posIni = index.read(idProcurado);

        try {
            arq.seek(posIni); // posiciona ponteiro no inicio do arquivo
            valido = arq.readBoolean();// ler lapide -- se TRUE filme existe , caso FALSE filme apagado
            len = arq.readInt(); // ler tamanho do registro
            ba = new byte[len]; // cria um vetor de bytes com o tamanho do registro
            arq.read(ba); // Ler registro
            filmeTemp.fromByteArray(ba); // Transforma vetor de bytes lido por instancia de FIlme
            posIni = arq.getFilePointer();// Marca posição que acabou o registro e será iniciado outro

            if (valido == true) { // caso idProcurado e id do filme lido forem iguais
                                  // e filme não tver sido apagado será escrito as
                                  // informações.
                System.out.println("-------------------------");
                System.out.println("");
                System.out.println("POS = " + posIni);
                System.out.println("ID = " + filmeTemp.id);
                System.out.println("TITULO = " + filmeTemp.titulo);
                System.out.println("LINGUA = " + filmeTemp.lingua);
                System.out.println("DATA " + filmeTemp.data);
                System.out.println("GENEROS: ");

                for (int j = 0; j < filmeTemp.generos.length; j++) {
                    System.out.println(filmeTemp.generos[j]);
                }
            }
        } catch (java.io.IOException e) {
            System.out.println("Não achei o filme"); // Erro fim do arquivo , ou seja , não achou o
                                                     // filme
        }

        /*
         * Vale ressaltar que foi usado um while(true) tanto no read quanto no update e
         * delete, pois mesmo que o id certo for
         * achado ele podia estar apagado, e se um filme com o mesmo id fosse criado,
         * não seria identificado, dessa forma , esse erro identificado foi corrigido.
         */
    }

    /* Função READ do CRUD */
    public static void lerHash(RandomAccessFile arq, IndexacaoTabelaHash index)
            throws NumberFormatException, Exception {
        // Variáveis e Instâncias//
        int len = 0;
        String idProcurado = "";
        long posIni = 0;
        boolean valido;
        byte ba[];
        Scanner sc = new Scanner(System.in);
        Filme filmeTemp = new Filme();
        // Variáveis e Instâncias//

        System.out.println("Digite o id: "); // Pede id para usuário
        idProcurado = sc.nextLine();
        posIni = index.read(Integer.parseInt(idProcurado));

        try {
            arq.seek(posIni); // posiciona ponteiro no inicio do arquivo
            valido = arq.readBoolean();// ler lapide -- se TRUE filme existe , caso FALSE filme apagado
            len = arq.readInt(); // ler tamanho do registro
            ba = new byte[len]; // cria um vetor de bytes com o tamanho do registro
            arq.read(ba); // Ler registro
            filmeTemp.fromByteArray(ba); // Transforma vetor de bytes lido por instancia de FIlme
            posIni = arq.getFilePointer();// Marca posição que acabou o registro e será iniciado outro

            if (valido == true) { // caso idProcurado e id do filme lido forem iguais
                                  // e filme não tver sido apagado será escrito as
                                  // informações.
                System.out.println("-------------------------");
                System.out.println("");
                System.out.println("POS = " + posIni);
                System.out.println("ID = " + filmeTemp.id);
                System.out.println("TITULO = " + filmeTemp.titulo);
                System.out.println("LINGUA = " + filmeTemp.lingua);
                System.out.println("DATA " + filmeTemp.data);
                System.out.println("GENEROS: ");

                for (int j = 0; j < filmeTemp.generos.length; j++) {
                    System.out.println(filmeTemp.generos[j]);
                }
            }
        } catch (java.io.IOException e) {
            System.out.println("Não achei o filme"); // Erro fim do arquivo , ou seja , não achou o
                                                     // filme
        }

        /*
         * Vale ressaltar que foi usado um while(true) tanto no read quanto no update e
         * delete, pois mesmo que o id certo for
         * achado ele podia estar apagado, e se um filme com o mesmo id fosse criado,
         * não seria identificado, dessa forma , esse erro identificado foi corrigido.
         */
    }

    /* Função DELETE do CRUD */
    public static void deletar(RandomAccessFile arq, IndexacaoArvoreB index, IndexacaoTabelaHash index2,
            IndexacaoIndireta index3)
            throws Exception {
        // Variáveis e Instâncias
        int idDeletado = 0, len = 0;
        long posIni = 0, posDel = 0;
        byte ba[];
        boolean valido;
        Filme filmeTemp = new Filme();

        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o id que você deseja deletar: ");// Pede id para usuário do filme a ser deletado
        idDeletado = sc.nextInt();

        try {
            // Funcionamento similar ao READ, porém a posição incial é marcada por meio da
            // variavel posDel

            posDel = index.read(Integer.toString(idDeletado));

            arq.seek(posDel);
            valido = arq.readBoolean();
            len = arq.readInt();
            ba = new byte[len];
            arq.read(ba);
            filmeTemp.fromByteArray(ba);
            posDel = arq.getFilePointer();

            if (valido == true) {
                arq.seek(posIni);
                arq.writeBoolean(false);// lápide <- false
                index.delete(Integer.toString(filmeTemp.id));
                index2.delete(filmeTemp.id);
                index3.lista = index3.removeArqIndexado(filmeTemp, index3.lista, posDel);
                System.out.println("Filme Deletado com sucesso!");
            }
        } catch (java.io.IOException e) {
            System.out.println("Não achei o filme");
        }

    }

    /* Função DELETE do CRUD recebendo o ID como parâmetro */
    public static void deletar(RandomAccessFile arq, int idDeletado, IndexacaoArvoreB index, IndexacaoTabelaHash index2)
            throws Exception {
        int len = 0;
        long posIni = 0, posDel = 0;
        byte ba[];
        boolean valido;
        Filme filmeTemp = new Filme();
        while (true) {
            try {
                arq.seek(posDel);
                posIni = arq.getFilePointer();
                valido = arq.readBoolean();
                len = arq.readInt();
                ba = new byte[len];
                arq.read(ba);
                filmeTemp.fromByteArray(ba);
                posDel = arq.getFilePointer();

                if (idDeletado == filmeTemp.id && valido == true) {
                    arq.seek(posIni);
                    arq.writeBoolean(false);
                    index.delete(Integer.toString(filmeTemp.id));
                    index2.delete(filmeTemp.id);
                    System.out.println("Filme Deletado com sucesso!");
                    break;
                }
            } catch (EOFException e) {
                System.out.println("Acabou o arquivo não achei o filme");
                break;
            }
        }

    }

    public static void alterar(RandomAccessFile arq, IndexacaoArvoreB index, IndexacaoTabelaHash index2,
            IndexacaoIndireta index3, IndexacaoIndireta index4) throws Exception {
        // Variáveis e Instâncias//
        RandomAccessFile arqIndiretoLingua = new RandomAccessFile("dados/indexIndiretoLingua.db", "rw");
        RandomAccessFile arqIndiretoGenero = new RandomAccessFile("dados/indexIndiretoGeneros.db", "rw");
        int idProcurado = 0, tamAntigo = 0, tamGeneros = 0;
        long posIni = 0, posDel = 0;
        String tituloTemp, linguaTemp, dataTempString;
        boolean valido;
        byte ba[];
        Scanner sc = new Scanner(System.in);
        DateFormat dataFormatadaTemp = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date dataTemp;
        Filme filmeTemp = new Filme();
        Filme filmeNovo = new Filme();

        System.out.println("Digite o id: ");
        idProcurado = Integer.parseInt(sc.nextLine()); // Pede id do filme a ser alterado

        while (true) {
            try {
                // Funcionamento idêntico ao DELETE, favor conferir
                arq.seek(posDel);
                posIni = arq.getFilePointer();
                valido = arq.readBoolean();
                tamAntigo = arq.readInt();
                ba = new byte[tamAntigo];
                arq.read(ba);
                filmeTemp.fromByteArray(ba);
                posDel = arq.getFilePointer();

                if (idProcurado == filmeTemp.id && valido == true) { // Caso filme exista e não tenha sido apagado
                    // Pede novas Informaçoes ao usuário
                    System.out.println("Insira o novo título do Filme: ");
                    tituloTemp = sc.nextLine();
                    System.out.println("Insira a nova língua do Filme: (xx)");
                    linguaTemp = sc.nextLine();
                    System.out.println("Insira a nova data de lançamento do Filme: (dd/MM/yyyy)");
                    dataTempString = sc.nextLine();
                    dataTemp = dataFormatadaTemp.parse(dataTempString);
                    System.out.println("Insira o número de gêneros: ");
                    tamGeneros = Integer.parseInt(sc.nextLine());
                    String generosTemp[] = new String[tamGeneros];

                    for (int i = 0; i < tamGeneros; i++) {
                        generosTemp[i] = sc.nextLine();
                    }

                    // Cria uma instância de filmes com as novas informações
                    filmeNovo = new Filme(idProcurado, tituloTemp, linguaTemp, dataTemp, generosTemp);

                    ba = filmeNovo.toByteArray();// filme <- byte array

                    if (ba.length == tamAntigo) { // se novo registro for menor ou igual antigo sobreescreve,
                                                  // caso não é deletado e criado de novo no final do arqruivo
                        arq.seek(posIni);
                        arq.writeBoolean(true);
                        arq.writeInt(ba.length); // Tamanho do registro em bytes
                        arq.write(ba);
                    } else {
                        deletar(arq, idProcurado, index, index2);
                        int posNova = Long.valueOf(arq.length()).intValue();
                        arq.seek(posNova);
                        arq.writeBoolean(true);
                        arq.writeInt(ba.length); // Tamanho do registro em bytes
                        arq.write(ba);
                        index.create(Integer.toString(filmeNovo.id), posNova);
                        index2.create(filmeNovo.id, posNova);
                    }

                    break;
                }
            } catch (EOFException e) {
                System.out.println("Acabou o arquivo não achei o filme");

                break;
            }
        }

    }

    public static int getUltimoID(RandomAccessFile arq) throws IOException, ParseException {
        // Variáveis e Instâncias//
        int len = 0;
        long posIni = 0;
        boolean valido;
        byte ba[];
        Scanner sc = new Scanner(System.in);
        int ultimoID = 0;
        Filme filmeTemp = new Filme();

        while (true) {
            try {
                arq.seek(posIni); // posiciona ponteiro no inicio do arquivo
                valido = arq.readBoolean();// leitura da lapide -- se TRUE filme existe, caso FALSE filme apagado
                len = arq.readInt(); // leitura tamanho do registro
                ba = new byte[len]; // cria um vetor de bytes com o tamanho do registro
                arq.read(ba); // Leitura registro
                filmeTemp.fromByteArray(ba); // Transforma vetor de bytes lido por instancia de FIlme
                posIni = arq.getFilePointer();// Marca posição que acabou o registro e será iniciado outro
                ultimoID++;

            } catch (EOFException e) {
                break;
            }
        }
        return ultimoID;
    }

    public static Filme[] leiaXFilmes(int n, RandomAccessFile arq) throws IOException, ParseException {

        boolean valido = true;
        long posIni = 0;
        byte ba[];
        int len;

        Filme filmes[] = new Filme[n];

        for (int i = 0; i < n; i++) {
            Filme filmeTemp = new Filme();
            arq.seek(posIni); // posiciona ponteiro no inicio do arquivo
            valido = arq.readBoolean();// ler lapide -- se TRUE filme existe , caso FALSE filme apagado
            len = arq.readInt(); // ler tamanho do registro
            ba = new byte[len]; // cria um vetor de bytes com o tamanho do registro
            arq.read(ba); // Ler registro
            filmeTemp.fromByteArray(ba); // Transforma vetor de bytes lido por instancia de FIlme
            posIni = arq.getFilePointer();// Marca posição que acabou o registro e será iniciado outro

            filmes[i] = filmeTemp;

        }

        return filmes;
    }

    public static RandomAccessFile deletaBanco(RandomAccessFile arq) throws IOException {
        arq.close();
        File file = new File("dados/filmes.db");
        if (file.exists())
            file.delete();
        RandomAccessFile arq2 = new RandomAccessFile("dados/filmes.db", "rw");
        return arq2;

    }

    public static void criaIndexArvoreB(RandomAccessFile arq, IndexacaoArvoreB index)
            throws IOException, ParseException {
        long posIni = 0;
        boolean valido = true;
        int len;
        byte ba[];
        Filme filmeTemp = new Filme();
        int posIniInt = 0;
        int i = 0;

        while (true) {
            try {
                arq.seek(posIni); // posiciona ponteiro no inicio do arquivo
                valido = arq.readBoolean();// leitura da lapide -- se TRUE filme existe, caso FALSE filme apagado
                len = arq.readInt(); // leitura tamanho do registro
                ba = new byte[len]; // cria um vetor de bytes com o tamanho do registro
                arq.read(ba); // Leitura registro
                filmeTemp.fromByteArray(ba); // Transforma vetor de bytes lido por instancia de FIlme
                posIniInt = Long.valueOf(posIni).intValue();
                index.create(Integer.toString(filmeTemp.id), posIniInt);
                posIni = arq.getFilePointer();// Marca posição que acabou o registro e será iniciado outro
                i++;

            } catch (EOFException e) {
                break;
            }
        }
        System.out.println("Index Arvore B criado com sucesso");

    }

    public static void criaIndexTabelaHash(RandomAccessFile arq, IndexacaoTabelaHash indexhash)
            throws Exception {
        long posIni = 0;
        boolean valido = true;
        int len;
        byte ba[];
        Filme filmeTemp = new Filme();
        int posIniInt = 0;
        int i = 0;

        while (i < 3000) {
            try {
                arq.seek(posIni); // posiciona ponteiro no inicio do arquivo
                valido = arq.readBoolean();// leitura da lapide -- se TRUE filme existe, caso FALSE filme apagado
                len = arq.readInt(); // leitura tamanho do registro
                ba = new byte[len]; // cria um vetor de bytes com o tamanho do registro
                arq.read(ba); // Leitura registro
                filmeTemp.fromByteArray(ba); // Transforma vetor de bytes lido por instancia de FIlme
                posIniInt = Long.valueOf(posIni).intValue();
                // System.out.println(filmeTemp.id);
                // System.out.println(posIni);
                indexhash.create(filmeTemp.id, posIni);
                posIni = arq.getFilePointer();// Marca posição que acabou o registro e será iniciado outro
                i++;

            } catch (EOFException e) {
                break;
            }
        }
        System.out.println("Index Hash criado com sucesso");

    }

    public void criaArquivoIndexadoIndireto(String fileOutput, Lista[] lista) throws IOException {
        RandomAccessFile saida = new RandomAccessFile(fileOutput, "rw");

        for (int i = 0; i < lista.length; i++) {
            if (lista[i].primeiro.prox != null) {
                saida.writeUTF(lista[i].primeiro.prox.elemento);
                for (Celula j = lista[i].primeiro.prox; j != null; j = j.prox) {
                    // System.out.print(j.elemento + " " + j.posArqOriginal);
                    saida.writeLong(j.posArqOriginal);
                }
                saida.writeUTF("?");
            }

        }
        saida.close();

    }

    public static void lerIndiretoGenero(String idProcurado, RandomAccessFile arq, IndexacaoIndireta index3)
            throws IOException, ParseException {
        // Variáveis e Instâncias//
        int len = 0;
        long posIni[] = new long[10];
        boolean valido;
        byte ba[];
        Scanner sc = new Scanner(System.in);
        Filme filmeTemp = new Filme();
        // Variáveis e Instâncias//

        try {
            posIni = index3.read2(idProcurado, index3.lista);
        } catch (java.lang.NullPointerException e) {
            // System.out.println("Categoria não encotrada em Generos!");
        }

        for (int i = 0; i < posIni.length; i++) {

            try {

                arq.seek(posIni[i]); // posiciona ponteiro no inicio do arquivo
                valido = arq.readBoolean();// ler lapide -- se TRUE filme existe , caso FALSE filme apagado
                len = arq.readInt(); // ler tamanho do registro
                ba = new byte[len]; // cria um vetor de bytes com o tamanho do registro
                arq.read(ba); // Ler registro
                filmeTemp.fromByteArray(ba); // Transforma vetor de bytes lido por instancia de FIlme

                if (valido == true && filmeTemp.id != 1) { // caso idProcurado e id do filme lido forem iguais
                    // e filme não tver sido apagado será escrito as
                    // informações
                    System.out.println("ID = " + filmeTemp.id);

                }
            } catch (java.io.EOFException e) {
            }
        }
    }

    /*
     * Vale ressaltar que foi usado um while(true) tanto no read quanto no update e
     * delete, pois mesmo que o id certo for
     * achado ele podia estar apagado, e se um filme com o mesmo id fosse criado,
     * não seria identificado, dessa forma , esse erro identificado foi corrigido.
     */
    public static void lerIndiretoLinguas(String idProcurado, RandomAccessFile arq, IndexacaoIndireta index3)
            throws IOException, ParseException {
        // Variáveis e Instâncias//
        int len = 0;
        long posIni[] = new long[10];
        boolean valido;
        byte ba[];
        Scanner sc = new Scanner(System.in);
        Filme filmeTemp = new Filme();
        // Variáveis e Instâncias//

        try {
            posIni = index3.read2(idProcurado, index3.lista);
        } catch (java.lang.NullPointerException e) {
            // System.out.println("Categoria não encotrada em Linguas!");
        }

        for (int i = 0; i < posIni.length; i++) {

            try {

                arq.seek(posIni[i]); // posiciona ponteiro no inicio do arquivo
                valido = arq.readBoolean();// ler lapide -- se TRUE filme existe , caso FALSE filme apagado
                len = arq.readInt(); // ler tamanho do registro
                ba = new byte[len]; // cria um vetor de bytes com o tamanho do registro
                arq.read(ba); // Ler registro
                filmeTemp.fromByteArray(ba); // Transforma vetor de bytes lido por instancia de FIlme

                if (valido == true && filmeTemp.id != 1) { // caso idProcurado e id do filme lido forem iguais
                    // e filme não tver sido apagado será escrito as
                    // informações
                    System.out.println("ID = " + filmeTemp.id);

                }
            } catch (java.io.EOFException e) {
            }
        }
        /*
         * Vale ressaltar que foi usado um while(true) tanto no read quanto no update e
         * delete, pois mesmo que o id certo for
         * achado ele podia estar apagado, e se um filme com o mesmo id fosse criado,
         * não seria identificado, dessa forma , esse erro identificado foi corrigido.
         */

    }

    /*
     * ATENÇÃO:
     * Você deve alterar o caminho para a leitura dos dados de acordo com seu
     * computador.
     * Isso estará sinalizado abaixo.
     * Caso você não faça, aparecerá uma mensagem de erro!
     */

    public static String digitaId() {
        String resp = "";
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite a categoria que voce quer procurar: "); // Pede id para usuário
        resp = sc.nextLine();

        return resp;

    }

    public static HashMap<Character, Integer> makeFrequency(String filename) {
        var frequency = new HashMap<Character, Integer>();
        try {
            RandomAccessFile raf = new RandomAccessFile(filename, "rw");
            while (raf.getFilePointer() < raf.length()) {
                char c = (char) raf.readByte();
                frequency.merge(c, 1, Integer::sum);
            }
            raf.seek(0);
            raf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return frequency;
    }

    public static String fileToString(RandomAccessFile arq) throws IOException, ParseException {
        String resp = "";
        boolean valido = true;
        int len = 0;
        byte ba[];
        long posIni = 0;

        while (true) {
            try {
                arq.seek(posIni);
                valido = arq.readBoolean();// ler lapide -- se TRUE filme existe , caso FALSE filme apagado
                len = arq.readInt(); // ler tamanho do registro
                ba = new byte[len]; // cria um vetor de bytes com o tamanho do registro
                arq.read(ba); // Ler registro
                Filme filmeTemp = new Filme();
                filmeTemp.fromByteArray(ba);
                resp += filmeTemp.toString();
                posIni = arq.getFilePointer();
            } catch (EOFException e) {
                return resp;
            }
        }
    }

    public static void criarArquivoCriptografadoCesar(String resp, String nomeArq) throws IOException {
        RandomAccessFile arq = new RandomAccessFile(nomeArq, "rw");
        byte ba[];
        ba = resp.getBytes();
        arq.write(ba);
        arq.close();
    }

    public static void main(String[] args) throws Exception {
        // ------------------------------------------------------------------------------
        RandomAccessFile arq = new RandomAccessFile("dados/filmes.db", "rw");
        RandomAccessFile arqIndiretoLingua = new RandomAccessFile("dados/indexIndiretoLingua.db", "rw");
        RandomAccessFile arqIndiretoGenero = new RandomAccessFile("dados/indexIndiretoGeneros.db", "rw");
        String path = "dados/bancoDados2.csv"; // ATENÇÃO -> ALTERAÇÃO DO CAMINHO
        IndexacaoArvoreB index = new IndexacaoArvoreB(8, "dados/indexArvoreB.db");
        IndexacaoTabelaHash indexHash = new IndexacaoTabelaHash(2000, "dados/diretorio.db", "dados/indexTabelaHash.db");
        IndexacaoIndireta indexacaoIndireta = new IndexacaoIndireta("dados/filmes.db", "dados/indexIndiretoLingua.db");
        IndexacaoIndireta indexacaoIndiretaGeneros = new IndexacaoIndireta("dados/filmes.db",
                "dados/indexIndiretoGeneros.db");
        // ------------------------------------------------------------------------------

        Scanner sc = new Scanner(System.in);// scanner para ler a opção digitada pelo usúario
        BufferedReader leitura = new BufferedReader(new FileReader(path)); // leitura do arquivo CSV
        int opcao = 0, opcao1 = 0;
        int ultimoID = getUltimoID(arq);
        int numeroCompressao = 0, numeroEscolhido = 0;

        System.out.println("Bem vindo ao Trabalho Prático 1 - Felipe Campolina e Gabriela Colen");

        // Menu CRUD
        while (opcao != 10) {
            System.out.println("--------------");
            System.out.println("Digite 1 para CRIAR um filme no banco de dados ");
            System.out.println("Digite 2 para ALTERAR um filme no banco de dados ");
            System.out.println("Digite 3 para LER um filme no banco de dados ");
            System.out.println("Digite 4 para DELETAR um filme no banco de dados ");
            System.out.println("Digite 5 para RESETAR o banco de dados ");
            System.out.println("Digite 6 para ORDENAR o banco de dados ");
            System.out.println("Digite 7 para COMPRIMIR o banco de dados ");
            System.out.println("Digite 8 para BUSCAR PADRAO  no banco de dados ");
            System.out.println("Digite 9 para CRIPTOGRAFAR  o banco de dados ");
            System.out.println("Digite 10 para SAIR do programa ");
            System.out.println("--------------");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    ultimoID = criar(arq, ultimoID, index, indexHash, indexacaoIndireta, indexacaoIndiretaGeneros); // CREATE
                    break;
                case 2:

                    alterar(arq, index, indexHash, indexacaoIndireta, indexacaoIndiretaGeneros);// UPDATE
                    break;
                case 3:
                    System.out.println("--------------");
                    System.out.println("Digite 1 para Pesquisar usando Árvore B");
                    System.out.println("Digite 2 para Pesquisar usando Tabela Hash (Id Máximo = 2000)");
                    System.out.println("Digite 3 para Pesquisar usando Indices invertidos");
                    System.out.println("Digite 4 para SAIR do menu de pesquisa");
                    opcao1 = sc.nextInt();
                    switch (opcao1) {
                        case 1:

                            ler(arq, index);
                            break;
                        case 2:
                            lerHash(arq, indexHash);
                            break;
                        case 3:
                            String idProcurado = "";
                            System.out.println("Campos aceitos: Lingua e Generos");
                            indexacaoIndireta.lista = indexacaoIndireta.lerArquivoIndexadoIndireto(arqIndiretoLingua);
                            indexacaoIndiretaGeneros.lista = indexacaoIndiretaGeneros
                                    .lerArquivoIndexadoIndireto(arqIndiretoGenero);

                            idProcurado = digitaId();

                            lerIndiretoGenero(idProcurado, arq, indexacaoIndiretaGeneros);
                            lerIndiretoLinguas(idProcurado, arq, indexacaoIndireta);
                            break;
                    }
                    break;
                case 4:

                    deletar(arq, index, indexHash, indexacaoIndireta);// DELETE
                    break;
                case 5:
                    arq = deletaBanco(arq);
                    index.apagar();
                    ultimoID = resetarBancoDeDados(arq, leitura);// RESET
                    criaIndexArvoreB(arq, index);
                    criaIndexTabelaHash(arq, indexHash);
                    indexacaoIndireta.lerArquivoDados("dados/filmes.db", "dados/indexIndiretoLingua.db",
                            indexacaoIndireta.lista);
                    indexacaoIndiretaGeneros.lerArquivoDadosGeneros("dados/filmes.db", "dados/indexIndiretoGeneros.db",
                            indexacaoIndiretaGeneros.lista);
                    break;
                case 6:
                    System.out.println("--------------");
                    System.out.println("Digite 1 para Intercalação balanceada comum");
                    System.out.println("Digite 2 para Intercalação balanceada com blocos de tamanho variável");
                    System.out.println("Digite 3 para Intercalação balanceada com seleção por substituição");
                    System.out.println("Digite 4 para SAIR do menu de ordenação");
                    opcao = sc.nextInt();
                    switch (opcao) {
                        case 1:
                            OrdenacaoExterna ordena = new OrdenacaoExterna(ultimoID,
                                    ultimoID);
                            ordena.IntercalaçãoComum("dados/filmes.db", arq);
                            System.out.println("Filmes ordenados com sucesso - Arquivo criado: filmesOrdenados1.db");
                            break;
                        case 2:
                            System.out.println(
                                    "Esse processo pode ser um pouco demorado, então foi setado para ordenar apenas 50 filmes");
                            int m = 50;
                            Filme filmes[] = new Filme[m];
                            filmes = leiaXFilmes(m, arq);
                            System.out.println(
                                    "-> Dê um valor para a quantidade de blocos que você deseja que o arquivo seja dividido: ");
                            int n = sc.nextInt();
                            filmes = OrdenacaoExterna.IntercalacaoBalanceadaVariavel(n, m, filmes);
                            System.out.println("Filmes ordenados com sucesso - Arquivo criado: filmesOrdenados2.db");
                            break;
                        case 3:

                            System.out.println("Neste processo o banco de dados será ordenado por completo :)");
                            System.out.println(
                                    " -> Dê um valor para a quantidade de blocos que você deseja que o arquivo seja dividido: ");
                            int x = sc.nextInt();
                            OrdenacaoExterna.ordenacaoExternaSubstituicao("dados/filmes.db",
                                    "dados/filmesOrdenados3.db", x,
                                    ultimoID);
                            System.out.println("Filmes ordenados com sucesso - Arquivo criado: filmesOrdenados3.db");
                            break;
                        case 4:
                            break;
                    }
                    break;
                case 7:
                    System.out.println("--------------");
                    System.out.println("Digite 1 para Comprimir usando Huffman");
                    System.out.println("Digite 2 para Descomprimir usando Huffman");
                    System.out.println("Digite 3 para Comprimir usando LZW");
                    System.out.println("Digite 4 para Descomprimir usando LZW");
                    System.out.println("Digite 5 para SAIR do menu de compressao");
                    opcao = sc.nextInt();
                    switch (opcao) {
                        case 1:
                            System.out.println("Escolha a versao para comprimir: ");
                            numeroCompressao = sc.nextInt();
                            System.out.println("\n======= HUFFMAN =========");
                            var frequency = makeFrequency("dados/filmes.db");
                            var tree = new Huffman(frequency);
                            tree.traverse(Huffman.root, "");
                            try {
                                RandomAccessFile source = new RandomAccessFile("dados/filmes.db", "rw");
                                RandomAccessFile dest = new RandomAccessFile(
                                        "dados/filmesHuffmanCompressao" + numeroCompressao + ".db", "rw");
                                long start = System.currentTimeMillis();
                                Huffman.compress(source, dest);
                                long end = System.currentTimeMillis() - start;

                                System.out.println("Arquivos filmesHuffmanCompressao" + numeroCompressao + ".db"
                                        + " criado com sucesso");
                                float porcentagem = 100 - (((float) dest.length() / (float) source.length()) * 100);
                                System.out.println("Porcentagem reduzida(%): " + porcentagem);
                                System.out.println("Tempo de execucao para comprimir(ms) usando Huffman : " + end);
                                source.seek(0);
                                dest.seek(0);
                                source.close();
                                dest.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case 2:
                            System.out.println("Escolha a versao para descomprimir: ");
                            numeroCompressao = sc.nextInt();
                            // Afim de evitar erros de usuarios por colocarem uma versão que não existe,
                            // optamos pela opção de sempre compactar o arquivo antes com a versao desejada.
                            System.out.println("\n======= HUFFMAN =========");
                            frequency = makeFrequency("dados/filmes.db");
                            tree = new Huffman(frequency);
                            tree.traverse(Huffman.root, "");
                            try {
                                RandomAccessFile source = new RandomAccessFile("dados/filmes.db", "rw");
                                RandomAccessFile dest = new RandomAccessFile(
                                        "dados/filmesHuffmanCompressao" + numeroCompressao + ".db", "rw");
                                RandomAccessFile desc = new RandomAccessFile(
                                        "dados/filmes.db", "rw");
                                Huffman.compress(source, dest);
                                long start = System.currentTimeMillis();
                                Huffman.decompress(dest, desc);
                                long end = System.currentTimeMillis() - start;

                                System.out.println("Arquivo filmes.db substituido com sucesso");
                                float porcentagem = 100 - (((float) dest.length() / (float) source.length()) * 100);
                                System.out.println("Porcentagem reduzida(%): " + porcentagem);
                                System.out.println("Tempo de execucao para descomprimir(ms) : " + end);
                                source.seek(0);
                                dest.seek(0);
                                source.close();
                                dest.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case 3:

                            System.out.println("Escolha a versao para comprimir: ");
                            numeroEscolhido = sc.nextInt();
                            System.out.println("\n======= LZW =========");
                            LZW lzw = new LZW();
                            long start = System.currentTimeMillis();
                            lzw.compress("dados/filmes.db", "dados/filmesLZWCompressao" + numeroEscolhido + ".db",
                                    numeroEscolhido);
                            System.out.println("Arquivo filmesLZWCompressao" + numeroEscolhido + ".db"
                                    + " criado com sucesso");
                            long end = System.currentTimeMillis() - start;
                            RandomAccessFile source = new RandomAccessFile("dados/filmes.db", "rw");
                            RandomAccessFile dest = new RandomAccessFile(
                                    "dados/filmesLZWCompressao" + numeroEscolhido + ".db", "rw");
                            float porcentagem = 100 - (((float) dest.length() / (float) source.length()) * 100);
                            System.out.println("Porcentagem reduzida(%): " + porcentagem);
                            System.out.println("Tempo de execucao para comprimir usando LZW(ms) : " + end);
                            break;
                        case 4:
                            System.out.println("Escolha a versao para descomprimir: ");
                            numeroEscolhido = sc.nextInt();
                            System.out.println("\n======= LZW =========");
                            LZW lzw2 = new LZW();
                            long start2 = System.currentTimeMillis();
                            lzw2.decompress("dados/filmesLZWCompressao" + numeroEscolhido + ".db", "dados/filmes.db",
                                    numeroEscolhido);
                            long end2 = System.currentTimeMillis() - start2;
                            source = new RandomAccessFile("dados/filmes.db", "rw");
                            dest = new RandomAccessFile("dados/filmesLZWCompressao" + numeroEscolhido + ".db", "rw");
                            porcentagem = 100 - (((float) dest.length() / (float) source.length()) * 100);
                            System.out.println("Arquivo filmes.db substituido com sucesso");
                            System.out.println("Porcentagem reduzida(%): " + porcentagem);
                            System.out.println("Tempo de execucao para descomprimir usando LZW(ms) : " + end2);

                            break;
                        default:
                            break;
                    }
                    break;
                case 8:
                    System.out.println("--------------");
                    System.out.println("Digite 1 para Buscar usando KMP");
                    System.out.println("Digite 2 para Buscar usando Forca Bruta");
                    System.out.println("Digite 3 para Sair ");
                    opcao = sc.nextInt();
                    switch (opcao) {
                        case 1:
                            long start = System.currentTimeMillis();
                            int resp = 0;
                            System.out.println("----------------KMP-----------------------");
                            String arqString = fileToString(arq);
                            System.out.println("Digite o padrao: ");
                            Scanner sc2 = new Scanner(System.in);
                            String padrao = sc2.nextLine();
                            resp = BuscadorPadrao.executaKMP(arqString, padrao);
                            while (resp != -1) {
                                arqString = arqString.substring(resp + 1); // Vale ressaltar no vídeo , que foi adotada
                                                                           // a tecnica de dividir a primeira string em
                                                                           // subStrings usando essa função.
                                // Ou seja, a partir do primeiro padrão achado , os proximos irao levar em
                                // consideracao a posicao da nova string -- ex OI TUDOI BEM? -- padrao : OI VAI
                                // ENCONTRAR NA POSICAO 1
                                // em seguida , vira TUDOI BEM? - achando de novo o padrao na posicao 4.
                                // Dessa forma , pode-se ocorrer repetiçoes de posicoes , por serem strings
                                // diferentes! Isso foi a tecnica usada para o padrao continuar sendo procurado
                                // mesmo depois de achado!
                                // Tem que falar isso no video
                                resp = BuscadorPadrao.executaKMP(arqString, padrao);
                            }
                            long end = System.currentTimeMillis() - start;
                            System.out.println("Tempo decorrido(ms): " + end);
                            break;
                        case 2:
                            start = System.currentTimeMillis();
                            resp = 0;
                            System.out.println("----------------FORÇA BRUTA-----------------------");
                            arqString = fileToString(arq);
                            System.out.println("Digite o padrao: ");
                            sc2 = new Scanner(System.in);
                            padrao = sc2.nextLine();
                            resp = BuscadorPadrao.forcaBruta(padrao, arqString);
                            while (resp != -1) {
                                arqString = arqString.substring(resp + 1);
                                // Vale ressaltar no vídeo , que foi adotada
                                // a tecnica de dividir a primeira string em
                                // subStrings usando essa função.
                                // Ou seja, a partir do primeiro padrão achado , os proximos irao levar em
                                // consideracao a posicao da nova string -- ex OI TUDOI BEM? -- padrao : OI VAI
                                // ENCONTRAR NA POSICAO 1
                                // em seguida , vira TUDOI BEM? - achando de novo o padrao na posicao 4.
                                // Dessa forma , pode-se ocorrer repetiçoes de posicoes , por serem strings
                                // diferentes! Isso foi a tecnica usada para o padrao continuar sendo procurado
                                // mesmo depois de achado!
                                // Tem que falar isso no video
                                resp = BuscadorPadrao.forcaBruta(padrao, arqString);
                            }
                            end = System.currentTimeMillis() - start;
                            System.out.println("Tempo decorrido(ms): " + end);
                            break;
                        default:
                            break;
                    }
                    break;
                case 9:
                    String resp = "";
                    System.out.println("--------------");
                    System.out.println("Digite 1 para criptografar usando Cifra de Cesar");
                    System.out.println("Digite 2 para descriptografar usando Cifra de Cesar");
                    System.out.println("Digite 3 para criptografar e descriptografar usando DES");
                    System.out.println("Digite 4 para Sair ");
                    opcao = sc.nextInt();
                    switch (opcao) {
                        case 1:
                            resp = Criptografia.criptografaCesar(fileToString(arq));
                            criarArquivoCriptografadoCesar(resp, "dados/dadosCriptografadoCesar.db");
                            break;
                        case 2:
                            RandomAccessFile arq2 = new RandomAccessFile("dados/dadosCriptografadoCesar.db", "rw");
                            resp = arq2.readUTF();
                            resp = Criptografia.descriptografaCesar(Criptografia.criptografaCesar(fileToString(arq)));
                            criarArquivoCriptografadoCesar(resp, "dados/dadosDescriptografadoCesar.db");
                            break;
                        case 3:
                            DES.mostraAlgoritimo(fileToString(arq));
                            break;
                        case 4:
                            break;
                        default:
                            break;
                    }
                    break;
                case 10:
                    System.out.println("Tchau!"); // EXIT
                    break;
                default:
                    System.out.println("Opção Incorreta, Tente Novamente");
                    break;
            }

        }
        sc.close();
    }

}
