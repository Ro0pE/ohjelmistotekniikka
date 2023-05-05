package game.service;


import game.ui.GameUi;
import game.ui.GameUi;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {
    
    

    @Override
    public void start(Stage stage) {
          GameUi game = new GameUi(stage);
        //Game game = new Game(stage);
         game.startGame();
    }

    public static void main(String[] args) {
        launch();
    }

}