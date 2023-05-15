package game.service;

import game.cards.Card;
import game.cards.Deck;
import game.database.Database;
import game.player.Player;
import java.io.File;
import java.util.regex.Pattern;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * Luokka huolehtii pelin käyttöliittymästä ja tarjoaa useita metodeja pelin
 * toiminnaliisuuden takaamiseksi ja tiedostojen tallentamiseen.
 */
public class GameService {

    public Deck deck;
    public Player ai;
    public Player player;
    public double bet;
    public File stats;
    public double oldStats;
    public Label cardsValueOverText;
    public Label winnerText;
    public Database database;
    public Boolean playerAce;
    public Boolean aiAce;
    public int counterPlayer;
    public int counterAI;
    private Pattern pattern;

    public GameService(Player ai, Player player) {
        this.pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        this.deck = new Deck();
        this.ai = ai;
        this.player = player;
        this.cardsValueOverText = new Label("OVER!");
        this.winnerText = new Label("WINNER!");
        this.database = new Database();
        oldStats = 0;
        this.playerAce = false;
        this.aiAce = false;
        this.counterPlayer = 0;
        this.counterAI = 0;
    }

    public void setUpResultTexts() {
        cardsValueOverText.setTranslateX(50);
        cardsValueOverText.setTranslateY(50);
        cardsValueOverText.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 22px;");
        cardsValueOverText.setMinWidth(150);
        cardsValueOverText.setMinHeight(20);

        winnerText.setTranslateX(50);
        winnerText.setTranslateY(50);
        winnerText.setStyle("-fx-background-color: #80FF66; -fx-text-fill: black; -fx-font-size: 22px;");
        winnerText.setMinWidth(150);
        winnerText.setMinHeight(20);

    }

    public void startGame() {
        this.deck.createDeck();

    }

    public void database() {
        database.createAccountBalanceFile();
        database.createHighScoresFile();
    }

    public Card dealPlayer() {
        Card randomCard = this.deck.getRandomCard();
        this.player.hand.addCardsToHand(randomCard);
        if (randomCard.getValue() == 11) {
            this.playerAce = true;
        }
        this.player.setHandValuePlayer(randomCard, playerAce);

        return randomCard;

    }

    public Card dealAI() {
        Card randomCard = this.deck.getRandomCard();
        this.ai.hand.addCardsToHand(randomCard);
        if (randomCard.getValue() == 11) {
            this.aiAce = true;
        }
        this.ai.setHandValueAI(randomCard, aiAce);

        return randomCard;
    }

    public boolean setBet(TextField newBet, boolean betIsSet) {
        if (pattern.matcher(newBet.getText()).matches()) {
            try {
                this.bet = Double.parseDouble(newBet.getText());

                betIsSet = true;

             if (player.getAccountBalance() < this.bet) {

                    return false;
                } else {
                    return betIsSet;
                }

        } catch (NumberFormatException e) {

            return false;
        }

            
        
    } else {
            return false;
        }


       
    }

    /**
     * Metodi tarkistaa onko jompi kumpi pelaaja saanut black jackin.
     *
     * @param bet Käyttäjän antama panos mikä kerrotaan black jack kertoimella
     * @param winner Voittajan tiedot tallennetaan tähän
     * @param opponentHand vastustajan käsi
     * @param playerHand pelaajan käsi
     *
     *
     */
    public void checkBlackJack(double bet, Label winner, Pane opponentHand, Pane playerHand) {
        setUpResultTexts();
        double blackJackMultiple = 1.5;
        double amount;
        if (ai.getHandValue() == 21 && player.getHandValue() == 21) {

            player.setWinner();
            player.setBalance(bet);
            winner.setText("BET RETURNED : " + Double.toString(bet) + "€");
            winnerText.setText("BOTH HAVE BLACK JACK, DRAW!");
            playerHand.getChildren().add(winnerText);
            opponentHand.getChildren().add(winnerText);
            database.saveStats(player);

        } else if (ai.getHandValue() == 21) {

            player.setLoser();
            player.setBalance(-bet);
            winnerText.setText("YOU LOST " + Double.toString(bet) + "€");
            winnerText.setText("BLACK JACK! AI WON!");
            opponentHand.getChildren().add(winnerText);
            database.saveStats(player);

        } else if (player.getHandValue() == 21) {
            amount = blackJackMultiple * bet;
            player.setWinner();
            player.setBalance(amount);
            winner.setText("YOU WON " + Double.toString(amount) + "€");
            winnerText.setText("BLACK JACK! YOU WON!");
            playerHand.getChildren().add(winnerText);
            database.saveStats(player);

        }

    }

    /**
     * Metodi tarkistaa pelin voittajan.
     *
     * @param bet Käyttäjän antama panos mikä kerrotaan normikertoimella
     * @param winner Voittajan tiedot tallennetaan tähän
     * @param opponentHand Vastustajan käsi, johon lisätään voitto/häviö label
     * @param playerHand Pelaajan käsi, johon lisätään voitto/häviö label
     *
     *
     */
    public void checkWinner(double bet, Label winner, Pane opponentHand, Pane playerHand) {
        setUpResultTexts();

        if (ai.getHandValue() <= 21 && player.getHandValue() <= 21) {
            if (ai.getHandValue() == player.getHandValue()) {

                player.setLoser();
                player.setBalance(-bet);
                winner.setText("DRAW,YOU LOST " + Double.toString(bet) + "€");
                opponentHand.getChildren().add(winnerText);
                database.saveStats(player);

            } else if (ai.getHandValue() > player.getHandValue()) {

                player.setLoser();

                player.setBalance((bet * -1));
                winner.setText("YOU LOST " + Double.toString(bet) + "€");
                opponentHand.getChildren().add(winnerText);
                database.saveStats(player);

            } else {

                player.setWinner();
                player.setBalance(bet);

                winner.setText("PLAYER WON " + Double.toString(bet * 2) + "€");
                playerHand.getChildren().add(winnerText);
                database.saveStats(player);

            }
        } else if (ai.getHandValue() >= 21) {

            player.setWinner();
            player.setBalance(bet);
            winner.setText("PLAYER WON " + Double.toString(bet * 2) + "€");

            opponentHand.getChildren().add(cardsValueOverText);
            playerHand.getChildren().add(winnerText);
            database.saveStats(player);

        } else if (player.getHandValue() > 21) {

            player.setLoser();
            player.setBalance(-bet);
            winner.setText("YOU LOST " + Double.toString(bet) + "€");
            playerHand.getChildren().add(cardsValueOverText);
            opponentHand.getChildren().add(winnerText);
            database.saveStats(player);

        }

    }
}
