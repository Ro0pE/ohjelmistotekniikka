
package blackjack.ui;
import BlackJack.ui.GameService;
import BlackJack.cards.Card;
import blackjack.player.Player;
import java.io.File;  
import java.io.FileWriter;
import java.io.IOException;   

import java.util.Scanner;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author suvik
 */
public class Game {
    Boolean gameIsOn;
    Player player; 
    Player ai;
    Scanner scanner;
    int bet;
    Boolean checkBalance;
    int profit;
    File stats;
    double oldStats;
    Stage stage;
    HBox playerHand;
    HBox handValues;
    HBox opponentHand;
    
    
    int input;
    public Game(Stage stage){
        this.stage = stage;
        bet = 0;
        profit = 0;
        scanner = new Scanner(System.in);
        player = new Player("Boris");
        ai = new Player("AI");
        gameIsOn = true;
        checkBalance = false;
        oldStats = 0;
        playerHand = new HBox(15);
        opponentHand = new HBox(15);
        handValues = new HBox(15);
        System.out.println("test");
        
    }
    public String getHandValue(Player player) {
        return String.valueOf(player.getHandValue());
    }
    public Button quitGameButton() {
        Button quitGame = new Button("Quit game");
        quitGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               stage.close();    
            }
        });
        return quitGame;
        
    }
    public void startOptionsUI() {
        Button newGame = new Button("New game");
        newGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               gameUI();
            }
        });
        Button loadGame = new Button("Load game");
        
        

        VBox optionsSetup = new VBox(8);
        optionsSetup.setPrefSize(600, 500);
        optionsSetup.getChildren().add(newGame);
        optionsSetup.getChildren().add(loadGame);
        optionsSetup.getChildren().add(quitGameButton());
        Scene startScene = new Scene(optionsSetup);
        stage.setScene(startScene);
        stage.show();
       
        
    }
    
    public void gameUI() {
        GameService newGame = new GameService(ai, player);
        newGame.startGame();
        Card aiFirstCard = newGame.dealAI();
        Card aiSecondCard = newGame.dealAI();
        Card playerFirstCard = newGame.dealPlayer();
        Card playerSecondCard = newGame.dealPlayer();
        

        HBox actionButtons = new HBox();
        Button hitButton = new Button("Hit");
        hitButton.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
               Card newCard = newGame.dealPlayer();
               playerHand.getChildren().add(new Label(newCard.getSuit() + " " + newCard.getValue()));
               //handValues = new HBox();
               
               
            }
        });
        Button stayButton = new Button("Stay");
           stayButton.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
               while (ai.getHandValue() < 17) {
                Card newCard = newGame.dealAI();
                opponentHand.getChildren().add(new Label(newCard.getSuit() + " " + newCard.getValue()));
                   
               }
               
               //handValues = new HBox();
               
               
            }
        });
        Button doubleButton = new Button("Double");
        actionButtons.getChildren().add(hitButton);
        actionButtons.getChildren().add(stayButton);
        actionButtons.getChildren().add(doubleButton);
        actionButtons.setTranslateX(200);
        actionButtons.setTranslateY(300);
        
        
            
       
       
        opponentHand.setTranslateX(200);
        opponentHand.setTranslateY(150);
        opponentHand.getChildren().add(new Label(aiFirstCard.getSuit() + " " + aiFirstCard.getValue()));
        opponentHand.getChildren().add(new Label(aiSecondCard.getSuit() + " " + aiSecondCard.getValue()));

        
        
        playerHand.setTranslateX(200);
        playerHand.setTranslateY(-150);
        playerHand.getChildren().add(new Label(playerFirstCard.getSuit() + " " + playerFirstCard.getValue()));
        playerHand.getChildren().add(new Label(playerSecondCard.getSuit() + " " + playerSecondCard.getValue()));
    
        
        handValues.getChildren().add(new Label(getHandValue(player)));
        

        
        BorderPane gameSetup = new BorderPane();
        gameSetup.setPrefSize(750, 750);
        gameSetup.setTop(opponentHand);
        gameSetup.setRight(quitGameButton());
        gameSetup.setBottom(playerHand);
        gameSetup.setLeft(handValues);
        gameSetup.setCenter(actionButtons);
        
        Scene gameView= new Scene(gameSetup);
        stage.setScene(gameView);
        
        final long startNanoTime = System.nanoTime();
         new AnimationTimer()
    {
        public void handle(long currentNanoTime)
        {
            double t = (currentNanoTime - startNanoTime) / 1000000000.0; 
            double x = 232 + 128 * Math.cos(t);
            double y = 232 + 128 * Math.sin(t);
            handValues.getChildren().clear();
            handValues.getChildren().add(new Label("Player hand value: " + getHandValue(player)));
            handValues.getChildren().add(new Label("Ai hand value: " + getHandValue(ai)));

            
        }
    }.start();
        
        
        
        
        stage.show();
       
    }
    
    
    public void createStatsFile(){
        try {
          stats = new File("stats.txt");
        if (stats.createNewFile()) {
          System.out.println("File created: " + stats.getName());
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
                System.out.println("Testi " + data);
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
    
    
    public void gameOptions() {
            createStatsFile();
            GameService newGame = new GameService(ai,player);
            player.setBalance(getStats());
            
           
            
            
           
   
            while (checkBalance == false) {
            System.out.println("Your balance is " + player.getAccountBalance());
            System.out.println("Set bet!");
                bet = scanner.nextInt();
            if (bet > player.getAccountBalance()) {
                System.out.println("Not enough money.Bet again");
                
            } else {
                checkBalance = true;                 
            }
            }

            newGame.startGame();
            System.out.println("Game is on!");
            System.out.println("Dealing cards:");
            newGame.dealAI();
            newGame.dealAI();
            System.out.println(newGame.ai.getHandValue());
            newGame.dealPlayer();
            newGame.dealPlayer();
            System.out.println(newGame.player.getHandValue());
            newGame.checkBlackJack(bet);
            while(!player.loser && !player.winner) {    
            System.out.println("[1] Hit");
            System.out.println("[2] Stay");
            System.out.println("[3] Double");
            input = scanner.nextInt();
            
            switch (input) {
                case 1:                   
                    newGame.dealPlayer();
                    System.out.println("You got now:");
                    newGame.player.showHand();
                    System.out.println(newGame.player.getHandValue());
                    if (this.player.getHandValue() > 21) {
                        System.out.println("OVER! You lose!");
                        this.player.setLoser();
                        player.setBalance(-bet);
                        
                    }
                    break;
                case 2:
                    
                    while (ai.getHandValue() < 17) {
                        System.out.println("AI takes more");
                        newGame.dealAI();
                        System.out.println("Ai got now:");
                        newGame.ai.showHand();
                    }
                    if (ai.getHandValue() >= 17) {
                        System.out.println("AI final hand:");
                        newGame.ai.showHand();
                        System.out.println("Ai hand value: " + newGame.ai.getHandValue());
                        System.out.println("Your final hand:");
                        newGame.player.showHand();
                        System.out.println("Player hand value: " + newGame.player.getHandValue());
                        newGame.checkWinner(bet);
                    }
                    break;
                case 3:
                    if (player.getAccountBalance() < (bet * 2)) {
                        System.out.println("Not enough funds to double!");
                    } else {
                        
                    System.out.println("Double! Dealing one card to the player");
                    newGame.dealPlayer();
                    System.out.println("Player got now:");
                    newGame.player.showHand();
                    System.out.println("Player hand value is " + player.getHandValue());
                    if (player.getHandValue() > 21) {
                        System.out.println("OVER! You lose!");
                        this.player.setLoser();
                        bet = bet * 2 ;
                        player.setBalance(-bet);
                        
                    } else {
                       while (ai.getHandValue() < 17) {
                        System.out.println("AI takes more");
                        newGame.dealAI();
                        System.out.println("Ai got now:");
                        newGame.ai.showHand();
                        System.out.println("Ai hand value "+ newGame.ai.getHandValue());
                    }
                       if (ai.getHandValue() >= 17) {
                        System.out.println("AI final hand:");
                        newGame.ai.showHand();
                        System.out.println("Ai hand value: " + newGame.ai.getHandValue());
                        System.out.println("Your final hand:");
                        newGame.player.showHand();
                        System.out.println("Player hand value: " + newGame.player.getHandValue());
                        bet = bet * 2;
                        newGame.checkWinner(bet);
                    }
                      
                    }
                        
                    }
                    break;
                        
                default:
                    System.out.println("Incorrect input");
                    break;
            }
            
            
            
            gameIsOn = false;
        }
    }
    public void afterGameOptions() {
         System.out.println("[3] Quit");
         System.out.println("[4] Play again");
         System.out.println("[5] Deposit money");
         input = scanner.nextInt();
            
            switch (input) {
                case 3:
                    System.out.println("Closing game");            
                    saveStats();
                    scanner.close();
                    break;
                case 4:
                    System.out.println("New game!");
                    player.clearStats();
                    ai.clearStats();
                    this.checkBalance = false;
                    this.startGame();
                    break;
                case 5:
                    System.out.println("How much you want to invest: (1-1000)");
                    input = scanner.nextInt();
                    if (input < 1 || input > 1000) {
                        System.out.println("incorrect amount");
                    } else {
                        System.out.println("Deposit " + input + " to your account");
                        player.setBalance(input);
                        System.out.println("Current balance is " + player.getAccountBalance());
                        afterGameOptions();
                        
                    }
                default:
                    afterGameOptions();
                    break;
            }
        
    }
    
    public void startGame() {

            startOptionsUI();
            //gameOptions();
            
    
            //afterGameOptions();
            
                    
    }
    
    
    
}