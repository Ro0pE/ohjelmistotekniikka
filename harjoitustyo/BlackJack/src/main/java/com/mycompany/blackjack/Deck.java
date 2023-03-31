/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blackjack;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;

/**
 *
 * @author suvik
 */
public class Deck {
 
    ArrayList<Card> deck;
    
    public Deck() {
        this.deck = new ArrayList<>();
        

                

        
    }
    
    public Card getRandomCard() {
        int max_num = deck.size();
        int min_num = 0;
        Random randomNumber = new Random();
        int x = randomNumber.nextInt(max_num - min_num) + min_num;
        Card randomCard = deck.get(x);
        deck.remove(x);
        return randomCard;


    }
    
    public void createDeck() {
        
        for (int i = 2; i < 15;i++) {
            String suit;
            switch (i) {
                case 11:
                    suit = "J";
                    break;
                case 12:
                    suit = "Q";
                    break;
                case 13:
                    suit = "K";
                    break;
                case 14:
                    suit = "A";
                    break;
                default:
                    suit = "";
                    break;
            }
            deck.add(new Card("Spades " + suit, i));
            deck.add(new Card("Diamonds " + suit, i));
            deck.add(new Card("Clubs " + suit, i));
            deck.add(new Card("Hearts "+ suit, i));
        }
        
    }
    

    
}
