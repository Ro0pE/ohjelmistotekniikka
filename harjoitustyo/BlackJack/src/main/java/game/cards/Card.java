
package game.cards;

/**
 * Luokka huolehtii korteista ja pitää huolen, että pelitilanteessa korttien arvot ovat oikein
 */
public class Card {
    public String suit;
    public int value;
    
    public Card(String suit, int value) {
        this.suit = suit;
        this.value = value;
    }

 /**
 * Metodi antaa kuvakorteille oikeat arvot
 */
    public String showCard() {
        switch (this.value) {
            case 11:
                
                return this.suit;
                
            case 12:
                
                return this.suit;
                
            case 13:
                
                return this.suit;
                
            case 14:
               
                return this.suit;
                
            default:
                break;
        }
        
        return this.suit + this.value;
    }
    
    public int getValue() {
        return this.value;
    }
    public String getSuit() {
        return this.suit;
    }
    
}
