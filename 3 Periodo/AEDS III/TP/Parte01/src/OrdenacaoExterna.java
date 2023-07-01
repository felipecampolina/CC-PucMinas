
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
  int N = 5000; // size of the file in disk
  int M = 5000; // max items the memory buffer can hold

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
   * Algoritmo de ordenacao Quicksort.
   * 
   * @param int esq inicio do array a ser ordenado
   * @param int dir fim do array a ser ordenado
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
    // Iterate through the elements in the file
    for (i = 0; i < slices; i++) {
      // Read M-element chunk at a time from the file

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

      // precisa ordenar o filmeTemp[]

      // Write the sorted numbers to temp file
      // FileWriter fw = new FileWriter(tfile + Integer.toString(i) + ".txt");
      // PrintWriter pw = new PrintWriter(fw);
      RandomAccessFile arqTemp = new RandomAccessFile(tfile + Integer.toString(i) + ".db", "rw");
      for (int k = 0; k < j; k++) {
        ba2 = filmeTemp[k].toByteArray();
        // System.out.println("TESTE:"+filmeTemp[k].titulo);
        arqTemp.writeBoolean(true); // Escreve lápide
        arqTemp.writeInt(ba2.length); // Escreve tamanho do vetor de bytes(Registro)
        arqTemp.write(ba2); // Escreve registro
      }

      arqTemp.close();

      // fw.close();
    }

    br.close();
    fr.close();
  }

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

      // Now open each file and merge them, then write back to disk

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
        valido = brs[i].readBoolean();// ler lapide -- se TRUE filme existe , caso FALSE filme apagado
        len = brs[i].readInt(); // ler tamanho do registro
        ba3 = new byte[len]; // cria um vetor de bytes com o tamanho do registro
        int x = brs[i].read(ba3); // Ler registro
        filmeTemp2[i].fromByteArray(ba3); // Transforma vetor de bytes lido por instancia de FIlme
        posIni = brs[i].getFilePointer();// Marca posição que acabou o registro e será iniciado outro
      }

      RandomAccessFile fw = new RandomAccessFile("dados/filmesOrdenadosInteiro.db", "rw");
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
         System.out.println(i + " " + min.titulo + " " + min.id);
        fw.writeBoolean(true); // Escreve lápide
        fw.writeInt(ba4.length); // Escreve tamanho do vetor de bytes(Registro)
        fw.write(ba4); // Escreve registro

        long dif = brs[minFile].length() - brs[minFile].getFilePointer();
        // System.out.println("DIF1:Arquivo " + minFile + " " + dif);
        arrayDif[minFile] = dif;

        // ULTIMA INTERCALAÇÃO --> acredito que o erro esteja aqui
        // Problema : repetições de alguns filmes
        // Se trocarmos no CRUD o N para 100 e o M para 10 o código funciona certinho ,
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
    System.out.println("Dados ordenados com sucesso - Intercalação comum");
  }

  // ----------------------------------3 Intercalação
  // -------------------------------------------

  // For sorting data stored on disk
  public static void ordenacaoExternaSubstituicao(String input_file, String output_file,
      int num_ways, int run_size) throws IOException, ParseException {

    // read the input file,
    // create the initial runs,
    // and assign the runs to
    // the scratch output files
    MinHeap.distribuicao(input_file, run_size, num_ways);

    // Merge the runs using
    // the K-way merging
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
    harr = a; // store address of array
    int i = (heap_size - 1) / 2;
    while (i >= 0) {
      MinHeapify(i);
      i--;
    }
  }

  int left(int i) {
    return (2 * i + 1);
  }

  // to get index of right child
  // of node at index i
  int right(int i) {
    return (2 * i + 2);
  }

  noMinHeap getMin() {
    return harr[0];
  }

  // to replace root with new node
  // x and heapify() new root
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

      // convert i to string
      // snprintf(fileName, fileName.length(), "%d", i);

      // Open output files in read mode.
      in[i] = openFile(tfile, "rw");
    }

    // FINAL OUTPUT FILE
    RandomAccessFile out = openFile(output_file, "rw");

    // Create a min heap with k heap
    // nodes. Every heap node
    // has first element of scratch
    // output file

    noMinHeap harr[] = new noMinHeap[k];

    Boolean valido;
    int len;
    byte ba[];

    int i;
    int teste = 0;

    for (i = 0; i < k; i++) {
      // break if no output file is empty and
      // index i will be no. of input files
      if (in[i].getFilePointer() == in[i].length()) {
        break;
      }
      valido = in[i].readBoolean();
      len = in[i].readInt();
      ba = new byte[len];
      in[i].read(ba);
      Filme filmeTemp = new Filme();
      filmeTemp.fromByteArray(ba);
      // System.out.println(filmeTemp.titulo);
      harr[i] = new noMinHeap(filmeTemp, i);
      harr[i].i = i;
      teste = i;
    }

    i = teste;

    MinHeap hp = new MinHeap(harr, i); // Pega o primeiro de todos os arq temp
    for (int j = 0; j < harr.length; j++) {
      System.out.println(j + " " + harr[j].elemento.titulo);
    }

    int count = 0;

    // for (int j = 0; j < harr.length; j++) {
    // System.out.println(harr[j].i);
    // System.out.println(harr[j].elemento.titulo);
    // }

    // Now one by one get the
    // minimum element from min
    // heap and replace it with
    // next element.
    // run till all filled input
    // files reach EOF
    byte ba2[];
    int contador = 1;
    while (count != i) {

      String generos[] = new String[1];
      generos[0] = "A";
      Date data = new Date("19/09/2003");
      Filme filmeTemp2 = new Filme(0, "a", "en", data, generos);

      // Get the minimum element
      // and store it in output file
      noMinHeap root = new noMinHeap(filmeTemp2, i);
      root = hp.getMin();
      // System.out.println(root.elemento.titulo);
      // System.out.println("hp.elemento = "+hp.harr[0].elemento.titulo);
      // System.out.println("hp.i"+hp.harr[0].i);

      System.out.println(contador + " root.elemento = " + root.elemento.titulo);

      // System.out.println("root.i"+root.i);
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

      // Replace root with next
      // element of input file
      contador++;
      hp.replaceMin(root);

    }

    // close input and output files
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
    // Iterate through the elements in the file
    for (i = 0; i < num_ways; i++) {
      // Read M-element chunk at a time from the file

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

      // Write the sorted numbers to temp file
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
