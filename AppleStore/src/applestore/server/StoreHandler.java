/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applestore.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juanfer
 */
public class StoreHandler extends Thread {

    private final int id;
    private final Socket socket;
    private final BufferedReader inputReader;
    private final DataOutputStream outputStream;

    private boolean isClosed = false;

    public StoreHandler(int id, Socket socket) throws IOException {
        this.id = id;
        this.socket = socket;
        this.inputReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.outputStream = new DataOutputStream(this.socket.getOutputStream());
    }

    @Override
    public void run() {
        try {
            this.writeToClient("WELCOME TO THE APPLE STORE!!");
            boolean execute = true;
            while (execute) {

                this.writeToClient("\nPlease enter the number of one of these options:\n1. Buy an iPhone\n2. See how many we have left\n3. exit");
                String option = inputReader.readLine();

                switch (option) {
                    case "1":
                        execute = !this.buyPhone();
                        break;

                    case "2":
                        this.checkInventory();
                        break;

                    case "3":
                        this.exit();
                        execute = false;
                        break;

                    default:
                        this.writeToClient("That isn't an option. Please enter a correct one.");
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

    private boolean buyPhone() throws IOException {
        this.writeToClient("The cost of the iPhone is $1000. Are you sure you're willing to buy it?\n\n1. Yes\n2. No");

        String response = this.inputReader.readLine();
        boolean r = "1".equals(response);
        if (r) {

            String phone = Data.getQueue().poll();
            if (phone != null) {
                this.writeToClient("Congrats! You bought the phone: " + phone + "\n\nGOOD BYE!! Enjoy your product");
            } else {
                this.writeToClient("Oh shit!! We don't have any phone left.");
            }

        }

        return r;
    }

    private void checkInventory() throws IOException {
        int inventorySize = Data.getQueue().size();
        String msg = inventorySize == 0
                ? "We don't have phones left."
                : "We still have " + inventorySize + " left.";

        this.writeToClient(msg);
    }

    private void exit() throws IOException {
        this.writeToClient("See you soon. We hope you come back to waste... oh! we mean spend you money on our AMAZING products!!");
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

}
