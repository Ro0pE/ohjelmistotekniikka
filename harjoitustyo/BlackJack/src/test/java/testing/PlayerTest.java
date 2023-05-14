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
    Player ai;
    
    public PlayerTest() {
        
        
    }
    
    @Before
    public void setUp() {
       player = new Player();
       ai = new Player();
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
        int counter = 0;
        player.setHandValuePlayer(cardAce,true);
        assertEquals(11, player.getHandValue());   
     
    }
    
    @Test
    public void handValueWithMultipleCardsWorks() {
        Card cardAce = new Card("Hearts", 14);
        Card card5 = new Card("Spades", 5);
        Card cardQueen = new Card("Diamonds", 12);
        player.gotAce = true;
        int counter = 0;
        player.setHandValuePlayer(cardAce, true);
        player.setHandValuePlayer(card5, true);
        player.setHandValuePlayer(cardQueen, true);
        assertEquals(16, player.getHandValue());
    }
    @Test
    public void setHandValueWorks() {
        Card card5 = new Card("Spades", 5);
        int counter = 0;
        player.setHandValuePlayer(card5, Boolean.FALSE);
        assertEquals(5, player.getHandValue());
        
    }
    @Test
    public void setHandValueWithAceWorks() {
        Card card5 = new Card("Spades", 5);
        Card cardQ = new Card("Spades", 12);
        Card cardAce = new Card("Hearts", 14);
        int counter = 0;
        player.setHandValuePlayer(cardQ, Boolean.FALSE);
        player.setHandValuePlayer(card5, Boolean.FALSE);
        player.setHandValuePlayer(cardAce, Boolean.TRUE);
        assertEquals(16, player.getHandValue());
        
    }
        @Test
    public void setAIHandValueWorks() {
        Card card5 = new Card("Spades", 5);
        int counter = 0;
        ai.setHandValueAI(card5, Boolean.FALSE);
        assertEquals(5, ai.getHandValue());
        
    }
    @Test
    public void setHandAIValueWithAceWorks() {
        Card card5 = new Card("Spades", 5);
        Card cardQ = new Card("Spades", 12);
        Card cardAce = new Card("Hearts", 14);
        int counter = 0;
        ai.setHandValueAI(cardQ, Boolean.FALSE);
        ai.setHandValueAI(card5, Boolean.FALSE);
        ai.setHandValueAI(cardAce, Boolean.TRUE);
        assertEquals(16, ai.getHandValue());
        
    }
    
    @Test
    public void removeValueFromAIHandWorks() {
        int counter = 0;
        Card cardQ = new Card("Spades", 12);
        ai.setHandValueAI(cardQ, Boolean.FALSE);
        ai.removeValueFromHand(5); 
        assertEquals(5, ai.getHandValue());
        
    }
    @Test
    public void addingValueToAIHandWorks() {
        int counter = 0;
        Card cardQ = new Card("Spades", 12);
        ai.setHandValueAI(cardQ, Boolean.FALSE);
        ai.addValueBackToHand(5); 
        assertEquals(15, ai.getHandValue());
        
    } 
    @Test
    public void setAndGetWinnerWorks(){
        player.setWinner();
        assertEquals(true, player.getWinner());
    }
    @Test
    public void setAndGetLoserWorks(){
        player.setLoser();
        assertEquals(true, player.getLoser());
    }
    
    @Test
    public void clearPlayerStatsWork() {
        Card cardQ = new Card("Spades", 12);
        player.setWinner();
        player.setHandValuePlayer(cardQ, Boolean.FALSE);
        player.clearStats();
        assertEquals(0, player.getHandValue());
        assertEquals(false, player.getLoser());
        assertEquals(false, player.getWinner());
       
        
    }


}
