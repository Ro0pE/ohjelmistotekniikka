package testing;

import game.cards.Card;
import game.cards.Hand;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author suvik
 */
public class HandTest {

    public HandTest() {
    }

    @Test
    public void getHandWorks() {
        Card firstCard = new Card("Hearts", 7);
        Card secondCard = new Card("Spades", 3);
        Hand myHand = new Hand();
        myHand.addCardsToHand(firstCard);
        myHand.addCardsToHand(secondCard);

        assertEquals(2, myHand.getHand().size());

    }

    @Test
    public void addCardToHandWorks() {
        Card newCard = new Card("Spades", 5);
        Hand myHand = new Hand();
        myHand.addCardsToHand(newCard);
        assertEquals("Spades5", myHand.getHand().get(0).showCard());

    }
}
