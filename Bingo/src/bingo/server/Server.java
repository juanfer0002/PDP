/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.server;

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

    List<BingoHandler> handlers = new ArrayList();

    private void playBingo() throws IOException {
        try (ServerSocket welcomeSocket = new ServerSocket(6900)) {

            System.out.println("Waitting next connection!!");
            Socket connectionSocket = welcomeSocket.accept();

            BingoHandler handler = new BingoHandler(connectionSocket);
            handler.play();
        }
    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.playBingo();
    }

}
