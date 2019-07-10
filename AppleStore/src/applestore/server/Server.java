/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applestore.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juanfer
 */
public class Server {

    private final static int MAX_THREADS_COUNT = 3; // Main thread + 2 concurrent clients
    List<StoreHandler> handlers = new ArrayList();

    private void openStore() throws IOException {
        try (ServerSocket welcomeSocket = new ServerSocket(6900)) {

            int i = 1;
            while (true) {
                System.out.println("Waitting next connection!!");
                Socket connectionSocket = welcomeSocket.accept();

                System.out.println("Current count: " + Thread.activeCount());

                StoreHandler handler = new StoreHandler(i, connectionSocket);
                if (Thread.activeCount() < MAX_THREADS_COUNT) {
                    handler.start();
                } else {
                    handler.reject();
                }

                i++;
            }

        }
    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.openStore();
    }
}
