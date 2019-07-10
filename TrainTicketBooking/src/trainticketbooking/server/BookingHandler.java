/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainticketbooking.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author Juanfer
 */
public class BookingHandler extends Thread {

    private final int id;
    private final Socket socket;
    private final BufferedReader inputReader;
    private final DataOutputStream outputStream;

    private boolean isClosed = false;

    public BookingHandler(int id, Socket socket) throws IOException {
        this.id = id;
        this.socket = socket;
        this.inputReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.outputStream = new DataOutputStream(this.socket.getOutputStream());
    }

    @Override
    public void run() {
        try {
            this.writeToClient("WELCOME TO THE TRAIN TICKET BOOKING SYSTEM!!");
            this.writeToClient("\nPlease enter the place you want to occupy with the following format <row>,<column>");

            boolean execute = true;
            while (execute) {

                if (TrainHandler.isFull()) {
                    this.writeToClient("FULL\nOh! we can't attend you, we are already full.");
                    execute = false;
                } else {
                    this.writeToClient("This is the current status of our train: \n\n" + TrainHandler.getPrintableMat());

                    String strPos = inputReader.readLine();
                    int pos[] = this.validateAndGetPosition(strPos);

                    if (pos != null) {
                        int x = pos[0];
                        int y = pos[1];

                        execute = this.reserve(x, y);
                    }
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

        } finally {
            isClosed = true;
        }
    }

    private boolean reserve(int x, int y) throws IOException {
        boolean continueExecuting = false;

        if (TrainHandler.reserve(x, y)) {
            this.writeToClient("BOOKED\nCongrats!! You have booked the ticket correctly.");

        } else if (TrainHandler.isFull()) {
            this.writeToClient("FULL\nOh! this is akward, it looks like we are already full.");

        } else {
            this.writeToClient("OCCUPIED\nOh! We can't book this is seat. It's already taken, you may try another.");
            continueExecuting = true;
        }

        return continueExecuting;
    }

    private int[] validateAndGetPosition(String strPos) throws IOException {
        int pos[] = null;
        String splitStr[] = strPos.split(",");

        try {

            if (splitStr.length == 2) {

                int x = Integer.parseInt(splitStr[0]) - 1;
                int y = Integer.parseInt(splitStr[1]) - 1;

                int rows = TrainHandler.getROWS();
                int cols = TrainHandler.getCOLS();

                boolean valid = x > -1 && x < rows && y > -1 && y < cols;
                if (valid) {
                    pos = new int[]{x, y};
                } else {
                    this.writeToClient("This seat doesn't exist.\nPlease enter a number between 1 and " + rows + " for rows "
                            + "and 1 and " + cols + " for columns.");
                }

            } else {
                this.writeToClient("Please stick to the format <row>,<column>. Both are numbers.");
            }

        } catch (NumberFormatException e) {
            this.writeToClient("I just told you BOTH are numbers!!!");
        }

        return pos;
    }

}
