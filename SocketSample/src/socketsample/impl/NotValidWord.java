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
public class NotValidWord extends RuntimeException {

    public NotValidWord(String msg) {
        super(msg);
    }

}
