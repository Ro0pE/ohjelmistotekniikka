/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.player;

import game.cards.Card;
import game.cards.Card;
import game.cards.Hand;
import game.cards.Hand;

/**
 * Luokka pitää lukua pelaajan pelinaikaisista "tiloista" ja korttikädestä
 */
public class Player {

    double accountBalance;
    public Boolean winner;
    public Boolean loser;
    public Hand hand;
    int handValue;
    public boolean gotAce;
    public int counterAI;
    public int counterPlayer;

    public Player() {
        this.hand = new Hand();
        this.handValue = 0;
        this.counterAI = 0;
        this.counterPlayer = 0;
        this.accountBalance = 0;
        this.winner = false;
        this.loser = false;
        this.gotAce = false;

    }

    public double getAccountBalance() {
        return this.accountBalance;
    }

    /**
     * Metodi lisää tai vähentää pelaajan tililtä rahaa annetun paramaterin
     * verran
     *
     * @param amount Käyttäjän antama summa
     *
     */
    public void setBalance(double amount) {

        this.accountBalance = this.accountBalance + amount;
    }

    /**
     * Metodi lisää pelaajan käden arvoon parametrinä annetun kortin arvon
     *
     * @param card Käyttäjän antama kortti
     * @param ace Tarkistaa onko pelaajalla ässä
     *
     */
    public void setHandValuePlayer(Card card, Boolean ace) {
        this.gotAce = ace;
        this.handValue += card.getValue();
        if (this.handValue > 21 && this.gotAce == true && this.counterPlayer == 0) {
            this.counterPlayer = 1;
            this.handValue = this.handValue - 10;

        }
        if (this.handValue > 21) {
            this.counterPlayer = 0;
        }

    }

    public void setHandValueAI(Card card, Boolean ace) {
        this.gotAce = ace;
        this.handValue += card.getValue();
        if (this.handValue > 21 && this.gotAce == true && this.counterAI == 0) {
            this.counterAI = 1;
            this.handValue = this.handValue - 10;

        }
        if (this.handValue > 21) {
            this.counterAI = 0;
        }

    }

    public void removeValueFromHand(int value) {
        this.handValue = this.handValue - value;
    }

    public void addValueBackToHand(int value) {
        this.handValue = this.handValue + value;
    }

    public int getHandValue() {
        return this.handValue;

    }

    public void setWinner() {
        this.winner = true;
    }

    public boolean getWinner() {
        return this.winner;
    }

    public void setLoser() {
        this.loser = true;
    }

    public boolean getLoser() {
        return this.loser;
    }

    /**
     * Alustaa käyttäjän pelinaikaiset tiedot uutta peliä varten.
     */
    public void clearStats() {
        this.hand = new Hand();
        this.loser = false;
        this.winner = false;
        this.gotAce = false;
        this.handValue = 0;

    }

}
