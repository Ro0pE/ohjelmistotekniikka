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
    String name;
    double accountBalance;
    public Boolean winner;
    public Boolean loser;
    public Hand hand;
    int handValue;
    boolean gotAce;
    
    
    public Player() {
        this.hand = new Hand();
        this.handValue = 0;
        this.name = "";
        this.accountBalance = 0;
        this.winner = false;
        this.loser = false;
        this.gotAce = false;
        
    }
    
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getAccountBalance() {
        return this.accountBalance;
    }
    /**
 * Metodi lisää tai vähentää pelaajan tililtä rahaa annetun paramaterin verran
 *
 * @param   amount    Käyttäjän antama summa
 *
 */
    public void setBalance(double amount) {
        System.out.println("adding " + amount);
        this.accountBalance = this.accountBalance + amount;
    }
    public void showHand() {
        for (int i = 0; i < this.hand.getHand().size(); i++) {
            if (this.hand.getHand().get(i).getValue() > 10) {
                System.out.println(this.hand.getHand().get(i).getSuit());
                
            } else {
                System.out.println(this.hand.getHand().get(i).getSuit() + " " + this.hand.getHand().get(i).getValue());
                
            }
            
        }
    }
    /**
 * Metodi lisää pelaajan käden arvoon parametrinä annetun kortin arvon
 *
 * @param   card   Käyttäjän antama kortti
 *
 */
    public void setHandValue(Card card) {
        this.handValue += card.getValue();
        if (this.handValue > 21 && this.gotAce == true) { 
            this.handValue = this.handValue - 10;
            this.gotAce = false;
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
