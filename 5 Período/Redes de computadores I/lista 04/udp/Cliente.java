import java.net.*;
import java.util.Scanner;

class Cliente {
    private static int portaServidor = 9875;
    private static byte[] sendData = new byte[1024];
    private static byte[] receiveData = new byte[1024];

    public static void main(String args[]) throws Exception {
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress ipServidor = InetAddress.getByName("localhost");

        DatagramPacket receivePacket;
        DatagramPacket sendPacket;

        Scanner scanner = new Scanner(System.in);

        while (true) {
            receiveData = new byte[1024];
            receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            String pergunta = new String(receivePacket.getData()).trim();
            System.out.println("Pergunta do servidor: " + pergunta);

            System.out.print("Sua resposta: ");
            String resposta = scanner.nextLine();
            sendData = resposta.getBytes();
            
            sendPacket = new DatagramPacket(sendData, sendData.length, ipServidor, portaServidor);
            clientSocket.send(sendPacket);

            receiveData = new byte[1024];
            receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            String resultado = new String(receivePacket.getData()).trim();
            System.out.println("Resultado: " + resultado);
        }
    }
}
