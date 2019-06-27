/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udpsample.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.Scanner;

/**
 *
 * @author sala312
 */
public class Client {

    private static final int PORT = 6900;

    public static void main(String[] args) throws SocketException, IOException {
        Scanner scanner = new Scanner(System.in);

        SocketAddress address = new InetSocketAddress("localhost", PORT);

        System.out.println("Stablishing connection!!");
        try (DatagramSocket dtgSocket = new DatagramSocket();) {

            boolean execute = true;
            while (execute) {

                String outputMsg = scanner.nextLine();
                System.out.println("Client: " + outputMsg);
                byte[] outputData = outputMsg.getBytes();
                DatagramPacket outputPacket = new DatagramPacket(outputData, outputData.length, address);
                dtgSocket.send(outputPacket);

                byte[] inputData = new byte[1024];
                DatagramPacket inputPacket = new DatagramPacket(inputData, inputData.length);
                dtgSocket.receive(inputPacket);

                String inputMsg = new String(inputData, 0, inputPacket.getLength());
                System.err.println("Server: " + inputMsg);

            }

        }

    }

}
