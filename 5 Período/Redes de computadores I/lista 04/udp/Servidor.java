import java.net.*;

class Servidor {
    private static int portaServidor = 9875;
    private static byte[] receiveData = new byte[1024];
    private static byte[] sendData = new byte[1024];

    private static String[] perguntas = {"Qual é a capital do Brasil?", "Quem é o autor de 'Romeu e Julieta'?", "Quanto é 2 + 2?"};
    private static String[] respostas = {"brasília", "william shakespeare", "4"};
    
    public static void main(String args[]) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(portaServidor);

        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            System.out.println("Aguardando datagrama do cliente....");
            serverSocket.receive(receivePacket);

            String pergunta = perguntas[(int) (Math.random() * perguntas.length)];
            InetAddress ipCliente = receivePacket.getAddress();
            int portaCliente = receivePacket.getPort();
            
            sendData = pergunta.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipCliente, portaCliente);
            serverSocket.send(sendPacket);

            receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);

            String respostaCliente = new String(receivePacket.getData()).trim().toLowerCase();
            String respostaCorreta = respostas[perguntas.length - 1 - perguntas.length % (perguntas.length - 1)];

            String resultado = respostaCliente.equals(respostaCorreta) ? "Correto!" : "Incorreto!";
            sendData = resultado.getBytes();
            
            sendPacket = new DatagramPacket(sendData, sendData.length, ipCliente, portaCliente);
            serverSocket.send(sendPacket);
            System.out.println("Enviado...");
        }
    }
}
