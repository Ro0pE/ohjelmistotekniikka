package game.ui;

import game.service.GameService;
import game.cards.Card;
import game.cards.CardGraphics;
import game.database.Database;
import game.player.Player;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Luokka hoitaa pelin graafisen ulkoasun
 */
public class GameUi {

    Player player;
    Player ai;
    Stage stage;
    Pane playerHand;
    Pane opponentHand;
    HBox handValues;
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
    HashMap<String, Image> cardImageMap;
    int playerUiCounter;
    int aiUiCounter;
    Node hideFirst;
    Scene gameView;
    StackPane fullGameBoard;
    Scene startScene;
    Button cashOut;
    GameService newGame;
    Label errorLabel;

    public GameUi(Stage stage) throws FileNotFoundException {
        this.errorLabel = new Label();
        this.cardImageMap = new HashMap<>();
        this.firstRound = true;
        this.betIsSet = false;
        this.counter = 0;
        this.stage = stage;
        this.player = new Player();
        this.ai = new Player();
        this.playerHand = new Pane();
        this.opponentHand = new Pane();
        this.handValues = new HBox();
        this.showWinner = new HBox();
        this.currentBet = 0;
        this.betAmount = new Label("Current bet: -");
        this.checkWinner = new Label("");
        this.playerAccountBalance = new Label("");
        this.stayButton = new Button("Stay");
        this.dealIsOn = false;
        this.playerUiCounter = 2;
        this.aiUiCounter = 2;
        this.firstRound = true;
        this.hideFirst = new ImageView();

    }

    public String getHandValue(Player player) {
        return String.valueOf(player.getHandValue());
    }

    public void setUpImages() throws FileNotFoundException {
        CardGraphics cardGraphics = new CardGraphics();
        cardImageMap = cardGraphics.setupCardGraphics();
    }

    public void startOptionsUI() {

        Label gameName = new Label("BLÄCK JÄCK!");
        Button newGame = new Button("Play");

        newGame.setMinWidth(250);
        newGame.setMinHeight(80);
        newGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    gameUI();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(GameUi.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        Button quitGame = new Button("Quit game");
        quitGame.setMinWidth(250);
        quitGame.setMinHeight(80);
        quitGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });
        Button highScores = new Button("High Scores");
        gameName.setMinWidth(350);
        gameName.setTranslateX(20);
        gameName.setTranslateY(10);
        gameName.setStyle("-fx-text-fill: white; -fx-font-size: 40px; -fx-font-family: Arial, Helvetica, sans-serif; -fx-font-weight: 700;");
        highScores.setMinWidth(250);
        highScores.setMinHeight(80);
        newGame.setTranslateX(30);
        newGame.setTranslateY(30);
        newGame.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 30px; -fx-border-color: #838383;");
        highScores.setTranslateX(30);
        highScores.setTranslateY(30);
        highScores.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 30px; -fx-border-color: #838383;");
        quitGame.setTranslateX(30);
        quitGame.setTranslateY(30);
        quitGame.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 30px; -fx-border-color: #838383;");

        highScores.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Database databaseHighscore = new Database();
                ArrayList<String> highScoresList = databaseHighscore.getHighScores();
                VBox high = new VBox();
                high.getChildren().add(new Label("HIGH SCORES:"));
                high.setScaleX(4);
                high.setScaleY(4);
                for (int i = 0; i < highScoresList.size(); i++) {
                    high.getChildren().add(new Label(highScoresList.get(i)));
                    high.setAlignment(Pos.CENTER);
                    high.setScaleX(3);
                    high.setScaleY(3);

                }
                Button goBack = new Button("Back");
                goBack.setOnAction((t) -> {
                    stage.setScene(startScene);
                });
                high.getChildren().add(goBack);

