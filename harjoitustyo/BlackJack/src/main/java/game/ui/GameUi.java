
package game.ui;
import game.service.GameService;
import game.cards.Card;
import game.player.Player;
import java.io.File;  
import java.io.FileWriter;
import java.io.IOException;   

import java.util.Scanner;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Luokka hoitaa pelin graafisen ulkoasun
 */
public class GameUi {
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
    VBox playerHand;
    HBox handValues;
    VBox opponentHand;
    HBox showWinner;
    public int counter;
    boolean betIsSet;
    boolean firstRound;
    double currentBet;
    Label betAmount;
    Label checkWinner;
    Label playerAccountBalance;
    Button stayButton;
    Boolean dealIsOn;
    
    

    
    
    int input;
    public GameUi(Stage stage){
        this.firstRound = true;
        this.betIsSet = false;
        this.counter = 0;
        this.stage = stage;
        bet = 0;
        profit = 0;
        scanner = new Scanner(System.in);
        player = new Player("Boris");
        ai = new Player("AI");
        gameIsOn = true;
        checkBalance = false;
        oldStats = 0;
        playerHand = new VBox();
        opponentHand = new VBox();
        handValues = new HBox();
        showWinner = new HBox();
        this.currentBet = 0;
        betAmount = new Label("0");
        checkWinner = new Label("");
        playerAccountBalance = new Label("");
        stayButton  = new Button("Stay");
        dealIsOn = false;
        
    }
    public String getHandValue(Player player) {
        return String.valueOf(player.getHandValue());
    }
    public Button quitGameButton() {
        Button quitGame = new Button("Quit game");
        quitGame.setMinWidth(250);
        quitGame.setMinHeight(80);
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
        newGame.setMinWidth(250);
        newGame.setMinHeight(80);
        newGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               
               gameUI();
            }
        });
        Button loadGame = new Button("Load game");
        loadGame.setMinWidth(250);
        loadGame.setMinHeight(80);

        VBox optionsSetup = new VBox(8);
        optionsSetup.setMaxSize(250, 250);
        optionsSetup.setPrefSize(250, 250);
        optionsSetup.getChildren().add(newGame);
        optionsSetup.getChildren().add(loadGame);
        optionsSetup.getChildren().add(quitGameButton());
       
        Scene startScene = new Scene(optionsSetup);
        stage.setScene(startScene);
        stage.setResizable(false);
        stage.show();
       
        
    }
    
    public void gameUI() {
        
        GameService newGame = new GameService(ai, player);
        newGame.startGame();
        newGame.createStatsFile();
        playerAccountBalance.setText("Account balance: " + Double.toString(newGame.getStats()));
        
        System.out.println("settin balance  " + newGame.getStats());
        if (firstRound) {
            player.setBalance(newGame.getStats());
        }
        
        System.out.println("player balance " + player.getAccountBalance());
        
        Card aiFirstCard = newGame.dealAI();
        Card aiSecondCard = newGame.dealAI();
        Card playerFirstCard = newGame.dealPlayer();
        Card playerSecondCard = newGame.dealPlayer();
        
        
        TextField betInput = new TextField();
        HBox actionButtons = new HBox();
        Button playAgain = new Button("Deal");
        playAgain.setTranslateX(-250);
        playAgain.setTranslateY(100);
        playAgain.setScaleX(1.5);
        playAgain.setScaleY(1.5);
           playAgain.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
               if (betIsSet && !dealIsOn) {
               dealIsOn = true;
               newGame.setBet(betInput, true);
               opponentHand.getChildren().clear();
               playerHand.getChildren().clear();
               counter = 0;
               ai.clearStats();
               player.clearStats();
               playerAccountBalance.setText("Account balance: " +Double.toString(player.getAccountBalance()));
               checkWinner.setText("");
              
               gameUI();
                   
               }

              
               
               
            }
        });
        
        
        Button setBet = new Button("Set bet");
           setBet.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
               
               betIsSet = newGame.setBet(betInput,betIsSet);
               if (!betInput.getText().isEmpty()) {
                   currentBet = Double.valueOf(betInput.getText());
                   betAmount.setText("Your bet: " + Double.toString(currentBet));
                   
                   
                   
                   
               }
               
              
               
               
            }
        });
        Button hitButton = new Button("Hit");
        hitButton.setTranslateY(100);
        hitButton.setScaleX(1.5);
        hitButton.setScaleY(1.5);
        
        System.out.println("bet is set? " +betIsSet);
       
           
           hitButton.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
               if (betIsSet) {
               Card newCard = newGame.dealPlayer();
               playerHand.getChildren().add(new Label(newCard.getSuit() + " " + newCard.getValue()));
               if (player.getHandValue() > 21) {
                   newGame.checkWinner(currentBet,betAmount);
                   betIsSet = false;
                   dealIsOn = false;
               }
                   
               } else if (player.getHandValue() == 21){
                   System.out.println("Stay pitäs lähtee rullaa");
               }
          
            }
        });
            
        

        
        stayButton.setTranslateY(100);
        stayButton.setScaleX(1.5);
        stayButton.setScaleY(1.5);
           stayButton.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
               if (player.getHandValue() < 22 && ai.getHandValue() >= 17) {
                   newGame.checkWinner(currentBet,checkWinner);
                   betIsSet = false;
                   dealIsOn = false;
               } else {
                                
               while (ai.getHandValue() < 17) {
                Card newCard = newGame.dealAI();
                opponentHand.getChildren().add(new Label(newCard.getSuit() + " " + newCard.getValue()));
                if (ai.getHandValue() > 21) {
                    newGame.checkWinner(currentBet,checkWinner);
                    betIsSet = false;
                    dealIsOn = false;
                } else if (ai.getHandValue() < 22 && ai.getHandValue() > 16) {
                    newGame.checkWinner(currentBet,checkWinner); 
                    betIsSet = false;
                    dealIsOn = false;
                  
                } 
                   
               }        
               
            }
      }
        });
        Button doubleButton = new Button("Double");
        doubleButton.setTranslateY(100);
        doubleButton.setScaleX(1.5);
        doubleButton.setScaleY(1.5);
        
        actionButtons.getChildren().add(hitButton);
        actionButtons.getChildren().add(stayButton);
        actionButtons.getChildren().add(doubleButton);
        actionButtons.getChildren().add(setBet);
        actionButtons.getChildren().add(betInput);
        actionButtons.getChildren().add(playAgain);
        

 
        opponentHand.getChildren().add(new Label(aiFirstCard.getSuit() + " " + aiFirstCard.getValue()));
        opponentHand.getChildren().add(new Label(aiSecondCard.getSuit() + " " + aiSecondCard.getValue()));
        opponentHand.setAlignment(Pos.TOP_RIGHT);

        playerHand.getChildren().add(new Label(playerFirstCard.getSuit() + " " + playerFirstCard.getValue()));
        playerHand.getChildren().add(new Label(playerSecondCard.getSuit() + " " + playerSecondCard.getValue()));
        playerHand.setAlignment(Pos.TOP_LEFT);
        playerHand.setTranslateY(-60);
        

        handValues.getChildren().add(new Label("Player hand value: " + getHandValue(player)));
        handValues.getChildren().add(new Label("AI hand value: " + getHandValue(ai)));
        handValues.setAlignment(Pos.TOP_CENTER);
        handValues.setSpacing(100);
     
        TilePane board = new TilePane();
        board.getChildren().add(handValues);
        board.getChildren().add(opponentHand);
        board.getChildren().add(playerHand);
        board.getChildren().add(showWinner);
        board.setAlignment(Pos.CENTER);

        board.setMaxWidth(700);
        board.setHgap(20);
        board.setVgap(20);
        actionButtons.setTranslateX(120);
        actionButtons.setSpacing(35);
        HBox betInfo = new HBox();
        betInfo.getChildren().add(betAmount);
        HBox winnerInfo = new HBox();
        winnerInfo.getChildren().add(checkWinner);
        winnerInfo.getChildren().add(playerAccountBalance);
        winnerInfo.setTranslateY(50);
        
        StackPane fullGameBoard = new StackPane(betInfo,board,winnerInfo,actionButtons);
        fullGameBoard.setPadding(new Insets(50, 50, 50, 50));
        
        



        
        Scene gameView = new Scene(fullGameBoard,700,700);
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
            if (firstRound) {
               opponentHand.getChildren().clear();
               playerHand.getChildren().clear();
               counter = 0;
               betIsSet = false;
               ai.clearStats();
               player.clearStats();
               firstRound = false;
            }
            
                if (counter < 1) {
                    if(player.getHandValue() == 21 || ai.getHandValue() == 21) {
                        newGame.checkBlackJack(currentBet,checkWinner);
                        betIsSet = false;
                        dealIsOn = false;
                        return;            
                    }
                    counter++;

                }
            
            
            if(player.getLoser()) {
                
                
                
            }
            if (ai.getLoser()) {
                
                
            }

            
        }
    }.start();
        
        
        
        //stage.setResizable(false);
        stage.show();
       
    }
    


    
    public void startGame() {

            startOptionsUI();

            
                    
    }
    
    
    
}