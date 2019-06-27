/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketsample.impl;

/**
 *
 * @author Juanfer
 */
public enum GameStatus {

    TOO_MANY_LETTERS("***Please send no more than one letter to guess"),
    NO_LETTER("***Please send a letter to guess"),
    GAMEOVER("***Oh, you got it. Congrats!"),
    PLAYING("***It looks like you still need to guess some more letters");

    private final String msg;

    private GameStatus(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

}