                Scene highSoreScene = new Scene(high, 900, 700);
                stage.setScene(highSoreScene);
                stage.centerOnScreen();

            }
        });

        VBox optionsSetup = new VBox(25);
        optionsSetup.setMinSize(320, 510);
        optionsSetup.setPrefSize(250, 250);
        optionsSetup.getChildren().add(gameName);
        optionsSetup.getChildren().add(newGame);
        optionsSetup.getChildren().add(highScores);
        optionsSetup.getChildren().add(quitGame);
        optionsSetup.setStyle("-fx-background-color: black;");

        startScene = new Scene(optionsSetup);
        stage.setScene(startScene);
        stage.setResizable(false);
        stage.show();

    }

    public void gameUI() throws FileNotFoundException {

        setUpImages();
        newGame = new GameService(ai, player);
        newGame.startGame();
        newGame.database();
        if (firstRound) {
            player.setBalance(newGame.database.getAccountBalanceFromFile());
        }
        playerAccountBalance.setText("Account balance: " + Double.toString(player.getAccountBalance() - currentBet));

        Card playerFirstCard = newGame.dealPlayer();
        Card playerSecondCard = newGame.dealPlayer();
        Card aiFirstCard = newGame.dealAI();
        Card aiSecondCard = newGame.dealAI();
        if (aiFirstCard.getValue() + aiSecondCard.getValue() == 21) {

        }

        ai.removeValueFromHand(aiFirstCard.getValue());

        cashOut = new Button();
        cashOut.setOnAction((event) -> {
            fullGameBoard.getChildren().clear();
            ai.clearStats();
            player.clearStats();
            handValues.getChildren().clear();
            this.firstRound = true;
            this.counter = 0;
            stage.setScene(startScene);
        });

        TextField betInput = new TextField();
        HBox actionButtons = new HBox();
        Button dealButton = new Button("Deal");
        dealButton.setTranslateX(-250);
        dealButton.setTranslateY(100);
        dealButton.setScaleX(1.5);
        dealButton.setScaleY(1.5);
        dealButton.setOnAction(new EventHandler<ActionEvent>() {
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
                    playerAccountBalance.setText("Account balance: " + Double.toString(player.getAccountBalance() - currentBet));
                    checkWinner.setText("");
                    errorLabel.setText("");

                    try {
                        gameUI();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(GameUi.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    errorLabel.setText("Set bet!");
                    errorLabel.setStyle("-fx-text-fill: red; -fx-font-size: 20px;");
                    errorLabel.setTranslateX(360);
                    errorLabel.setTranslateY(-34);
                    errorLabel.setScaleX(1.5);
                    errorLabel.setScaleY(1.5);

                }
            }
        });

        Button setBet = new Button("Set bet");
        setBet.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                betIsSet = newGame.setBet(betInput, betIsSet);
                if (betIsSet) {
                    currentBet = Double.valueOf(betInput.getText());
                    betAmount.setText("Current bet: " + Double.toString(currentBet));
                    errorLabel.setText("");
                } else {
                    errorLabel.setText("Invalid input");
                    errorLabel.setStyle("-fx-text-fill: red; -fx-font-size: 20px;");
                    errorLabel.setTranslateX(360);
                    errorLabel.setTranslateY(-34);
                    errorLabel.setScaleX(1.5);
                    errorLabel.setScaleY(1.5);
                }
            }
        });
        Button hitButton = new Button("Hit");
        hitButton.setTranslateY(100);
        hitButton.setScaleX(1.5);
        hitButton.setScaleY(1.5);

        hitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (betIsSet && dealIsOn) {
                    Card newCard = newGame.dealPlayer();
                    Node playerHit = new ImageView(cardImageMap.get(newCard.showCard()));
                    playerHit.setTranslateX(30 * playerUiCounter);
                    playerHand.getChildren().add(playerHit);
                    playerUiCounter++;
                    if (player.getHandValue() > 21) {
                        ai.addValueBackToHand(aiFirstCard.getValue());
                        hideFirst.setVisible(false);
                        newGame.checkWinner(currentBet, checkWinner, opponentHand, playerHand);
                        betIsSet = false;
                        dealIsOn = false;
                        playerUiCounter = 2;
                        aiUiCounter = 2;
                        playerAccountBalance.setText("Account balance: " + Double.toString(player.getAccountBalance()));

                    }
                }
            }
        });

        stayButton.setTranslateY(100);
        stayButton.setScaleX(1.5);
        stayButton.setScaleY(1.5);
        stayButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (betIsSet && dealIsOn) {
                    ai.addValueBackToHand(aiFirstCard.getValue());
                    if (player.getHandValue() < 22 && ai.getHandValue() >= 17) {
                        hideFirst.setVisible(false);
                        newGame.checkWinner(currentBet, checkWinner, opponentHand, playerHand);
                        betIsSet = false;
                        dealIsOn = false;
                        playerUiCounter = 2;
                        aiUiCounter = 2;
                        playerAccountBalance.setText("Account balance: " + Double.toString(player.getAccountBalance()));
                    } else {

                        while (ai.getHandValue() < 17) {
                            hideFirst.setVisible(false);
                            Card newCard = newGame.dealAI();
                            Node aiHit = new ImageView(cardImageMap.get(newCard.showCard()));
                            aiHit.setTranslateX(30 * aiUiCounter);
                            opponentHand.getChildren().add(aiHit);
                            aiUiCounter++;
                            if (ai.getHandValue() > 21) {
                                hideFirst.setVisible(false);
                                newGame.checkWinner(currentBet, checkWinner, opponentHand, playerHand);
                                betIsSet = false;
                                dealIsOn = false;
                                playerUiCounter = 2;
                                aiUiCounter = 2;
                                playerAccountBalance.setText("Account balance: " + Double.toString(player.getAccountBalance()));
                            } else if (ai.getHandValue() < 22 && ai.getHandValue() > 16) {
                                hideFirst.setVisible(false);
                                newGame.checkWinner(currentBet, checkWinner, opponentHand, playerHand);
                                betIsSet = false;
                                dealIsOn = false;
                                playerUiCounter = 2;
                                aiUiCounter = 2;
                                playerAccountBalance.setText("Account balance: " + Double.toString(player.getAccountBalance()));

                            }

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
        actionButtons.getChildren().add(dealButton);
        hideFirst = new ImageView(cardImageMap.get("card back"));
        Node aiFirst = new ImageView(cardImageMap.get(aiFirstCard.showCard()));
        Node aiSecond = new ImageView(cardImageMap.get(aiSecondCard.showCard()));
        aiSecond.setTranslateX(30);
        opponentHand.getChildren().add(aiFirst);
        opponentHand.getChildren().add(hideFirst);
        opponentHand.getChildren().add(aiSecond);
        opponentHand.setTranslateX(350);

        Node playerFirst = new ImageView(cardImageMap.get(playerFirstCard.showCard()));
        Node playerSecond = new ImageView(cardImageMap.get(playerSecondCard.showCard()));
        playerSecond.setTranslateX(30);
        playerHand.getChildren().add(playerFirst);
        playerHand.getChildren().add(playerSecond);

        playerHand.setTranslateX(-300);

        TilePane board = new TilePane();
        if (!firstRound) {
            handValues.getChildren().add(new Label("Player hand value: " + getHandValue(player)));
            handValues.getChildren().add(new Label("AI hand value: " + getHandValue(ai)));
            handValues.setAlignment(Pos.TOP_CENTER);
            handValues.setSpacing(100);
            handValues.setTranslateY(170);
            handValues.setTranslateX(40);
            handValues.setScaleX(1.5);
            handValues.setScaleY(1.5);
            board.getChildren().add(handValues);

        }
        showWinner.setScaleX(1.5);
        showWinner.setScaleY(1.5);

        board.getChildren().add(showWinner);
        board.getChildren().add(opponentHand);
        board.getChildren().add(playerHand);

        board.setAlignment(Pos.CENTER);

        board.setMaxWidth(700);
        board.setHgap(20);
        board.setVgap(20);
        actionButtons.setTranslateX(120);
        actionButtons.setSpacing(35);
        Pane info = new Pane();
        info.getChildren().add(errorLabel);
        info.getChildren().add(betAmount);
        info.getChildren().add(playerAccountBalance);
        info.getChildren().add(cashOut);
        cashOut.setTranslateX(670);
        cashOut.setTranslateY(-50);
        cashOut.setText("Cash out");
        betAmount.setScaleY(2);
        betAmount.setScaleX(2);

        playerAccountBalance.setTranslateY(30);
        playerAccountBalance.setTranslateX(10);
        playerAccountBalance.setScaleY(2);
        playerAccountBalance.setScaleX(2);

        checkWinner.setScaleX(2);
        checkWinner.setScaleY(2);
        checkWinner.setTranslateY(300);

        fullGameBoard = new StackPane(info, board, actionButtons, checkWinner);
        fullGameBoard.setStyle("-fx-background-color: #339900; -fx-text-fill: black;");
        fullGameBoard.setPadding(new Insets(50, 50, 50, 50));

        gameView = new Scene(fullGameBoard, 900, 700);
        stage.setScene(gameView);
        stage.centerOnScreen();

        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {

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

                if (counter < 1 && dealIsOn) {
                    if (player.getHandValue() == 21 || ai.getHandValue() == 21) {
                        newGame.checkBlackJack(currentBet, checkWinner, opponentHand, playerHand);
                        betIsSet = false;
                        dealIsOn = false;
                        playerAccountBalance.setText("Account balance: " + Double.toString(player.getAccountBalance()));

                        return;
                    }

                    counter++;
                }
            }
        }.start();
        stage.setResizable(false);
        stage.show();
    }

    public void startGame() {

        startOptionsUI();
    }

}
