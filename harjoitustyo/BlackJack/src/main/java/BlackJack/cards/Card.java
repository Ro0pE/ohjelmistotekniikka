
package BlackJack.cards;

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
