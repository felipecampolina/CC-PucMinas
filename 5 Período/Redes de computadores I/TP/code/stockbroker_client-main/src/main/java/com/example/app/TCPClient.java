package com.example.app;

import org.jfree.data.xy.XYSeries;

import javax.swing.SwingUtilities;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPClient implements Runnable {
    private String ip;
    private int port;
    private XYSeries series;
    private int xValue = 0;
    private volatile boolean running = true;
    private Socket socket;

    public TCPClient(String ip, int port, XYSeries series) {
        this.ip = ip;
        this.port = port;
        this.series = series;
    }

    @Override
    public void run() {
        try {
            socket = new Socket(ip, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (running) {
                // Receive response from the server
                String received = in.readLine();
                if (received != null) {
                    try {
                        double value = Double.parseDouble(received.trim());
                        SwingUtilities.invokeLater(() -> updateSeries(value));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        running = false;
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateSeries(double value) {
        series.add(xValue++, value);
    }
}
