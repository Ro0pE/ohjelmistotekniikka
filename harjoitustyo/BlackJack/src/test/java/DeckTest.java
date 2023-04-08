import com.mycompany.blackjack.Card;
import com.mycompany.blackjack.Deck;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DeckTest {
    
    public DeckTest() {
        
    }
    
    @Test
    public void createDeckMakesDeckWith52Cards() {
        Deck deck = new Deck();
        deck.createDeck();
        assertEquals(52, deck.getDeckSize());
        
    }
    @Test
    public void randomCardValueIsCorrect() {     
        Deck deck = new Deck();
        deck.createDeck();
        for (int i = 0; i < 52; i++) {
            Card randomCard = deck.getRandomCard();
            assertTrue(randomCard.getValue() > 0);
            assertTrue(randomCard.getValue() < 15);
        }
    }
    @Test
    public void deckSizeIsCorrectAfterRemovingRandomCard() {
        Deck deck = new Deck();
        deck.createDeck();
        deck.getRandomCard();
        assertEquals(51, deck.getDeckSize());
    }
    

}
