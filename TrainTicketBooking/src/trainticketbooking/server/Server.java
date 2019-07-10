/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainticketbooking.server;

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

    List<BookingHandler> handlers = new ArrayList();

    private void openStore() throws IOException {
        try (ServerSocket welcomeSocket = new ServerSocket(6900)) {

            int i = 1;
            while (true) {
                System.out.println("Waitting next connection!!");
                Socket connectionSocket = welcomeSocket.accept();

                System.out.println("Current count: " + Thread.activeCount());

                BookingHandler handler = new BookingHandler(i, connectionSocket);
                handler.start();

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
