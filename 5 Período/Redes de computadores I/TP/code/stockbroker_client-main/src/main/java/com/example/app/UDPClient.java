package com.example.app;

import org.jfree.data.xy.XYSeries;

import javax.swing.SwingUtilities;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient implements Runnable {
    private String ip;
    private int port;
    private XYSeries series;
    private int xValue = 0;
    private volatile boolean running = true;

    public UDPClient(String ip, int port, XYSeries series) {
        this.ip = ip;
        this.port = port;
        this.series = series;
    }

    @Override
    public void run() {
        try {
            InetAddress address = InetAddress.getByName(ip);
            DatagramSocket socket = new DatagramSocket();
            byte[] buffer = new byte[256];
            DatagramPacket packet;

            while (running) {
                // Send request to the server
                String message = "request";
                byte[] sendData = message.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
                socket.send(sendPacket);

                // Receive response from the server
                packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());

                try {
                    double value = Double.parseDouble(received.trim());
                    SwingUtilities.invokeLater(() -> updateSeries(value));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                // Wait for 1 second before sending the next request
                Thread.sleep(1000);
            }
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        running = false;
    }

    private void updateSeries(double value) {
        series.add(xValue++, value);
    }
}
