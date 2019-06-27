/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketsample.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author Juanfer
 */
public class Client {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        try (Socket clientSocket = new Socket("localhost", 6900)) {
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String serverMsg = inFromServer.readLine();
            System.out.println(serverMsg.replace("/n", "\n"));

            while (true) {
                System.out.println("\nSend a letter to guess:");
                String sentence = inFromUser.readLine();
                outToServer.writeBytes(sentence + "\n");

                serverMsg = inFromServer.readLine();
                System.out.println("\n" + serverMsg.replace("/n", "\n"));
                System.out.println("--------------------------------------------------------------");
            }

        }
    }

}
