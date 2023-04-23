/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BlackJack.cards;

/**
 *
 * @author suvik
 */
public class Card {
    String suit;
    int value;
    
    public Card(String suit, int value) {
        this.suit = suit;
        this.value = value;
    }
    
    public String showCard() {
        switch (this.value) {
            case 11:
                
                this.value = 10;
                break;
            case 12:
                
                this.value = 10;
                break;
            case 13:
                
                this.value = 10;
                break;
            case 14:
               
                this.value = 11;
                break;
            default:
                break;
        }
        
        return this.suit + " " + this.value;
    }
    
    public int getValue() {
        return this.value;
    }
    public String getSuit() {
        return this.suit;
    }
    
}
