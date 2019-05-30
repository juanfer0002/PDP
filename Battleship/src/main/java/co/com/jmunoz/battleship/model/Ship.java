/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jmunoz.battleship.model;

/**
 *
 * @author sala312
 */
public class Ship {

    private Position[] positions;
    private boolean alive;

    public Ship(Position[] positions) {
        this.positions = positions;
        this.alive = true;
    }

    public Position[] getPositions() {
        return positions;
    }

    public void setPositions(Position[] positions) {
        this.positions = positions;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

}
