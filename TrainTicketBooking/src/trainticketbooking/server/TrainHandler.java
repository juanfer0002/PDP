/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainticketbooking.server;

/**
 *
 * @author Juanfer
 */
public class TrainHandler {

    private static final int ROWS = 10;
    private static final int COLS = 4;
    private final static boolean TRAIN_SEATS[][] = new boolean[ROWS][COLS];

    public static boolean isFull() {
        boolean isFull = true;

        for (int i = 0; i < TRAIN_SEATS.length && isFull; i++) {
            for (int j = 0; j < TRAIN_SEATS[i].length && isFull; j++) {
                isFull = isFull && TRAIN_SEATS[i][j];
            }
        }

        return isFull;
    }

    public static boolean reserve(int x, int y) {
        boolean reserved = false;

        boolean available = !TRAIN_SEATS[x][y];
        if (available) {
            reserved = TRAIN_SEATS[x][y] = true;
        }

        return reserved;
    }

    public static String getPrintableMat() {
        String msg = "";
        
        for (boolean[] row : TRAIN_SEATS) {

            for (int i = 0; i < row.length; i++) {
                boolean cell = row[i];

                String middleBlank = row.length / 2 == i + 1 ? "  " : "";
                msg += (cell ? "X " : "O ") + middleBlank;

            }

            msg += "\n";
        }

        return msg;
    }

    public static int getROWS() {
        return ROWS;
    }

    public static int getCOLS() {
        return COLS;
    }

}
