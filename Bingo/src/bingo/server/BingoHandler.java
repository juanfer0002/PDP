/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author Juanfer
 */
public class BingoHandler {

    private final Socket socket;
    private final BufferedReader inputReader;
    private final DataOutputStream outputStream;

    private static final int ADDITION = 14;
    private static final String ROW = "row";
    private static final String COL = "col";

    private static final String BINGO_STR = "BINGO";

    private final int bingo[][] = new int[5][5];
    private final boolean bingoStatus[][] = new boolean[5][5];

    public BingoHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.inputReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.outputStream = new DataOutputStream(this.socket.getOutputStream());

        bingoStatus[2][2] = true;
        this.generateRandoms();
        bingo[2][2] = -1;
    }

    private void generateRandoms() {
        this.generateRandomsFor(0, 1);
        this.generateRandomsFor(1, 16);
        this.generateRandomsFor(2, 31);
        this.generateRandomsFor(3, 46);
        this.generateRandomsFor(4, 61);
    }

    private void generateRandomsFor(int col, int start) {
        int row[] = bingo[col];

        int i = 0;
        while (i < 5) {
            int n = (int) (Math.random() * ADDITION + start);

            if (checkNumberNotPresent(row, n)) {
                row[i] = n;
                i++;
            }
        }
    }

    private boolean checkNumberNotPresent(int row[], int number) {
        boolean found = false;

        for (int i = 0; i < row.length && !found; i++) {
            found = row[i] == number;
        }

        return !found;
    }

    public void play() {
        try {
            this.writeToClient("WELCOME TO THE BINGO!!!");
            boolean execute = true;
            while (execute) {

                this.printBingo();

                System.out.println("\n------------------\n");

                this.writeToClient("\nPlease tell us what number played!!");
                String option = inputReader.readLine();
                int playedNumber = Integer.parseInt(option);

                this.checkNumberInGame(playedNumber);

                if (this.didBingoWon()) {
                    this.writeToClient("\nGood bye!! YOU WON!");
                    execute = false;

                    this.printBingo();
                }

            }
        } catch (IOException e) {
            System.err.println("Error in handler with id: " + e.getMessage());
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
            System.err.println("Error closing handler with id: " + ex.getMessage());
        }

        try {
            socket.close();
        } catch (IOException ex) {
            System.err.println("Error closing handler with id: " + ex.getMessage());
        }

        try {
            socket.close();
        } catch (IOException ex) {
            System.err.println("Error closing handler with id: " + ex.getMessage());
        }
    }

    private void checkNumberInGame(int number) {
        boolean found = false;
        for (int i = 0; i < bingo.length && !found; i++) {
            for (int j = 0; j < bingo[i].length && !found; j++) {

                found = bingo[i][j] == number;
                if (found) {
                    bingoStatus[i][j] = true;
                }

            }
        }
    }

    private boolean didBingoWon() {
        boolean anyRowWon = this.checkIfAnyRowOrColWon(ROW);
        boolean anyColWon = anyRowWon || this.checkIfAnyRowOrColWon(COL);

        boolean primaryDiagonalWon = anyColWon || this.checkPrimaryDiagonalWon();
        boolean secondaryDiagonalWon = primaryDiagonalWon || this.checkSecondaryDiagonalWon();

        return anyRowWon || anyColWon || primaryDiagonalWon || secondaryDiagonalWon;

    }

    private boolean checkPrimaryDiagonalWon() {
        boolean won = true;

        for (int i = 0; i < bingoStatus.length && won; i++) {
            won = won && bingoStatus[i][i];
        }

        return won;
    }

    private boolean checkSecondaryDiagonalWon() {
        boolean won = true;

        for (int i = 0; i < bingoStatus.length && won; i++) {
            int col = i + 4 - 2 * i;
            won = won && bingoStatus[i][col];
        }

        return won;
    }

    private boolean checkIfAnyRowOrColWon(String type) {
        boolean won = false;

        for (int i = 0; i < bingoStatus.length && !won; i++) {
            won = ROW.equals(type) ? checkIfRowWon(i) : checkIfColumnWon(i);
        }

        return won;
    }

    private boolean checkIfRowWon(int row) {
        boolean won = true;

        for (int i = 0; i < bingoStatus.length && won; i++) {
            won = won && bingoStatus[row][i];
        }

        return won;
    }

    private boolean checkIfColumnWon(int col) {
        boolean won = true;

        for (int i = 0; i < bingoStatus.length && won; i++) {
            won = won && bingoStatus[i][col];
        }

        return won;
    }

    public void printBingo() {
        for (int i = 0; i < bingoStatus.length; i++) {
            String msg = BINGO_STR.charAt(i) + "   ";
            for (int j = 0; j < bingoStatus[i].length; j++) {
                String leftPadded = String.format("%02d", bingo[i][j]);
                msg += " " + (bingoStatus[i][j] ? "--" : leftPadded);
            }

            System.out.println(msg);
        }
    }

}
