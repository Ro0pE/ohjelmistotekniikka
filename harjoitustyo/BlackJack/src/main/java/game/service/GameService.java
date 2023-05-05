

package game.service;

import game.cards.Card;
import game.cards.Deck;
import game.player.Player;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
 /**
 * Luokka huolehtii pelin käyttöliittymästä ja tarjoaa useita metodeja pelin toiminnaliisuuden takaamiseksi ja tiedostojen tallentamiseen.
 */
public class GameService { 
    public Deck deck;
    public Player ai;
    public Player player;
    public double bet;
    public File stats;
    public double oldStats; 
    

    
    public GameService(Player ai, Player player) {
        this.deck = new Deck();
        this.ai = ai;
        this.player = player;

        oldStats = 0;
       
        
    }
    public void startGame() {
        this.deck.createDeck();
        
        
    }
        public void createStatsFile(){
        try {
          stats = new File("stats.txt");
        if (stats.createNewFile()) {
          System.out.println("File created: " + stats.getName());
          FileWriter saveFiles = new FileWriter("stats.txt");
          saveFiles.write("100");
          saveFiles.close();
        } else {
          System.out.println("File already exists.");
        }
      } catch (IOException e) {
        System.out.println("An error occurred.");
        
    }
    }
    public double getStats() {
            try {
            File statsFile = new File("stats.txt");
            Scanner myReader = new Scanner(statsFile);
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              oldStats = Double.parseDouble(data);
               System.out.println("old stats:" + oldStats);
            }
            myReader.close();
          } catch (IOException e) {
            System.out.println("An error occurred.!!!!!!");
            
          }
            return oldStats;
  }
    
        
    public void saveStats() {
    try {
      FileWriter saveFiles = new FileWriter("stats.txt");
      double newBalance = getStats();
       
      newBalance =+ player.getAccountBalance();
      System.out.println("toimiikos balanze "  + newBalance);
      saveFiles.write(Integer.toString((int) player.getAccountBalance()));
      saveFiles.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      
    }
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
    
    public boolean setBet(TextField newBet, boolean betIsSet) {
        if (newBet.getText().isEmpty() || newBet.getText() == null) {
            return false;
        }
        
        try {
            this.bet = Double.parseDouble(newBet.getText());
            betIsSet = true;

            
            if (player.getAccountBalance() < this.bet) {
            System.out.println("Not enough money");
            return false;
            } else {
                return betIsSet;           
                }
            
                
        } catch (NumberFormatException e) {
            System.out.println("Invalid input, not a number");
            return false;
        }
       
    }
    

/**
 * Metodi tarkistaa onko jompi kumpi pelaaja saanut black jackin.
 *
 * @param   bet   Käyttäjän antama panos mikä kerrotaan black jack kertoimella
 * @param   winner Voittajan tiedot tallennetaan tähän
 *
 * 
 */
    public void checkBlackJack(double bet, Label winner) {
        

        double blackJackMultiple = 1.5;
        double amount;
        if (ai.getHandValue() == 21 && player.getHandValue() == 21) {
            System.out.println("Both have BLACK JACK. DRAW! Bet is returned");
            player.setWinner();
            player.setBalance(bet);
            winner.setText("DRAW, BOTH HAVE BLACK JACK! You win: " + Double.toString(bet) + "€");
            saveStats();
        } else if (ai.getHandValue() == 21) {
            System.out.println("AI got BLACK JACK. Ai won!");
            player.setLoser();
            player.setBalance(-bet);
            winner.setText("AI GOT BLACK JACK: You lose: " + Double.toString(bet) + "€");
            saveStats();
        } else if (player.getHandValue() == 21) {
            amount = blackJackMultiple * bet;
            System.out.println("Player got BLACK JACK! Player won!");
            player.setWinner();
            player.setBalance(amount);
            winner.setText("YOU GOT BLACK JACK: You win: " + Double.toString(amount) + "€");
            saveStats();
        }
        System.out.println("Player balance  " + player.getAccountBalance());
        

    }
 
  /**
 * Metodi tarkistaa pelin voittajan.
 *
 * @param   bet   Käyttäjän antama panos mikä kerrotaan normikertoimella
 * @param   winner Voittajan tiedot tallennetaan tähän
 *
 * 
 */
    public void checkWinner(double bet, Label winner) {
        System.out.println("Checking winner:");
        if (ai.getHandValue() <= 21 && player.getHandValue() <= 21) {
            if (ai.getHandValue() == player.getHandValue()) {
                System.out.println("DRAW so AI wins!");
                player.setLoser();
                player.setBalance(-bet);
                winner.setText("DRAW! You lose: " + Double.toString(bet) + "€");
                saveStats();
            } else if (ai.getHandValue() > player.getHandValue()) {
                System.out.println("AI WON!");
                player.setLoser();
                System.out.println("current bet " + bet);
                player.setBalance((bet * -1));
                winner.setText("AI WON! You lose: " + Double.toString(bet) + "€");
                saveStats();
            } else {
                System.out.println("PLAYER WON");
                player.setWinner();
                player.setBalance(bet);
                System.out.println("Player balance is " + this.player.getAccountBalance());
                winner.setText("PLAYER WON! You win: " + Double.toString(bet) + "€");
                saveStats();
            }    
        } else if (ai.getHandValue() >= 21) {
            System.out.println("PLAYER WON!");
            player.setWinner();
            player.setBalance(bet);
            winner.setText("PLAYER WON! You win: " + Double.toString(bet) + "€");
            System.out.println("Player balance is " + this.player.getAccountBalance());
            saveStats();
        } else if (player.getHandValue() > 21) {
                System.out.println("AI WON!");
                player.setLoser();
                player.setBalance(-bet);
                winner.setText("AI WON! You lose: " + Double.toString(bet) + "€");
                saveStats();
            
        }
        System.out.println("Player balance  " + player.getAccountBalance());
        
    }
}