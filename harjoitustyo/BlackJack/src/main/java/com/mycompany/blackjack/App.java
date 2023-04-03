package com.mycompany.blackjack;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        
        Game game = new Game();
        game.startGame();

       // stage.setScene(scene);
       // stage.show();

 


    }

    public static void main(String[] args) {
        launch();
    }

}