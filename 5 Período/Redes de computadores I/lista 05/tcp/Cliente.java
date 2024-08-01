import java.io.*;
import java.net.*;

class Cliente {
    private static String ipServidor = "192.168.5.67";
    private static int portaServidor = 6790;

    public static String lerString() throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        return in.readLine();
    }

    public static void main(String argv[]) throws Exception {
        Socket socket = new Socket(ipServidor, portaServidor);

        // Thread para receber mensagens do servidor
        Thread receberThread = new Thread(() -> {
            try {
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while (true) {
                    String mensagemRecebida = entrada.readLine();
                    if (mensagemRecebida == null) {
                        break; // Termina a thread se não houver mais mensagens
                    }
                    System.out.println("Servidor: " + mensagemRecebida);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        receberThread.start();

        // Loop principal para enviar mensagens para o servidor
        while (true) {
            String mensagemEnviada = lerString();
            DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
            saida.writeBytes(mensagemEnviada + '\n');
        }
    }
}
