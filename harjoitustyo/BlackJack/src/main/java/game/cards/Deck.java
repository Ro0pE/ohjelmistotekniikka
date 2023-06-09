/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.cards;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;

/**
 * Luokka rakentaa korttipakan ja tarjoaa toiminnallisuuden korttien
 * satunnaiseen poistoon
 */
public class Deck {

    ArrayList<Card> deck;

    public Deck() {
        this.deck = new ArrayList<>();

    }

    public int getDeckSize() {
        return this.deck.size();
    }

    /**
     * Arpoo satunnaisen kortin pakasta ja poistaa sen sieltä.
     *
     * @return randomCard palauttaa satunnaisesti vedetyn kortin pakasta.
     *
     */
    public Card getRandomCard() {
        int maxNum = deck.size();
        int minNum = 0;
        Random randomNumber = new Random();
        int x = randomNumber.nextInt(maxNum - minNum) + minNum;
        Card randomCard = deck.get(x);
        deck.remove(x);
        return randomCard;

    }

    /**
     * Metodi rakentaa korttipakan
     */
    public void createDeck() {
        for (int i = 2; i < 15; i++) {
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
            deck.add(new Card("Hearts " + suit, i));
        }
    }

}
