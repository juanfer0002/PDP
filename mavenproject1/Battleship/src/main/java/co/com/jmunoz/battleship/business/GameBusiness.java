/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jmunoz.battleship.business;

import co.com.jmunoz.battleship.model.Position;
import co.com.jmunoz.battleship.model.Ship;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sala312
 */
public class GameBusiness {

    private static final int BOARD_SIZE = 10;
    private final Position[][] shipBoardPositions = new Position[BOARD_SIZE][BOARD_SIZE];
    private final int[][] gameBoard = new int[BOARD_SIZE][BOARD_SIZE];

    private final List<Ship> ships = new ArrayList<>();

    public GameBusiness() {
        this.buildBoards();
    }

    private void buildBoards() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            shipBoardPositions[i] = new Position[BOARD_SIZE];
            gameBoard[i] = new int[BOARD_SIZE];
        }

        this.setShipPositions();
    }

    private void setShipPositions() {
        ships.clear();
        this.initShips();
        this.markGameBoardWithShips();
    }

    private void initShips() {
        Ship ship1 = new Ship(new Position[]{
            new Position(0, 0),
            new Position(0, 1),
            new Position(0, 2)
        });

        Ship ship2 = new Ship(new Position[]{
            new Position(3, 3),
            new Position(4, 3),
            new Position(5, 3)
        });

        Ship ship3 = new Ship(new Position[]{
            new Position(8, 9),
            new Position(8, 8),
            new Position(8, 7),
            new Position(7, 7)
        });

        ships.add(ship1);
        ships.add(ship2);
        ships.add(ship3);
    }

    private void markGameBoardWithShips() {
        for (Ship ship : ships) {
            Position[] positions = ship.getPositions();
            for (Position position : positions) {
                shipBoardPositions[position.getX()][position.getY()] = position;
            }
        }
    }

    public boolean play(int x, int y) {
        Position position = shipBoardPositions[x][y];

        boolean didItHit = false;
        if (position != null) {
            position.setHit(true);
            didItHit = true;
            gameBoard[x][y] = 1;
        } else {
            gameBoard[x][y] = -1;
        }

        return didItHit;
    }

    public boolean checkIfOver() {
        boolean isThereOneAlive = false;

        for (Ship ship : ships) {
            Position[] positions = ship.getPositions();

            boolean allHit = true;
            for (Position position : positions) {
                allHit = allHit && position.isHit();
            }

            ship.setAlive(!allHit);
            isThereOneAlive = isThereOneAlive || ship.isAlive();
        }

        return !isThereOneAlive;
    }

    public void restart() {
        this.buildBoards();
    }

    public int[][] getGameBoard() {
        return gameBoard;
    }

}
