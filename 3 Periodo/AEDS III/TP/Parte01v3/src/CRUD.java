/* Primeira Parte - Trabalho Prático AEDS 3
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
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public static int criar(RandomAccessFile arq, int ultimoID) throws ParseException, IOException {
        // Variáveis e Instâncias
        int tamGeneros;
        String tituloTemp, dataTempString, linguaTemp;
        DateFormat dataFormatadaTemp = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date dataTemp;
        Scanner sc = new Scanner(System.in);
        byte ba[];

        ultimoID = ultimoID + 1;

        arq.seek(arq.length()); // posiciona o ponteiro do arquivo no final , ou seja, todos filmes novos serão
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

        while (leitura.ready()) { // le TODOS filmes do arquivo CSV e salva em bytes em um arquivo .db
            Filme filme = leDadosCsv(leitura);// Cria uma instancia de filme a partir de cada linha csv
            ba = filme.toByteArray();// transforma filme em vetor de bytes
            arq.writeBoolean(true); // Escreve lápide
            arq.writeInt(ba.length); // Escreve tamanho do vetor de bytes(Registro)
            arq.write(ba); // Escreve registro
            ultimoID++;

        }
        System.out.println("Reset concluído");
        return ultimoID;
    }

    /* Função READ do CRUD */
    public static void ler(RandomAccessFile arq) throws IOException, ParseException {
        // Variáveis e Instâncias//
        int idProcurado = 0, len = 0;
        long posIni = 0;
        boolean valido;
        byte ba[];
        Scanner sc = new Scanner(System.in);
        Filme filmeTemp = new Filme();
        // Variáveis e Instâncias//

        System.out.println("Digite o id: "); // Pede id para usuário
        idProcurado = sc.nextInt();

        while (true) {

            try {
                arq.seek(posIni); // posiciona ponteiro no inicio do arquivo
                valido = arq.readBoolean();// ler lapide -- se TRUE filme existe , caso FALSE filme apagado
                len = arq.readInt(); // ler tamanho do registro
                ba = new byte[len]; // cria um vetor de bytes com o tamanho do registro
                arq.read(ba); // Ler registro
                filmeTemp.fromByteArray(ba); // Transforma vetor de bytes lido por instancia de FIlme
                posIni = arq.getFilePointer();// Marca posição que acabou o registro e será iniciado outro

                if (idProcurado == filmeTemp.id && valido == true) { // caso idProcurado e id do filme lido forem iguais
                                                                     // e filme não tver sido apagado será escrito as
                                                                     // informações.
                    System.out.println("-------------------------");
                    System.out.println("");
                    System.out.println("ID = " + filmeTemp.id);
                    System.out.println("TITULO = " + filmeTemp.titulo);
                    System.out.println("LINGUA = " + filmeTemp.lingua);
                    System.out.println("DATA " + filmeTemp.data);
                    System.out.println("GENEROS: ");

                    for (int j = 0; j < filmeTemp.generos.length; j++) {
                        System.out.println(filmeTemp.generos[j]);
                    }
                    break;
                }

            } catch (EOFException e) {
                System.out.println("Acabou o arquivo não achei o filme"); // Erro fim do arquivo , ou seja , não achou o
                                                                          // filme

                break;
            }
        }
        /*
         * Vale ressaltar que foi usado um while(true) tanto no read quanto no update e
         * delete, pois mesmo que o id certo for
         * achado ele podia estar apagado, e se um filme com o mesmo id fosse criado,
         * não seria identificado, dessa forma , esse erro identificado foi corrigido.
         */
    }

    /* Função DELETE do CRUD */
    public static void deletar(RandomAccessFile arq) throws IOException, ParseException {
        // Variáveis e Instâncias
        int idDeletado = 0, len = 0;
        long posIni = 0, posDel = 0;
        byte ba[];
        boolean valido;
        Filme filmeTemp = new Filme();

        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o id que você deseja deletar: ");// Pede id para usuário do filme a ser deletado
        idDeletado = sc.nextInt();

        while (true) {
            try {
                // Funcionamento similar ao READ, porém a posição incial é marcada por meio da
                // variavel posDel
                arq.seek(posDel);
                posIni = arq.getFilePointer(); // marca onde tá a lápide
                valido = arq.readBoolean();
                len = arq.readInt();
                ba = new byte[len];
                arq.read(ba);
                filmeTemp.fromByteArray(ba);
                posDel = arq.getFilePointer();

                if (idDeletado == filmeTemp.id && valido == true) {
                    arq.seek(posIni);
                    arq.writeBoolean(false);// lápide <- false
                    System.out.println("Filme Deletado com sucesso!");
                    break;
                }
            } catch (EOFException e) {
                System.out.println("Acabou o arquivo não achei o filme");
                break;
            }
        }

    }

    /* Função DELETE do CRUD recebendo o ID como parâmetro */
    public static void deletar(RandomAccessFile arq, int idDeletado) throws IOException, ParseException {
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
                    System.out.println("Filme Deletado com sucesso!");
                    break;
                }
            } catch (EOFException e) {
                System.out.println("Acabou o arquivo não achei o filme");
                break;
            }
        }

    }

    public static void alterar(RandomAccessFile arq) throws IOException, ParseException {
        // Variáveis e Instâncias//
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
                        deletar(arq, idProcurado);
                        arq.seek(arq.length());
                        arq.writeBoolean(true);
                        arq.writeInt(ba.length); // Tamanho do registro em bytes
                        arq.write(ba);
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
    public static RandomAccessFile deletaBanco(RandomAccessFile arq) throws IOException{
        arq.close();
        File file = new File("dados/filmes.db");
        if(file.exists())file.delete();
        RandomAccessFile arq2 = new RandomAccessFile("dados/filmes.db", "rw");
        return arq2;
                
    }

    /*
     * ATENÇÃO:
     * Você deve alterar o caminho para a leitura dos dados de acordo com seu
     * computador.
     * Isso estará sinalizado abaixo.
     * Caso você não faça, aparecerá uma mensagem de erro!
     */

    public static void main(String[] args) throws Exception {
        // ------------------------------------------------------------------------------
        RandomAccessFile arq = new RandomAccessFile("dados/filmes.db", "rw");
        String path = "dados/bancoDados2.csv"; // ATENÇÃO -> ALTERAÇÃO DO CAMINHO
        // ------------------------------------------------------------------------------

        Scanner sc = new Scanner(System.in);// scanner para ler a opção digitada pelo usúario
        BufferedReader leitura = new BufferedReader(new FileReader(path)); // leitura do arquivo CSV
        int opcao = 0;
        int ultimoID = getUltimoID(arq);

        System.out.println("Bem vindo ao Trabalho Prático 1 - Felipe Campolina e Gabriela Colen");

        // Menu CRUD
        while (opcao != 7) {
            System.out.println("--------------");
            System.out.println("Digite 1 para CRIAR um filme no banco de dados ");
            System.out.println("Digite 2 para ALTERAR um filme no banco de dados ");
            System.out.println("Digite 3 para LER um filme no banco de dados ");
            System.out.println("Digite 4 para DELETAR um filme no banco de dados ");
            System.out.println("Digite 5 para RESETAR o banco de dados ");
            System.out.println("Digite 6 para ORDENAR o banco de dados ");
            System.out.println("Digite 7 para SAIR do programa ");
            System.out.println("--------------");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    ultimoID = criar(arq, ultimoID); // CREATE
                    break;
                case 2:
                    alterar(arq);// UPDATE
                    break;
                case 3:
                    ler(arq);// READ
                    break;
                case 4:
                    deletar(arq);// DELETE
                    break;
                case 5:
                    arq = deletaBanco(arq);
                    ultimoID = resetarBancoDeDados(arq, leitura);// RESET
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
                            System.out.println("-> Dê um valor para a quantidade de blocos que você deseja que o arquivo seja dividido: ");
                            int n = sc.nextInt();
                            filmes = OrdenacaoExterna.IntercalacaoBalanceadaVariavel(n, m, filmes);
                            System.out.println("Filmes ordenados com sucesso - Arquivo criado: filmesOrdenados2.db");
                            break;
                        case 3:

                            System.out.println("Neste processo o banco de dados será ordenado por completo :)");
                            System.out.println(" -> Dê um valor para a quantidade de blocos que você deseja que o arquivo seja dividido: ");
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
