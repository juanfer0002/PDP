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
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Juanfer
 */
public class Server {

    final Hangman hangman;

    public Server() {
        this.hangman = new Hangman("guess");
    }

    private void startGame() throws IOException {
        System.out.println("Waitting connection!!");

        try (ServerSocket welcomeSocket = new ServerSocket(6900);
                Socket connectionSocket = welcomeSocket.accept();
                BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());) {

            System.out.println("Connected!!");

            int wordLength = this.hangman.getCurrentGuessing().length();
            outToClient.writeBytes("Ready to play the hangman?/n/nYour word has: " + wordLength + " letters\n");

            boolean isOver = false;
            while (!isOver) {

                String letterToGuess = inFromClient.readLine().trim().toLowerCase();
                GameStatus status = this.playHangman(letterToGuess);

                String msg = status.getMsg() + "/n/nThis is what you've gotten 'til now: " + this.hangman.getCurrentGuessing() + "\n";
                outToClient.writeBytes(msg);
            }

        }
    }

    private GameStatus playHangman(String letterToGuess) throws IOException {
        GameStatus status;

        if (letterToGuess.isEmpty()) {
            status = GameStatus.NO_LETTER;
        } else if (letterToGuess.length() > 1) {
            status = GameStatus.TOO_MANY_LETTERS;
        } else {

            this.hangman.guess(letterToGuess.charAt(0));
            status = this.hangman.isOver() ? GameStatus.GAMEOVER : GameStatus.PLAYING;
        }

        return status;
    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.startGame();
    }
}
