
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;

public class OrdenacaoExterna extends CRUD {
  int N = 5000; // tamanho do arquivo no disco
  int M = 5000; // tamanho máximo de itens que o buffer pode aguentar

  OrdenacaoExterna() {
    this.N = 0;
    this.M = 0;

  }

  OrdenacaoExterna(int n, int m) {
    this.N = n;
    this.M = m;

  }

  public static void swap(int i, int j, Filme array[]) {
    Filme temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

  public static void sort(Filme array[]) {
    int n = array.length;
    quicksort(0, n - 1, array);
  }

  /**
   * ORDENAÇÃO NA MEMÓRIA PRINCIPAL
   * ALGORITMO - QUICKSORT
   */
  private static void quicksort(int esq, int dir, Filme array[]) {
    int i = esq, j = dir;
    String pivo = array[(dir + esq) / 2].titulo;
    while (i <= j) {
      while (array[i].titulo.compareTo(pivo) < 0)
        i++;
      while (array[j].titulo.compareTo(pivo) > 0)
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

  public void distribuicao(int slices, String fileName, RandomAccessFile arq, String tfile)
      throws IOException, ParseException {
    FileReader fr = new FileReader(fileName);
    BufferedReader br = new BufferedReader(fr);
    Filme[] filmeTemp = new Filme[M];
    boolean valido;
    int len = 0;
    long posIni = 0;
    byte ba[], ba2[];

    int i, j;
    i = j = 0;
    // Passar pelos elementos do arquivo
    for (i = 0; i < slices; i++) {
      // Lê os M-elementos do arquivo de cada vez

      for (j = 0; j < (M < N ? M : N); j++) {

        arq.seek(posIni); // posiciona ponteiro no inicio do arquivo
        valido = arq.readBoolean();// ler lapide -- se TRUE filme existe , caso FALSE filme apagado
        len = arq.readInt(); // ler tamanho do registro
        ba = new byte[len]; // cria um vetor de bytes com o tamanho do registro
        int x = arq.read(ba); // Ler registro
        filmeTemp[j] = new Filme();
        filmeTemp[j].fromByteArray(ba); // Transforma vetor de bytes lido por instancia de FIlme
        posIni = arq.getFilePointer();// Marca posição que acabou o registro e será iniciado outro

      }

      sort(filmeTemp);

      // Escreve os filmes ordenados no arquivo temporário
      RandomAccessFile arqTemp = new RandomAccessFile(tfile + Integer.toString(i) + ".db", "rw");
      for (int k = 0; k < j; k++) {
        ba2 = filmeTemp[k].toByteArray();
        arqTemp.writeBoolean(true); // Escreve lápide
        arqTemp.writeInt(ba2.length); // Escreve tamanho do vetor de bytes(Registro)
        arqTemp.write(ba2); // Escreve registro
      }

      arqTemp.close();
    }

    br.close();
    fr.close();
  }

  /*
   * PRIMEIRA INTERCALAÇÃO
   * INTERCALAÇÃO COMUM
   */
  public void IntercalaçãoComum(String fileName, RandomAccessFile arq) throws ParseException {
    String tfile = "arquivosTemporarios/temp-file-";
    String[] buffer = new String[M < N ? M : N];
    long posIni = 0;
    boolean valido;
    int len = 0;
    byte ba[], ba2[];
    int slices = (int) Math.ceil((double) N / M);
    int i, j;
    i = j = 0;

    try {
      distribuicao(slices, fileName, arq, tfile);
      // Abre cada arquivo e faz um merge, depois escreve de volta no disco
      RandomAccessFile[] brs = new RandomAccessFile[slices];
      File file[] = new File[slices];

      Filme filmeTemp2[] = new Filme[slices];
      Filme filmeTemp3 = new Filme();
      Filme filmeTemp4 = new Filme();

      posIni = 0;
      byte ba3[];
      valido = true;
      len = 0;

      for (i = 0; i < slices; i++) {
        filmeTemp2[i] = new Filme();
        brs[i] = new RandomAccessFile(tfile + Integer.toString(i) + ".db", "rw");
        file[i] = new File(tfile + Integer.toString(i) + ".db");
        valido = brs[i].readBoolean();// ler lapide -- se TRUE filme existe, caso FALSE filme apagado
        len = brs[i].readInt(); // ler tamanho do registro
        ba3 = new byte[len]; // cria um vetor de bytes com o tamanho do registro
        int x = brs[i].read(ba3); // Ler registro
        filmeTemp2[i].fromByteArray(ba3); // Transforma vetor de bytes lido por instancia de FIlme
        posIni = brs[i].getFilePointer();// Marca posição que acabou o registro e será iniciado outro
      }

      RandomAccessFile fw = new RandomAccessFile("dados/filmesOrdenados1.db", "rw");
      posIni = 0;
      byte ba4[];

      long arrayDif[] = new long[slices];
      int teste = 0;
      for (i = 0; i < N; i++) {
        Filme min = filmeTemp2[0];
        int minFile = 0;

        for (j = 0; j < slices; j++) {
          if (min.titulo.compareTo(filmeTemp2[j].titulo) > 0) {
            min = filmeTemp2[j];
            minFile = j;
          }
        }

        ba4 = min.toByteArray();
        fw.writeBoolean(true); // Escreve lápide
        fw.writeInt(ba4.length); // Escreve tamanho do vetor de bytes(Registro)
        fw.write(ba4); // Escreve registro

        long dif = brs[minFile].length() - brs[minFile].getFilePointer();
        arrayDif[minFile] = dif;

        // ULTIMA INTERCALAÇÃO --> acredito que o erro esteja aqui
        // Problema : repetições de alguns filmes
        // Se trocarmos no CRUD o N para 100 e o M para 10 o código funciona certinho,
        // porém acho que é apenas concidencia

        if (arrayDif[minFile] == 0) {
          for (int k = 0; k < slices; k++) {
            // System.out.println("ARRAYDIF"+" "+arrayDif[k]);
            dif = brs[k].length() - brs[k].getFilePointer();
            // System.out.println("Arquivo " + k + " " + dif);
            if (arrayDif[k] != 0) {
              System.out.println("ENTREI NO IF");
              brs[k].seek(brs[k].length() - arrayDif[k]);
              valido = brs[k].readBoolean();// ler lapide -- se TRUE filme existe , caso FALSE filme apagado
              len = brs[k].readInt(); // ler tamanho do registro
              ba3 = new byte[len]; // cria um vetor de bytes com o tamanho do registro
              brs[k].read(ba3); // Ler registro
              filmeTemp3 = new Filme();
              filmeTemp3.fromByteArray(ba3);
              // arrayDif[k] = arrayDif[k]-len-5;
              break;
            }

          }
        } else {
          valido = brs[minFile].readBoolean();// ler lapide -- se TRUE filme existe , caso FALSE filme apagado
          len = brs[minFile].readInt(); // ler tamanho do registro
          ba3 = new byte[len]; // cria um vetor de bytes com o tamanho do registro
          brs[minFile].read(ba3); // Ler registro
          filmeTemp3 = new Filme();
          filmeTemp3.fromByteArray(ba3); // Transforma vetor de bytes lido por instancia de FIlme
        }
        filmeTemp2[minFile] = filmeTemp3;

      }

      fw.close();
      for (i = 0; i < slices; i++) {
        file[i].deleteOnExit();
        brs[i].close();
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /*
   * SEGUNDA INTERCALAÇÃO 
   */

  public static Filme[] lerTemp(String path) throws Exception {
    int len;
    byte[] ba;
    int contador = 0;
    boolean controle = false;
    boolean valido = true;

    RandomAccessFile arq = new RandomAccessFile(path, "rw");
    Filme[] temps = new Filme[40000];

    while (controle == false) {
      try {
        Filme filme_temp = new Filme();
        valido = arq.readBoolean();
        len = arq.readInt();
        ba = new byte[len];
        arq.read(ba);
        filme_temp.fromByteArray(ba);

        temps[contador] = filme_temp;

        contador++;
      } catch (Exception e) {
        break;
      }
    }

    return temps;

  }

  public static Filme[] ordenar(Filme[] ordenados) {
    int menor = 0;

    for (int a = 1; a < ordenados.length - 1; a++) {
      menor = a;
      for (int b = a + 1; b < ordenados.length; b++) {
        if (ordenados[b] != null && ordenados[menor] != null) {
          if (ordenados[b].titulo.compareTo(ordenados[menor].titulo) < 0) {
            menor = b;
          }
        }
      }

      Filme temp = ordenados[menor];
      ordenados[menor] = ordenados[a];
      ordenados[a] = temp;

    }

    return ordenados;
  }

  /*
   * SEGUNDA INTERCALAÇÃO 
   * INTERCALAÇÃO BALANCEADA COM BLOCOS DE TAMANHO VARIÁVEL
   * 
   * O algoritmo funciona dividindo o array em blocos de tamanho "bloco" e ordenando cada bloco. 
   * Em seguida, os blocos ordenados são intercalados de forma a produzir um único array ordenado.
   * A implementação usa dois arquivos temporários para armazenar os blocos de dados ordenados 
   * antes de intercalá-los. Além disso, é usada a função "ordenar" (não fornecida no código) 
   * para ordenar cada bloco.
   */
  public static Filme[] IntercalacaoBalanceadaVariavel(int bloco, int n, Filme[] filme) throws Exception {
    int contadorBloco = 0;
    Filme[] ordenados = new Filme[bloco];

    //criação dos dois arquivos temporários vazios
    RandomAccessFile arq1 = new RandomAccessFile("arquivosTemporarios/arq1.tmp", "rw");
    RandomAccessFile arq2 = new RandomAccessFile("arquivosTemporarios/arq2.tmp", "rw");
    boolean switchArq1 = true;


    /*
     * Percorre o array de Filmes e armazena em um array de tamanho igual ao 
     * tamanho do bloco de memória do arquivo em que está sendo lido.
     */
    
    for (int i = 0; i < filme.length; i++) {
      if (filme[i] != null) {
        if (contadorBloco == bloco) {
          ordenados = ordenar(ordenados);
          if (switchArq1 == true) {
            byte[] ba;
            int contador = 0;
            while (contador < ordenados.length) {
              if (ordenados[contador].titulo != null) {
                ba = ordenados[contador].toByteArray();
                arq1.writeBoolean(true);
                arq1.writeInt(ba.length);
                arq1.write(ba);
              }

              contador++;
            }

            for (int a = 0; a < ordenados.length; a++) {
              ordenados[a] = null;
            }

            switchArq1 = false;
            i--;
            contadorBloco = 0;
          } else {
            byte[] ba;
            int contador = 0;

            while (contador < ordenados.length) {
              if (ordenados[contador].titulo != null) {
                ba = ordenados[contador].toByteArray();
                arq2.writeBoolean(true);
                arq2.writeInt(ba.length);
                arq2.write(ba);
              }

              contador++;
            }

            for (int a = 0; a < ordenados.length; a++) {
              ordenados[a] = null;
            }

            switchArq1 = true;
            i--;
            contadorBloco = 0;
          }
        } else {
          ordenados[contadorBloco] = filme[i];
          contadorBloco++;
        }
      } else {
        if (ordenados[0] != null) {
          ordenados = ordenar(ordenados);
          if (switchArq1 == true) {
            byte[] ba;
            int contador = 0;

            while (contador < ordenados.length) {
              if (ordenados[contador] != null) {
                ba = ordenados[contador].toByteArray();
                arq1.writeBoolean(true);
                arq1.writeInt(ba.length);
                arq1.write(ba);
              }
              contador++;
            }
            ordenados[0] = null;
          } else {
            byte[] ba;
            int contador = 0;

            while (contador < ordenados.length) {
              if (ordenados[contador] != null) {
                ba = ordenados[contador].toByteArray();
                arq2.writeBoolean(true);
                arq2.writeInt(ba.length);
                arq2.write(ba);
              }
              contador++;
            }
            ordenados[0] = null;
          }
        }
      }
    }

    /*
     * Aqui é feita a intercalação dos arquivos temporários.
     * arquivoTemporario1 e arquivoTemporário2 são usados para armazenar os dados enquanto isso.
     */
    Filme[] arquivoTemporario1 = lerTemp("arquivosTemporarios/arq1.tmp");
    Filme[] arquivoTemporario2 = lerTemp("arquivosTemporarios/arq2.tmp");

    RandomAccessFile arq3 = new RandomAccessFile("arquivosTemporarios/arq3.tmp", "rw");
    RandomAccessFile arq4 = new RandomAccessFile("arquivosTemporarios/arq4.tmp", "rw");

    Boolean switchArq2 = true;
    Boolean aux1 = false;
    Boolean aux2 = false;
    Boolean auxiliar = false;
    int countEtapa = 0; //usada pra contar o numero de elementos intercalados
    int p1 = 0;
    int p2 = 0;
    Filme[] compararEIntercalar = new Filme[3000];

    while (arquivoTemporario1[p1] != null || arquivoTemporario2[p2] != null) {
      while (aux1 == false || aux2 == false) {

        if (arquivoTemporario1[p1] == null && arquivoTemporario2[p2] == null) {
          aux1 = true;
          aux2 = true;
        } else if (arquivoTemporario1[p1] == null) {
          aux1 = true;
          p1++;
        } else if (arquivoTemporario1[p1] != null && arquivoTemporario1[p1 + 1] != null) {
          if (arquivoTemporario1[p1].titulo.compareTo(arquivoTemporario1[p1 + 1].titulo) > 0) {
            compararEIntercalar[countEtapa] = arquivoTemporario1[p1];
            p1++;
            countEtapa++;
          } else {
            aux1 = true;
            p1++;
          }
        } else {
          compararEIntercalar[countEtapa] = arquivoTemporario1[p1];
          aux1 = true;
          p1++;
          countEtapa++;
        }
        if (arquivoTemporario2[p2] == null) {
          aux2 = true;
          p2++;
        } else if (arquivoTemporario2[p2] != null && arquivoTemporario2[p2 + 1] != null) {
          if (arquivoTemporario2[p2].titulo.compareTo(arquivoTemporario2[p2 + 1].titulo) > 0) {
            compararEIntercalar[countEtapa] = arquivoTemporario2[p2];
            p2++;
            countEtapa++;
          } else {
            aux2 = true;
            p2++;
          }
        } else {
          compararEIntercalar[countEtapa] = arquivoTemporario2[p2];
          aux2 = true;
          p2++;
          countEtapa++;
        }

      }
      // aqui os arquivos serão comparados e intercalados em um terceiro array
      compararEIntercalar = ordenar(compararEIntercalar);

      for (int i = 0; i < compararEIntercalar.length; i++) {
        if (switchArq2 == true) {
          if (compararEIntercalar[i] != null) {
            byte[] ba;
            ba = compararEIntercalar[i].toByteArray();
            arq3.writeBoolean(true);
            arq3.writeInt(ba.length);
            arq3.write(ba);
          }
        } else {
          if (compararEIntercalar[i] != null) {
            byte[] ba;
            ba = compararEIntercalar[i].toByteArray();
            arq4.writeBoolean(true);
            arq4.writeInt(ba.length);
            arq4.write(ba);
            auxiliar = true;
          }
        }

      }

      /* 
       * 
       */
      switchArq2 = !switchArq2;
      countEtapa = 0;
      aux1 = false;
      aux2 = false;

      for (int i = 0; i < compararEIntercalar.length; i++) {
        compararEIntercalar[i] = null;
      }
    }

    Filme[] temp3 = lerTemp("arquivosTemporarios/arq3.tmp");
    Filme[] temp4 = lerTemp("arquivosTemporarios/arq4.tmp");

    if (auxiliar) {
      RandomAccessFile arq5 = new RandomAccessFile("arquivosTemporarios/arq5.tmp", "rw");
      aux1 = false;
      aux2 = false;
      auxiliar = false;
      countEtapa = 0;
      p1 = 0;
      p2 = 0;
      Filme[] etapa3 = new Filme[40000];

      while (temp3[p1] != null || temp4[p2] != null) {
        while (aux1 == false || aux2 == false) {

          if (temp3[p1] == null && temp4[p2] == null) {
            aux1 = true;
            aux2 = true;
          } else if (temp3[p1] == null) {
            aux1 = true;
            p1++;
          } else if (temp3[p1] != null && temp3[p1 + 1] != null) {
            if (temp3[p1].titulo.compareTo(temp3[p1 + 1].titulo) > 0) {
              etapa3[countEtapa] = temp3[p1];
              p1++;
              countEtapa++;
            } else {
              aux1 = true;
              p1++;
            }
          } else {
            etapa3[countEtapa] = temp3[p1];
            aux1 = true;
            p1++;
            countEtapa++;
          }
          if (temp4[p2] == null) {
            aux2 = true;
            p2++;
          } else if (temp4[p2] != null && temp4[p2 + 1] != null) {
            if (temp4[p2].titulo.compareTo(temp4[p2 + 1].titulo) > 0) {
              etapa3[countEtapa] = temp4[p2];
              p2++;
              countEtapa++;
            } else {
              aux2 = true;
              p2++;
            }
          } else {
            etapa3[countEtapa] = temp4[p2];
            aux2 = true;
            p2++;
            countEtapa++;
          }

        }
        etapa3 = ordenar(etapa3);

        for (int i = 0; i < etapa3.length; i++) {
          if (etapa3[i] != null) {
            byte[] ba;
            ba = etapa3[i].toByteArray();
            arq5.writeBoolean(true);
            arq5.writeInt(ba.length);
            arq5.write(ba);
          }
        }

        switchArq2 = !switchArq2;
        countEtapa = 0;
        aux1 = false;
        aux2 = false;

        for (int i = 0; i < etapa3.length; i++) {
          etapa3[i] = null;
        }
      }

      Filme[] temp5 = lerTemp("arquivosTemporarios/arq5.tmp");

      sort(filme);
      RandomAccessFile arq6 = new RandomAccessFile("dados/filmesOrdenados2.db", "rw");
      for (int i = 0; i < filme.length; i++) {
        byte[] ba;
        ba = filme[i].toByteArray();
        arq6.writeBoolean(true);
        arq6.writeInt(ba.length);
        arq6.write(ba);
        
      }

      return temp5;

    } else {

      sort(filme);
      RandomAccessFile arq6 = new RandomAccessFile("dados/filmesOrdenados2.db", "rw");
      for (int i = 0; i < filme.length; i++) {
        byte[] ba;
        ba = filme[i].toByteArray();
        arq6.writeBoolean(true);
        arq6.writeInt(ba.length);
        arq6.write(ba);
        
      }

      return temp3;
    }
  }

  // TERCEIRA INTERCALAÇÃO
  // Para classificar os dados armazenados em disco
  public static void ordenacaoExternaSubstituicao(String input_file, String output_file,
      int num_ways, int run_size) throws IOException, ParseException {

    /*
     * Lê o arquivo de entrada
     * Cria as execuções iniciais
     * Atribui as execuções a arquivos
     * temporários dos arquivos de saída
     */
    MinHeap.distribuicao(input_file, run_size, num_ways);

    // Faz um merge usando o K-way
    MinHeap.mergeFiles(output_file, run_size, num_ways);
  }
}

class noMinHeap {
  public Filme elemento;
  public int i;

  public noMinHeap(Filme elemento, int i) {
    this.elemento = elemento;
    this.i = i;
  }

  public static void swap(noMinHeap[] arr, int i, int j) {
    noMinHeap temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

}

class MinHeap {
  public noMinHeap[] harr;
  public int heap_size;

  MinHeap(noMinHeap a[], int size) {
    heap_size = size;
    harr = a; // armazena o endereço do array
    int i = (heap_size - 1) / 2;
    while (i >= 0) {
      MinHeapify(i);
      i--;
    }
  }

  int left(int i) {
    return (2 * i + 1);
  }

  // para conseguir o índice do filho certo -> nó de index i
  int right(int i) {
    return (2 * i + 2);
  }

  noMinHeap getMin() {
    return harr[0];
  }

  /*
   * substitui a raiz pelo novo nó
   * x e heapify() nova raiz
   */

  void replaceMin(noMinHeap x) {
    harr[0] = x;
    MinHeapify(0);
  }

  public void MinHeapify(int i) {
    int l = left(i);
    int r = right(i);

    int smallest = i;

    if (l < heap_size && harr[l].elemento.titulo.compareTo(harr[i].elemento.titulo) < 0)
      smallest = l;

    if (r < heap_size
        && harr[r].elemento.titulo.compareTo(harr[smallest].elemento.titulo) < 0)
      smallest = r;

    if (smallest != i) {

      noMinHeap.swap(harr, i, smallest);

      MinHeapify(smallest);
    }

  }

  public static RandomAccessFile openFile(String fileName, String mode) throws FileNotFoundException {
    RandomAccessFile fp = new RandomAccessFile(fileName, mode);
    return fp;
  }

  public static RandomAccessFile criarArquivo(String fileName, String mode) throws IOException {
    File file = new File(fileName);
    RandomAccessFile fp = new RandomAccessFile(file, "rw");
    return fp;
  }

  public static void mergeFiles(String output_file, int n, int k) throws IOException, ParseException {

    RandomAccessFile in[] = new RandomAccessFile[k];

    for (int i = 0; i < k; i++) {
      String tfile = "arquivosTemporarios/temp-file-" + i + ".db";

      // converte i para string
      // abre o arquivo de saída em read mode
      in[i] = openFile(tfile, "rw");
    }

    // ARQUIVO DE SAÍDA FINAL
    RandomAccessFile out = openFile(output_file, "rw");

    /*
     * Cria o min heap com k heap nós
     * Todo nó heap tem o primeiro elemento do arq temporário
     */

    noMinHeap harr[] = new noMinHeap[k];

    Boolean valido;
    int len;
    byte ba[];

    int i;
    int teste = 0;

    for (i = 0; i < k; i++) {
      /*
       * para se o arquivo de saída tá vazio
       * ou o index i seja no. dos arquivos de entrada
       */
      if (in[i].getFilePointer() == in[i].length()) {
        break;
      }
      valido = in[i].readBoolean();
      len = in[i].readInt();
      ba = new byte[len];
      in[i].read(ba);
      Filme filmeTemp = new Filme();
      filmeTemp.fromByteArray(ba);
      harr[i] = new noMinHeap(filmeTemp, i);
      harr[i].i = i;
      teste = i;
    }

    i = teste;

    MinHeap hp = new MinHeap(harr, i); // Pega o primeiro de todos os arq temp


    int count = 0;

    /*
     * Agora um por um,
     * pega o menor elemento,
     * empilha e substitui pelo próximo,
     * executa até que todos os inputs sejam preenchidos,
     * arquivo -> EOF
     */
    byte ba2[];
    int contador = 1;
    while (count != i) {

      String generos[] = new String[1];
      generos[0] = "A";
      Date data = new Date("19/09/2003");
      Filme filmeTemp2 = new Filme(0, "a", "en", data, generos);

      // pega o menor elemento e armazena no arquivo de saida
      noMinHeap root = new noMinHeap(filmeTemp2, i);
      root = hp.getMin();
      ba2 = root.elemento.toByteArray();

      out.writeBoolean(true);
      out.writeInt(ba2.length);
      out.write(ba2);

      if (in[root.i].getFilePointer() == in[root.i].length()) {
        root.elemento = filmeTemp2;
        count++;
      } else {
        valido = in[root.i].readBoolean();
        len = in[root.i].readInt();
        ba = new byte[len];
        in[root.i].read(ba);
        Filme filmeTemp = new Filme();
        filmeTemp.fromByteArray(ba);
        harr[root.i] = new noMinHeap(filmeTemp, i);
        root.elemento = filmeTemp;
      }

      // System.out.println(root.elemento);

      // substitui o nó pelo próximo elemento do arquivo de saída
      contador++;
      hp.replaceMin(root);

    }

    // fecha a entrada e a saida
    for (int g = 0; g < k; g++) {
      in[g].close();
    }

    out.close();
  }

  public static void distribuicao(String fileName, int run_size, int num_ways)
      throws IOException, ParseException {
    String tfile = "arquivosTemporarios/temp-file-";
    RandomAccessFile arq = new RandomAccessFile(fileName, "rw");
    FileReader fr = new FileReader(fileName);
    BufferedReader br = new BufferedReader(fr);
    Filme[] filmeTemp = new Filme[run_size];
    boolean valido;
    int len = 0;
    long posIni = 0;
    byte ba[], ba2[];

    int i, j;
    i = j = 0;

    // passa por todos os elementos na filas
    for (i = 0; i < num_ways; i++) {
      // lê os M-elementos de uma vez no arquivo

      for (j = 0; j < run_size; j++) {
        try {
          arq.seek(posIni); // posiciona ponteiro no inicio do arquivo
          valido = arq.readBoolean();// ler lapide -- se TRUE filme existe , caso FALSE filme apagado
          len = arq.readInt(); // ler tamanho do registro
          ba = new byte[len]; // cria um vetor de bytes com o tamanho do registro
          int x = arq.read(ba); // Ler registro
          filmeTemp[j] = new Filme();
          filmeTemp[j].fromByteArray(ba); // Transforma vetor de bytes lido por instancia de FIlme
          posIni = arq.getFilePointer();// Marca posição que acabou o registro e será iniciado outro
        } catch (java.io.EOFException e) {
        }

      }

      OrdenacaoExterna sort = new OrdenacaoExterna();
      sort.sort(filmeTemp);

      // precisa ordenar o filmeTemp[]

      // escreve os elementos ordenados no arquivo temporário
      // FileWriter fw = new FileWriter(tfile + Integer.toString(i) + ".txt");
      // PrintWriter pw = new PrintWriter(fw);
      RandomAccessFile arqTemp = new RandomAccessFile(tfile + Integer.toString(i) + ".db", "rw");
      for (int k = 0; k < j; k++) {
        ba2 = filmeTemp[k].toByteArray();
        // System.out.println("TESTE:"+filmeTemp[k].titulo);
        arqTemp.writeBoolean(true); // Escreve lápide
        arqTemp.writeInt(ba2.length); // Escreve tamanho do vetor de bytes(Registro)
        arqTemp.write(ba2); // Escreve registro
        // System.out.println(filmeTemp[k].titulo);
      }

      arqTemp.close();

      // fw.close();
    }

    br.close();
    fr.close();
  }

}
