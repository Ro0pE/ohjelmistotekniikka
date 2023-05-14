package testing;

import game.cards.Card;
import game.player.Player;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {
    Player player;
    
    public PlayerTest() {
        
        
    }
    
    @Before
    public void setUp() {
       player = new Player();
    }
    

    
    @Test
    public void getAccountBalanceReturnsCorrectBalance(){
        assertEquals(0.0, player.getAccountBalance(), 0.01);
    }
    
    @Test
    public void setBalanceChangesCurrentBalance() {
        player.setBalance(1250);
        assertEquals(1250.0, player.getAccountBalance(), 0.01);
        
    }
    @Test
    public void handValueIsCorrect() {
        Card cardAce = new Card("Spades", 14);
        player.setHandValuePlayer(cardAce,true);
        assertEquals(11, player.getHandValue());   
     
    }
    
    @Test
    public void handValueWithMultipleCardsWorks() {
        Card cardAce = new Card("Hearts", 14);
        Card card5 = new Card("Spades", 5);
        Card cardQueen = new Card("Diamonds", 12);
        player.gotAce = true;
        player.setHandValuePlayer(cardAce, true);
        player.setHandValuePlayer(card5, true);
        player.setHandValuePlayer(cardQueen, true);
        assertEquals(16, player.getHandValue());
    }

}
