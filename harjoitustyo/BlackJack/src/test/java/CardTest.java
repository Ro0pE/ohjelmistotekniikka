import com.mycompany.blackjack.Card;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CardTest {
    
    public CardTest() {
        
    }
    
    @Test
    public void getValueReturnsCorrectValue(){
        Card card = new Card("Spades", 5);
        assertEquals(5, card.getValue());
        
    }
    
    @Test
    public void getSuitReturnsCorrectSuit(){
        Card card = new Card("Hearts", 5);
        assertEquals("Hearts", card.getSuit());
    }
    @Test
    public void showCardReturnsCorrectOuput(){
        Card faceCard11 = new Card("Diamonds", 11);
        Card faceCard12 = new Card("Diamonds", 12);
        Card faceCard13 = new Card("Diamonds", 13);
        Card aceCard = new Card("Hearts", 14);
        Card card = new Card("Hearts", 5);
        assertEquals("Diamonds 10", faceCard11.showCard());
        assertEquals("Diamonds 10", faceCard12.showCard());
        assertEquals("Diamonds 10", faceCard13.showCard());
        assertEquals("Hearts 11", aceCard.showCard());
        assertEquals("Hearts 5", card.showCard());
    }
    


    
}
