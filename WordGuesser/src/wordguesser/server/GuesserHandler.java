/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordguesser.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Arrays;

/**
 *
 * @author Juanfer
 */
public class GuesserHandler extends Thread {

    private final static char BLACK_CIRCLE = '*';
    private final static char WHITE_CIRCLE = '+';
    private static final char UNDERSCORE = '_';

    private final static String CORRECT_ANSWER = "****";

    private final char[] word;

    private final int id;
    private final Socket socket;
    private final BufferedReader inputReader;
    private final DataOutputStream outputStream;

    public GuesserHandler(int id, Socket socket) throws IOException {
        this.word = this.getDynamicWord();
        this.id = id;
        this.socket = socket;
        this.inputReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.outputStream = new DataOutputStream(this.socket.getOutputStream());
    }

    private char[] getDynamicWord() {
        char dynamicChars[] = new char[4];

        for (int i = 0; i < dynamicChars.length; i++) {
            dynamicChars[i] = (char) (65 + Math.random() * 25);
        }

        return dynamicChars;
    }

    @Override
    public void run() {
        try {
            this.writeToClient("WELCOME TO THE GUESS-WORD GAME");
            boolean execute = true;
            
            System.out.println("The handler with id: " + id + " is guessing: " + new String(word));
            
            while (execute) {

                this.writeToClient("\nEnter a word of 4 characters to begin guessing");
//                TODO: rules!!
                String input = inputReader.readLine();

                boolean validLength = input != null && input.length() == 4;
                if (validLength) {
                    String response = this.compareInputToWord(input);
                    this.writeToClient("Oh!! this is what you got:\n" + response);

                    if (CORRECT_ANSWER.equals(response)) {
                        this.writeToClient("Congrats!! It looks like you WON!!");
                        execute = false;
                        this.close();
                    }

                } else {
                    this.writeToClient("\nWe just told you FOUR(4) characters");
                }

            }
        } catch (IOException e) {
            System.err.println("Error in handler with id: " + id + " - " + e.getMessage());
        }

        this.close();
    }

    private void writeToClient(String msg) throws IOException {
        msg = msg.replace("\n", "/n");
        outputStream.writeBytes(msg + "\n");
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException ex) {
            System.err.println("Error closing handler with id: " + id + " - " + ex.getMessage());
        }

        try {
            socket.close();
        } catch (IOException ex) {
            System.err.println("Error closing handler with id: " + id + " - " + ex.getMessage());
        }

        try {
            socket.close();
        } catch (IOException ex) {
            System.err.println("Error closing handler with id: " + id + " - " + ex.getMessage());
        }
    }

    public void reject() {
        try {
            this.writeToClient("We can't attend you right now. We are at our full capacity. Good bye.");
        } catch (IOException e) {
            System.err.println("Error in handler with id: " + id + " - " + e.getMessage());
        } finally {
            this.close();
        }

    }

    private String compareInputToWord(String input) {
        String response = "";
        input = input.toUpperCase();
        char inputChars[] = input.toCharArray();

        for (int i = 0; i < word.length; i++) {
            char wordChar = word[i];
            int idxFound = -1;
            boolean samePosition = false;

            for (int j = 0; j < inputChars.length; j++) {

                char inputChar = inputChars[j];
                boolean sameChar = wordChar == inputChar;

                if (sameChar) {
                    idxFound = j;
                    samePosition = i == j;
                    if (samePosition) {
                        break;
                    }
                }
            }

            if (idxFound > -1) {
                inputChars[idxFound] = UNDERSCORE;
                response += samePosition ? BLACK_CIRCLE : WHITE_CIRCLE;
            }
        }

        return this.orderResponse(response);
    }

    private String orderResponse(String response) {
        char chars[] = response.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

}
