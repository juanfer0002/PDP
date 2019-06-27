/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udpsample.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author sala312
 */
public class Server {

    private static final int PORT = 6900;

    /**
     * @param args the command line arguments
     * @throws java.net.SocketException
     */
    public static void main(String[] args) throws SocketException, IOException {
        System.out.println("Starting server!!");
        try (DatagramSocket dtgSocket = new DatagramSocket(PORT);) {

            boolean execute = true;
            while (execute) {
                byte[] inputData = new byte[1024];
                DatagramPacket inputPacket = new DatagramPacket(inputData, inputData.length);
                dtgSocket.receive(inputPacket);

                String inputMsg = new String(inputData, 0, inputPacket.getLength());
                System.err.println("Client asks for " + inputMsg);

                inputMsg = inputMsg.toLowerCase().replaceAll("\\s*", "");
                boolean isPalindrome = Server.isPalindrome(inputMsg);

                String outputMsg = isPalindrome ? "It's a palindrome" : "It is not a palindrome";
                byte[] outputData = outputMsg.getBytes();
                DatagramPacket outputPacket = new DatagramPacket(outputData, outputData.length,
                        inputPacket.getSocketAddress());

                dtgSocket.send(outputPacket);
            }

        }

    }

    private static boolean isPalindrome(String word) {

        boolean isPalindrome = true;
        for (int i = 0, j = word.length() - 1; i <= j && isPalindrome; i++, j--) {
            isPalindrome = word.charAt(i) == word.charAt(j);
        }

        return isPalindrome;
    }

}
