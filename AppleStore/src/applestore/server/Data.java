/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applestore.server;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author Juanfer
 */
public class Data {

    private final static Queue<String> QUEUE = new ConcurrentLinkedQueue<>();

    static {
        QUEUE.add("iPhone 1");
        QUEUE.add("iPhone 2");
    }

    public static Queue<String> getQueue() {
        return QUEUE;
    }

}
