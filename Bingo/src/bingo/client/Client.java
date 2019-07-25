/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juanfer
 */
public class Client {

    private static final List<Integer> ALL_NUMBERS = new ArrayList<>();

    private static void fillAllNumbers() {
        for (int i = 1; i < 76; i++) {
            ALL_NUMBERS.add(i);
        }
    }

    private static int getRandomPlay() {
        int randomNumber = 0;

        if (!ALL_NUMBERS.isEmpty()) {
            int addition = ALL_NUMBERS.size() - 1;
            int randomIdx = (int) (Math.random() * addition + 0);

            randomNumber = ALL_NUMBERS.get(randomIdx);
            ALL_NUMBERS.remove(randomIdx);
        }

        return randomNumber;
    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        Client.fillAllNumbers();

        Thread readerThread = null;
        Thread writerThread = null;

        try (Socket clientSocket = new Socket("localhost", 6900)) {
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            readerThread = new Thread(getRunnableReader(inFromServer));
            writerThread = new Thread(getRunnableWriter(outToServer));

            readerThread.start();
            writerThread.start();
            boolean execute = !clientSocket.isClosed();
            while (execute) {
                execute = !clientSocket.isClosed() && readerThread.isAlive() && writerThread.isAlive();
            }
            System.out.println("Finished!!");
        } finally {

            if (readerThread != null) {
                readerThread.interrupt();
            }

            if (writerThread != null) {
                writerThread.interrupt();
            }
            System.exit(0);
        }
    }

    public static Runnable getRunnableReader(final BufferedReader inFromServer) {

        return new Runnable() {

            @Override
            public void run() {
                try {
                    while (true) {

                        String serverMsg = inFromServer.readLine();
                        if (serverMsg != null) {
                            System.out.println(serverMsg.replace("/n", "\n"));
                            System.out.println("\n----------------------------------------------------");
                        } else {
                            break;
                        }

                    }
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

    }

    public static Runnable getRunnableWriter(final DataOutputStream outToServer) {
        final BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        return new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    while (true) {

                        // MANUAL TESTING !!!!!!!!!!!!!!
//                        String sentence = inFromUser.readLine();
//                        outToServer.writeBytes(sentence + "\n");
                        int randomNumber = Client.getRandomPlay();

                        if (randomNumber > 0) {
                            String sentence = randomNumber + "";
                            outToServer.writeBytes(sentence + "\n");

                            System.out.println("You have played: " + sentence);
                            Thread.sleep(1500);
                        } else {
                            break;
                        }

                    }
                } catch (InterruptedException | IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
    }

}
