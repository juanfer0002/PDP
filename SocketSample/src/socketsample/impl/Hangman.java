/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketsample.impl;

import java.util.Arrays;

/**
 *
 * @author Juanfer
 */
public class Hangman {

    private char[] word;
    private char[] currentGuessing;

    public Hangman(String word) {
        if (word == null || word.trim().isEmpty()) {
            throw new NotValidWord("Not word can't be empty or null");
        }

        this.word = word.trim().toLowerCase().toCharArray();
        this.currentGuessing = new char[this.word.length];
        Arrays.fill(this.currentGuessing, '-');
    }

    public void guess(char c) {
        for (int i = 0; i < this.word.length; i++) {
            this.currentGuessing[i] = this.word[i] == c ? c : this.currentGuessing[i];
        }
    }

    public String getCurrentGuessing() {
        return new String(currentGuessing);
    }

    public boolean isOver() {
        return !new String(currentGuessing).contains("-");
    }

}
