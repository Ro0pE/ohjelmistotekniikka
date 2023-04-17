/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blackjack;

/**
 *
 * @author suvik
 */
public class Player {
    String name;
    double accountBalance;
    Boolean winner;
    Boolean loser;
    Hand hand;
    int handValue;
    boolean gotAce;
    
    
    public Player(String name) {
        this.hand = new Hand();
        this.handValue = 0;
        this.name = name;
        this.accountBalance = 0;
        this.winner = false;
        this.loser = false;
        this.gotAce = false;
        
    }
    
    public String getName() {
        return this.name;
    }
    public double getAccountBalance() {
        return this.accountBalance;
    }
    public void setBalance(double amount) {
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
    public void setHandValue(Card card) {
        int value = 0;
        if (card.getValue() > 10 && card.getValue() < 14) {
            value = 10;
        } else if (card.getValue() == 14) {      
            this.gotAce = true;
            value = 11;

        } else {
            value = card.getValue();
        }
        this.handValue = this.handValue + value;
        if (this.handValue > 21 && this.gotAce == true) { 
            this.handValue = this.handValue - 10;
            this.gotAce = false;
        }
        
    }
    public int getHandValue() {
        return this.handValue;
        
    }
    
    public void setWinner() {
        this.winner = true;
    }
    public void setTest(){
        
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
    
    public void clearStats() {
        this.hand = new Hand();
        this.loser = false;
        this.winner = false;
        this.gotAce = false;
        this.handValue = 0;
        
    }
    
}
