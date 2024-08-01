package com.example.app;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class ChartDemo extends JFrame {

    private static final long serialVersionUID = 1L;
    private XYSeries series;
    private TCPClient client;
    private Thread clientThread;
    private double balance = 1000;
    private List<Double> stocks = new ArrayList<>();
    private JLabel balanceLabel;
    private JTextArea stockList;

    public ChartDemo() {
        // Create the dataset
        series = new XYSeries("Series1");

        // Create the chart
        JFreeChart chart = createChart();

        // Create a panel for the chart
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 370));

        // Add the chart panel to the frame
        setLayout(new BorderLayout());
        add(chartPanel, BorderLayout.CENTER);

        // Create a panel for the buttons and labels
        JPanel buttonPanel = new JPanel();
        JButton startButton = new JButton("Start TCP Client");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTCPClient("127.0.0.1", 9999);
            }
        });

        JButton stopButton = new JButton("Stop TCP Client");
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopTCPClient();
            }
        });

        JButton buyButton = new JButton("Buy Stock");
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (client != null) {
                    buyStock();
                }
            }
        });

        JButton sellButton = new JButton("Sell Stock");
        sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (client != null) {
                    sellStock();
                }
            }
        });

        balanceLabel = new JLabel("Balance: $" + balance);
        stockList = new JTextArea(5, 20);
        stockList.setEditable(false);

        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(buyButton);
        buttonPanel.add(sellButton);
        buttonPanel.add(balanceLabel);

        // Add the button panel to the frame
        add(buttonPanel, BorderLayout.NORTH);
        add(new JScrollPane(stockList), BorderLayout.SOUTH);
    }

    private JFreeChart createChart() {
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        return ChartFactory.createXYLineChart(
                "Sample Chart",
                "Category",
                "Value",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);
    }

    private void startTCPClient(String ip, int port) {
        client = new TCPClient(ip, port, series);
        clientThread = new Thread(client);
        clientThread.start();
    }

    private void stopTCPClient() {
        if (client != null) {
            client.stop();
            try {
                clientThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void buyStock() {
        if (series.getItemCount() > 0) {
            double currentPrice = series.getY(series.getItemCount() - 1).doubleValue();
            if (balance >= currentPrice) {
                sendUDPRequest("buy", currentPrice);
            } else {
                JOptionPane.showMessageDialog(this, "Insufficient balance to buy stock.");
            }
        }
    }

    private void sellStock() {
        if (!stocks.isEmpty()) {
            double currentPrice = series.getY(series.getItemCount() - 1).doubleValue();
            sendUDPRequest("sell", currentPrice);
        } else {
            JOptionPane.showMessageDialog(this, "No stocks to sell.");
        }
    }

    private void sendUDPRequest(String action, double currentPrice) {
        try {
            InetAddress address = InetAddress.getByName("127.0.0.1");
            DatagramSocket socket = new DatagramSocket();
            String message = action + " " + currentPrice;
            byte[] sendData = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, 9998);
            socket.send(sendPacket);

            byte[] receiveData = new byte[256];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            String response = new String(receivePacket.getData(), 0, receivePacket.getLength()).trim();

            if (response.equals("success")) {
                if (action.equals("buy")) {
                    balance -= currentPrice;
                    stocks.add(currentPrice);
                } else if (action.equals("sell")) {
                    balance += currentPrice;
                    stocks.remove(stocks.size() - 1);
                }
                updateBalanceAndStocks();
            } else {
                JOptionPane.showMessageDialog(this, "Transaction failed.");
            }
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateBalanceAndStocks() {
        balanceLabel.setText("Balance: $" + balance);
        stockList.setText("");
        for (Double stock : stocks) {
            stockList.append("Stock purchased at: $" + stock + "\n");
        }
    }

    public static void main(String[] args) {
        ChartDemo demo = new ChartDemo();
        demo.setSize(800, 600);
        demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        demo.setVisible(true);
    }
}
