import java.io.*;
import java.text.*;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import java.io.IOException;

public class testeOrdenacao {
    static int m = 2; // caminhos
    static int b = 4; // tamanho do bloco

    public static void swap(int i, int j, String array[]) {
        String temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void sort(String array[]) {
        int n = array.length;
        quicksort(0, n - 1, array);
    }

    /**
     * Algoritmo de ordenacao Quicksort.
     * 
     * @param int esq inicio do array a ser ordenado
     * @param int dir fim do array a ser ordenado
     */
    private static void quicksort(int esq, int dir, String array[]) {
        int i = esq, j = dir;
        String pivo = array[(dir + esq) / 2];
        while (i <= j) {
            while (array[i].compareTo(pivo) < 0)
                i++;
            while (array[j].compareTo(pivo) > 0)
                j--;
            if (i <= j) {
                swap(i, j, array);
                i++;
                j--;
            }
        }
        if (esq < j)
            quicksort(esq, j, array);
        if (i < dir)
            quicksort(i, dir, array);
    }

    public testeOrdenacao() {
        this.m = 2;
        this.b = 4;
    }

    public static BufferedWriter escreveArquivoTemporario(String nomeArquivo) throws IOException {
        File arquivoTemp = new File(
                "C:/Users/felip/OneDrive - sga.pucminas.br/PUC MINAS/Produtos desenvolvidos/3 Periodo/AEDS III/TP01/Parte01/arquivosTemporarios/"
                        + nomeArquivo + ".txt");
        arquivoTemp.createNewFile();
        FileWriter fw = new FileWriter(arquivoTemp);
        BufferedWriter escreveArquivoTemp = new BufferedWriter(fw);
        return escreveArquivoTemp;

    }

    public static BufferedReader leArquivoTemporario(String nomeArquivo) throws IOException {
        File arquivoTemp = new File(
                "C:/Users/felip/OneDrive - sga.pucminas.br/PUC MINAS/Produtos desenvolvidos/3 Periodo/AEDS III/TP01/Parte01/arquivosTemporarios/"
                        + nomeArquivo + ".txt");
        arquivoTemp.createNewFile();
        FileReader fw = new FileReader(arquivoTemp);
        BufferedReader escreveArquivoTemp = new BufferedReader(fw);
        return escreveArquivoTemp;

    }

    public void distribuicao(RandomAccessFile arq, BufferedWriter arquivoTemp1, BufferedWriter arquivoTemp2)
            throws IOException, ParseException {
        int contador = 0, j = 0, x = 1;
        int len = 0;
        long posIni = 0;
        boolean valido;
        byte ba[];
        Scanner sc = new Scanner(System.in);
        int ultimoID = 0;

        String nomesOrdenados[] = new String[b];

        Filme filmeTemp = new Filme();
        while (x <= 20) {
            try {
                arq.seek(posIni); // posiciona ponteiro no inicio do arquivo
                valido = arq.readBoolean();// ler lapide -- se TRUE filme existe , caso FALSE filme apagado
                len = arq.readInt(); // ler tamanho do registro
                ba = new byte[len]; // cria um vetor de bytes com o tamanho do registro
                arq.read(ba); // Ler registro
                filmeTemp.fromByteArray(ba); // Transforma vetor de bytes lido por instancia de FIlme
                posIni = arq.getFilePointer();// Marca posição que acabou o registro e será iniciado outro
                ultimoID = filmeTemp.id;
                if (contador % b != 0 || contador == 0) {
                    nomesOrdenados[contador] = filmeTemp.titulo;
                    contador++;

                } else {
                    contador = 0;
                    sort(nomesOrdenados);
                    if (j % 2 == 0) {
                        System.out.println("entrei1");
                        for (int i = 0; i < nomesOrdenados.length; i++) {
                            arquivoTemp1.write(nomesOrdenados[i]);
                            arquivoTemp1.newLine();
                        }
                        j++;

                    } else {
                        System.out.println("entrei2");
                        for (int i = 0; i < nomesOrdenados.length; i++) {
                            arquivoTemp2.write(nomesOrdenados[i]);
                            arquivoTemp2.newLine();
                        }
                        j++;
                    }

                }
                x++;

            } catch (EOFException e) {
                break;
            }
        }
        arquivoTemp1.close();
        arquivoTemp2.close();

    }

    public static void intecalacao() throws IOException {
        BufferedReader leArquivoTemp1 = leArquivoTemporario("temp1");
        BufferedReader leArquivoTemp2 = leArquivoTemporario("temp2");
        BufferedWriter escreveArquivoTemp3 = escreveArquivoTemporario("temp3");
        BufferedWriter escreveArquivoTemp4 = escreveArquivoTemporario("temp4");
        String nome1, nome2;
        int tam1 = 0, tam2 = 0;
        int slices = (int) Math.ceil((double) N/M);

        nome1 = leArquivoTemp1.readLine();
        nome2 = leArquivoTemp2.readLine();

        try {
            for (int g = 0; g < 2; g++) {
                if (g % 2 == 0) {
                    tam1 = 0;
                    tam2 = 0;
                    for (int i = 0; i < 8; i++) {
                        if (nome1.compareTo(nome2) <= 0 && tam1 < b) {
                            escreveArquivoTemp3.write(nome1);
                            escreveArquivoTemp3.newLine();
                            nome1 = leArquivoTemp1.readLine();
                            tam1++;
                        } else if (nome1.compareTo(nome2) > 0 && tam2 < b) {
                            escreveArquivoTemp3.write(nome2);
                            escreveArquivoTemp3.newLine();
                            nome2 = leArquivoTemp2.readLine();
                            tam2++;

                        } else {
                            if (tam1 < tam2) {
                                escreveArquivoTemp3.write(nome1);
                                escreveArquivoTemp3.newLine();
                                nome1 = leArquivoTemp1.readLine();
                                tam1++;
                            } else {
                                escreveArquivoTemp3.write(nome2);
                                escreveArquivoTemp3.newLine();
                                nome2 = leArquivoTemp2.readLine();
                                tam2++;
                            }
                        }
                    }
                } else {
                    tam1 = 0;
                    tam2 = 0;
                    for (int i = 0; i < b*2; i++) {
                        System.out.println("NOME1: " + nome1);
                        System.out.println("NOME2: " + nome2);
                        if (nome1.compareTo(nome2) <= 0 && tam1 < b) {
                            escreveArquivoTemp4.write(nome1);
                            escreveArquivoTemp4.newLine();
                            nome1 = leArquivoTemp1.readLine();
                            tam1++;
                        } else if (nome1.compareTo(nome2) > 0 && tam2 < b) {
                            escreveArquivoTemp4.write(nome2);
                            escreveArquivoTemp4.newLine();
                            nome2 = leArquivoTemp2.readLine();
                            tam2++;

                        } else {
                            if (tam1 < tam2) {
                                escreveArquivoTemp4.write(nome1);
                                escreveArquivoTemp4.newLine();
                                nome1 = leArquivoTemp1.readLine();
                                tam1++;
                            } else {
                                escreveArquivoTemp4.write(nome2);
                                escreveArquivoTemp4.newLine();
                                nome2 = leArquivoTemp2.readLine();
                                tam2++;
                            }
                        }
                    }

                }
            }
        } catch (java.lang.NullPointerException e) {
            while (tam1 != tam2) {
                if (tam1 < tam2) {
                    escreveArquivoTemp4.write(nome1);
                    escreveArquivoTemp4.newLine();
                    nome1 = leArquivoTemp1.readLine();
                    tam1++;
                } else {
                    escreveArquivoTemp4.write(nome2);
                    escreveArquivoTemp4.newLine();
                    nome2 = leArquivoTemp2.readLine();
                    tam2++;
                }
            }
        }
        escreveArquivoTemp3.close();
        escreveArquivoTemp4.close();
    }

    public static void main(String[] args) throws IOException, ParseException {
        RandomAccessFile arq = new RandomAccessFile("/tp/filmes.db", "rw");
        BufferedWriter arquivoTemp1 = escreveArquivoTemporario("temp1");
        BufferedWriter arquivoTemp2 = escreveArquivoTemporario("temp2");
        BufferedWriter arquivoTemp3 = escreveArquivoTemporario("temp3");
        BufferedWriter arquivoTemp4 = escreveArquivoTemporario("temp4");

        testeOrdenacao teste = new testeOrdenacao();
        teste.distribuicao(arq, arquivoTemp1, arquivoTemp2);
        intecalacao();
    }

}
