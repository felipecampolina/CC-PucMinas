import java.io.*;
import java.net.*;

class Servidor {
    private static int portaServidor = 6790;

    public static String lerString() throws IOException {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      return in.readLine();
  }

    public static void main(String argv[]) throws Exception {
        ServerSocket socket = new ServerSocket(portaServidor);
        

        while (true) {
            Socket conexao = socket.accept();

            // Thread para receber mensagens do cliente
            Thread receberThread = new Thread(() -> {
                try {
                    BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
                    while (true) {
                        String mensagemRecebida = entrada.readLine();
                        if (mensagemRecebida == null) {
                            break; // Termina a thread se não houver mais mensagens
                        }
                        System.out.println("Cliente: " + mensagemRecebida);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            receberThread.start();

            // Loop principal para enviar mensagens para o cliente
            while (true) {
                String mensagemEnviada = lerString();
                DataOutputStream saida = new DataOutputStream(conexao.getOutputStream());
                saida.writeBytes(mensagemEnviada + '\n');
            }
        }
    }
}
