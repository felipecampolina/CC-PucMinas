import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.BitSet;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Huffman {
    class Node {
        public int frequency;   //frequencia do caractere
        public char data;       //caractere
        public Node left;       //filho esquerdo
        public Node right;      //filho direito
    }

    static class Pair<A, B> {
        public A first;         //primeiro elemento do par
        public B second;        //segundo elemento do par
    }

    static Node root;           //raiz da árvore de Huffman
    static HashMap<Character, String> charMap = new HashMap<>();    //mapeamento de caractere para código binário   
    static HashMap<String, Character> codeMap = new HashMap<>();    //mapeamento do código para caractere


    // Construtor: recebe um mapa de caracteres e suas frequências
    public Huffman(HashMap<Character, Integer> frequency) {
        PriorityQueue<Node> pq = new PriorityQueue<Node>(frequency.size(), new Comparator<Node>() { //cria uma fila de prioridade para armazenar os nós da árvore de Huffman

            //comparador para ordenar os nós com base em suas frequências
            public int compare(Node o1, Node o2) { 
                if (o1.frequency > o2.frequency) { 
                    return 1;
                } else if (o1.frequency < o2.frequency) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
         
        //cria um nó para cada caractere e adiciona à fila de prioridade
        for (char c : frequency.keySet()) {
            Node node = new Node();
            node.frequency = frequency.get(c);
            node.data = c;
            node.left = null;
            node.right = null;
            pq.add(node);
        }

         //combina repetidamente os nós de menor frequência até que reste apenas um nó na fila
        while (pq.size() != 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            Node parent = new Node();
            parent.frequency = left.frequency + right.frequency;
            parent.data = '\0';
            parent.left = left;
            parent.right = right;
            pq.add(parent);
        }
        root = pq.poll(); //o nó que resta é a raiz da árvore
    }

    /*
     * percorre a árvore de Huffman e atribui códigos binários a cada caractere
     * 
     * @param node: nó atual
     * @param s: código binário da letra
     */
    public void traverse(Node root, String s) {
        if (root.left == null && root.right == null) {
            //se chegamos a uma folha da árvore (nó sem filhos), atribui o código binário ao caractere
            charMap.put(root.data, s);
            codeMap.put(s, root.data);
            return;
        }
        //percorre recursivamente os filhos esquerdo e direito da árvore
        traverse(root.left, s + '0');
        traverse(root.right, s + '1');
    }

    public static void printCode(Node root, String s) {
        if (root.left == null && root.right == null && Character.isLetter(root.data)) {
            System.out.println(root.data + ":" + s); // Imprime o caractere e seu código binário
            return;
        }
        printCode(root.left, s + "0");
        printCode(root.right, s + "1");
    }

    public void printCode() {
        printCode(root, "");
    }

    static int index = 0;

    public static char travFromBitset(Node root, String s, char ans) {
        if (root.left == null && root.right == null) {
            //se chegamos a uma folha da árvore (nó sem filhos), retornamos o caractere 
            //se chegar a null -> retorna
            return root.data;
        }
        if (s.charAt(index) == '1') {
            //se o valor do bitset for 1, vai pra direita
            index++;
            ans = travFromBitset(root.right, s, ans);
        } else {
            //else -> vai pra esquerda
            index++;
            ans = travFromBitset(root.left, s, ans);
        }
        return ans;
    }

    /*
     * descomprime a string de bits
     * @param s: string a ser decodificada e descomprimida
     */
    public static void decompress(RandomAccessFile source, RandomAccessFile dest) throws IOException {
        source.seek(0);         //reposiciona o ponteiro do arquivo de origem para o início
        String s = new String();     //string a ser decodificada e descomprimida

        //lê cada byte do arquivo de origem e converte para uma sequência de bits
        while (source.getFilePointer() < source.length()) {
            byte b = source.readByte();
            BitSet bitSet = BitSet.valueOf(new byte[] { b });
            for (int i = 7; i >= 0; --i) {
                if (bitSet.get(i)) {
                    s += '1';
                } else {
                    s += '0';
                }
            }
        }
        //chama o método de descompressão com a sequência de bits convertida em string
        decompress(s, dest);

    }

    static int lengthDiff = 0;

    public static void decompress(String s, RandomAccessFile dest) throws IOException {
        String ans = new String();
        while (index < s.length() - lengthDiff) {
            char k = ' ';
            k = travFromBitset(root, s, k);
            ans += k;
        }

        //escreve a string descomprimida no arquivo de destino
        try {
            dest.writeBytes(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
        dest.close();
    }

    /*
     * comprime o arquivo @source (origem) para o arquivo @dest (destino)
     */
    public static void compress(RandomAccessFile source, RandomAccessFile dest) throws IOException {
        //lê cada caractere do arquivo de origem e obtém o código binário correspondente
        String binary = new String();
        while (source.getFilePointer() < source.length()) {
            char c = (char) source.readByte();
            binary += charMap.get(c);
        }

        StringBuilder tmp = new StringBuilder();

        //converte a sequência de bits em bytes e escreve no arquivo de destino
        for (int i = 0; i < binary.length(); ++i) {
            tmp.append(binary.charAt(i));
            if (tmp.length() == 8) {
                byte b = (byte) Integer.parseInt(tmp.toString(), 2);// passa 8 bits pra byte
                dest.write(b);// escreve o byte no arquivo comprimido
                tmp.setLength(0); // reseta o stringBuilder
            } else if (tmp.length() < 8 && i == binary.length() - 1 && tmp.length() != 0) {
                lengthDiff = 8 - tmp.length();
                while (tmp.length() < 8) {
                    tmp.append('0');
                }
                byte b = (byte) Integer.parseInt(tmp.toString(), 2);// passa 8 bits pra byte
                dest.write(b);// escreve o byte no arquivo comprimido
                tmp.setLength(0); // reseta o stringBuilder
            }
        }
    }
}