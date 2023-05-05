/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.cards;

import java.util.ArrayList;

/**
 * Luokka tarjoaa toiminnallisuuden pelaajan korttikäden tutkimiseen ja muokkkaamiseen
 */
public class Hand  {
    ArrayList<Card> hand;
    
    public Hand() {
        hand = new ArrayList<>();
        
    }
    /**
 * Lisää annetun kortin pelaajan käteen
* @param   card   Käyttäjän antama kortti
 */
    
    public void addCardsToHand(Card card) {
        hand.add(new Card(card.suit, card.value));
    }
    
    public void showHand() {
        for (int i = 0; i < hand.size(); i++) {
            System.out.println(this.hand.get(i).showCard());
        }
       
    }
    public ArrayList<Card> getHand() {
        return this.hand;
    }
    
}