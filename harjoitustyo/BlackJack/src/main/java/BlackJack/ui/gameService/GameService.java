

package BlackJack.ui.gameService;

import BlackJack.cards.Card;
import BlackJack.cards.Deck;
import blackjack.player.Player;

public class GameService { 
    public Deck deck;
    public Player ai;
    public Player player;
    
    
    public GameService(Player ai, Player player) {
        this.deck = new Deck();
        this.ai = ai;
        this.player = player;
        
    }
    public void startGame() {
        this.deck.createDeck();
        
        
    }
    public Card dealPlayer() {
        Card randomCard = this.deck.getRandomCard();
        this.player.hand.addCardsToHand(randomCard);
        this.player.setHandValue(randomCard);
        System.out.println("Player got: " + randomCard.showCard());
        return randomCard;
    
    }
    public Card dealAI() {
        Card randomCard = this.deck.getRandomCard();
        this.ai.hand.addCardsToHand(randomCard);
        this.ai.setHandValue(randomCard);
        System.out.println("AI got: " + randomCard.showCard());
        return randomCard;
    
    }

    public void checkBlackJack(int bet) {
        double blackJackMultiple = 1.5;
        double amount;
        if (ai.getHandValue() == 21 && player.getHandValue() == 21) {
            System.out.println("Both have BLACK JACK. DRAW! Bet is returned");
            player.setWinner();
            player.setBalance(bet);
        } else if (ai.getHandValue() == 21) {
            System.out.println("AI got BLACK JACK. Ai won!");
            player.setLoser();
            player.setBalance(-bet);
        } else if (player.getHandValue() == 21) {
            amount = blackJackMultiple * bet;
            System.out.println("Player got BLACK JACK! Player won!");
            player.setWinner();
            player.setBalance(amount);
        }

    }
 
    
    public void checkWinner(int bet) {
        System.out.println("Checking winner:");
        if (ai.getHandValue() <= 21 && player.getHandValue() <= 21) {
            if (ai.getHandValue() == player.getHandValue()) {
                System.out.println("DRAW so AI wins!");
                player.setLoser();
                player.setBalance(-bet);
            } else if (ai.getHandValue() > player.getHandValue()) {
                System.out.println("AI WON!");
                player.setLoser();
                player.setBalance(-bet);
            } else {
                System.out.println("PLAYER WON");
                player.setWinner();
                player.setBalance(bet);
                System.out.println("Player balance is " + this.player.getAccountBalance());
            }    
        } else if (ai.getHandValue() >= 21) {
            System.out.println("PLAYER WON!");
            player.setWinner();
            player.setBalance(bet);
            System.out.println("Player balance is " + this.player.getAccountBalance());
        }
    }
}