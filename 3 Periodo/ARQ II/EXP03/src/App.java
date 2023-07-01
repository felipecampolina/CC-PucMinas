import java.io.*;

public class App {

    // Método para abrir um arquivo para leitura e retornar um BufferedReader
    public static BufferedReader abreArquivoLeitura(String nomeArquivo) throws FileNotFoundException {
        File file = new File(nomeArquivo);
        BufferedReader fr = new BufferedReader(new FileReader(file));
        return fr;
    }

    // Método para abrir um arquivo para escrita e retornar um BufferedWriter
    public static BufferedWriter abreArquivoEscrita(String nomeArquivo) throws IOException {
        File file = new File(nomeArquivo);
        BufferedWriter fr = new BufferedWriter(new FileWriter(file));
        return fr;
    }

    // Método para remover alguns caracteres específicos de uma string
    public static String trataPalavra(String palavra) {
        palavra = palavra.replace("=", "");
        palavra = palavra.replace(";", "");
        palavra = palavra.replace("X", "");
        palavra = palavra.replace("W", "");
        palavra = palavra.replace("Y", "");

        return palavra;
    }

    // Método principal
    public static void main(String[] args) throws Exception {
        // Abre o arquivo de entrada para leitura e o arquivo de saída para escrita
        BufferedReader fr = abreArquivoLeitura("dados/testeula.ula");
        BufferedWriter fw = abreArquivoEscrita("dados/testeula.hex");

        // Define algumas variáveis
        String palavra = "";
        String instrucoes[] = { "An", "Bn", "umL", "zeroL", "nAeB", "nAoB", "AxB", "AeBn", "AnoB", "nAxB", "AoB", "AeB",
                "AneB", "AoBn", "copiaB", "copiaA" };
        String xHex = "";
        String yHex = "";
        String comando = "";

        // Lê a primeira linha do arquivo de entrada
        palavra = fr.readLine();

        // Enquanto não chegar ao final do arquivo
        while (!palavra.contentEquals("fim.")) {
            // Se a linha começa com "X", converte para hexadecimal e armazena na variável
            // xHex
            if (palavra.charAt(0) == 'X') {
                palavra = trataPalavra(palavra); // Remove alguns caracteres
                xHex = Integer.toHexString(Integer.parseInt(palavra)); // Converte para hexadecimal
            }
            // Se a linha começa com "Y", converte para hexadecimal e armazena na variável
            // yHex
            else if (palavra.charAt(0) == 'Y') {
                palavra = trataPalavra(palavra); // Remove alguns caracteres
                yHex = Integer.toHexString(Integer.parseInt(palavra)); // Converte para hexadecimal
            }
            // Se a linha é inicio
            else if (palavra.contentEquals("inicio:")) {
                // Não faz nada
            } else {
                // Se a linha é uma instrução
                palavra = trataPalavra(palavra); // Remove alguns caracteres
                int posicao = -1;
                String posicaoString = "";

                // Procura pela instrução no array de instruções e armazena a posição
                for (int i = 0; i < instrucoes.length; i++) {
                    if (palavra.contentEquals(instrucoes[i])) {
                        posicao = i;
                        break;
                    }
                }
                // Converte a posição para hexadecimal e armazena na variável posicaoString
                posicaoString = Integer.toHexString(posicao);

                // Concatena as variáveis xHex, yHex e posicaoString
                // para formar o comando em hexadecimal
                comando = xHex + yHex + posicaoString;
                // Escreve o comando no arquivo de saída
                fw.write(comando.toUpperCase()+" ");
            }

            // Lê a próxima linha do arquivo de entrada
            palavra = fr.readLine();
        }

        // Fecha o arquivo de entrada e o arquivo de saída
        fw.write("FIM");
        fr.close();
        fw.close();
    }
}